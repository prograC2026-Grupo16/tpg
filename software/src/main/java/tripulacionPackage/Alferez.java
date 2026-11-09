package tripulacionPackage;

/*
* @pre  antiguedad >= 0
* @post getAntiguedad() == antiguedad
* @post getSueldo() == 200 + 200 * 0.005 * antiguedad
*/
public class Alferez extends Tripulante{
    private double sueldo;
    public Alferez ( int antiguedad){
        super(antiguedad);
        sueldo = 200 + 200*0.005*antiguedad;
    }
    @Override
    //@post retorna un valor >= 200
    public double getSueldo(){
        return sueldo;
    }
    @Override
    //  @post retorna "Alferez"
    public String getCargo(){
        return "Alferez";
    }
    @Override
    // @post retorna un String no nulo que incluye id, antiguedad y cargo
    public String descripcion(){
        return " El tripulante " + getId() + " tiene una antiguedad de " + getAntiguedad()
                + " con un cargo de " + getCargo();
                
    }
}
