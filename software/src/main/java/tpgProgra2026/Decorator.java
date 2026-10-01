
package tpgProgra2026;

/**
 *
 * @author lasso
 */
public abstract class Decorator extends Tripulante {
    protected Tripulante tripulante;
    
    public Decorator( Tripulante tripulante ){
        super( tripulante.getId(), tripulante.getAntiguedad() );
        this.tripulante = tripulante;
    }
    
    public abstract double getSueldo();
    public abstract String descripcion();
}
