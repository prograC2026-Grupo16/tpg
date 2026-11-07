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
		// gastar 5 unidades de energía de la nave
		if (motor.getNave().getRecursos().getEnergia() >= 5) {
			motor.realizarSaltoLargo();
			motor.setEstado(new Enfriamiento(motor));
		}
		else
			motor.realizarSaltoCorto();
	}
}