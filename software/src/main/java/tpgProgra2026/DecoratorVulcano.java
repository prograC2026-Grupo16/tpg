
package tpgProgra2026;

/**
 *
 * @author lasso
 */
public class DecoratorVulcano extends Decorator{
        public String origen;
    
    public DecoratorVulcano( Tripulante tripulante){
        super(tripulante);
        this.origen =   "Vulcano";
    }
    public String getOrigen(){
        return origen;
    }
     @Override
    public String getCargo(){
        return tripulante.getCargo();
    }
    @Override
    public double getSueldo(){
        return tripulante.getSueldo() + 30;
    }
    @Override
    public String descripcion(){
        return tripulante.descripcion() + " El origen es " + getOrigen() + " y su sueldo es " + getSueldo();
    }
    
    
}
