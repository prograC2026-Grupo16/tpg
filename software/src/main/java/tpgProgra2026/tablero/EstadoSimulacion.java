package tpgProgra2026.tablero;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tpgProgra2026.TableroDestino.Posicion;

/**
 * Foto inmutable del estado de la simulación, tomada por el modelo en el hilo que cambió algo.
 * La vista dibuja siempre desde una foto, así nunca lee datos que otro hilo está modificando.
 */
public final class EstadoSimulacion {

    /** Una fila de la tabla de tripulantes. */
    public static final class FilaTripulante {
        public final int id;
        public final String cargo;
        public final String origen;
        public final int antiguedad;
        public final double sueldo;

        FilaTripulante(int id, String cargo, String origen, int antiguedad, double sueldo) {
            this.id = id;
            this.cargo = cargo;
            this.origen = origen;
            this.antiguedad = antiguedad;
            this.sueldo = sueldo;
        }
    }

    public final boolean hayNave;
    public final String nombreNave;
    public final String tipoNave;
    public final int combustible, energia, desgaste, gemas;
    public final boolean viva;
    public final boolean misionEnCurso;
    public final String estadoMotor;
    public final int filas, columnas;
    public final String[][] elementos;   // tipo de elemento de cada casilla, o null si está vacía
    public final Posicion nave, objetivo, base;
    public final List<String> bitacora;
    public final List<String> informes;
    public final List<FilaTripulante> tripulantes;

    EstadoSimulacion(boolean hayNave, String nombreNave, String tipoNave, int combustible, int energia, int desgaste,
                     int gemas, boolean viva, boolean misionEnCurso, String estadoMotor, int filas, int columnas,
                     String[][] elementos, Posicion nave, Posicion objetivo, Posicion base, List<String> bitacora,
                     List<String> informes, List<FilaTripulante> tripulantes) {
        this.hayNave = hayNave;
        this.nombreNave = nombreNave;
        this.tipoNave = tipoNave;
        this.combustible = combustible;
        this.energia = energia;
        this.desgaste = desgaste;
        this.gemas = gemas;
        this.viva = viva;
        this.misionEnCurso = misionEnCurso;
        this.estadoMotor = estadoMotor;
        this.filas = filas;
        this.columnas = columnas;
        this.elementos = elementos;
        this.nave = nave;
        this.objetivo = objetivo;
        this.base = base;
        this.bitacora = Collections.unmodifiableList(new ArrayList<String>(bitacora));
        this.informes = Collections.unmodifiableList(new ArrayList<String>(informes));
        this.tripulantes = Collections.unmodifiableList(new ArrayList<FilaTripulante>(tripulantes));
    }

    static EstadoSimulacion vacio(List<String> informes) {
        return new EstadoSimulacion(false, "", "", 0, 0, 0, 0, false, false, "", 0, 0, new String[0][0],
                null, null, null, new ArrayList<String>(), informes, new ArrayList<FilaTripulante>());
    }

    /** @post true si hay una nave viva y no hay una misión en curso: se puede lanzar una misión. */
    public boolean puedeIniciarMision() {
        return hayNave && viva && !misionEnCurso;
    }
}
