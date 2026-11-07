package motorPackage;

public class Enfriamiento implements EstadoWarp {
	private MotorWarp motor;
	public static final int DESGASTE_ENFRIAMIENTO = 3;
	
	public Enfriamiento(MotorWarp motor) {
		this.motor = motor;
	}
	
	@Override
	public void saltar() {
		motor.getNave().generarDesgaste(DESGASTE_ENFRIAMIENTO);
		motor.realizarSaltoCorto();
		motor.setEstado(new Disponible(motor));
	}
	
	@Override
	public void saltarWarp() {
		motor.getNave().generarDesgaste(DESGASTE_ENFRIAMIENTO);
		motor.realizarSaltoCorto();
		motor.setEstado(new Disponible(motor));
	}
}