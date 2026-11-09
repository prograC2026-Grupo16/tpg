
package tpgProgra2026.usuarios;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.sql.SQLException;

public class MainVistaLogin
{

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        try{    
            VistaLogin vista = new VistaLogin();
            UsuarioDAO dao = new UsuarioDaoSQLite();  // -> aca se crea la base de datos
            ModeloLogin modelo = new ModeloLogin(dao); 
            ControladorLogin controlador = new ControladorLogin(vista, modelo);
            vista.setActionListener(controlador);
            modelo.registrar("Ana", "1234");
            modelo.registrar("pablo", "123");
            modelo.registrar("matias", "12");
            // Listener de prueba: reemplaza al controlador
            vista.setActionListener(e -> {
                System.out.println("Botón: " + e.getActionCommand());
                System.out.println("Nombre: " + vista.getNombre());
                System.out.println("Contra: " + vista.getContra());
            });
            
            JFrame frame = new JFrame("Prueba - Login");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(vista);
            frame.pack();                    // toma el tamaño preferido de la vista
            frame.setLocationRelativeTo(null); // centra la ventana en la pantalla
            frame.setVisible(true);
        }
        catch(SQLException e){
        }
        });
    }
}
