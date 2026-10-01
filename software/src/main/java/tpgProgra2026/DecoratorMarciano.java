
package tpgProgra2026;

/**
 *
 * @author lasso
 */
public class DecoratorMarciano extends Decorator{
        public String origen;
    
    public DecoratorMarciano( Tripulante tripulante ){
        super(tripulante);
        this.origen =   "Marciano";
    }
    @Override
    public String getCargo(){
        return tripulante.getCargo();
    }
    public String getOrigen(){
        return origen;
    }
    @Override
    public double getSueldo(){
        return tripulante.getSueldo() + 18;
    }
    @Override
    public String descripcion(){
        return tripulante.descripcion() + " El origen es " + getOrigen() + " y su sueldo es " + getSueldo();
    }
    
}
