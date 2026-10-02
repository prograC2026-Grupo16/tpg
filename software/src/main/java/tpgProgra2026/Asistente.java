package tpgProgra2026;
import java.util.ArrayList;


public class Asistente {
    protected Nave nave;
    protected ArrayList<Evento> bitacora = new ArrayList<>();
    protected Mision misiones;

    public Asistente(Nave nave, Mision misiones) {
        this.nave = nave;
        this.misiones = misiones;
    }
    
    public void registrarEvento(String mensaje, String tipo) {
    	bitacora.add(new Evento(mensaje,tipo));
    }
    
    public boolean estaMotorDisponible(){
        return getMotorWarp().estaMotorDisponible();
    }
    
    public void cargarCombustible(int cantidad){
        getRecursos().setCombustible(cantidad);
    }
    
    public void cargarEnergia(int cantidad){
        getRecursos().setEnergia(cantidad);
    }
    
    public void consumirRecursos(int combustible, int cantidad, int desgaste){     
        getRecursos().gastarRecursos(combustible, cantidad, desgaste);
    }
    
    public boolean alcanzaPara(int combustible, int energia, int desgaste){                          
        return getRecursos().getCombustible() > combustible && getRecursos().getEnergia() > energia && (getRecursos().getDesgaste() + desgaste) < 80;
    }
    
    public boolean requiereMantenimiento(){
        return getRecursos().getDesgaste() >= 80;
    }
    
    public void realizarMantenimiento(){
        getRecursos().setDesgaste();
    }
    
    public Recursos getRecursos() {
        return nave.getRecursos();
    }
    
    public MotorWarp getMotorWarp() {
        return nave.getMotorWarp();
    }
}