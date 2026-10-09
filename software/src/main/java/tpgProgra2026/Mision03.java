package tpgProgra2026;

/**
 * Variante de misión tipo 03: no otorga energía a la nave.
 *
 * @inv Esta misión nunca modifica la energía de la nave.
 * @inv Se conservan los invariantes de Mision.
 */
public class Mision03 extends Mision{
//    private final int ENERGIA_GANADA = 0;

    /**
     * @pre el Asistente ac recibido no es null y está inicializado.
     * @post la misión queda asociada a ac y sin preparar.
     */
    public Mision03(Asistente ac) {
        super(ac);
    }

    /**
     * @post deja inalterado el estado de energía de la nave, ya que esta variante no otorga energía (cuerpo vacío).
     */
    public void EnergiaGanada(){}

    /**
     * @post devuelve exactamente el String "03" para identificar la misión en la bitácora y en la consola.
     */
    @Override
    public String getTipomision(){
        return "03";
    }
}