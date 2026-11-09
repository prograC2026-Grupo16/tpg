package tpgProgra2026;


public abstract class NaveEnemiga {
	public NaveEnemiga() { }
	
	public abstract void interactuar(Asistente ac);
	
	public String getTipoElemento() {
		return "Nave Enemiga";
	}

}
