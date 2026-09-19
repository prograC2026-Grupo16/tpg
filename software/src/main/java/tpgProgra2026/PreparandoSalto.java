package tpgProgra2026;

public class PreparandoSalto implements EstadoWarp {
	private MotorWarp motor;
	
	public PreparandoSalto () {
		
	}
	
	@Override
	public void prepararSalto() {
		// TODO transición invalida
	}
	@Override
	public void pasarAWarp() {
		EstadoWarp estado = new EnWarp();
		motor.setEstado(estado);
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
