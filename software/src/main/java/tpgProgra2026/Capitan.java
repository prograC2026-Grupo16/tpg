
package tpgProgra2026;

/*
* @pre  antiguedad >= 0
* @post getAntiguedad() == antiguedad
* @post getSueldo() == 1000 + 1000 * 0.2 * antiguedad
*/
public class Capitan extends Tripulante{
    private double sueldo;
    public Capitan( int antiguedad ){
        super( antiguedad );
        this.sueldo = 1000 +  1000*0.2*antiguedad;
    }
    @Override
    //@post retorna un valor >= 1000
    public double getSueldo(){
        return sueldo;
    }
    @Override
    //@post retorna "Capitan"
    public String getCargo(){
        return " Capitan ";
    }
    @Override
    //@post retorna un String no nulo que incluye id, antiguedad y cargo
    public String descripcion(){
        return " El tripulante " + getId() + "tiene una antiguedad de " + getAntiguedad()
                + "Con un cargo de " + getCargo();         
    }
        
    
    
}
