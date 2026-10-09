package tpgProgra2026;

/**
 * @invariante combustible >= 0 && energia >= 0 && desgaste >= 0
 */
public class Recursos {
    private int combustible = 0;
    private int energia = 0;
    private int desgaste = 0;

    /**
     * @pre  cantidad >= 0
     * @post getCombustible() == combustible anterior + cantidad
     */
    public void setCombustible(int cantidad) {
        combustible += cantidad;
    }

    /**
     * @pre  cantidad >= 0
     * @post getEnergia() == energia anterior + cantidad
     */
    public void setEnergia(int cantidad) {
        energia += cantidad;
    }

    /**
     * Reinicia el desgaste (mantenimiento).
     * @post getDesgaste() == 0
     */
    public void setDesgaste() {
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