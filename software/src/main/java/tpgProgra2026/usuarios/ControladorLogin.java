package tpgProgra2026.usuarios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlador del Login: recibe los botones de la vista, consulta al modelo y le dice a la vista qué mostrar.
 * Cuando el ingreso es válido avisa al OyenteDeIngreso; no crea ventanas ni modelos.
 */
public class ControladorLogin
implements ActionListener {
    public VistaLogin vista;
    public ModeloLogin modelo;
    private final OyenteDeIngreso oyente;

    public ControladorLogin(VistaLogin vista, ModeloLogin modelo, OyenteDeIngreso oyente) {
        this.vista = vista;
        this.modelo = modelo;
        this.oyente = oyente;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            switch (e.getActionCommand()) {
                case "INGRESAR": {
                    boolean valido = this.modelo.login(this.vista.getNombre(), this.vista.getContra());
                    if (valido) {
                        this.oyente.ingresoValido(this.modelo.getUsuarioActual());
                    } else {
                        this.vista.mostrarError("Usuario o contraseña incorrectos.");
                    }
                    break;
                }
                case "CANCELAR": {
                    //Limpia los campos
                    this.vista.limpiarCampos();
                    break;
                }
                case "REGISTRAR": {
                    if (this.vista.getNombre().isEmpty() || this.vista.getContra().isEmpty()) {
                        this.vista.mostrarError("Completá usuario y contraseña.");
                    } else if (this.modelo.registrar(this.vista.getNombre(), this.vista.getContra())) {
                        this.vista.mostrarExito("Usuario registrado. Ya podés ingresar.");
                    } else {
                        this.vista.mostrarError("El usuario ya existe.");
                    }
                    break;
                }
                default:
                    break;
            }
        }
        catch (DaoException exc) {
            this.vista.mostrarError("Fallo en la búsqueda o acceso a la base de datos.");
        }
    }
}
