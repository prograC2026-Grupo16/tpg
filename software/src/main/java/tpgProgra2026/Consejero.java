/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpgProgra2026;

/**
 *
 * @author lasso
 */
public class Consejero extends Tripulante{
    private int cantCons;
    private double sueldo;
    
    public Consejero( int id, int antiguedad ){
        super( id, antiguedad );
        this.sueldo = 600 + 0.05*600*antiguedad;
        this.cantCons = 0;
    }
    public void hizoConsejo(){
        this.cantCons+=1;
    }
    @Override
    public double getSueldo(){
        return sueldo + cantCons*2;
    }
    @Override
    public String getCargo(){
        return "Consejero";
    }
    @Override
    public String descripcion(){
        return " El tripulante " + getId() + "tiene una antiguedad de " + getAntiguedad()
                + "Con un cargo de " + getCargo(); 
    }
}
