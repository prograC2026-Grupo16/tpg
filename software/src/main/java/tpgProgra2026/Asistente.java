package tpgProgra2026;
import tpgProgra2026.TableroDestino.*;

import java.util.ArrayList;

/**
 * @invariante nave != null && misiones != null && bitacora != null
 */
public class Asistente {
    private static final int LIMITE_DESGASTE = 80;

    protected Nave nave;
    protected ArrayList<Evento> bitacora = new ArrayList<>();
    protected Mision misiones;
    protected Tablero tablero;
    protected Destino destino;

    /**
     * @pre  nave != null && misiones != null
     * @post la bitacora esta vacia
     */
    public Asistente(Nave nave, Mision misiones) {
        this.nave = nave;
        this.misiones = misiones;
    }

    /**
     * @pre  mensaje != null && tipo != null
     * @post bitacora.size() == tamanio anterior + 1
     */
    public void registrarEvento(String mensaje, String tipo) {
        bitacora.add(new Evento(mensaje, tipo));
    }

    public boolean estaMotorDisponible() {
        return getMotorWarp().estaMotorDisponible();
    }

    /**
     * @pre  cantidad >= 0
     * @post getRecursos().getCombustible() == combustible anterior + cantidad
     */
    public void cargarCombustible(int cantidad) {
        getRecursos().setCombustible(cantidad);
    }

    /**
     * @pre  cantidad >= 0
     * @post getRecursos().getEnergia() == energia anterior + cantidad
     */
    public void cargarEnergia(int cantidad) {
        getRecursos().setEnergia(cantidad);
    }

    /**
     * @pre  combustible >= 0 && cantidad >= 0 && desgaste >= 0
     * @pre  alcanzaPara(combustible, cantidad, desgaste)
     * @post se descontaron combustible y energia, y se sumo el desgaste
     */
    public void consumirRecursos(int combustible, int cantidad, int desgaste) {
        Recursos recursos = getRecursos();
        recursos.gastarRecursos(combustible, cantidad, desgaste);
        if(recursos.getCombustible() <= 0 || recursos.getEnergia() <= 0 || recursos.getDesgaste() >= 100) {
            destruirNave(); // Destruyo la nave
        }
    }

    /**
     * @pre  combustible >= 0 && energia >= 0 && desgaste >= 0
     * @post no modifica el estado de la nave
     */
    public boolean alcanzaPara(int combustible, int energia, int desgaste) {
        return getRecursos().getCombustible() >= combustible
            && getRecursos().getEnergia() >= energia
            && (getRecursos().getDesgaste() + desgaste) <= LIMITE_DESGASTE;
    }

    public boolean requiereMantenimiento() {
        return getRecursos().getDesgaste() >= LIMITE_DESGASTE;
    }

    /**
     * @post !requiereMantenimiento() && getRecursos().getDesgaste() == 0
     */
    public void realizarMantenimiento() {
        getRecursos().setDesgaste();
    }

    public boolean estaVivaNave(){
        return nave.estaViva();
    }

    public void destruirNave(){
        nave.Destruir();
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

    public Recursos getRecursos() {
        return nave.getRecursos();
    }

    public MotorWarp getMotorWarp() {
        return nave.getMotorWarp();
    }
}