package Tp;
import java.util.ArrayList;


public abstract class Nave {
    protected String id;
    protected String nombre;
    protected String tipo;
    protected Recursos recursos;
    protected MotorWarp motorwarp;
    protected ArrayList<Tripulacion> tripulacion = new ArrayList<>();

    public Nave(String id, String nombre, String tipo, MotorWarp motorwarp, int combustible, int energia) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.motorwarp = motorwarp;
        this.recursos.setCombustible(combustible);
        this.recursos.setEnergia(energia);
    }
    
    public void asignarTripulacion(Tripulacion t){
        tripulacion.add(t);
    }
    
    public boolean estaMotorDisponible(){
        return motorwarp.estaMotorDisponible();
    }
    
    
    public void cargarCombustible(int cantidad){
        recursos.setCombustible(cantidad);
    }
    
    public void cargarEnergia(int cantidad){
        recursos.setEnergia(cantidad);
    }   
    
    public void realizarMantenimiento(){
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

    public MotorWarp getMotorwarp() {
        return motorwarp;
    }

    public ArrayList<Tripulacion> getTripulacion() {
        return tripulacion;
    }
    
    
    
    
    
    
    
}
