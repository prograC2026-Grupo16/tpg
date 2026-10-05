package tpgProgra2026;

/**
 * Clase abstracta que define una misión de la nave con el patrón Template Method:
 * Ejecuto_Mision() fija el orden de los pasos (preparar, ejecutar, EnergiaGanada,
 * evaluar y cerrar) y las subclases aportan la energía ganada y el identificador de la misión.
 *
 * Precondiciones:
 *   Constructor: el Asistente ac recibido no es  null y está inicializado.
 *   Como consecuencia, todos los métodos que usan  ac (Ejecuto_Mision, preparar,
 *   ejecutar, cerrar y EnergiaGanada) lanzarían  NullPointerException si fuera nulo.
 *
 * Postcondiciones:
 *
 *   Ejecuto_Mision(): ejecuta en orden preparar, ejecutar, EnergiaGanad,
 *   evaluar y cerrar; deja registrado en la bitácora el cierre de la misión.
 *   Si no pudo prepararse, no se consumen recursos ni se gana energía.
 *
 *    preparar(): preparada es true si y solo si hay recursos
 *   suficientes y el motor está disponible (incluso si una ejecución anterior la había dejado
 *   en true); registra exactamente un evento y no consume recursos.
 *
 *   ejecutar(): si preparada, consume exactamente COSTO_COMBUSTIBLE
 *   de combustible y COSTO_DESGASTE de desgaste e informa por consola que la misión fue
 *   completada; si no, no consume nada e informa que fue fallida.
 *
 *    cerrar(): registra exactamente un evento, de éxito si preparada o de
 *   fallo (sin recursos disponibles) en caso contrario.
 *
 *   evaluar(): por ahora es un metodo vacio, no se sabe que implementar.
 *
 *   EnergiaGanada(): la energía de la nave nunca disminuye; aumenta en la cantidad
 *   propia del tipo de mision, o queda igual si el tipo de mision no otorga energía.(Hook)
 *
 *   getTipomision(): devuelve un  String no nulo y no vacío, siempre el mismo
 *   para una misma variante, que se concatena en los mensajes de registro y de consola.
 *
 *
 * Invariantes:
 *    COSTO_COMBUSTIBLE y COSTO_DESGASTE son constantes (final) y
 *   estrictamente positivas.
 *    ac no cambia después de construida la misión.
 *   preparada es true solo si el último preparar() encontró
 *   recursos suficientes y el motor disponible.
 */
public abstract class Mision {

    protected final int COSTO_COMBUSTIBLE = 4; // Por si luego cambia el valor
    protected final int COSTO_DESGASTE = 4;
    private boolean preparada = false;
    protected Asistente ac;

    public Mision(Asistente ac) {
        this.ac = ac;
    }

    public void Ejecuto_Mision() {
        preparar();
        ejecutar();
        if (preparada) {
            EnergiaGanada();
        }
        evaluar();// ???
        cerrar();
    }

    public void preparar() {
        preparada = ac.alcanzaPara(COSTO_COMBUSTIBLE, 0, COSTO_DESGASTE) && ac.estaMotorDisponible();
        if (preparada) {
            ac.registrarEvento("Nave preparada para mision", "Tipo");
        } else {
            ac.registrarEvento("Preparacion fallida, no hay recursos suficientes", "Tipo");
        }
    }

    public void ejecutar() {
        if (preparada) {
            ac.consumirRecursos(COSTO_COMBUSTIBLE, 0, COSTO_DESGASTE);
            System.out.println("Mision " + getTipomision() + "  Completada");
        } else {
            System.out.println("Mision " + getTipomision() + "  Fallida");
        }
    }

    public void cerrar() {
        if (preparada) {
            ac.registrarEvento("Mision " + getTipomision() + " Completada con exito", "Tipo");
        } else {
            ac.registrarEvento("Mision " + getTipomision() + " fallida (No hay recursos disponibles)", "Tipo");
        }
    }

    public void evaluar() {
        //?????
    }

    public abstract void EnergiaGanada();
    public abstract String getTipomision();

}
