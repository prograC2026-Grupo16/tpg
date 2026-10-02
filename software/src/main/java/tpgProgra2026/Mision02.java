package tpgProgra2026;

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