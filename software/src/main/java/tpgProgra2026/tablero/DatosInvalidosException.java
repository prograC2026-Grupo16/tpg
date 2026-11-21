package tpgProgra2026.tablero;

/** Un dato cargado en la vista no es válido (nombre vacío, antigüedad negativa, cargo u origen desconocido, etc.). */
public class DatosInvalidosException extends SimulacionException {
    private static final long serialVersionUID = 1L;
    private final String campo;

    /**
     * @param campo   dato inválido (por ejemplo "nombre" o "antigüedad")
     * @param detalle qué está mal, en una frase para mostrarle al usuario
     */
    public DatosInvalidosException(String campo, String detalle) {
        super(detalle);
        this.campo = campo;
    }

    public String getCampo() {
        return campo;
    }
}
