
package tpgProgra2026.usuarios;

import java.sql.SQLException;

public class ModeloLogin {
    private final UsuarioDAO dao;

    public ModeloLogin(UsuarioDAO dao) {
        this.dao = dao;
    }

    public boolean registrar(String nombre, String contra) throws SQLException {
        if (this.dao.existe(nombre)) {
            return false;
        }
        UsuarioDTO dto = new UsuarioDTO(nombre, contra);
        this.dao.guardar(dto);
        return true;
    }

    public boolean login(String nombre, String contra) throws SQLException {
        UsuarioDTO dto = this.dao.buscarPorNombre(nombre);
        return dto != null && dto.getPassword().equals(contra);
    }
}