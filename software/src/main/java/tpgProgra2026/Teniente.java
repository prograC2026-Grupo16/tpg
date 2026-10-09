
package tpgProgra2026;

/*
* @pre  antiguedad >= 0
* @post getAntiguedad() == antiguedad
* @post getSueldo() == 400 + 0.03 * 400 * antiguedad
*/
public class Teniente extends Tripulante{
    private double sueldo;
    
    public Teniente(  int antiguedad ){
        super(  antiguedad);
        this.sueldo = 400 + 0.03*400*antiguedad;
    }
    @Override
    //@post retorna un valor >= 400 
    public double getSueldo(){
        return sueldo;
    }
    @Override
    //@post retorna "Teniente"
    public String getCargo(){
        return "Teniende";
    }
    // @post retorna un String no nulo que incluye id, antiguedad y cargo
    @Override
    public String descripcion(){
        return " El tripulante " + getId() + "tiene una antiguedad de " + getAntiguedad()
                + "Con un cargo de " + getCargo();        
    }
    
    
}
