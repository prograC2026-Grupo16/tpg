package tpgProgra2026;
import java.util.ArrayList;

/**
 * @invariante id != null && nombre != null && tipo != null
 * @invariante recursos != null && motorwarp != null && Tripulante != null
 */
public abstract class Nave {
    protected String id;
    protected String nombre;
    protected String tipo;
    protected Recursos recursos;
    protected MotorWarp motorwarp;
    protected ArrayList<Tripulante> Tripulante = new ArrayList<>();

    /**
     * @pre  id != null && nombre != null && tipo != null && motorwarp != null
     * @pre  combustible >= 0 && energia >= 0
     * @post getRecursos().getCombustible() == combustible
     * @post getRecursos().getEnergia() == energia
     * @post getTripulante().isEmpty()
     */
    public Nave(String id, String nombre, String tipo, MotorWarp motorwarp, int combustible, int energia) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.motorwarp = motorwarp;
        this.recursos = new Recursos();
        this.recursos.setCombustible(combustible);
        this.recursos.setEnergia(energia);
    }

    /**
     * @pre  t != null && !getTripulante().contains(t)
     * @post getTripulante().contains(t)
     * @post getTripulante().size() == tamanio anterior + 1
     */
    public void asignarTripulante(Tripulante t) {
        Tripulante.add(t);
    }

    public boolean estaMotorDisponible() {
        return motorwarp.estaMotorDisponible();
    }

    /**
     * @pre  cantidad >= 0
     * @post getRecursos().getCombustible() == combustible anterior + cantidad
     */
    public void cargarCombustible(int cantidad) {
        recursos.setCombustible(cantidad);
    }

    /**
     * @pre  cantidad >= 0
     * @post getRecursos().getEnergia() == energia anterior + cantidad
     */
    public void cargarEnergia(int cantidad) {
        recursos.setEnergia(cantidad);
    }

    /**
     * @post getRecursos().getDesgaste() == 0
     */
    public void realizarMantenimiento() {
        recursos.setDesgaste();
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public Recursos getRecursos() {
        return recursos;
    }

    public MotorWarp getMotorWarp() {
        return motorwarp;
    }

    public ArrayList<Tripulante> getTripulante() {
        return Tripulante;
    }
}