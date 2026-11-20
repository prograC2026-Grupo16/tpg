package motorPackage;

public class PreparandoSalto implements EstadoWarp {
	private MotorWarp motor;
	
	public PreparandoSalto(MotorWarp motor) {
		this.motor = motor;
	}
	
	@Override
	public boolean saltar() {
		if (motor.getNave().getEnergia() >= MotorWarp.COSTO_ENERGIA_SALTO_LARGO)
			motor.setEstado(new EnWarp(motor));	
		return motor.realizarSaltoCorto();	}
	
	@Override
	public boolean saltarWarp() {
		if (motor.getNave().getEnergia() >= MotorWarp.COSTO_ENERGIA_SALTO_LARGO)
			motor.setEstado(new EnWarp(motor));	
		return false;
	}
}