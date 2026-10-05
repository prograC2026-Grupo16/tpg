package tpgProgra2026;
/**
 * Variante de misión tipo 01: al completarse otorga energía a la nave.
 *
 * Precondiciones:
 *   Constructor: el Asistente ac recibido no es null y está inicializado.
 *   Como consecuencia, EnergiaGanada() lanzaría NullPointerException si fuera
 *   nulo.
 *
 *   EnergiaGanada(): se invoca solo después de una ejecución exitosa de la misión.
 *
 * Postcondiciones:
 *   Constructor: la misión queda asociada a ac y sin preparar.
 *
 *   EnergiaGanada(): la energía de la nave aumenta en ENERGIA_GANADA,
 *   delegando en ac.cargarEnergia}.
 *
 *    getTipomision(): devuelve exactamente el String "02", cumpliendo
 *   el contrato de la clase abstracta padre.
 *
 * Invariantes
 *   ENERGIA_GANADA es una constante (final) estrictamente positiva, por lo que esta
 *   misión nunca consume energía.
 *   Se conservan los invariantes de Mision.
 */
public class Mision02 extends Mision{
    private final int ENERGIA_GANADA = 5;

    public Mision02(Asistente ac) {
        super(ac);
    }

    public void EnergiaGanada(){
        ac.cargarEnergia(ENERGIA_GANADA);// o nave.setEnergia(nave.getEnergia() + ENERGIA_GANADA)
    }

    @Override
    public String getTipomision(){
        return "02";
    }
}