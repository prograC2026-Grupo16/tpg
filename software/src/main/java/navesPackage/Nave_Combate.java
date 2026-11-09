package navesPackage;

import tpgProgra2026.Recursos;

public class Nave_Combate extends Nave{
	private static final int COMBUSTIBLE_INICIAL = 80, ENERGIA_INICIAL = 100, DESGASTE_INICIAL = 0;

	public Nave_Combate(String id, String nombre) {
        super(id, nombre, new Recursos(COMBUSTIBLE_INICIAL, ENERGIA_INICIAL, DESGASTE_INICIAL));    
    }
    
    // DOUBLE DISPATCH    
    public int esVencidoPorExploradora() { return -1; }
    public int esVencidoPorCarguera() { return 1;	}
    public int esVencidoPorCombate() { return 0; }
	
}
