package motorPackage;

public class EnWarp implements EstadoWarp{
	private MotorWarp motor;
	
	public EnWarp(MotorWarp motor) {
		this.motor = motor;
	}
	
	@Override
	public void saltar() {
		motor.realizarSaltoCorto();
		// no cambia de estado, se queda en <<EnWarp>> para efectuar un posible salto warp
	}
	
	@Override
	public void saltarWarp() {
		if (motor.getNave().getEnergia() >= MotorWarp.COSTO_SALTO_LARGO) {
			motor.realizarSaltoLargo();
			motor.setEstado(new Enfriamiento(motor));
		}
		else
			motor.realizarSaltoCorto();
	}
}