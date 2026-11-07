package motorPackage;

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
		// generar 3 puntos de desgaste en la nave
		motor.realizarSaltoCorto();
		motor.setEstado(new Disponible(motor));
	}
}