package navesPackage;

public abstract class Fabricacion_Nave {

    /**
     * @pre  id != null && nombre != null
     * @post el resultado != null
     * @throws TipoNaveInvalidoException si tipo no es "Exploradora", "Carguero" o "Combate"
     */
    public static Nave construirNave(String id, String nombre, String tipo) throws TipoNaveInvalidoException {

    	if (tipo == null) throw new TipoNaveInvalidoException(tipo);
    	
        if (tipo.toLowerCase().equals("exploradora"))
            return new Nave_Exploradora(id, nombre);

        if (tipo.toLowerCase().equals("carguero"))
            return new Nave_Carguero(id, nombre);

        if (tipo.toLowerCase().equals("combate"))
            return new Nave_Combate(id, nombre);

        throw new TipoNaveInvalidoException(tipo);
    }
}