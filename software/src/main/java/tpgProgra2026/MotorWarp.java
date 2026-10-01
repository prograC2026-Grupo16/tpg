package tpgProgra2026;

public class MotorWarp {
	private EstadoWarp estado;
	
	// Bob el Constructor
	public MotorWarp() {
		estado = new Disponible();
	}
	
	// getters y setters
	public void setEstado(EstadoWarp estado) {
		this.estado = estado;
	}
	
	public EstadoWarp getEstado() {
		return estado;
	}

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
