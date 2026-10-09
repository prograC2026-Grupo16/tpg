package tpgProgra2026;
/*
* @pre  tripulante != null
* @post getAntiguedad() == tripulante.getAntiguedad()
* @post getId() == tripulante.getId()
*/
public abstract class Decorator extends Tripulante {
    protected Tripulante tripulante;
    
    public Decorator( Tripulante tripulante ){
        super( tripulante.getAntiguedad() );
        this.tripulante = tripulante;
    }
    @Override
    //@post retorna el id del tripulante decorado
    public int getId(){
        return tripulante.getId();
    }
    // @post retorna un valor >= tripulante.getSueldo() 
    public abstract double getSueldo();
    //@post retorna un String no nulo 
    public abstract String descripcion();
}
