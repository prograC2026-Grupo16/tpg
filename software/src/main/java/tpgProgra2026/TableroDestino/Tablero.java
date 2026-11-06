package tpgProgra2026.TableroDestino;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

/**
 * Administra una grilla rectangular, sus elementos, la nave y el objetivo.
 * La coordenada x identifica la fila y la coordenada y la columna. Ambas
 * comienzan en 0. Cada casilla admite un elemento.
 */
public class Tablero extends Observable {
    /** Cantidad de filas del tablero. */
    private final int x;
    /** Cantidad de columnas del tablero. */
    private final int y;
    /** Copia de las entradas originales que se redistribuyen. */
    private final ArrayList<ElementoDelUniverso> elementos;
    /** Asociación entre cada casilla ocupada y su elemento. */
    private final HashMap<Posicion, ElementoDelUniverso> ocupacion = new HashMap<Posicion, ElementoDelUniverso>();
    /** Generador para distribuir elementos y elegir objetivos. */
    private final Random azar = new Random();
    /** Ubicación actual de la nave. */
    private Posicion posicionNave;
    /** Objetivo actual, o nulo si todavía no se eligió. */
    private Posicion objetivo;

    /**
     * Crea el tablero y distribuye aleatoriamente los elementos recibidos.
     * <p><b>Precondiciones:</b> la lista no es nula y contiene los elementos
     * que se desean distribuir.</p>
     * <p><b>Postcondiciones:</b> conserva una copia de la lista. Cada entrada
     * ocupa una casilla distinta dentro del tablero. La nave comienza en el
     * centro del tablero y el objetivo comienza en {@code null}.</p>
     * @param x cantidad de filas, entre 5 y 100 inclusive
     * @param y cantidad de columnas, entre 5 y 100 inclusive
     * @param elementos elementos que se ubicarán en el tablero
     * @throws DimensionesDeTableroInconsistentesException si una dimensión está fuera del rango
     * @throws CantidadDeElementosSuperaCasillaException si la cantidad supera
     *         {@code x * y / 4 * 3}, con división entera
     */
    public Tablero(int x, int y, ArrayList<ElementoDelUniverso> elementos) throws DimensionesDeTableroInconsistentesException, CantidadDeElementosSuperaCasillaException {
        if (x < 5 || y < 5) {
            throw new DimensionesDeTableroInconsistentesException("tamaño mínimo 5x5");
        }
        if (x > 100 || y > 100) {
            throw new DimensionesDeTableroInconsistentesException("tamaño máximo 100x100");
        }
        int limite = (x * y / 4) * 3;
        if (elementos.size() > limite) {
            throw new CantidadDeElementosSuperaCasillaException("La cantidad de elementos : " + elementos.size() + ", supera al tamaño permitido del tablero: " + limite, limite);
        }
        this.x = x;
        this.y = y;
        this.elementos = new ArrayList<ElementoDelUniverso>(elementos);
        this.posicionNave = new Posicion(x / 2, y / 2);
        distribuirObjetos();
    }

    /**
     * Consulta la cantidad de filas.
     * @return cantidad de filas (tamaño del eje x)
     */
    public int getX() {
        return x;
    }

    /**
     * Consulta la cantidad de columnas.
     * @return cantidad de columnas (tamaño del eje y)
     */
    public int getY() {
        return y;
    }

    /**
     * Consulta la posición actual de la nave.
     * <p><b>Precondiciones:</b> ninguna.</p>
     * <p><b>Postcondiciones:</b> el estado del tablero permanece igual.</p>
     * @return posición actual de la nave
     */
    public Posicion getPosicionNave() {
        return posicionNave;
    }

    /**
     * Redistribuye todos los elementos de la lista original, descartando la
     * ocupación anterior. Parte de coordenadas aleatorias y resuelve colisiones
     * mediante búsqueda en anchura.
     * <p><b>Precondiciones:</b> se mantienen las dimensiones y el límite de
     * elementos establecidos al construir el tablero.</p>
     * <p><b>Postcondiciones:</b> cada entrada de la lista ocupa una casilla
     * distinta dentro del tablero. El objetivo sigue libre. La nave y el
     * objetivo conservan sus posiciones. Este método no notifica a los
     * observadores.</p>
     */
    private void distribuirObjetos() {
        ocupacion.clear();
        for (ElementoDelUniverso elemento : elementos) {
            Posicion posicion = buscarLibre(new Posicion(azar.nextInt(x), azar.nextInt(y)));
            ocupacion.put(posicion, elemento);
        }
    }

    /**
     * Busca por BFS una casilla disponible a la menor cantidad de pasos desde
     * la posición inicial, considerando movimientos en ocho direcciones.
     * El orden de expansión de las vecinas se mezcla aleatoriamente.
     * <p><b>Precondiciones:</b> la posición inicial no es nula y está dentro
     * del tablero.</p>
     * <p><b>Postcondiciones:</b> devuelve una casilla sin elemento y distinta
     * del objetivo. No altera la ocupación, la nave ni el objetivo.
     * Los empates no se resuelven necesariamente con probabilidad uniforme.</p>
     * @param actual posición inicial de la búsqueda
     * @return posición disponible más cercana, o la inicial si ya está disponible
     * @throws IllegalStateException si no existe ninguna casilla disponible
     * @throws NullPointerException si la posición inicial es nula
     */
    private Posicion buscarLibre(Posicion actual) {
        if (estaLibre(actual)) {
            return actual;
        }
        Queue<Posicion> cola = new ArrayDeque<Posicion>();
        Set<Posicion> visitadas = new HashSet<Posicion>();
        cola.add(actual);
        visitadas.add(actual);
        while (!cola.isEmpty()) {
            Posicion nodo = cola.poll();
            if (estaLibre(nodo)) {
                return nodo;
            }
            ArrayList<Posicion> vecinas = vecinasDe(nodo);
            Collections.shuffle(vecinas, azar);
            for (Posicion vecina : vecinas) {
                if (visitadas.add(vecina)) {
                    cola.add(vecina);
                }
            }
        }
        throw new IllegalStateException("No hay casillas libres para distribuir los elementos");
    }

    /**
     * Construye la lista de vecinas horizontales, verticales y diagonales válidas.
     * <p><b>Precondiciones:</b> la posición no es nula y pertenece al tablero.</p>
     * <p><b>Postcondiciones:</b> devuelve una lista nueva de hasta ocho posiciones,
     * sin incluir el centro ni coordenadas fuera del tablero. No modifica el estado.</p>
     * @param posicion centro del vecindario
     * @return posiciones vecinas dentro de los límites
     * @throws NullPointerException si la posición es nula
     */
    private ArrayList<Posicion> vecinasDe(Posicion posicion) {
        ArrayList<Posicion> vecinas = new ArrayList<Posicion>();
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx != 0 || dy != 0) {
                    Posicion vecina = new Posicion(posicion.getX() + dx,
                            posicion.getY() + dy);
                    if (estaDentro(vecina)) {
                        vecinas.add(vecina);
                    }
                }
            }
        }
        return vecinas;
    }

    /**
     * Comprueba si unas coordenadas pertenecen al tablero.
     * <p><b>Precondiciones:</b> la posición no es nula.</p>
     * @param posicion coordenadas por comprobar
     * @return {@code true} si {@code 0 <= x < cantidad de filas} y
     *         {@code 0 <= y < cantidad de columnas}
     * @throws NullPointerException si la posición es nula
     */
    protected boolean estaDentro(Posicion posicion) {
        return posicion.getX() >= 0 && posicion.getX() < x && posicion.getY() >= 0 && posicion.getY() < y;
    }

    /**
     * Comprueba si una casilla puede recibir un elemento.
     * <p><b>Precondiciones:</b> la posición no es nula y está dentro del tablero.
     * Este método no comprueba los límites.</p>
     * <p><b>Postcondiciones:</b> el estado permanece igual.</p>
     * @param posicion casilla por consultar
     * @return {@code true} si no hay elemento y no coincide con el objetivo
     */
    private boolean estaLibre(Posicion posicion) {
        return !ocupacion.containsKey(posicion) && !posicion.equals(objetivo);
    }

    /**
     * Enumera las casillas que no contienen elementos.
     * <p><b>Precondiciones:</b> ninguna.</p>
     * <p><b>Postcondiciones:</b> devuelve una lista nueva, ordenada por fila
     * y luego por columna. Puede incluir el objetivo. No modifica el estado.</p>
     * @return todas las casillas sin elementos, o una lista vacía si no hay ninguna
     */
    private ArrayList<Posicion> casillasLibres() {
      ArrayList<Posicion> libres = new ArrayList<Posicion>();
       for (int fila = 0; fila < x; fila++) {
           for (int columna = 0; columna < y; columna++) {
               Posicion posicion = new Posicion(fila, columna);
               if (!ocupacion.containsKey(posicion)) {
                   libres.add(posicion);
               }
           }
       }
       return libres;
   }
 
   /**
     * Obtiene una copia de la ocupación actual.
     * <p><b>Precondiciones:</b> ninguna.</p>
     * <p><b>Postcondiciones:</b> modificar las entradas del mapa devuelto no altera
     * el tablero. Las posiciones y los elementos se comparten por referencia.
     * No se clonan. La nave y el objetivo no se agregan al mapa.</p>
     * @return copia del mapa de posiciones ocupadas y sus elementos
     */
    protected HashMap<Posicion, ElementoDelUniverso> obtenerElementos() {
        return new HashMap<Posicion, ElementoDelUniverso>(ocupacion);
    }

    /**
     * Actualiza la posición de la nave y consulta el elemento de esa casilla.
     * <p><b>Precondiciones:</b> la posición no es nula y está dentro del tablero.
     * el llamador garantiza estas condiciones, que no se validan.</p>
     * <p><b>Postcondiciones:</b> la nave queda en la posición recibida, incluso
     * si contiene un elemento. La ocupación y el objetivo permanecen iguales.
     * al terminar, notifica a los observadores registrados en este tablero.</p>
     * @param posicion nueva ubicación de la nave
     */
    public void moverNave(Posicion posicion) {
        posicionNave = posicion;
        notificarObservadores();
    } 

    /**
     * Consulta el elemento ubicado en la posición actual de la nave.
     * <p><b>Postcondiciones:</b> no modifica el tablero ni la posición de la
     * nave.</p>
     * @return elemento en la posición de la nave, o {@code null} si está vacía
     */
    public ElementoDelUniverso inspeccionarPosicion() {
        return ocupacion.get(posicionNave);
    }

    /**
     * Devuelve los elementos del vecindario de 3 por 3 centrado en la nave.
     * Las filas y columnas de la matriz avanzan en el mismo sentido que las
     * coordenadas del tablero. Las casillas vacías y las que quedan fuera del
     * tablero se representan con {@code null}.
     * <p><b>Postcondiciones:</b> devuelve una matriz nueva de tamaño 3 por 3,
     * con la ocupación observada al momento de la llamada. Modificar la matriz
     * no modifica el tablero. Los elementos se comparten por referencia.</p>
     * @return matriz del vecindario, con la posición de la nave en [1][1]
     */
    public ElementoDelUniverso[][] observarAlrededores() {
        ElementoDelUniverso[][] alrededores = new ElementoDelUniverso[3][3];
        for (int fila = -1; fila <= 1; fila++) {
            for (int columna = -1; columna <= 1; columna++) {
                Posicion posicion = new Posicion(posicionNave.getX() + fila,
                        posicionNave.getY() + columna);
                if (estaDentro(posicion)) {
                    alrededores[fila + 1][columna + 1] = ocupacion.get(posicion);
                }
            }
        }
        return alrededores;
    }

    

    /**
     * Consulta el objetivo actual sin volver a elegirlo.
     * <p><b>Precondiciones:</b> ninguna.</p>
     * <p><b>Postcondiciones:</b> el estado permanece igual.</p>
     * @return objetivo actual, o {@code null} si todavía no se eligió uno
     */
    protected Posicion getObjetivo() {
        return objetivo;
    }

    /**
     * Elige y guarda un objetivo aleatorio entre las casillas sin elementos.
     * <p><b>Precondiciones:</b> existe al menos una casilla sin elementos,
     * garantizada por el límite de ocupación bajo el uso previsto del tablero.
     * La posición de referencia es la posición actual de la nave.</p>
     * <p><b>Postcondiciones:</b> el objetivo guardado coincide con el resultado.
     * Puede ser igual al anterior. No cambia la ocupación ni la nave.</p>
     * @return nueva posición del objetivo
     */
    public Posicion obtenerObjetivo() {
        Posicion referencia = posicionNave;
        Posicion libre = null;
        ArrayList<Posicion> libres = casillasLibres();
        Collections.shuffle(libres, azar);
        int i = 0;
        while(i < libres.size() && libre == null){
            Posicion posicion = libres.get(i);
            int distancia = Math.max(Math.abs(posicion.getX() - referencia.getX()), Math.abs(posicion.getY() - referencia.getY()));
            if (distancia >= 2) {
                libre = posicion;
            }
            i++;
        }
        if (libre == null){
            libre = libres.get(azar.nextInt(libres.size()));
        }
        this.objetivo = libre;
        notificarObservadores();
        return libre;
    }

    /**
     * Traslada un elemento entre dos posiciones.
     * <p><b>Precondiciones:</b> el origen está ocupado. El destino no es nulo,
     * está dentro del tablero, está libre y no coincide con el objetivo.
     * El llamador garantiza estas condiciones, que no se validan.</p>
     * <p><b>Postcondiciones:</b> el origen queda libre y el mismo elemento queda
     * asociado al destino. Se conserva la cantidad de elementos y no cambian
     * la nave ni el objetivo.</p>
     * @param posActual posición ocupada por el elemento
     * @param posNueva destino del elemento
     */
    protected void moverElemento(Posicion posActual, Posicion posNueva) {
        ElementoDelUniverso elemento = ocupacion.remove(posActual);
        ocupacion.put(posNueva, elemento);
    }

    /**
     * Notifica a los observadores registrados que el estado del tablero se
     * actualizó.
     * <p><b>Precondiciones:</b> ninguna.</p>
     * <p><b>Postcondiciones:</b> marca el tablero como modificado y notifica a
     * sus observadores. No altera dimensiones, ocupación, nave ni objetivo.</p>
     * <p><b>Excepciones declaradas:</b> ninguna.</p>
     */
    protected void notificarActualizacion(){
        notificarObservadores();
    }


    /**
     * Devuelve una representación matricial de los elementos ocupantes del
     * tablero. La primera dimensión corresponde a las filas y la segunda a las
     * columnas. Las casillas vacías se representan con {@code null}.
     * <p><b>Precondiciones:</b> ninguna.</p>
     * <p><b>Postcondiciones:</b> devuelve una matriz nueva de tamaño
     * {@code x} por {@code y}, donde el primer índice es la fila y el segundo
     * la columna, con la ocupación observada al momento de la llamada.
     * Modificar la matriz no modifica el tablero. Los elementos
     * contenidos se comparten por referencia. No incluye la nave ni el
     * objetivo.</p>
     * <p><b>Excepciones declaradas:</b> ninguna.</p>
     * @return matriz de elementos indexada como {@code matriz[fila][columna]},
     *         con {@code null} en las casillas vacías
     */
    public ElementoDelUniverso[][] verTablero() {
        ElementoDelUniverso[][] tablero = new ElementoDelUniverso[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                Posicion posicion = new Posicion(i, j);
                tablero[i][j] = ocupacion.get(posicion);
            }
        }
        return tablero;
    }

}
