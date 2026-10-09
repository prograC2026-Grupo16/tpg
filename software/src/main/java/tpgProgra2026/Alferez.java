
package tpgProgra2026;


public class Alferez extends Tripulante{
    private double sueldo;
    public Alferez (int id, int antiguedad){
        super(id,antiguedad);
        sueldo = 200 + 200*0.005*antiguedad;
    }
    @Override
    public double getSueldo(){
        return sueldo;
    }
    @Override
    public String getCargo(){
        return "Alferez";
    }
    @Override
    public String descripcion(){
        return " El tripulante " + getId() + "tiene una antiguedad de " + getAntiguedad()
                + "Con un cargo de " + getCargo();
                
    }
}
