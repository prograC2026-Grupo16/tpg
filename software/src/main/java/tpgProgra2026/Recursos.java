package tpgProgra2026;

/**
 * @invariante combustible >= 0 && energia >= 0 && desgaste >= 0
 */
public class Recursos {
    private int combustible = 100;
    private int energia = 100;
    private int desgaste = 0;

    public Recursos() { }
    
    /**
     * @pre  cantidad >= 0
     * @post getCombustible() == combustible anterior + cantidad
     */
    public void cargarCombustible(int cantidad) {
        combustible += cantidad;
        if (combustible > 100)
        	combustible = 100;
    }

    /**
     * @pre  cantidad >= 0
     * @post getEnergia() == energia anterior + cantidad
     */
    public void cargarEnergia(int cantidad) {
        energia += cantidad;
        if (energia > 100)
        	energia = 100;
    }

    /**
     * @pre cantidad >= 0
     * @param cantidad
     */
    public void agregarDesgaste(int cantidad) {
    	desgaste += cantidad;
    }
    
    /**
     * Reinicia el desgaste (mantenimiento).
     * @post getDesgaste() == 0
     */
    public void reiniciarDesgaste() {
        desgaste = 0;
    }

    /**
     * @pre  combustible >= 0 && energia >= 0 && desgaste >= 0
     * @pre  combustible <= getCombustible() && energia <= getEnergia()
     * @post getCombustible() == combustible anterior - combustible
     * @post getEnergia() == energia anterior - energia
     * @post getDesgaste() == desgaste anterior + desgaste
     */
    public void gastarRecursos(int combustible, int energia, int desgaste) {
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