package tpgProgra2026;
import tpgProgra2026.TableroDestino.ElementoDelUniverso;

public class NaveEnemigaCombate extends NaveEnemiga implements ElementoDelUniverso {
	public NaveEnemigaCombate() { super(); }
	
	@Override
	public void interactuar(Asistente ac) {		
		if (ac.esVencidoPorCombate() == 1)
			ac.destruirNave();
		// por ahora si la nave original no pierde
		// es decir, si gana o empata
		// no hace nada
	}
	
	@Override
	public String getTipoElemento() {
		return super.getTipoElemento() + " Combate";
	}
	
}