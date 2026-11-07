package tpgProgra2026.TableroDestino;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

/** Coordina los intentos de movimiento aleatorio de los elementos del tablero. */
public class Destino {
    private final Random azar = new Random();
    private Tablero tablero;

    /**
     * Crea un coordinador de turnos que opera sobre el tablero recibido.
     * <p><b>Precondiciones:</b> el tablero no es nulo.</p>
     * <p><b>Postcondiciones:</b> conserva la referencia al tablero y crea un
     * generador aleatorio para seleccionar y mover elementos. No modifica el
     * tablero.</p>
     * <p><b>Excepciones declaradas:</b> ninguna.</p>
     * @param tablero tablero cuyos elementos coordinará
     */
    public Destino(Tablero tablero){
        this.tablero = tablero;
    }
    /**
     * Selecciona sin repetir la mitad entera de los elementos y realiza un intento
     * de movimiento a una de sus ocho vecinas por cada seleccionado.
     * Los intentos se procesan secuencialmente y observan los cambios anteriores.
     * <p><b>Precondiciones:</b> tablero no es nulo.</p>
     * <p><b>Postcondiciones:</b> cada seleccionado se mueve a lo sumo una vez.
     * Los intentos fuera de límites, hacia un elemento o hacia el objetivo se
     * descartan. Se conservan los elementos, la nave y el objetivo actual.
     * Al terminar, notifica a los observadores del tablero, incluso si ningún
     * elemento se movió.</p>
     */
    public void turnoDestino() {
        HashMap<Posicion, ElementoDelUniverso> elementos = tablero.obtenerElementos();
        ArrayList<Posicion> seleccion = new ArrayList<Posicion>(elementos.keySet());
        Collections.shuffle(seleccion, azar);
        Posicion objetivo = tablero.getObjetivo();
        int cantidad = seleccion.size() / 2;
        for (int i = 0; i < cantidad; i++) {
            Posicion actual = seleccion.get(i);
            int direccion = azar.nextInt(8);
            int indice = direccion < 4 ? direccion : direccion + 1;
            Posicion nueva = new Posicion(actual.getX() + indice / 3 - 1,
                    actual.getY() + indice % 3 - 1);
            if (tablero.estaDentro(nueva) && !elementos.containsKey(nueva) && !nueva.equals(objetivo)) {
                ElementoDelUniverso elemento = elementos.remove(actual);
                elementos.put(nueva, elemento);
                tablero.moverElemento(actual, nueva);
            }
        }
        tablero.notificarActualizacion();
    }
}
