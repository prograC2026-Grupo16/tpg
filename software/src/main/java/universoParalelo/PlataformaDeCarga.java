package universoParalelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tpgProgra2026.TableroDestino.Observable;

/**
 * Recurso compartido (monitor) del universo paralelo.
 * Capacidad limitada y acceso exclusivo: un solo hilo opera a la vez.
 *
 * @invariante 0 <= baterias && baterias <= capacidad
 */
public class PlataformaDeCarga extends Observable {

    private final int capacidad;
    private int baterias = 0;

    /**
     * @pre  capacidad > 0
     * @post baterias == 0
     */
    public PlataformaDeCarga(int capacidad) {
        this.capacidad = capacidad;
    }

    /**
     * Deposita una batería. Si la plataforma está llena, espera.
     * @pre  nombreGeneradora != null
     * @post baterias == cantidad anterior + 1
     */
    private static final int MAX_EVENTOS = 300; // la consola solo muestra los últimos
    private final List<String> eventos = Collections.synchronizedList(new ArrayList<String>());

    /**
     * Guarda el evento y avisa a los observadores. Se puede llamar desde cualquier hilo.
     * @post el mensaje queda como último elemento de getEventos()
     */
    public void registrar(String mensaje) {
        synchronized (eventos) {
            eventos.add(mensaje);
            if (eventos.size() > MAX_EVENTOS) {
                eventos.remove(0);
            }
        }
        notificarObservadores();
    }

    /** @post devuelve una copia: se puede recorrer sin interferir con los hilos de la simulación. */
    public List<String> getEventos() {
        synchronized (eventos) {
            return new ArrayList<String>(eventos);
        }
    }

    public synchronized int getBaterias() {
        return baterias;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public synchronized void depositar(String nombreGeneradora) throws InterruptedException {
        if (baterias == capacidad)
            registrar(nombreGeneradora + " esperando a que se libere espacio en la Plataforma.");
        while (baterias == capacidad)
            wait();

        baterias++;
        registrar(nombreGeneradora + " depositó una batería. (Plataforma: " + baterias + "/" + capacidad + ")");
        notifyAll();
    }

    /**
     * Retira una batería. Si la plataforma está vacía, espera.
     * @pre  nombreNave != null
     * @post baterias == cantidad anterior - 1
     */
    public synchronized void retirar(String nombreNave) throws InterruptedException {
        if (baterias == 0)
            registrar(nombreNave + " esperando por falta de baterías en la Plataforma.");
        while (baterias == 0)
            wait();

        baterias--;
        registrar("Batería retirada y utilizada por " + nombreNave + ". (Plataforma: " + baterias + "/" + capacidad + ")");
        notifyAll();
    }
}
