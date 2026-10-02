package Tp;
import java.util.ArrayList;


public class Asistente {
    protected Nave nave;
    protected ArrrayList<Eventos> bitacora = new ArrayList<>();
    protected Misiones mision;

    public Asistente(Nave nave, Misiones mision) {
        this.nave = nave;
        this.mision = mision;
    }
    
    
    public boolean estaMotorDisponible(){
        return nave.motorwarp.estaMotorDisponible();
    }
    
    public void cargarCombustible(int cantidad){
        nave.recursos.setCombustible(cantidad);
    }
    
    public void cargarEnergia(int cantidad){
        nave.recursos.setEnergia(cantidad);
    }
    
    public void consumirRecursos(int combustible, int cantidad, int desgaste){     
        nave.recursos.gastarRecursos(combustible, cantidad, desgaste);
    }
    
    public boolean alcanzaPara(int combustible, int energia, int desgaste){                          
        return nave.recursos.getCombustible() > combustible && nave.recursos.getEnergia() > energia && (nave.recursos.getDesgaste() + desgaste) < 80;
    }
    
    public boolean requiereMantenimiento(){
        return nave.recursos.getDesgaste() >= 80;
    }
    
    public void realizarMantenimiento(){
        nave.recursos.setDesgaste();
    }
    
    public Recursos getRecursos() {
        return nave.getRecursos();
    }
    
    
    
    
    
    
    
    
    
    
}
