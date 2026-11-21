package tpgProgra2026.tablero;

import java.util.List;

import tpgProgra2026.TableroDestino.Observador;
import universoParalelo.GeneradoraDeEnergia;
import universoParalelo.NaveConsumidora;
import universoParalelo.PlataformaDeCarga;

/**
 * Modelo del Visor Recóndito: la plataforma de carga y los hilos productores/consumidores.
 * Los eventos llegan a la vista por Observer: la plataforma es el Observable.
 */
public class ModeloUniverso {

    public static final int CAPACIDAD_PLATAFORMA = 5;

    private final PlataformaDeCarga plataforma = new PlataformaDeCarga(CAPACIDAD_PLATAFORMA);
    private int naves = 0;
    private int generadoras = 0;

    /** @post un hilo consumidor nuevo queda corriendo y se anuncia el ingreso. */
    public void agregarNaveConsumidora() {
        naves++;
        String nombre = "Nave Desconocida " + naves;
        plataforma.registrar("¡Ingresó una nueva Nave (" + nombre + ") al universo paralelo!");
        new NaveConsumidora(nombre, plataforma).start();
    }

    /** @post un hilo productor nuevo queda corriendo y se anuncia el ingreso. */
    public void agregarGeneradora() {
        generadoras++;
        String nombre = "Generadora " + generadoras;
        plataforma.registrar("¡Ingresó una nueva Generadora (" + nombre + ") al universo paralelo!");
        new GeneradoraDeEnergia(nombre, plataforma).start();
    }

    public void agregarObservador(Observador observador) {
        plataforma.agregarObservador(observador);
    }

    public List<String> getEventos() {
        return plataforma.getEventos();
    }

    public int getBaterias() {
        return plataforma.getBaterias();
    }

    public int getCapacidad() {
        return plataforma.getCapacidad();
    }

    public int getCantidadNaves() {
        return naves;
    }

    public int getCantidadGeneradoras() {
        return generadoras;
    }
}
