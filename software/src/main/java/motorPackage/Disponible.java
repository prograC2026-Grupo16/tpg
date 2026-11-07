package motorPackage;

public class Disponible implements EstadoWarp {
	private MotorWarp motor;
	
	public Disponible(MotorWarp motor) {
		this.motor = motor;
	}
	
	@Override
	public void saltar() {
		motor.realizarSaltoCorto();
		motor.setEstado(new PreparandoSalto(motor));	
	}
	
	@Override
	public void saltarWarp() {
		motor.realizarSaltoCorto();
		motor.setEstado(new PreparandoSalto(motor));		
	}
}