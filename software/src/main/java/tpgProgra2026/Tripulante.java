/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tpgProgra2026;

/**
 *
 * @author lasso
 */
public abstract class Tripulante {
    private int id;
    private int antiguedad;
    
    public Tripulante(int id, int antiguedad){
        this.antiguedad =   antiguedad;
        this.id         =   id;
    }

    public int getId() {
        return id;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public abstract String getCargo();
    public abstract double getSueldo();
    public abstract String descripcion();
    
}
