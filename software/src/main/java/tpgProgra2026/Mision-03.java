package tpgProgra2026;

public class Mision03 extends Mision{
    private final int ENERGIA_GANADA = 5;

    public Mision03(Asistente ac) {
        super(ac);
    }

    public void EnergiaGanada(){}

    @Override
    public String getTipomision(){
        return "03";
    }
}