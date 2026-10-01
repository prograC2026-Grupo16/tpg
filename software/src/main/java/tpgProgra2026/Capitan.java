/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpgProgra2026;

/**
 *
 * @author lasso
 */
public class Capitan extends Tripulante{
    private double sueldo;
    public Capitan( int id, int antiguedad ){
        super( id, antiguedad );
        this.sueldo = 1000 +  1000*0.2*antiguedad;
    }
    @Override
    public double getSueldo(){
        return sueldo;
    }
    @Override
    public String getCargo(){
        return " Capitan ";
    }
    @Override
    public String descripcion(){
        return " El tripulante " + getId() + "tiene una antiguedad de " + getAntiguedad()
                + "Con un cargo de " + getCargo();         
    }
        
    
    
}
