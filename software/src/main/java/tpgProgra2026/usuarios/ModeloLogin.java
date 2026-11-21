
package tpgProgra2026.usuarios;

// import java.sql.SQLException;

public class ModeloLogin {
    private final UsuarioDAO dao;
    private String usuarioActual = null;

    public ModeloLogin(UsuarioDAO dao) {
        this.dao = dao;
    }

    public boolean registrar(String nombre, String contra) throws DaoException {
    	if (this.dao.existe(nombre)) {
            return false;
        }
        UsuarioDTO dto = new UsuarioDTO(nombre, contra);
        this.dao.guardar(dto);
        return true;
    }

    public boolean login(String nombre, String contra) throws DaoException {
        UsuarioDTO dto = this.dao.buscarPorNombre(nombre);
        boolean valido = dto != null && dto.getPassword().equals(contra);
        if (valido) {
            this.usuarioActual = dto.getNombre();
        }
        return valido;
    }

    /** @return el nombre del usuario que inició sesión, o null si todavía nadie ingresó. */
    public String getUsuarioActual() {
        return usuarioActual;
    }
}