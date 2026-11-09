package tpgProgra2026;
import java.util.ArrayList;
import navesPackage.Nave;
import misionesPackage.Mision;
import motorPackage.MotorWarp;
import tpgProgra2026.TableroDestino.*;

/**
 * @invariante nave != null && misiones != null && bitacora != null
 */
public class Asistente {
    private static final int LIMITE_DESGASTE = 80;

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
    public Asistente(Nave nave, Mision mision) {
        this.nave = nave;
        this.mision = mision;
    }

    /**
     * @pre  mensaje != null && tipo != null
     * @post bitacora.size() == tamanio anterior + 1
     */
    public void registrarEvento(String mensaje, String tipo) {
        bitacora.add(new Evento(mensaje, tipo));
    }

    
    
    // RECURSOS

    
    public boolean requiereMantenimiento() {
        return nave.getDesgaste() >= LIMITE_DESGASTE;
    }

    // SACARLO ??
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
    
    
    // SACARLO ??
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

    public boolean alcanzaPara(int combustible, int energia, int desgaste) {
        return nave.getCombustible() >= combustible
            && nave.getEnergia() >= energia
            && (nave.getDesgaste() + desgaste) <= LIMITE_DESGASTE;
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

    public void setTableroyDestino(int dimension_x, int dimension_y) throws CantidadDeElementosSuperaCasillaException, DimensionesDeTableroInconsistentesException {
        ArrayList<ElementoDelUniverso> elementos = new ArrayList<>();
        elementos.add(new Asteroide());            // uno por cada cosa que quieras en el mapa
        elementos.add(new EstacionesDeCarga());
        elementos.add(new naveEnemiga()); // Todavia no implemento

        Tablero tablero = new Tablero(20, 20, elementos);   // filas, columnas, lista
        Destino destino = new Destino(tablero);
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
        nave.destruir();
    }

}