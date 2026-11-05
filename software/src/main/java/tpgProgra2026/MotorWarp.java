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

	// transiciones 
	public void saltar() {
		estado.saltar();
	}
	public void saltarWarp() {
		estado.saltarWarp();			
	}
	
	public void realizarSaltoCorto() {
		// DO SOMETHING
	}

	public void realizarSaltoLargo() {
		// DO SOMETHING
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