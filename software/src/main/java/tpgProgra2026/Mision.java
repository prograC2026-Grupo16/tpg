package tpgProgra2026;

public abstract class Mision{

    protected final int COSTO_COMBUSTIBLE = 4; // Por si luego cambia el valor
    protected int COSTO_DESGASTE = 4;
    private boolean preparada = false;
    protected Asistente ac;

    public Mision(Asistente ac) {
        this.ac = ac;
    }

    public void Ejecuto_Mision(){
        preparar(ac);
        ejecutar(ac);
        EnergiaGanada(ac);
        evaluar();// ???
        cerrar(ac);
    }

    public void preparar() {
        if ( ac.alcanzaPara(COSTO_COMBUSTIBLE, 0, COSTO_DESGASTE) && ac.estaMotorDisponible()) { // Desgaste no contemplado en metodo alcanzaPara - MODIFICAR
                ac.registrarEvento("Nave preparada para mision", "Tipo");
            preparada = true;
        }
        else{
            ac.registrarEvento("Preparacion fallida, no hay recursos suficientes", "Tipo");
        }
    }

    public void ejecutar(){
        if ( preparada ) {
            ac.nave.consumirRecursos(COSTO_COMBUSTIBLE, 0, COSTO_DESGASTE);
            System.out.println("Mision " + getTipomision() + "  Completada");
        }else{
            System.out.println("Mision" + getTipomision() + "  Fallida");
        }
    }

    public void cerrar() {
        if (preparada) {
            ac.registrarEvento("Mision" + getTipomision() + " Completada con exito", "Tipo");
        } else {
            ac.registrarEvento("Mision " + getTipomision() + " fallida (No hay recursos disponibles)", "Tipo");
        }
    }

    public void evaluar(){
        //Tampoco se que poner acá
    }

    public abstract void EnergiaGanada();
    public abstract String getTipomision();
}
