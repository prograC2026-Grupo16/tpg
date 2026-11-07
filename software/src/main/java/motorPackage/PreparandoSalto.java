package motorPackage;

public class PreparandoSalto implements EstadoWarp {
	private MotorWarp motor;
	
	public PreparandoSalto(MotorWarp motor) {
		this.motor = motor;
	}
	
	@Override
	public void saltar() {
		motor.realizarSaltoCorto();
		if (motor.getNave().getRecursos().getEnergia() >= 5)
			motor.setEstado(new EnWarp(motor));	
	}
	
	@Override
	public void saltarWarp() {
		motor.realizarSaltoCorto();
		if (motor.getNave().getRecursos().getEnergia() >= 5)
			motor.setEstado(new EnWarp(motor));	
	}
}