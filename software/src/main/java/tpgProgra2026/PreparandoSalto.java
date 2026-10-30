package tpgProgra2026;

public class PreparandoSalto implements EstadoWarp {
	private MotorWarp motor;
	
	public PreparandoSalto (MotorWarp motor) {
		this.motor = motor;
	}
	
	@Override
	public void prepararSalto() {
		System.out.println("Transición Invalida");
	}
	@Override
	public void pasarAWarp() {
		EstadoWarp estado = new EnWarp(motor);
		motor.setEstado(estado);
	}
	@Override
	public void enfriar() {
		System.out.println("Transición Invalida");
	}
	@Override
	public void dejarDisponible() {
		System.out.println("Transición Invalida");
	}
	
	public boolean estaMotorDisponible() {
		return false;
	}
}