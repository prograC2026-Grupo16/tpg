package tpgProgra2026;
import java.util.ArrayList;
import motorPackage.MotorWarp;
import tpgProgra2026.Recursos;
import tripulacionPackage.Tripulante;

/**
 * @invariante id != null && nombre != null && tipo != null
 * @invariante recursos != null && motorwarp != null && Tripulante != null
 */
public abstract class Nave {
    protected String id;
    protected String nombre;
    protected Recursos recursos;
    protected MotorWarp motorWarp;
    protected ArrayList<Tripulante> Tripulante;
    protected MotorWarp motorwarp;
    protected ArrayList<Tripulante> Tripulante = new ArrayList<>();
    protected boolean destruida = false;

    /**
     * @pre  id != null && nombre != null && tipo != null && motorwarp != null
     * @pre  combustible >= 0 && energia >= 0
     * @post getRecursos().getCombustible() == combustible
     * @post getRecursos().getEnergia() == energia
     * @post getTripulante().isEmpty()
     */
    public Nave(String id, String nombre, Recursos recursos) {
        this.id = id;
        this.nombre = nombre;
        this.motorWarp = new MotorWarp(this);
        this.recursos = recursos;
    }

    /**
     * @pre  t != null && !getTripulante().contains(t)
     * @post getTripulante().contains(t)
     * @post getTripulante().size() == tamanio anterior + 1
     */
    public void asignarTripulante(Tripulante t) {
        Tripulante.add(t);
    }

    
    // RECURSOS
    public int getCombustible() {
        return recursos.getCombustible();
    public boolean estaViva() {
        return !destruida;
    }

    public void Destruir() {
        destruida = true;
    }

    public boolean estaMotorDisponible() {
        return motorwarp.estaMotorDisponible();
    }

    public int getEnergia() {
        return recursos.getEnergia();
    }

    public int getDesgaste() {
        return recursos.getDesgaste();
    }

    // REPONER RECURSOS
    /**
     * @pre  cantidad >= 0
     * @param cantidad de combustible a reponer
     * @post el combustible queda agregado en cantidad, o en su valor máximo
     */
    public void cargarCombustible(int cantidad) {
    	recursos.cargarCombustible(cantidad);
    }

    /**
     * @pre  cantidad >= 0
     * @param cantidad de energía a reponer
     * @post la energía queda agregada en cantidad, o en su valor máximo
     */
    public void cargarEnergia(int cantidad) {
    	recursos.cargarEnergia(cantidad);
    }

    /**
     * @pre  cantidad >= 0
     * @param cantidad de desgaste a quitar
     * @post el desgaste queda disminuido en cantidad, o en su valor mínimo
     */
    public void quitarDesgaste(int cantidad) {
    	recursos.quitarDesgaste(cantidad);
    }
    
    // USO DE RECURSOS
    
    /**
     * @pre  cantidad >= 0
     * @param cantidad de combustible a usar
     * @post el combustible queda disminuido en cantidad, o en su valor mínimo
     */
    public void usarCombustible(int cantidad) {
    	recursos.usarCombustible(cantidad);
    }
    
    /**
     * @pre  cantidad >= 0
     * @param cantidad de energia a usar
     * @post la energía queda disminuida en cantidad, o en su valor mínimo
     */
    public void usarEnergia(int cantidad) {
    	recursos.usarEnergia(cantidad);
    }
        
    /**
     * @pre cantidad >= 0
     * @param cantidad de desgaste a generar
     * @post el desgaste queda agregado en cantidad, o en su valor máximo
     */
    public void generarDesgaste(int cantidad) {
    	recursos.generarDesgaste(cantidad);
    }

    
    /**
     * Reinicia el combustible.
     * @post getcombustible() == 100
     */
    public void reiniciarCombustible() {
    	recursos.reiniciarCombustible();
    }
    
    /**
     * Reinicia la energía.
     * @post getEnergia() == 100
     */
    public void reiniciarEnergia() {
    	recursos.reiniciarEnergia();
    }
    
    /**
     * Reinicia el desgaste (mantenimiento).
     * @post getDesgaste() == 0
     */
    public void reiniciarDesgaste() {
    	recursos.reiniciarDesgaste();
    }
    
    
    
    // OTROS
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public MotorWarp getMotorWarp() {
        return motorWarp;
    }

    public ArrayList<Tripulante> getTripulante() {
        return Tripulante;
    }

}