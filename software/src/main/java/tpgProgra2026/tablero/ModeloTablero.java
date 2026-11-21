package tpgProgra2026.tablero;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import misionesPackage.Mision;
import misionesPackage.Mision01;
import misionesPackage.Mision02;
import misionesPackage.Mision03;
import navesPackage.Fabricacion_Nave;
import navesPackage.Nave;
import navesPackage.TipoNaveInvalidoException;
import tpgProgra2026.Asistente;
import tpgProgra2026.Evento;
import tpgProgra2026.TableroDestino.CantidadDeElementosSuperaCasillaException;
import tpgProgra2026.TableroDestino.DimensionesDeTableroInconsistentesException;
import tpgProgra2026.TableroDestino.ElementoDelUniverso;
import tpgProgra2026.TableroDestino.Observable;
import tpgProgra2026.TableroDestino.Observador;
import tpgProgra2026.TableroDestino.Posicion;
import tpgProgra2026.TableroDestino.Tablero;
import tripulacionPackage.Alferez;
import tripulacionPackage.Capitan;
import tripulacionPackage.Consejero;
import tripulacionPackage.DecoratorMarciano;
import tripulacionPackage.DecoratorTerricola;
import tripulacionPackage.DecoratorVulcano;
import tripulacionPackage.Teniente;
import tripulacionPackage.Tripulante;

/**
 * Modelo de la simulación: la nave, su asistente, la tripulación y las misiones.
 *
 * Observer en dos niveles:
 *   - Es Observador de todo lo que cambia en el dominio: Tablero, Nave, MotorWarp, Asistente (bitácora) y Mision.
 *   - Es Observable para la vista: ante cualquier cambio saca una foto inmutable (EstadoSimulacion) y avisa.
 *
 * La misión corre en un hilo propio (tiene pausas de Thread.sleep): el hilo de la interfaz nunca se bloquea.
 *
 * @inv misionEnCurso es true solo mientras el hilo de la misión está corriendo.
 * @inv mientras hay una misión en curso no se crea otra nave ni se asignan tripulantes.
 */
public class ModeloTablero extends Observable implements Observador {

    public static final String[] TIPOS_NAVE = {"Exploradora", "Carguero", "Combate"};
    public static final String[] CARGOS = {"Capitán", "Teniente", "Alférez", "Consejero"};
    public static final String[] ORIGENES = {"Ninguno", "Terrícola", "Vulcano", "Marciano"};
    public static final String[] MISIONES = {"01", "02", "03"};

    public static final int FILAS = 20, COLUMNAS = 20;   // tamaño por defecto del tablero
    private static final DateTimeFormatter HORA = DateTimeFormatter.ofPattern("HH:mm:ss");

    private int contadorNaves = 0;
    private Nave nave = null;
    private Asistente asistente = null;
    private String tipoNave = "";
    private Mision misionActual = null;
    private volatile boolean misionEnCurso = false;
    private final List<String> informes = Collections.synchronizedList(new ArrayList<String>());
    private volatile EstadoSimulacion estado = EstadoSimulacion.vacio(new ArrayList<String>());

    // ---------- operaciones que pide el controlador ----------

    /**
     * @pre !hayMisionEnCurso()
     * @post hay una nave nueva con su asistente y un tablero donde la nave parte de la base; reemplaza a la anterior.
     * @post la tripulación y el historial de informes quedan vacíos.
     * @throws MisionEnCursoException     si todavía hay una misión corriendo
     * @throws DatosInvalidosException    si el nombre está vacío
     * @throws TipoNaveInvalidoException  si el tipo no es uno de TIPOS_NAVE
     * @throws SimulacionException        si no se pudo armar el tablero
     */
    public synchronized void crearNave(String nombre, String tipo)
            throws SimulacionException, TipoNaveInvalidoException {
        crearNave(nombre, tipo, FILAS, COLUMNAS);
    }

    /**
     * Igual que {@link #crearNave(String, String)} pero con el tamaño del tablero elegido.
     * @throws SimulacionException si el tamaño está fuera de 5x5..100x100 o si los elementos del universo
     *         (asteroides y estaciones) no entran en el tablero (CantidadDeElementosSuperaCasillaException)
     */
    public synchronized void crearNave(String nombre, String tipo, int filas, int columnas)
            throws SimulacionException, TipoNaveInvalidoException {
        verificarSinMision();
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new DatosInvalidosException("nombre", "Ingresá un nombre para la nave.");
        }
        Nave nueva = Fabricacion_Nave.construirNave("N-" + (++contadorNaves), nombre.trim(), tipo);
        Asistente nuevo = new Asistente(nueva);
        try {
            nuevo.setTableroyDestino(filas, columnas);
        } catch (CantidadDeElementosSuperaCasillaException e) {
            // salta cuando los elementos (asteroides + estaciones) superan las 3/4 partes de las celdas
            throw new SimulacionException("Tablero demasiado chico. " + e.getMessage());
        } catch (DimensionesDeTableroInconsistentesException e) {
            throw new SimulacionException("Tamaño de tablero inválido: " + e.getMessage());
        }
        nuevo.setBase(); // la base es el punto de partida de la nave: a ella vuelve la misión 03

        // Observer: el modelo se entera de todo lo que cambia en el dominio
        nueva.agregarObservador(this);
        nueva.getMotorWarp().agregarObservador(this);
        nuevo.agregarObservador(this);
        nuevo.getTablero().agregarObservador(this);

        this.nave = nueva;
        this.asistente = nuevo;
        this.tipoNave = tipo;
        this.misionActual = null;
        this.informes.clear();
        refrescar();
    }

    /**
     * @pre hay nave y !hayMisionEnCurso()
     * @post el tripulante queda asignado a la nave.
     * @throws NaveNoCreadaException    si todavía no hay nave
     * @throws MisionEnCursoException   si hay una misión corriendo
     * @throws CapitanDuplicadoException si se agrega un Capitán y la nave ya tiene uno
     * @throws DatosInvalidosException  si la antigüedad es negativa o el cargo/origen no existe
     */
    public synchronized void agregarTripulante(String cargo, int antiguedad, String origen) throws SimulacionException {
        verificarSinMision();
        if (nave == null) {
            throw new NaveNoCreadaException();
        }
        if (antiguedad < 0) {
            throw new DatosInvalidosException("antigüedad", "La antigüedad no puede ser negativa.");
        }
        if ("Capitán".equals(cargo) && nave.tieneCapitan()) {
            throw new CapitanDuplicadoException();   // máximo un capitán por nave
        }
        Tripulante t;
        switch (cargo) {
            case "Capitán":   t = new Capitan(antiguedad);   break;
            case "Teniente":  t = new Teniente(antiguedad);  break;
            case "Alférez":   t = new Alferez(antiguedad);   break;
            case "Consejero": t = new Consejero(antiguedad); break;
            default: throw new DatosInvalidosException("cargo", "Cargo desconocido: " + cargo);
        }
        switch (origen) {
            case "Terrícola": t = new DecoratorTerricola(t); break;
            case "Vulcano":   t = new DecoratorVulcano(t);   break;
            case "Marciano":  t = new DecoratorMarciano(t);  break;
            case "Ninguno":   break;
            default: throw new DatosInvalidosException("origen", "Origen desconocido: " + origen);
        }
        nave.asignarTripulante(t); // la nave avisa (Observable) y la vista se actualiza sola
    }

    /**
     * @pre hay una nave viva con al menos un tripulante y no hay otra misión en curso
     * @post la misión empieza a correr en un hilo propio; al terminar se agrega su informe al historial.
     * @throws NaveNoCreadaException              si todavía no hay nave
     * @throws MisionEnCursoException             si ya hay una misión corriendo
     * @throws NaveInoperanteException            si la nave fue destruida
     * @throws TripulacionInsuficienteException   si no hay tripulantes
     * @throws CapitanRequeridoException          si hay tripulantes pero ninguno es Capitán
     * @throws DatosInvalidosException            si el código de misión no existe
     */
    public synchronized void iniciarMision(String tipo) throws SimulacionException {
        if (nave == null) {
            throw new NaveNoCreadaException();
        }
        verificarSinMision();
        if (!asistente.estaVivaNave()) {
            throw new NaveInoperanteException();
        }
        if (nave.getTripulacion().isEmpty()) {
            throw new TripulacionInsuficienteException();
        }
        if (!nave.tieneCapitan()) {
            throw new CapitanRequeridoException();   // sin capitán no se despega
        }
        final Mision mision;
        switch (tipo) {
            case "01": mision = new Mision01(asistente); break;
            case "02": mision = new Mision02(asistente); break;
            case "03": mision = new Mision03(asistente); break;
            default: throw new DatosInvalidosException("misión", "Misión desconocida: " + tipo);
        }
        mision.agregarObservador(this); // la misión avisa al terminar cada fase
        misionActual = mision;
        misionEnCurso = true;
        refrescar();

        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                correr(mision);
            }
        }, "hilo-mision-" + tipo);
        hilo.setDaemon(true);
        hilo.start();
    }

    // ---------- consultas ----------

    public boolean hayMisionEnCurso() {
        return misionEnCurso;
    }

    /** @post la última foto del estado (nunca null). Segura para leer desde cualquier hilo. */
    public EstadoSimulacion getEstado() {
        return estado;
    }

    // ---------- Observador del dominio ----------

    /** Lo llaman el Tablero, la Nave, el MotorWarp, el Asistente y la Mision cuando cambian. */
    @Override
    public void actualizar() {
        refrescar();
    }

    // ---------- internos ----------

    private void verificarSinMision() throws MisionEnCursoException {
        if (misionEnCurso) {
            throw new MisionEnCursoException();
        }
    }

    /** Se ejecuta en el hilo de la misión. */
    private void correr(Mision mision) {
        int c0 = nave.getCombustible(), e0 = nave.getEnergia(), d0 = nave.getDesgaste(), g0 = nave.getGemas();
        try {
            mision.Ejecuto_Mision();
        } catch (RuntimeException ex) {
            asistente.registrarEvento("Error inesperado en la misión: " + ex, "Error");
        } finally {
            informes.add(armarInforme(mision, c0, e0, d0, g0));
            misionEnCurso = false;
            refrescar();
        }
    }

    private String armarInforme(Mision m, int c0, int e0, int d0, int g0) {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(LocalTime.now().format(HORA)).append("] Misión M-").append(m.getTipomision()).append(": ");
        if (m.fueExitosa()) {
            sb.append("EXITOSA");
        } else if (!nave.estaViva()) {
            sb.append("FALLIDA (la nave fue destruida)");
        } else if (c0 == nave.getCombustible() && e0 == nave.getEnergia() && d0 == nave.getDesgaste()) {
            sb.append("FALLIDA (no pudo despegar: recursos insuficientes)");
        } else {
            sb.append("FALLIDA (no cumplió el objetivo)");
        }
        sb.append("\n    Combustible: ").append(c0).append(" → ").append(nave.getCombustible()).append(variacion(nave.getCombustible() - c0));
        sb.append("\n    Energía:     ").append(e0).append(" → ").append(nave.getEnergia()).append(variacion(nave.getEnergia() - e0));
        sb.append("\n    Desgaste:    ").append(d0).append(" → ").append(nave.getDesgaste()).append(variacion(nave.getDesgaste() - d0));
        sb.append("\n    Gemas:       ").append(g0).append(" → ").append(nave.getGemas()).append(variacion(nave.getGemas() - g0));
        return sb.toString();
    }

    private static String variacion(int delta) {
        return "  (" + (delta > 0 ? "+" : "") + delta + ")";
    }

    private List<String> copiaInformes() {
        synchronized (informes) {
            return new ArrayList<String>(informes);
        }
    }

    /** Saca una foto del estado y avisa a la vista. */
    private synchronized void refrescar() {
        estado = construirEstado();
        notificarObservadores();
    }

    private EstadoSimulacion construirEstado() {
        if (asistente == null) {
            return EstadoSimulacion.vacio(copiaInformes());
        }
        Tablero tablero = asistente.getTablero();
        ElementoDelUniverso[][] celdas = tablero.verTablero();
        String[][] tipos = new String[celdas.length][celdas[0].length];
        for (int i = 0; i < celdas.length; i++) {
            for (int j = 0; j < celdas[i].length; j++) {
                if (celdas[i][j] != null) {
                    tipos[i][j] = celdas[i][j].getTipoElemento();
                }
            }
        }
        Posicion objetivo = (misionEnCurso && misionActual != null) ? misionActual.getDestino() : null;

        List<String> lineas = new ArrayList<String>();
        for (Evento ev : new ArrayList<Evento>(asistente.getBitacora())) {
            lineas.add("[" + ev.getFecha().toLocalTime().format(HORA) + "] " + ev.getMensaje().trim());
        }
        List<EstadoSimulacion.FilaTripulante> filas = new ArrayList<EstadoSimulacion.FilaTripulante>();
        for (Tripulante t : new ArrayList<Tripulante>(nave.getTripulacion())) {
            filas.add(new EstadoSimulacion.FilaTripulante(t.getId(), t.getCargo(), t.getOrigen(), t.getAntiguedad(), t.getSueldo()));
        }
        return new EstadoSimulacion(true, nave.getNombre(), tipoNave, nave.getCombustible(), nave.getEnergia(),
                nave.getDesgaste(), nave.getGemas(), asistente.estaVivaNave(), misionEnCurso,
                nave.getMotorWarp().getNombreEstado(), tablero.getX(), tablero.getY(), tipos,
                tablero.getPosicionNave(), objetivo, asistente.getBase(), lineas, copiaInformes(), filas);
    }
}
