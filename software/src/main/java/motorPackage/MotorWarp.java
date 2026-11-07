package motorPackage;
import tpgProgra2026.Nave;

public class MotorWarp {
	private EstadoWarp estado;
	private Nave nave;
	
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
	public MotorWarp(Nave nave) {
		this.nave = nave;
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
	protected void setEstado(EstadoWarp estado) { // solo se puede llamar desde esta clase, subclases o el mismo paquete
		assert estado != null : "El estado pasado como parámetro es null";
		this.estado = estado;
		assert invariantes() : "El estado de la instancia es null";
	}
	
	/**
	 * Postcondicion:
	 * - Devuelve un estado no null estado != null
	 * @return el estado del motor warp
	 */
	public EstadoWarp getEstado() {
		return estado;
	}
	
	/**
	 * Postcondicion:
	 * - Devuelve una nave no null nave != null
	 * @return la nave que compone al motor warp
	 */
	public Nave getNave() {
		return nave;
	}

	// transiciones 
	public void saltar() {
		// gastar 2 puntos de combustible
		estado.saltar();
	}
	public void saltarWarp() {
		estado.saltarWarp();			
	}
	
	protected void realizarSaltoCorto() {
		if (nave.getRecursos().getCombustible() >= 2) {
//			nave.getRecursos().set
			// TODO: Hacer el Salto
		}

	}

	protected void realizarSaltoLargo() {
		
		// DO SOMETHING
	}
	
}