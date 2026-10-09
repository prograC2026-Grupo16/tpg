
package tpgProgra2026;
/*
* @pre  tripulante != null
* @post getOrigen() equals "Terricola"
* @post getCargo() equals tripulante.getCargo()
* @post getSueldo() == tripulante.getSueldo() + 20
*/

public class DecoratorTerricola extends Decorator {
    public String origen;
    
    public DecoratorTerricola( Tripulante tripulante){
        super(tripulante);
        this.origen =   "Terricola";
    }
    //@post retorna "Terricola"
    public String getOrigen(){
        return origen;
    }
    @Override
    //@post retorna el mismo cargo que el tripulante decorado
    public String getCargo(){
        return tripulante.getCargo();
    }
    @Override
    //@post retorna tripulante.getSueldo() + 20
    public double getSueldo(){
        return tripulante.getSueldo() + 20;
    }
    @Override
    //@post retorna un String no nulo que contiene la descripcion del tripulante decorado
    public String descripcion(){
        return tripulante.descripcion() + " El origen es " + getOrigen() + " y su sueldo es " + getSueldo();
    }
    
}
