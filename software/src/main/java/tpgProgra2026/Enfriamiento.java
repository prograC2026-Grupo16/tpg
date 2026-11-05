package tpgProgra2026;

public class Enfriamiento implements EstadoWarp {
	private MotorWarp motor;
	
	public Enfriamiento(MotorWarp motor) {
		this.motor = motor;
	}
	
	@Override
	public void saltar() {
		motor.realizarSaltoCorto();
		motor.setEstado(new Disponible(motor));
	}
	
	@Override
	public void saltarWarp() {
		motor.realizarSaltoCorto(); // jaja cagaste Light
		motor.setEstado(new Disponible(motor));
	}
}