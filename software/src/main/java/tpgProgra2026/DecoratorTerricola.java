
package tpgProgra2026;

/**
 *
 * @author lasso
 */
public class DecoratorTerricola extends Decorator {
    public String origen;
    
    public DecoratorTerricola( Tripulante tripulante){
        super(tripulante);
        this.origen =   "Terricola";
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
        return tripulante.getSueldo() + 20;
    }
    @Override
    public String descripcion(){
        return tripulante.descripcion() + " El origen es " + getOrigen() + " y su sueldo es " + getSueldo();
    }
    
}
