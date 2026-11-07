package motorPackage;
import navesPackage.Nave;

public class MotorWarp {
	private EstadoWarp estado;
	private Nave nave;
	public static final int COSTO_SALTO_CORTO = 2, COSTO_SALTO_LARGO = 5; // COSTO_SALTO_CORTO es de combustible, COSTO_SALTO_LARGO es de energía
	
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
		estado.saltar();
	}
	public void saltarWarp() {
		estado.saltarWarp();			
	}
	
	protected void realizarSaltoCorto() {
		if (nave.getCombustible() >= COSTO_SALTO_CORTO) {
			nave.usarCombustible(COSTO_SALTO_CORTO);
			// TODO: Hacer el salto corto
		}
	}

	/**
	 * @pre nave.getEnergia() >= COSTO_SALTO_LARGO
	 */
	protected void realizarSaltoLargo() {
		assert nave.getEnergia() >= COSTO_SALTO_LARGO : "Se intentó hacer un salto warp sin los recursos necesarios";
		nave.usarEnergia(COSTO_SALTO_LARGO);
		// TODO: hacer el salto warp
	}
	
}