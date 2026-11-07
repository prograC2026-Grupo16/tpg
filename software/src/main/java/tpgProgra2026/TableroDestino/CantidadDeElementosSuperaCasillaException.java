package tpgProgra2026.TableroDestino;

/** Indica que la cantidad de elementos supera la capacidad permitida del tablero. */
public class CantidadDeElementosSuperaCasillaException extends Exception {
    /** Identificador de versión para la serialización. */
    private static final long serialVersionUID = 1L;
    /** Capacidad máxima de elementos del tablero que originó la excepción. */
    private final int cantidadMaxima;

    /**
     * Crea la excepción con el mensaje y la capacidad máxima del tablero.
     * <p><b>Precondiciones:</b> ninguna. El mensaje puede ser nulo.</p>
     * <p><b>Postcondiciones:</b> {@link #getMessage()} devuelve el mensaje
     * recibido y {@link #getCantidadMaxima()} devuelve la cantidad máxima
     * recibida.</p>
     * @param mensaje descripción del motivo de la excepción
     * @param cantidadMaxima capacidad máxima de elementos permitida
     */
    public CantidadDeElementosSuperaCasillaException(String mensaje, int cantidadMaxima) {
        super(mensaje);
        this.cantidadMaxima = cantidadMaxima;
    }

    /**
     * Consulta la capacidad máxima de elementos que se permitió en el tablero.
     * <p><b>Precondiciones:</b> ninguna.</p>
     * <p><b>Postcondiciones:</b> la excepción permanece sin cambios.</p>
     * @return cantidad máxima de elementos permitida
     */
    public int getCantidadMaxima() {
        return cantidadMaxima;
    }
}
