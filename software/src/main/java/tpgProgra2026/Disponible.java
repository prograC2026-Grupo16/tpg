package tpgProgra2026;

public class Disponible implements EstadoWarp {
	private MotorWarp motor;
	
	public Disponible() {
		
	}
		
	@Override
	public void prepararSalto() {
		EstadoWarp estado = new PreparandoSalto();
		motor.setEstado(estado);
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
		System.out.println("Transición Invalida");
	}
	
	public boolean estaMotorDisponible() {
		return true;
	}
}