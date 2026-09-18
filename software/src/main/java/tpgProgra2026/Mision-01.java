public class Mision01 extends Mision{

    private final ENERGIA_GANADA = 5;

    public Mision01() {
    }

    public void EnergiaGanada(){
        nave.AgregarEnergia(ENERGIA_GANADA);// o nave.setEnergia(nave.getEnergia() + ENERGIA_GANADA)
    }

}