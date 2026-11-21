package tpgProgra2026.tablero;

/** Se intentó operar con la nave (asignar tripulantes, iniciar una misión) antes de crearla. */
public class NaveNoCreadaException extends SimulacionException {
    private static final long serialVersionUID = 1L;

    public NaveNoCreadaException() {
        super("Primero creá una nave en la sección Creación.");
    }
}
