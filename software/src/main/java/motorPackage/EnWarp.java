package motorPackage;

public class EnWarp implements EstadoWarp{
	private MotorWarp motor;
	
	public EnWarp(MotorWarp motor) {
		this.motor = motor;
	}
	
	@Override
	public boolean saltar() {
		return motor.realizarSaltoCorto();		// no cambia de estado, se queda en <<EnWarp>> para efectuar un posible salto warp
	}
	
	@Override
	public boolean saltarWarp() {
		if (motor.getNave().getEnergia() >= MotorWarp.COSTO_ENERGIA_SALTO_LARGO) {
			motor.getNave().generarDesgaste(MotorWarp.DESGASTE_ENFRIAMIENTO);
			motor.setEstado(new Enfriamiento(motor));
			motor.realizarSaltoLargo();
			return true; // el único caso que saltarWarp devuelve true;
		}
		else {
			motor.setEstado(new Enfriamiento(motor));
			return false;
		}
	}
}