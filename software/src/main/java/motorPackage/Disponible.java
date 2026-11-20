package motorPackage;

public class Disponible implements EstadoWarp {
	private MotorWarp motor;
	
	public Disponible(MotorWarp motor) {
		this.motor = motor;
	}
	
	@Override
	public boolean saltar() {
		motor.setEstado(new PreparandoSalto(motor));
		return motor.realizarSaltoCorto();
	}
	
	@Override
	public boolean saltarWarp() {
		motor.setEstado(new PreparandoSalto(motor));
		return false;
	}
}