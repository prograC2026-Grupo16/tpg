package tpgProgra2026;
import tpgProgra2026.TableroDestino.ElementoDelUniverso;

public class NaveEnemigaCarguera extends NaveEnemiga implements ElementoDelUniverso {
	public NaveEnemigaCarguera() { super(); }
		
	@Override
	public void interactuar(Asistente ac) {		
		if (ac.esVencidoPorCarguera() == 1)
			ac.destruirNave();
		// por ahora si la nave original no pierde
		// es decir, si gana o empata
		// no hace nada
	}
	
	@Override
	public String getTipoElemento() {
		return super.getTipoElemento() + " Carguera";
	}	
	
}