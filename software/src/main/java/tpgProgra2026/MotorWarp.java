package tpgProgra2026;

public class MotorWarp {
	private EstadoWarp estado;
	
	/**
	 * Invariantes:
	 * - estado != null
	 */
	
	private boolean invariantes() {
		return estado != null;
	}
	
	/**
	 * Constructor - instancia un objeto
	 * 
	 */
	public MotorWarp() {
		estado = new Disponible(this);
	}

	/**
	 * Configura el estado del motor warp
	 * @param estado Estado del motor Warp a configurar
	 * 
	 * Precondiciones:
	 * - estado != null;
	 * 
	 */
	public void setEstado(EstadoWarp estado) {
		assert estado != null : "El estado pasado como parámetro es null";
		this.estado = estado;
		assert invariantes() : "El estado de la instancia es null";
	}
	
	/**
	 * Devuelve el estado del motor warp
	 * Postcondicion:
	 * - Devuelve un estado no null estado != null
	 * @return el estado del motor warp
	 */
	public EstadoWarp getEstado() {
		return estado;
	}

	/**
	 * Precondición
	 * - this.estado != null
	 * 
	 * @return Booleano xD
	 */
	public boolean estaSaltoWarpDisponible() {
		assert estado != null;
		return estado.estaSaltoWarpDisponible();
	}

	// transiciones visibles al resto de clases
	public void saltar() {
		// no hace nada ?
	}
	public void saltarWarp() {
		if (estado.estaSaltoWarpDisponible()) {
			// hacer salto
			// descontar recursos
			// pasar a enfriamiento
		}
			
	}
	
	/* transiciones no visibles al resto de clases
	public void prepararSalto() {
		estado.prepararSalto();
	}
	
	public void pasarAWarp() {
		estado.pasarAWarp();
	}
	
	public void enfriar() {
		estado.enfriar();
	}
	
	public void dejarDisponible() {
		estado.dejarDisponible();
	}
	
	public void abortar() {
		estado.abortar();
	}
	
	*/

}