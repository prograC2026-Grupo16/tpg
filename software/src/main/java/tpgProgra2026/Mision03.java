package tpgProgra2026;

import tpgProgra2026.TableroDestino.Posicion;

/**
 * Variante de misión tipo 03: no otorga energía a la nave.
 *
 * @inv Esta misión nunca modifica la energía de la nave.
 * @inv Se conservan los invariantes de Mision.
 */
public class Mision03 extends Mision{
//    private final int ENERGIA_GANADA = 0;
    private final int COSTO_ENERGIA = 0;
    private final int COSTO_DESGASTE = 4;
    private final int COSTO_COMBUSTIBLE = 4;
    /**
     * @pre el Asistente ac recibido no es null y está inicializado.
     * @post la misión queda asociada a ac y sin preparar.
     */
    public Mision03(Asistente ac) {
        super(ac);
    }

    @Override
    public void cerrar() {
        if (exito)
            ac.realizarMantenimiento();
    }

    @Override
    protected Posicion calcularDestino() { // Porque misión 3 vuelve a base
        return ac.getBase();
    }

    /**
     * @post devuelve exactamente el String "03" para identificar la misión en la bitácora y en la consola.
     */
    @Override
    public String getTipomision(){
        return "03";
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