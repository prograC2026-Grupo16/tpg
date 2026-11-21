package tpgProgra2026.tablero;

/** Se intentó asignar un segundo Capitán: la nave admite como máximo uno. */
public class CapitanDuplicadoException extends SimulacionException {
    private static final long serialVersionUID = 1L;

    public CapitanDuplicadoException() {
        super("La nave ya tiene Capitán: se admite como máximo uno.");
    }
}
