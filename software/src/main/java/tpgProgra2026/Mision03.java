package tpgProgra2026;
/**
 * Variante de misión tipo 03: no otorga energía a la nave.
 *
 * Precondiciones:
 *
 *   Constructor: el Asistente ac recibido no es null} y está inicializado.
 *
 * Postcondiciones:
 *   Constructor: la misión queda asociada a ac y sin preparar.
 *   EnergiaGanada(): deja inalterado el estado de energía de la nave, ya que esta
 *   variante no otorga energía (cuerpo vacío).
 *   getTipomision(): devuelve exactamente el  String "03" para
 *   identificar la misión en la bitácora y en la consola.
 *
 * Invariantes:
 *   Esta misión nunca modifica la energía de la nave.
 *   Se conservan los invariantes de Mision.
 *
 */
public class Mision03 extends Mision{
//    private final int ENERGIA_GANADA = 0;

    public Mision03(Asistente ac) {
        super(ac);
    }

    public void EnergiaGanada(){}

    @Override
    public String getTipomision(){
        return "03";
    }
}