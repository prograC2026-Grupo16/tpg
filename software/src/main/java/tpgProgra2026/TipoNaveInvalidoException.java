package tpgProgra2026;

public class TipoNaveInvalidoException extends Exception {

    public TipoNaveInvalidoException(String tipo) {
        super("Tipo de nave invalido: " + tipo);
    }
}