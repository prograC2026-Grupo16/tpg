
package tripulacionPackage;
/*
* @pre  tripulante != null
* @post getOrigen() equals "Marciano"
* @post getCargo() equals tripulante.getCargo()
* @post getSueldo() == tripulante.getSueldo() + 18
*/
public class DecoratorMarciano extends Decorator{
	
    public DecoratorMarciano( Tripulante tripulante ){
        super(tripulante);
    }
    @Override
    //@post retorna el mismo cargo que el tripulante decorado
    public String getCargo(){
        return tripulante.getCargo();
    }
   //@post retorna "Marciano"
    @Override
    public String getOrigen(){
        return "Marciano";
    }
    @Override
    //@post retorna tripulante.getSueldo() + 18
    public double getSueldo(){
        return tripulante.getSueldo() + 18;
    }
    @Override
    //@post retorna un String no nulo que contiene la descripcion del tripulante decorado
    public String descripcion(){
        return tripulante.descripcion() + " El origen es " + getOrigen() + " y su sueldo es " + getSueldo();
    }
    
}
