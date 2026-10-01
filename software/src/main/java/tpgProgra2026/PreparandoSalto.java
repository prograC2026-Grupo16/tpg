package tpgProgra2026;

public class PreparandoSalto implements EstadoWarp {
	private MotorWarp motor;
	
	public PreparandoSalto () {
		
	}
	
	@Override
	public void prepararSalto() {
		System.out.println("Transición Invalida");
	}
	@Override
	public void pasarAWarp() {
		EstadoWarp estado = new EnWarp();
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