package tpgProgra2026;

public interface EstadoWarp {
	
	// transiciones
	public void prepararSalto();
	public void pasarAWarp();
	public void enfriar();
	public void dejarDisponible();
	public void abortar(); // implementamos? cómo?
}
