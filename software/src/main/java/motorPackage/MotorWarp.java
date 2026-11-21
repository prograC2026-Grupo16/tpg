package motorPackage;
import navesPackage.Nave;
import tpgProgra2026.TableroDestino.Observable;

public class MotorWarp extends Observable {
	private EstadoWarp estado;
	private Nave nave;
	public static final int COSTO_COMB_SALTO_CORTO = 2, COSTO_ENERGIA_SALTO_LARGO = 5;
	public static final int DESGASTE_ENFRIAMIENTO = 3;
	
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
		notificarObservadores();
	}

	/** @post devuelve el nombre del estado actual (Disponible, PreparandoSalto, EnWarp o Enfriamiento); no modifica el motor. */
	public String getNombreEstado() {
		return estado.getClass().getSimpleName();
	}
	
	
	/**
	 * Postcondicion:
	 * - Devuelve una nave no null nave != null
	 * @return la nave que compone al motor warp
	 */
	protected Nave getNave() {
		return nave;
	}

	// transiciones 
	public boolean saltar() {
		return estado.saltar();
	}

	public boolean saltarWarp() {
		return estado.saltarWarp();			
	}
	
	protected boolean realizarSaltoCorto() {
		if (nave.getCombustible() >= COSTO_COMB_SALTO_CORTO) {
			nave.usarCombustible(COSTO_COMB_SALTO_CORTO);
			return true;
		}
		else return false;
	}

	/**
	 * @pre nave.getEnergia() >= COSTO_SALTO_LARGO
	 */
	protected void realizarSaltoLargo() {
		assert nave.getEnergia() >= COSTO_ENERGIA_SALTO_LARGO : "Se intentó hacer un salto warp sin los recursos necesarios";
		nave.usarEnergia(COSTO_ENERGIA_SALTO_LARGO);
	}
	
	
}