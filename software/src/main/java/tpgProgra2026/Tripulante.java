
package tpgProgra2026;


public abstract class Tripulante {
    private static int contador = 0;
    private int id;
    private int antiguedad;
    /*
     * Crea un tripulante con un id asignado automáticamente.
     *
     * @pre  antiguedad >= 0
     * @post getAntiguedad() == antiguedad
     * @post getId() > 0 y es distinto al id de cualquier otro tripulante creado
    */
    public Tripulante( int antiguedad){
        this.antiguedad =   antiguedad;
        this.id         =   ++contador;
    }
    // @post retorna un valor > 0 
    public int getId() {
        return id;
    }
    // @post retorna un valor >= 0
    public int getAntiguedad() {
        return antiguedad;
    }
    // @post retorna un String no nulo 
    public abstract String getCargo();
    // @post retorna un valor >= 0
    public abstract double getSueldo();
    //@post retorna un String no nulo@post retorna un String no nulo
     public abstract String descripcion();
    
}
