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
	 * Constructor - instancia un objeto xD
	 * 
	 */
	public MotorWarp() {
		estado = new Disponible();
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
	 * @return el estado del motor warp xd
	 */
	public EstadoWarp getEstado() {
		return estado;
	}

	/**
	 * Precondición
	 * - estado != null
	 * 
	 * @return Booleano xD
	 */
	public boolean estaMotorDisponible() {
		return estado.estaMotorDisponible();
	}
	
	// transiciones
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
	
/*	public void abortar() {
		estado.abortar();
	} */

}
