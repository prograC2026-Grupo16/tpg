
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
            boolean valido = this.modelo.login(this.vista.getNombre(), this.vista.getContra());
            switch (e.getActionCommand()) {
                case "INGRESAR": {
                    if (valido) {
                        System.out.println("Ingreso un usuario existente");
                        //Aca deberia ingresar a la tabla o para registrar un usuario
                        break;
                    }
                    System.out.println("Usuario no encontrado");
                    this.vista.getTextNombre().setText("Intento Fallido");
                }
                case "CANCELAR": {
                    //Limpia los campos 
                    break;
                }
            }
        }
        catch (SQLException exc) {
            System.out.println("Fallo en la busqueda o acceso de la DB");
        }
    }
}