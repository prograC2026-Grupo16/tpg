package tpgProgra2026;

/**
 * @invariante combustible >= 0 && energia >= 0 && desgaste >= 0
 * @invariante combustible <= 100 && energia <= 100 && desgaste <= 100
 */
public class Recursos {
    private int combustible, energia, desgaste;
    private static final int MINIMO_COMBUSTIBLE = 0, MINIMO_ENERGIA = 0, MINIMO_DESGASTE = 0;
    private static final int MAXIMO_COMBUSTIBLE = 100, MAXIMO_ENERGIA = 100, MAXIMO_DESGASTE = 100;
    
    /**
     * @pre combustibleInicial>= 0 && combustibleInicial <= 100
     * @pre energiaInicial>= 0 && energiaInicial <= 100
     * @pre desgasteInicial>= 0 && desgasteInicial <= 100
     * @param combustibleInicial
     * @param energiaInicial
     * @param desgasteInicial
     */
    public Recursos(int combustibleInicial, int energiaInicial, int desgasteInicial) {
    	assert combustibleInicial >= MINIMO_COMBUSTIBLE && combustibleInicial <= MAXIMO_COMBUSTIBLE : "Combustible Inicial fuera del rango aceptable";
    	assert energiaInicial >= MINIMO_ENERGIA && energiaInicial <= MAXIMO_ENERGIA: "Energía Inicial fuera del rango aceptable";
    	assert desgasteInicial >= MINIMO_DESGASTE && desgasteInicial <= MAXIMO_DESGASTE : "Combustible Inicial fuera del rango aceptable";
    	this.combustible = combustibleInicial;
    	this.energia = energiaInicial;
    	this.desgaste = desgasteInicial;
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
    
    /**
     * @pre  cantidad >= 0
     * @param cantidad de combustible a reponer
     * @post el combustible queda agregado en cantidad, o en su valor máximo
     */
    public void cargarCombustible(int cantidad) {
        if (combustible + cantidad > MAXIMO_COMBUSTIBLE)
        	combustible = MAXIMO_COMBUSTIBLE;
        else
            combustible += cantidad;
    }

    /**
     * @pre  cantidad >= 0
     * @param cantidad de energía a reponer
     * @post la energía queda agregada en cantidad, o en su valor máximo
     */
    public void cargarEnergia(int cantidad) {
        if (energia + cantidad > MAXIMO_ENERGIA)
        	energia = MAXIMO_ENERGIA;
        else
            energia += cantidad;
    }

    /**
     * @pre  cantidad >= 0
     * @param cantidad de desgaste a quitar
     * @post el desgaste queda disminuido en cantidad, o en su valor mínimo
     */
    public void quitarDesgaste(int cantidad) {
        if (desgaste - cantidad < MINIMO_DESGASTE)
        	desgaste = MINIMO_ENERGIA;
        else
            desgaste -= cantidad;
    }
    
    // USO DE RECURSOS
    
    /**
     * @pre  cantidad >= 0
     * @param cantidad de combustible a usar
     * @post el combustible queda disminuido en cantidad, o en su valor mínimo
     */
    public void usarCombustible(int cantidad) {
        if (combustible - cantidad < MINIMO_COMBUSTIBLE)
        	combustible = MINIMO_COMBUSTIBLE;
        else
            combustible -= cantidad;
    }
    
    /**
     * @pre  cantidad >= 0
     * @param cantidad de energia a usar
     * @post la energía queda disminuida en cantidad, o en su valor mínimo
     */
    public void usarEnergia(int cantidad) {
        if (energia - cantidad < MINIMO_ENERGIA)
        	energia = MINIMO_COMBUSTIBLE;
        else
            energia -= cantidad;
    }
        
    /**
     * @pre cantidad >= 0
     * @param cantidad de desgaste a generar
     * @post el desgaste queda agregado en cantidad, o en su valor máximo
     */
    public void generarDesgaste(int cantidad) {
    	if (desgaste + cantidad > MAXIMO_DESGASTE)
    		desgaste = MAXIMO_DESGASTE;
    	else
    		desgaste += cantidad;
    }

    
    /**
     * Reinicia el desgaste (mantenimiento).
     * @post getDesgaste() == 0
     */
    public void reiniciarDesgaste() {
        desgaste = MINIMO_DESGASTE;
    }

    
    /**
     * Reinicia el combustible.
     * @post getcombustible() == 100
     */
    public void reiniciarCombustible() {
        desgaste = MAXIMO_COMBUSTIBLE;
    }
    
    /**
     * Reinicia la energía.
     * @post getEnergia() == 100
     */
    public void reiniciarEnergia() {
        desgaste = MAXIMO_ENERGIA;
    }
    
    /* yo esto lo quitaría ~ Mauro
    /**
     * @pre  combustible >= 0 && energia >= 0 && desgaste >= 0
     * @pre  combustible <= getCombustible() && energia <= getEnergia()
     * @post getCombustible() == combustible anterior - combustible
     * @post getEnergia() == energia anterior - energia
     * @post getDesgaste() == desgaste anterior + desgaste
     */
    /*
    public void gastarRecursos(int combustible, int energia, int desgaste) {
    	usarCombustible(combustible);
    	usarEnergia(energia);
    	agregarDesgaste(desgaste);        
    }
*/

}