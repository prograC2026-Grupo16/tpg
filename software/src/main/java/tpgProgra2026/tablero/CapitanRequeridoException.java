package tpgProgra2026.tablero;

/** Se intentó despegar con tripulantes pero sin ningún Capitán: la nave no puede partir sin uno. */
public class CapitanRequeridoException extends SimulacionException {
    private static final long serialVersionUID = 1L;

    public CapitanRequeridoException() {
        super("Hace falta un Capitán para despegar. Asignalo en Nave y tripulación.");
    }
}
