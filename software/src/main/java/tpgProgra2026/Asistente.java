package tpgProgra2026;
import java.util.ArrayList;
import navesPackage.Nave;
import misionesPackage.Mision;
import misionesPackage.Mision03;
import motorPackage.MotorWarp;
import tpgProgra2026.TableroDestino.*;

/**
 * @invariante nave != null && misiones != null && bitacora != null
 */
public class Asistente extends Observable {

    protected Nave nave;
    protected ArrayList<Evento> bitacora = new ArrayList<>();
    protected Mision mision;
    protected Tablero tablero = null;
    protected Destino destino = null;
    protected Posicion base;
    /**
     * @pre  nave != null && misiones != null
     * @post la bitacora esta vacia
     */
    public Asistente(Nave nave) {
        this.nave = nave;
    }
    
    // DOUBLE DISPATCH
    
    public int esVencidoPorExploradora() { return nave.esVencidoPorExploradora(); }
    public int esVencidoPorCarguera() { return nave.esVencidoPorCarguera(); }
    public int esVencidoPorCombate() { return nave.esVencidoPorCombate(); }
    
    
    /**
     * @pre  mensaje != null && tipo != null
     * @post bitacora.size() == tamanio anterior + 1
     */
    public void registrarEvento(String mensaje, String tipo) {
        bitacora.add(new Evento(mensaje, tipo));
        notificarObservadores();
    }

    public ArrayList<Evento> getBitacora(){
    	return bitacora;
    }
    
    // RECURSOS

    /**
     * @post !requiereMantenimiento() && getRecursos().getDesgaste() == 0
     */
    public void realizarMantenimiento() {
        reiniciarDesgaste();
    }

    public int getCombustible() {
        return nave.getCombustible();
    }

    public int getEnergia() {
        return nave.getEnergia();
    }

    public int getDesgaste() {
        return nave.getDesgaste();
    }

    
    
    // REPONER RECURSOS
    
    /**
     * @pre  cantidad >= 0
     * @param cantidad de combustible a reponer
     * @post el combustible queda agregado en cantidad, o en su valor máximo
     */
    public void cargarCombustible(int cantidad) {
    	nave.cargarCombustible(cantidad);
    }

    /**
     * @pre  cantidad >= 0
     * @param cantidad de energía a reponer
     * @post la energía queda agregada en cantidad, o en su valor máximo
     */
    public void cargarEnergia(int cantidad) {
    	nave.cargarEnergia(cantidad);
    }

    /**
     * @pre  cantidad >= 0
     * @param cantidad de desgaste a quitar
     * @post el desgaste queda disminuido en cantidad, o en su valor mínimo
     */
    public void quitarDesgaste(int cantidad) {
    	nave.quitarDesgaste(cantidad);
    }

    
    
    // USO DE RECURSOS
    
    /**
     * @pre  cantidad >= 0
     * @param cantidad de combustible a usar
     * @post el combustible queda disminuido en cantidad, o en su valor mínimo
     */
    public void usarCombustible(int cantidad) {
    	nave.usarCombustible(cantidad);
    }
    
    /**
     * @pre  cantidad >= 0
     * @param cantidad de energia a usar
     * @post la energía queda disminuida en cantidad, o en su valor mínimo
     */
    public void usarEnergia(int cantidad) {
    	nave.usarEnergia(cantidad);
    }
        
    /**
     * @pre cantidad >= 0
     * @param cantidad de desgaste a generar
     * @post el desgaste queda agregado en cantidad, o en su valor máximo
     */
    public void generarDesgaste(int cantidad) {
    	nave.generarDesgaste(cantidad);
    }

    
    /**
     * Reinicia el combustible.
     * @post getcombustible() == 100
     */
    public void reiniciarCombustible() {
    	nave.reiniciarCombustible();
    }
    
    /**
     * Reinicia la energía.
     * @post getEnergia() == 100
     */
    public void reiniciarEnergia() {
    	nave.reiniciarEnergia();
    }
    
    /**
     * Reinicia el desgaste (mantenimiento).
     * @post getDesgaste() == 0
     */
    public void reiniciarDesgaste() {
    	nave.reiniciarDesgaste();
    }
    
    
    public MotorWarp getMotorWarp() {
        return nave.getMotorWarp();
    }
    
    
    
    /* yo esto lo sacaría ~ Mauro
    /**
     * @pre  combustible >= 0 && cantidad >= 0 && desgaste >= 0
     * @pre  alcanzaPara(combustible, cantidad, desgaste)
     * @post se descontaron combustible y energia, y se sumo el desgaste
     */
    /*
    public void consumirRecursos(int combustible, int cantidad, int desgaste) {
        Recursos recursos = getRecursos();
        recursos.gastarRecursos(combustible, cantidad, desgaste);
        if(recursos.getCombustible() <= 0 || recursos.getEnergia() <= 0 || recursos.getDesgaste() >= 100) {
            destruirNave(); // Destruyo la nave
        }
    }
    */

    /* yo esto lo sacaría ~ Mauro
    /**
     * @pre  combustible >= 0 && energia >= 0 && desgaste >= 0
     * @post no modifica el estado de la nave
     */

    /**
     * @post true si la nave no podría empezar ni siquiera la misión más barata, que es M-03 (volver a la base).
     *       Quiere decir que no puede hacer ninguna misión y quedaría varada en el espacio. No modifica la nave.
     */
    public boolean estaVarada() {
        Mision03 retorno = new Mision03(this);
        return !alcanzaPara(retorno.getCostoCombustible(), retorno.getCostoEnergia(), retorno.getCostoDesgaste());
    }

    public boolean alcanzaPara(int combustible, int energia, int desgaste) {
        return nave.alcanzaPara(combustible,energia,desgaste);
    }

    public ArrayList<Posicion> calcularRuta(Posicion destino) {
        ArrayList<Posicion> ruta = new ArrayList<>();
        Posicion actual = tablero.getPosicionNave();
        int x = actual.getX();
        int y = actual.getY();
        while (x != destino.getX() || y != destino.getY()) {
            x += Integer.signum(destino.getX() - x);   // -1, 0 o +1
            y += Integer.signum(destino.getY() - y);   // Integer.signum devuelve -1, 0 o 1 según el signo del número.
            ruta.add(new Posicion(x, y)); // Por eso la nave avanza en diagonal mientras le falte distancia en las dos direcciones, y en línea recta cuando solo le falta en una.
        }
        return ruta;
    }

    /** Proporción de casillas que se llenan con elementos del universo (20%). */
    private static final double PORCENTAJE_OCUPACION = 0.20; // desde acá se cambia la proporción de elementosDelUniverso

    /**
     * Arma el tablero y su destino. Cantidad de elementos = 20% de las casillas (10x15 = 150 casillas -> 60 elementos),
     * cada uno elegido al azar entre Asteroide, Estación de carga y Nave enemiga; las naves enemigas son a su vez
     * Exploradora, Carguera o de Combate al azar. Como 20% < 75% (límite del Tablero), cualquier tamaño válido
     * entra sin lanzar CantidadDeElementosSuperaCasillaException.
     */
    public void setTableroyDestino(int dimension_x, int dimension_y) throws CantidadDeElementosSuperaCasillaException, DimensionesDeTableroInconsistentesException {
        ArrayList<ElementoDelUniverso> elementos = new ArrayList<>();
        if (dimension_x >= 5 && dimension_y >= 5 && dimension_x <= 100 && dimension_y <= 100) {   // fuera de rango: el Tablero lanza su excepción
            int cantidad = (int) Math.round(dimension_x * dimension_y * PORCENTAJE_OCUPACION);
            java.util.Random azar = new java.util.Random();
            for (int i = 0; i < cantidad; i++) {
                switch (azar.nextInt(3)) {
                    case 0:  elementos.add(new Asteroide()); break;
                    case 1:  elementos.add(new EstacionesDeCarga()); break;
                    default: elementos.add(nuevaNaveEnemiga(azar)); break;
                }
            }
        }

        tablero = new Tablero(dimension_x, dimension_y, elementos);   // filas, columnas, lista
        destino = new Destino(tablero);
    }

    private ElementoDelUniverso nuevaNaveEnemiga(java.util.Random azar) {
        switch (azar.nextInt(3)) {
            case 0:  return new NaveEnemigaExploradora();
            case 1:  return new NaveEnemigaCarguera();
            default: return new NaveEnemigaCombate();
        }
    }

    public Posicion getBase(){
        return base;
    }

    public void setBase(){
        base = tablero.getPosicionNave();
    }

    public Tablero getTablero() {
        return tablero;
    }

    public Destino getDestino(){
        return destino;
    }

    public int getGemas(){
        return nave.getGemas();
    }

    public void cargarGemas(int cantidad){
        nave.cargarGemas(cantidad);
    }

    public boolean estaVivaNave(){
        return nave.estaViva();
    }

    public void destruirNave(){
        destruirNave("causa no especificada");
    }

    /**
     * Destruye la nave y lo deja escrito en la bitácora, con la causa. Si ya estaba destruida no hace nada,
     * así el aviso aparece una sola vez.
     */
    public void destruirNave(String causa){
        if (!nave.estaViva()) {
            return;
        }
        registrarEvento("¡La nave ha sido destruida! Causa: " + causa + ".", "Destrucción");
        nave.destruir();
    }

}