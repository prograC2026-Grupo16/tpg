package tpgProgra2026;

/**
 * Variante de misión tipo 01: al completarse otorga energía a la nave.
 *
 * @inv ENERGIA_GANADA es una constante (final) estrictamente positiva, por lo que esta misión nunca consume energía.
 * @inv Se conservan los invariantes de Mision.
 */
public class Mision01 extends Mision {

    private final int ENERGIA_GANADA = 5;

    /**
     * @pre el Asistente ac recibido no es null y está inicializado.
     * @post la misión queda asociada a ac y sin preparar.
     */
    public Mision01(Asistente ac) {
        super(ac);
    }

    /**
     * @pre se invoca solo después de una ejecución exitosa de la misión.
     * @pre Como consecuencia, EnergiaGanada() lanzaría NullPointerException si ac fuera nulo.
     * @post la energía de la nave aumenta en ENERGIA_GANADA, delegando en ac.cargarEnergia.
     */
    @Override
    public void EnergiaGanada() {
        ac.cargarEnergia(ENERGIA_GANADA);
    }

    /**
     * @post devuelve exactamente el String "01", cumpliendo el contrato de la clase abstracta padre.
     */
    @Override
    public String getTipomision() {
        return "01";
    }

}