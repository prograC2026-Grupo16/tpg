package tpgProgra2026;

public class Enfriamiento implements EstadoWarp {
	private MotorWarp motor;
	
	public Enfriamiento() {
		
	}
	
	@Override
	public void prepararSalto() {
		// TODO transición invalida
	}
	@Override
	public void pasarAWarp() {
		// TODO transición invalida
	}
	@Override
	public void enfriar() {
		// TODO transición invalida
	}
	@Override
	public void dejarDisponible() {
		EstadoWarp estado = new Disponible();
		motor.setEstado(estado);
	}
	@Override
	public void abortar() {
		// TODO añadir implementación abortar()
	}
}
