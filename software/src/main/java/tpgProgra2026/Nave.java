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
    
    
    public void cargarCombustible(int cantidad){
        recursos.setCombustible(cantidad);
    }
    
    public void cargarEnergia(int cantidad){
        recursos.setEnergia(cantidad);
    }
    
    public void consumirRecursos(int combustible, int cantidad, int desgaste){      // !!!! Consultar con el grupo, esta bien o deberia estar en recursos ?
        recursos.gastarRecursos(combustible, cantidad, desgaste);
    }
    
    public boolean alcanzaPara(int combustible, int energia){      //La idea es que recibe como parametros la cantidad de recursos que conlleva esa accion
        int x;
        int y;
        x = recursos.getCombustible();                      //  !!! Deberia verificar desgaste ??
        y = recursos.getEnergia();
        return x > combustible && y > energia;
    }
    
    public boolean requiereMantenimiento(){
        int x;
        x = recursos.getDesgaste();
        return x >= 80;
    }
    
    public void realizarMantenimiento(){        //Muy posible reciba cambios
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
