
package tpgProgra2026.usuarios;


public class DaoException extends Exception{
    private String descripcion;
    public DaoException(String problema){
        super("Problemas a la hora de querer acceder a los datos");
        this.descripcion = descripcion;
    }
    public String getDescripcion(){
        return descripcion;
    }
}
