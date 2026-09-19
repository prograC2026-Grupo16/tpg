package tpgProgra2026;

public class EnWarp implements EstadoWarp {
	private MotorWarp motor;
	
	public EnWarp() {
		
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
		EstadoWarp estado = new Enfriamiento();
		motor.setEstado(estado);
	}
	@Override
	public void dejarDisponible() {
		// TODO transición invalida
	}
	@Override
	public void abortar() {
		// TODO añadir implementación abortar()
	}
}
