package tpgProgra2026;
import java.util.ArrayList;

/**
 * @invariante nave != null && misiones != null && bitacora != null
 */
public class Asistente {
    private static final int LIMITE_DESGASTE = 80;

    protected Nave nave;
    protected ArrayList<Evento> bitacora = new ArrayList<>();
    protected Mision misiones;

    /**
     * @pre  nave != null && misiones != null
     * @post la bitacora esta vacia
     */
    public Asistente(Nave nave, Mision misiones) {
        this.nave = nave;
        this.misiones = misiones;
    }

    /**
     * @pre  mensaje != null && tipo != null
     * @post bitacora.size() == tamanio anterior + 1
     */
    public void registrarEvento(String mensaje, String tipo) {
        bitacora.add(new Evento(mensaje, tipo));
    }

    public boolean estaMotorDisponible() {
        return getMotorWarp().estaMotorDisponible();
    }

    /**
     * @pre  cantidad >= 0
     * @post getRecursos().getCombustible() == combustible anterior + cantidad
     */
    public void cargarCombustible(int cantidad) {
        getRecursos().setCombustible(cantidad);
    }

    /**
     * @pre  cantidad >= 0
     * @post getRecursos().getEnergia() == energia anterior + cantidad
     */
    public void cargarEnergia(int cantidad) {
        getRecursos().setEnergia(cantidad);
    }

    /**
     * @pre  combustible >= 0 && cantidad >= 0 && desgaste >= 0
     * @pre  alcanzaPara(combustible, cantidad, desgaste)
     * @post se descontaron combustible y energia, y se sumo el desgaste
     */
    public void consumirRecursos(int combustible, int cantidad, int desgaste) {
        getRecursos().gastarRecursos(combustible, cantidad, desgaste);
    }

    /**
     * @pre  combustible >= 0 && energia >= 0 && desgaste >= 0
     * @post no modifica el estado de la nave
     */
    public boolean alcanzaPara(int combustible, int energia, int desgaste) {
        return getRecursos().getCombustible() > combustible
            && getRecursos().getEnergia() > energia
            && (getRecursos().getDesgaste() + desgaste) < LIMITE_DESGASTE;
    }

    public boolean requiereMantenimiento() {
        return getRecursos().getDesgaste() >= LIMITE_DESGASTE;
    }

    /**
     * @post !requiereMantenimiento() && getRecursos().getDesgaste() == 0
     */
    public void realizarMantenimiento() {
        getRecursos().setDesgaste();
    }

    public Recursos getRecursos() {
        return nave.getRecursos();
    }

    public MotorWarp getMotorWarp() {
        return nave.getMotorWarp();
    }
}