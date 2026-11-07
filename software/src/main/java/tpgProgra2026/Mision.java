package tpgProgra2026;

import tpgProgra2026.TableroDestino.ElementoDelUniverso;
import tpgProgra2026.TableroDestino.Posicion;

import java.util.ArrayList;

/**
 * Clase abstracta que define una misión de la nave con el patrón Template Method: Ejecuto_Mision() fija el orden de los pasos (preparar, ejecutar, EnergiaGanada, evaluar y cerrar) y las subclases aportan la energía ganada y el identificador de la misión.
 *
 * @inv COSTO_COMBUSTIBLE y COSTO_DESGASTE son constantes (final) y estrictamente positivas.
 * @inv ac no cambia después de construida la misión.
 * @inv preparada es true solo si el último preparar() encontró recursos suficientes y el motor disponible.
 */
public abstract class Mision {

    protected boolean preparada = false;
    protected boolean exito = false;
    protected Asistente ac;
    protected Posicion destino = null;
    protected ArrayList<Posicion> ruta = null;
    protected int gastadoCombustible = 0;
    protected int gastadoEnergia = 0;
    protected int gastadoDesgaste = 0;

    /**
     * @pre el Asistente ac recibido no es null y está inicializado.
     */
    public Mision(Asistente ac) {
        this.ac = ac;
    }

    protected Posicion calcularDestino() {
        return ac.tablero.obtenerObjetivo();
    }

    /**
     * @pre Todos los métodos que usan ac (Ejecuto_Mision, preparar, ejecutar, cerrar y EnergiaGanada) lanzarían NullPointerException si fuera nulo.
     * @post ejecuta en orden preparar, ejecutar, EnergiaGanad, evaluar y cerrar; deja registrado en la bitácora el cierre de la misión.
     * @post Si no pudo prepararse, no se consumen recursos ni se gana energía.
     */
    public final void Ejecuto_Mision() {
        preparar();
        ejecutar();
        evaluar();
        cerrar();
    }

    /**
     * @post preparada es true si y solo si hay recursos suficientes y el motor está disponible (incluso si una ejecución anterior la había dejado en true); registra exactamente un evento y no consume recursos.
     */
    public void preparar() {
        if (ac.alcanzaPara(getCostoCombustible(), getCostoEnergia(), getCostoDesgaste())) {

            destino = calcularDestino(); // Todavía sin implementar
            ruta = ac.calcularRuta(destino); // Todavía sin implementar
            preparada = true;
            ac.registrarEvento("Mision " + getTipomision() + " preparada: ruta de " + ruta.size() + " pasos hacia (" + destino.getX() + "," + destino.getY() + ")", "Mision");

        }
    }

    /**
     * @post si preparada, consume exactamente COSTO_COMBUSTIBLE de combustible y COSTO_DESGASTE de desgaste e informa por consola que la misión fue completada; si no, no consume nada e informa que fue fallida.
     */
    public void ejecutar() {
        if (preparada) {
            ac.consumirRecursos(getCostoCombustible(), getCostoEnergia(), getCostoDesgaste());
            gastadoCombustible += getCostoCombustible();
            gastadoEnergia += getCostoEnergia();
            gastadoDesgaste += getCostoDesgaste();
            // Mientras la nave siga con vida y no haya alcanzado el destino
            while (ac.estaVivaNave() && !llegoAlDestino()) {

                // Turno de la nave
                ElementoDelUniverso elemento = ac.tablero.inspeccionarPosicion(); // esto lo implementa tablero
                if (elemento != null) {
                    Posicion act = ac.tablero.getPosicionNave();// esto lo implementa tablero
                    ac.registrarEvento("La nave interactúa con " + elemento.getTipoElemento() + " en la posicion actual", "Encuentro");
                    elemento.interactuar(ac);
                }

                // si la nave es destruida
                if(!ac.estaVivaNave()) {
                    ac.registrarEvento("La nave ha sido destruida. ", "Destruccion");
                    exito = false;
                    break;
                }

                // Escaneo (radar de corto alcance) y planificación
                ElementoDelUniverso[][] alrededores = ac.tablero.observarAlrededores();
                ac.planificarMovimiento(alrededores);

                // Consumo de combustible por turno
                ac.consumirRecursos(0, 0, 0); // ¿Se gastaba combustible por cada salto?

                // Turno del destino
                ac.destino.turnoDestino();

                // Pausa con Thread después la implementamos
            }

            if (ac.estaVivaNave() && llegoAlDestino()) {
                exito = true;
            }
        }
    }

    /**
     * @post
     */
    public void cerrar() {}

    /**
     * @post
     */
    public void evaluar() {
        if (preparada) {
            if (exito)
                ac.registrarEvento("Misión " + getTipomision() + " Cumplida. ", "Mision");
            else
                ac.registrarEvento("Misión " + getTipomision() + " Fallida. No se otorgan recompensas.", "Mision");

            ac.registrarEvento("Cantidad de recursos gastados: " + gastadoCombustible + " combustible, +" + gastadoEnergia + " energia, +" + gastadoDesgaste + " Desgaste.", "Mision");
        }else
             ac.registrarEvento("Mision " + getTipomision() + " Fallida (No hay recursos disponibles)", "Tipo");

    }

    /**
     * @post devuelve un String no nulo y no vacío, siempre el mismo para una misma variante, que se concatena en los mensajes de registro y de consola.
     */
    public abstract String getTipomision();

    public abstract int getCostoCombustible();
    public abstract int getCostoEnergia();
    public abstract int getCostoDesgaste();
}