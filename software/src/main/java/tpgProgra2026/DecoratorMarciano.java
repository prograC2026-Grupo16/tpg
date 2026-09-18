/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpgProgra2026;

/**
 *
 * @author lasso
 */
public class DecoratorMarciano extends Decorator{
        public String origen;
    
    public DecoratorMarciano( Tripulante tripulante, String origen ){
        super(tripulante);
        this.origen =   origen;
    }
    @Override
    public String getCargo(){
        return tripulante.getCargo();
    }
    public String getOrigen(){
        return origen;
    }
    @Override
    public double getSueldo(){
        return tripulante.getSueldo() + 18;
    }
    @Override
    public String descripcion(){
        return tripulante.descripcion() + " El origen es " + getOrigen() + " y su sueldo es " + getSueldo();
    }
    
}
