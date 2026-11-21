package tpgProgra2026.tablero;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import navesPackage.TipoNaveInvalidoException;

/**
 * Controlador de la VistaTablero (mismo esquema que ControladorLogin: ActionListener + switch por ActionCommand).
 * Lee los datos de la vista, le pide la operación al modelo y muestra en la vista los errores que éste informe.
 * No dibuja nada: el refresco lo hace la vista cuando el modelo la notifica.
 */
public class ControladorTablero implements ActionListener {

    public VistaTablero vista;
    public ModeloTablero modelo;
    public ModeloUniverso universo;

    public ControladorTablero(VistaTablero vista, ModeloTablero modelo, ModeloUniverso universo) {
        this.vista = vista;
        this.modelo = modelo;
        this.universo = universo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            switch (e.getActionCommand()) {
                case VistaTablero.CREAR_NAVE:
                    modelo.crearNave(vista.getNombreNave(), vista.getTipoNave(),
                            leerEntero(vista.getFilasTexto(), "filas"),
                            leerEntero(vista.getColumnasTexto(), "columnas"));
                    vista.limpiarCampos();
                    vista.mostrarExito("Nave creada.");
                    break;
                case VistaTablero.AGREGAR_TRIPULANTE:
                    modelo.agregarTripulante(vista.getCargo(), leerAntiguedad(), vista.getOrigen());
                    vista.mostrarExito("Tripulante asignado.");
                    break;
                case VistaTablero.INICIAR_MISION:
                    modelo.iniciarMision(vista.getMision());
                    vista.mostrarExito("Misión " + vista.getMision() + " en marcha.");
                    break;
                case VistaTablero.AGREGAR_NAVE_UNIVERSO:
                    universo.agregarNaveConsumidora();
                    break;
                case VistaTablero.AGREGAR_GENERADORA:
                    universo.agregarGeneradora();
                    break;
                default:
                    break;
            }
        } catch (TipoNaveInvalidoException ex) {
            vista.mostrarError("Tipo de nave inválido.");
        } catch (SimulacionException ex) {
            vista.mostrarError(ex.getMessage());
        }
    }

    private int leerAntiguedad() throws DatosInvalidosException {
        try {
            return Integer.parseInt(vista.getAntiguedadTexto().trim());
        } catch (NumberFormatException ex) {
            throw new DatosInvalidosException("antigüedad", "La antigüedad debe ser un número entero.");
        }
    }

    private int leerEntero(String texto, String campo) throws DatosInvalidosException {
        try {
            return Integer.parseInt(texto.trim());
        } catch (NumberFormatException ex) {
            throw new DatosInvalidosException(campo, "Las " + campo + " del tablero deben ser un número entero.");
        }
    }
}
