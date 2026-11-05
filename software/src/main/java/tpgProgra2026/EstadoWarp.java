package tpgProgra2026;

public interface EstadoWarp {
	
	// transiciones
	public void prepararSalto();
	public void pasarAWarp();
	public void enfriar();
	public void dejarDisponible();
//	public void abortar(); // en esta primera parte no se implementa

	public boolean estaSaltoWarpDisponible();
	
	// el salto warp gasta:
	// 5 unidades de energía
	// el enfríamiento genera 3 puntos de desgaste
	
	// el salto normal consume 2 puntos de combustible
	
	// estos gastos se gestionan desde el motor??
	// porque el motor no conoce a su nave
	// yo diría que lo gestione la propia nave
	// para eso tiene que recibir información de si se produjo un salto warp o no, y otros
	
	// ~ Mauro
}
