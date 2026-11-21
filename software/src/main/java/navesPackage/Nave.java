package navesPackage;
import java.util.ArrayList;
import motorPackage.MotorWarp;
import tpgProgra2026.Recursos;
import tpgProgra2026.TableroDestino.Observable;
import tripulacionPackage.Tripulante;

/**
 * @invariante id != null && nombre != null && tipo != null
 * @invariante recursos != null && motorwarp != null && Tripulante != null
 */
public abstract class Nave extends Observable {
    protected String id;
    protected String nombre;
    protected Recursos recursos;
    protected int gemas;
    protected MotorWarp motorWarp;
    protected ArrayList<Tripulante> tripulacion;
    protected boolean destruida = false;
    public static final int LIMITE_DESGASTE = 100;

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
        this.gemas = 0;
        this.tripulacion = new ArrayList<>();
    }

    // DOUBLE DISPATCH
    
    public abstract int esVencidoPorExploradora();
    public abstract int esVencidoPorCarguera();
    public abstract int esVencidoPorCombate();
    
    
    
    /**
     * @pre  t != null && !getTripulante().contains(t)
     * @post getTripulante().contains(t)
     * @post getTripulante().size() == tamanio anterior + 1
     */
    public void asignarTripulante(Tripulante t) {
        tripulacion.add(t);
        notificarObservadores();
    }

    /** @post cantidad de tripulantes con cargo Capitan (los decoradores de origen conservan el cargo). */
    public int cantidadCapitanes() {
        int cantidad = 0;
        for (Tripulante t : tripulacion) {
            if ("Capitan".equals(t.getCargo())) {
                cantidad++;
            }
        }
        return cantidad;
    }

    /** @post true si hay al menos un Capitán asignado. */
    public boolean tieneCapitan() {
        return cantidadCapitanes() > 0;
    }

    public boolean estaViva() {
        return !destruida;
    }

    public void destruir() {
        destruida = true;
        notificarObservadores();
    }

    // RECURSOS
    
    public boolean alcanzaPara(int combustible, int energia, int desgaste) {
        return getCombustible() >= combustible
            && getEnergia() >= energia
            && (getDesgaste() + desgaste) < LIMITE_DESGASTE;
    }
    

    public int getCombustible() {
        return recursos.getCombustible();
    }
    
    public int getEnergia() {
        return recursos.getEnergia();
    }

    public int getDesgaste() {
        return recursos.getDesgaste();
    }

    public int getGemas() {
        return gemas;
    }

    // REPONER RECURSOS
    /**
     * @pre  cantidad >= 0
     * @param cantidad de combustible a reponer
     * @post el combustible queda agregado en cantidad, o en su valor máximo
     */
    public void cargarCombustible(int cantidad) {
    	recursos.cargarCombustible(cantidad);
        notificarObservadores();
    }

    /**
     * @pre  cantidad >= 0
     * @param cantidad de energía a reponer
     * @post la energía queda agregada en cantidad, o en su valor máximo
     */
    public void cargarEnergia(int cantidad) {
    	recursos.cargarEnergia(cantidad);
        notificarObservadores();
    }

    /**
     * @pre  cantidad >= 0
     * @param cantidad de desgaste a quitar
     * @post el desgaste queda disminuido en cantidad, o en su valor mínimo
     */
    public void quitarDesgaste(int cantidad) {
    	recursos.quitarDesgaste(cantidad);
        notificarObservadores();
    }

    public void cargarGemas(int cantidad) {
        gemas += cantidad;
        notificarObservadores();
    }
    
    // USO DE RECURSOS
    
    /**
     * @pre  cantidad >= 0
     * @param cantidad de combustible a usar
     * @post el combustible queda disminuido en cantidad, o en su valor mínimo
     */
    public void usarCombustible(int cantidad) {
    	recursos.usarCombustible(cantidad);
        notificarObservadores();
    }
    
    /**
     * @pre  cantidad >= 0
     * @param cantidad de energia a usar
     * @post la energía queda disminuida en cantidad, o en su valor mínimo
     */
    public void usarEnergia(int cantidad) {
    	recursos.usarEnergia(cantidad);
        notificarObservadores();
    }
        
    /**
     * @pre cantidad >= 0
     * @param cantidad de desgaste a generar
     * @post el desgaste queda agregado en cantidad, o en su valor máximo
     */
    public void generarDesgaste(int cantidad) {
    	recursos.generarDesgaste(cantidad);
        notificarObservadores();
    }

    
    /**
     * Reinicia el combustible.
     * @post getcombustible() == 100
     */
    public void reiniciarCombustible() {
    	recursos.reiniciarCombustible();
        notificarObservadores();
    }
    
    /**
     * Reinicia la energía.
     * @post getEnergia() == 100
     */
    public void reiniciarEnergia() {
    	recursos.reiniciarEnergia();
        notificarObservadores();
    }
    
    /**
     * Reinicia el desgaste (mantenimiento).
     * @post getDesgaste() == 0
     */
    public void reiniciarDesgaste() {
    	recursos.reiniciarDesgaste();
        notificarObservadores();
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

    public ArrayList<Tripulante> getTripulacion() {
        return tripulacion;
    }

}