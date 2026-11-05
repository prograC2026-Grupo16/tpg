package tpgProgra2026;

public class PreparandoSalto implements EstadoWarp {
	private MotorWarp motor;
	
	public PreparandoSalto (MotorWarp motor) {
		this.motor = motor;
	}
	
	@Override
	public void saltar() {
		motor.realizarSaltoCorto();
		motor.setEstado(new EnWarp(motor));	
	}
	
	@Override
	public void saltarWarp() {
		motor.realizarSaltoCorto();
		motor.setEstado(new EnWarp(motor));		
	}
}