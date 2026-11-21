package tpgProgra2026;

import javax.swing.JFrame;

import tpgProgra2026.tablero.ControladorTablero;
import tpgProgra2026.tablero.ModeloTablero;
import tpgProgra2026.tablero.ModeloUniverso;
import tpgProgra2026.tablero.VistaTablero;
import tpgProgra2026.usuarios.ControladorLogin;
import tpgProgra2026.usuarios.DaoException;
import tpgProgra2026.usuarios.ModeloLogin;
import tpgProgra2026.usuarios.UsuarioDAO;
import tpgProgra2026.usuarios.VistaLogin;

/**
 * Arma la aplicación: la ventana del Login y, cuando el ControladorLogin avisa que el ingreso fue válido,
 * la ventana del Tablero (modelos, vista y controlador). Es el único lugar que crea ventanas y conecta las piezas.
 */
public class Aplicacion {

    private final ModeloLogin modeloLogin;

    /** @throws DaoException si no se puede acceder a la base de usuarios */
    public Aplicacion(UsuarioDAO dao) throws DaoException {
        this.modeloLogin = new ModeloLogin(dao);
    }

    /** Debe llamarse desde el hilo de Swing. */
    public void iniciar() {
        final JFrame ventanaLogin = new JFrame("TP Naves - Login");
        VistaLogin vista = new VistaLogin();
        ControladorLogin controlador = new ControladorLogin(vista, modeloLogin, usuario -> {
            abrirTablero(usuario);
            ventanaLogin.dispose();
        });
        vista.setActionListener(controlador);

        ventanaLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaLogin.add(vista);
        ventanaLogin.pack();
        ventanaLogin.setLocationRelativeTo(null);
        ventanaLogin.setVisible(true);
    }

    /** Arma los modelos, la vista y el controlador de la simulación y abre su ventana. */
    private void abrirTablero(String usuario) {
        ModeloTablero modeloTablero = new ModeloTablero();
        ModeloUniverso universo = new ModeloUniverso();
        VistaTablero vistaTablero = new VistaTablero(modeloTablero, universo, usuario);
        ControladorTablero controladorTablero = new ControladorTablero(vistaTablero, modeloTablero, universo);
        vistaTablero.setActionListener(controladorTablero);

        JFrame ventana = new JFrame("TP Naves - Simulación (" + usuario + ")");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.add(vistaTablero);
        ventana.pack();
        // en pantallas chicas la ventana se achica a lo disponible (las pestañas tienen barras de desplazamiento)
        java.awt.Rectangle pantalla = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        ventana.setSize(Math.min(ventana.getWidth(), pantalla.width), Math.min(ventana.getHeight(), pantalla.height));
        ventana.setMinimumSize(new java.awt.Dimension(480, 360));
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}
