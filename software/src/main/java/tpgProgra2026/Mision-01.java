package tpgProgra2026;

public class Mision01 extends Mision{

    private final int ENERGIA_GANADA = 5;

    public Mision01(Asistente ac) {
        super(ac);
    }

    public void EnergiaGanada(){
        ac.cargarEnergia(ENERGIA_GANADA);
    }

    @Override
    public String getTipomision(){
        return "01";
    }

}