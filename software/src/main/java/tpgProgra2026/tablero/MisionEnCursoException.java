package tpgProgra2026.tablero;

/** Se intentó cambiar la nave o iniciar otra misión mientras una misión todavía se está ejecutando. */
public class MisionEnCursoException extends SimulacionException {
    private static final long serialVersionUID = 1L;

    public MisionEnCursoException() {
        super("Hay una misión en curso: esperá a que termine.");
    }
}
