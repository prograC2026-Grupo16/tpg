public abstract class Mision{
    protected final int COSTO_COMBUSTIBLE = 4; // Por si luego cambia el valor
    protected int COSTO_DESGASTE = 4;
    private boolean preparada = false;


    public Mision() {
    }

    // Supongo que debo tener la referencia a una nave para poder saber si es posible ejecutar la mision
    public void Ejecuto_Mision(Nave nave){ //
        preparar(nave);
        if(preparada){
            ejecutar(nave); // Que puedo hacer en ejecutar ???
            EnergiaGanada(nave);
        }
        evaluar();// ???
        cerrar(nave);
    }

    public void preparar(Nave nave) {
        if (nave.getCombustible() >= COSTO_COMBUSTIBLE && (100 - nave.getDesgaste()) >= COSTO_DESGASTE) {
                nave.bitacora.registrar_preparacion_con_exito(); // Mi idea de implementacion de bitacora
            preparada = true;
        }
        else{
            nave.bitacora.registrar_preparacion_fallida(); // mensaje: "Preparacion fallida, no hay recursos suficientes"
        }
    }

    public void ejecutar(){
        //No se que hacer acá
    }

    public void cerrar(Nave nave) {
        if (preparada) {
            nave.bitacora.registrar_MisionExito();
        } else {
            nave.bitacora.registrar_MisionFallo();
        }
    }

    public void evaluar(){
        //Tampoco se que poenr acá xD
    }

    public abstract void EnergiaGanada();
}
