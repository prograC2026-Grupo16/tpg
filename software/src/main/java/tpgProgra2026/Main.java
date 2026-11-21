package tpgProgra2026;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import tpgProgra2026.usuarios.DaoException;
import tpgProgra2026.usuarios.UsuarioDAO;
import tpgProgra2026.usuarios.UsuarioDaoMemoria;
import tpgProgra2026.usuarios.UsuarioDaoSQLite;

/** Clase principal: Run sobre esta clase desde cualquier IDE. Abre el Login y, tras autenticarse, el Tablero. */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                UsuarioDAO dao;
                try {
                    dao = new UsuarioDaoSQLite();
                } catch (DaoException e) {
                    JOptionPane.showMessageDialog(null,
                            "No se pudo abrir la base de usuarios (" + e.getDescripcion() + ").\n"
                                    + "Se usará una lista de usuarios temporal en memoria.\n"
                                    + "Para usar SQLite, importá el proyecto como Maven (pom.xml) o agregá sqlite-jdbc al classpath.",
                            "Aviso", JOptionPane.WARNING_MESSAGE);
                    dao = new UsuarioDaoMemoria();
                }
                try {
                    new Aplicacion(dao).iniciar();
                } catch (DaoException e) {
                    JOptionPane.showMessageDialog(null, "No se pudo iniciar la aplicación: " + e.getDescripcion(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
