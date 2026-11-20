package motorPackage;

public class Enfriamiento implements EstadoWarp {
	private MotorWarp motor;
	
	public Enfriamiento(MotorWarp motor) {
		this.motor = motor;
	}
	
	@Override
	public boolean saltar() {
		motor.setEstado(new Disponible(motor));
		return motor.realizarSaltoCorto();	}
	
	@Override
	public boolean saltarWarp() {
		motor.setEstado(new Disponible(motor));
		return false;
	}
}