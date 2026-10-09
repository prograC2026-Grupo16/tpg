package tpgProgra2026;

public class Fabricacion_Nave {

    /**
     * @pre  id != null && nombre != null && motor != null
     * @post el resultado != null
     * @post resultado.getTipo().equals(tipo) && resultado.getId().equals(id)
     * @throws TipoNaveInvalidoException si tipo no es "Exploradora", "Carguero" o "Combate"
     */
    public Nave construirNave(String id, String nombre, String tipo, MotorWarp motor) throws TipoNaveInvalidoException {

        if (tipo.equals("Exploradora"))
            return new Nave_Exploradora(id, nombre, tipo, motor, 60, 80);

        if (tipo.equals("Carguero"))
            return new Nave_Carguero(id, nombre, tipo, motor, 100, 60);

        if (tipo.equals("Combate"))
            return new Nave_Combate(id, nombre, tipo, motor, 80, 100);

        throw new TipoNaveInvalidoException(tipo);
    }
}