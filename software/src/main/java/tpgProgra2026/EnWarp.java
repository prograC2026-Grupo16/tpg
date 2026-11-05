package tpgProgra2026;

public class EnWarp implements EstadoWarp{
	private MotorWarp motor;
	
	public EnWarp(MotorWarp motor) {
		this.motor = motor;
	}
	
	@Override
	public void saltar() {
		motor.realizarSaltoCorto();
	}
	
	@Override
	public void saltarWarp() {
		motor.realizarSaltoLargo();
		motor.setEstado(new Enfriamiento(motor));
	}
}