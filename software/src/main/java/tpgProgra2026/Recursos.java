package Tp;


public class Recursos {
    private int combustible = 0;
    private int energia = 0;
    private int desgaste = 0;
    
    public void setCombustible(int cantidad){
        combustible += cantidad;
    }
    
    public void setEnergia(int cantidad){
        energia += cantidad;
    }
    
    public void setDesgaste(){
        desgaste = 0;
    }
    
    public void gastarRecursos( int combustible, int enegia, int desgaste){
        this.combustible -= combustible;
        this.energia -= energia;
        this.desgaste += desgaste;
    }

    public int getCombustible() {
        return combustible;
    }

    public int getEnergia() {
        return energia;
    }

    public int getDesgaste() {
        return desgaste;
    }
    
    
    
    
    
}
