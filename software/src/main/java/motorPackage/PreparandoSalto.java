package motorPackage;

public class PreparandoSalto implements EstadoWarp {
	private MotorWarp motor;
	
	public PreparandoSalto(MotorWarp motor) {
		this.motor = motor;
	}
	
	@Override
	public void saltar() {
		motor.realizarSaltoCorto();
		if (motor.getNave().getEnergia() >= MotorWarp.COSTO_SALTO_LARGO)
			motor.setEstado(new EnWarp(motor));	
	}
	
	@Override
	public void saltarWarp() {
		motor.realizarSaltoCorto();
		if (motor.getNave().getEnergia() >= MotorWarp.COSTO_SALTO_LARGO)
			motor.setEstado(new EnWarp(motor));	
	}
}