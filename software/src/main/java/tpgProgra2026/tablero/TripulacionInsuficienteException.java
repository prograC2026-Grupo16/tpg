package tpgProgra2026.tablero;

/** Se intentó despegar sin ningún tripulante asignado a la nave. */
public class TripulacionInsuficienteException extends SimulacionException {
    private static final long serialVersionUID = 1L;

    public TripulacionInsuficienteException() {
        super("Asigná al menos un tripulante antes de despegar.");
    }
}
