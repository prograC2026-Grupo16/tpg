package misionesPackage;

import tpgProgra2026.Asistente;

import java.util.Random;

/**
 * Variante de misión tipo 01: al completarse otorga energía a la nave.
 *
 * @inv ENERGIA_GANADA es una constante (final) estrictamente positiva, por lo que esta misión nunca consume energía.
 * @inv Se conservan los invariantes de Mision.
 */
public class Mision02 extends Mision{
    private final int ENERGIA_GANADA = 5;
    private final int COSTO_ENERGIA = 10;
    private final int COSTO_DESGASTE = 4;
    private final int COSTO_COMBUSTIBLE = 4;
    private final Random random = new Random();
    /**
     * @pre el Asistente ac recibido no es null y está inicializado.
     * @post la misión queda asociada a ac y sin preparar.
     */
    public Mision02(Asistente ac) {
        super(ac);
    }

    @Override
    public void cerrar() {
        if(exito)
            ac.cargarGemas(random.nextInt(81) + 20);
    }

    /**
     * @post devuelve exactamente el String "02", cumpliendo el contrato de la clase abstracta padre.
     */
    @Override
    public String getTipomision(){
        return "02";
    }

    @Override
    public int  getCostoCombustible() {
        return COSTO_COMBUSTIBLE;
    }

    @Override
    public int getCostoEnergia() {
        return COSTO_ENERGIA;
    }

    @Override
    public int getCostoDesgaste() {
        return COSTO_DESGASTE;
    }
}