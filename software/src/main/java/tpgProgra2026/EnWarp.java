package tpgProgra2026;

public class EnWarp implements EstadoWarp {
	private MotorWarp motor;
	
	public EnWarp() {
		
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
		/*
		EstadoWarp estado = new Enfriamiento();
		motor.setEstado(estado);
		*/
		System.out.println("por esta primera parte de la entrega esta transición es válida");
	}
	@Override
	public void dejarDisponible() {
		// por esta primera parte de la entrega esta transición es válida
		EstadoWarp estado = new Disponible();
		motor.setEstado(estado);
	}
	
	public boolean estaMotorDisponible() {
		return false;
	}
}