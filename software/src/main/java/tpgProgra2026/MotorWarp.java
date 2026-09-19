package tpgProgra2026;

public class MotorWarp {
	private EstadoWarp estado;
	
	// Bob el Constructor
	public MotorWarp() {
		estado = new Disponible();
	}
	
	// getters y setters -> están bien? están bien que sean protected (para que no sean públicas)?
	protected void setEstado(EstadoWarp estado) {
		this.estado = estado;
	}
	
	protected EstadoWarp getEstado() {
		return estado;
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
	
	public void abortar() {
		estado.abortar();
	}

}
