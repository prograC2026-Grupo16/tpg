package tpgProgra2026.tablero;

/** Base de los errores que el modelo de la simulación le informa al controlador. */
public class SimulacionException extends Exception {
    private static final long serialVersionUID = 1L;

    public SimulacionException(String mensaje) {
        super(mensaje);
    }
}
