package tpgProgra2026;

public interface EstadoWarp {
	
	// transiciones
	public void prepararSalto();
	public void pasarAWarp();
	public void enfriar();
	public void dejarDisponible();
//	public void abortar(); // en esta primera parte no se implementa

	public boolean estaMotorDisponible();

}
