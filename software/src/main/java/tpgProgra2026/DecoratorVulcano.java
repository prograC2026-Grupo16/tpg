
package tpgProgra2026;
/*
* @pre  tripulante != null
* @post getOrigen() equals "Vulcano"
* @post getCargo() equals tripulante.getCargo()
* @post getSueldo() == tripulante.getSueldo() + 30
*/
public class DecoratorVulcano extends Decorator{
        public String origen;
    
    public DecoratorVulcano( Tripulante tripulante){
        super(tripulante);
        this.origen =   "Vulcano";
    }
    //@post retorna "Vulcano"
    public String getOrigen(){
        return origen;
    }
     @Override
    //@post retorna el mismo cargo que el tripulante decorado
    public String getCargo(){
        return tripulante.getCargo();
    }
    @Override
    //@post retorna tripulante.getSueldo() + 30
    public double getSueldo(){
        return tripulante.getSueldo() + 30;
    }
    @Override
    //@post retorna un String no nulo que contiene la descripcion del tripulante decorado
    public String descripcion(){
        return tripulante.descripcion() + " El origen es " + getOrigen() + " y su sueldo es " + getSueldo();
    }
    
    
}
