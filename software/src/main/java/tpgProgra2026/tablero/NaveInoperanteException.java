package tpgProgra2026.tablero;

/** Se intentó iniciar una misión con una nave destruida. */
public class NaveInoperanteException extends SimulacionException {
    private static final long serialVersionUID = 1L;

    public NaveInoperanteException() {
        super("La nave fue destruida. Creá una nueva para seguir.");
    }
}
