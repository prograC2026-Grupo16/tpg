package navesPackage;
import tpgProgra2026.Recursos;

public class Nave_Carguero extends Nave{
	private static final int COMBUSTIBLE_INICIAL = 100, ENERGIA_INICIAL = 60, DESGASTE_INICIAL = 0;
    
	public Nave_Carguero(String id, String nombre) {
        super(id, nombre, new Recursos(COMBUSTIBLE_INICIAL, ENERGIA_INICIAL, DESGASTE_INICIAL));
    }
    
    
}
