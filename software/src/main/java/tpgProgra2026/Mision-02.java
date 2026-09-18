public class Mision02 extends Mision{
    private final ENERGIA_GANADA = 5;

    public Mision02() {
    }

    public void EnergiaGanada(){
        nave.AgregarEnergia(ENERGIA_GANADA);// o nave.setEnergia(nave.getEnergia() + ENERGIA_GANADA)
    }
}