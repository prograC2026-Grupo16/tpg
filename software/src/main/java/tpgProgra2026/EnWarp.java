package tpgProgra2026;

public class EnWarp implements EstadoWarp {
	private MotorWarp motor;
	
	public EnWarp(MotorWarp motor) {
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
		EstadoWarp estado = new Enfriamiento(motor);
		motor.setEstado(estado);

		// el thread sleep va en esta transición o en la siguiente??

		try{
			Thread.sleep(1000); // duermo al Thread por 1seg (1000ms)
		}
		catch (InterruptedException e) {
			System.out.println("Hubo una interrupción (? ");
		}

	}
	@Override
	public void dejarDisponible() {
		System.out.println("Transición Invalida");
	}
	
	public boolean estaSaltoWarpDisponible() {
		return true;
	}
}