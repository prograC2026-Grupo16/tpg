package navesPackage;
import tpgProgra2026.Recursos;

public class Nave_Exploradora extends Nave {
	private static final int COMBUSTIBLE_INICIAL = 60, ENERGIA_INICIAL = 80, DESGASTE_INICIAL = 0;
	
    public Nave_Exploradora(String id, String nombre) {
        super(id, nombre, new Recursos(COMBUSTIBLE_INICIAL, ENERGIA_INICIAL, DESGASTE_INICIAL));
    }
 
    
    // DOUBLE DISPATCH    
    public int esVencidoPorExploradora() { return 0; }
    public int esVencidoPorCarguera() { return -1;	}
    public int esVencidoPorCombate() { return 1; }
    
}