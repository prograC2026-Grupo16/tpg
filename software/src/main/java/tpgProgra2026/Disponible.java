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
		// TODO transición invalida
	}
	@Override
	public void enfriar() {
		// TODO transición invalida
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
