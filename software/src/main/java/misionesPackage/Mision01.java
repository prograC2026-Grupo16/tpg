package misionesPackage;

import tpgProgra2026.Asistente;
import tpgProgra2026.Recursos;

import java.util.Random;

/**
 * Variante de misión tipo 01: al completarse otorga energía a la nave.
 *
 * @inv ENERGIA_GANADA es una constante (final) estrictamente positiva, por lo que esta misión nunca consume energía.
 * @inv Se conservan los invariantes de Mision.
 */
public class Mision01 extends Mision {

    private final int ENERGIA_GANADA = 5;
    private final int COSTO_ENERGIA = 5;
    private final int COSTO_DESGASTE = 4;
    private final int COSTO_COMBUSTIBLE = 4;
    private int combustibleAEntregar = 0;
    private int energiaAEntregar = 0;

    /**
     * @pre el Asistente ac recibido no es null y está inicializado.
     * @post la misión queda asociada a ac y sin preparar.
     */
    public Mision01(Asistente ac) {
        super(ac);
    }

    @Override
    public void cerrar() {
        if (exito) {
            ac.usarCombustible(combustibleAEntregar);
            ac.usarEnergia(energiaAEntregar);
            ac.registrarEvento("Misión 01 cerrada: se entregaron " + combustibleAEntregar + " de combustible y " + energiaAEntregar + " de energia", "Misión");
        }
    }

    /**
     * @post true si, al llegar, a la nave le sobra lo que debe entregar. Es estrictamente mayor
     *       porque con combustible o energía en 0 la nave muere.
     */
    @Override
    protected boolean puedeCumplirEnDestino() {
        Random random = new Random();
        combustibleAEntregar = random.nextInt(51); // 0 a 50
        energiaAEntregar = random.nextInt(51);
        return ac.getCombustible() > combustibleAEntregar && ac.getEnergia() > energiaAEntregar;
    }

    /**
     * @post devuelve exactamente el String "01", cumpliendo el contrato de la clase abstracta padre.
     */
    @Override
    public String getTipomision() {
        return "01";
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