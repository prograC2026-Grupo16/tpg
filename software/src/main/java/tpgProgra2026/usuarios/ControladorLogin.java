
package tpgProgra2026.usuarios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ControladorLogin
implements ActionListener {
    public VistaLogin vista;
    public ModeloLogin modelo;

    public ControladorLogin(VistaLogin vista, ModeloLogin modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            switch (e.getActionCommand()) {
                case "INGRESAR": {
                    boolean valido = this.modelo.login(this.vista.getNombre(), this.vista.getContra());
                    if (valido) {
                        System.out.println("Ingreso un usuario existente");
                        //Aca deberia ingresar a la tabla o para registrar un usuario
                        break;
                    }
                    System.out.println("Usuario no encontrado");
                }
                case "CANCELAR": {
                    //Limpia los campos 
                	vista.limpiarCampos();
                    break;
                }
                case "REGISTRAR":{
                	if (this.vista.getNombre() == "" || this.vista.getContra() == "")
                        System.out.println("Escribí imbécil");
                	else {
                		if (this.modelo.registrar(this.vista.getNombre(), this.vista.getContra())) {
                			System.out.println("Usuario registrado");
                		} else {
                			System.out.println("El usuario ya existe");
                		}
                	}
                }
            }
        }
        catch (DaoException exc) {
            System.out.println("Fallo en la busqueda o acceso de la DB");
        }
    }
}