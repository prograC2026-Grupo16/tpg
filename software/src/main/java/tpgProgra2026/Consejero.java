
package tpgProgra2026;

/*
* @pre  antiguedad >= 0
* @post getAntiguedad() == antiguedad
* @post getSueldo() == 600 + 0.05 * 600 * antiguedad (todavia no hizo consejos)
*/
public class Consejero extends Tripulante{
    private int cantCons;
    private double sueldo;
    
    public Consejero(  int antiguedad ){
        super( antiguedad );
        this.sueldo = 600 + 0.05*600*antiguedad;
        this.cantCons = 0;
    }
    // @post getSueldo() aumenta exactamente 2 respecto del valor anterior a la llamada
    public void hizoConsejo(){
        this.cantCons+=1;
    }
    @Override
    //@post retorna un valor >= 600 
    public double getSueldo(){
        return sueldo + cantCons*2;
    }
    @Override
    //@post retorna "Consejero" 
    public String getCargo(){
        return "Consejero";
    }
    @Override
    //@post retorna un String no nulo que incluye id, antiguedad y cargo
    public String descripcion(){
        return " El tripulante " + getId() + "tiene una antiguedad de " + getAntiguedad()
                + "Con un cargo de " + getCargo(); 
    }
}
