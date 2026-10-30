package tpgProgra2026;

public class Enfriamiento implements EstadoWarp {
	private MotorWarp motor;
	
	public Enfriamiento(MotorWarp motor) {
		this.motor = motor;
	}
	
	@Override
	public void prepararSalto() {
		System.out.println("Transición Invalida");
	}
	@Override
	public void pasarAWarp() {
		System.out.println("Transición Invalida");
	}
	@Override
	public void enfriar() {
		System.out.println("Transición Invalida");
	}
	@Override
	public void dejarDisponible() {
		EstadoWarp estado = new Disponible(motor);
		motor.setEstado(estado);
	}
	
	public boolean estaMotorDisponible() {
		return false;
	}
}