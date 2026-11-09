
package tpgProgra2026.usuarios;

import java.sql.SQLException;

public interface UsuarioDAO {
    public void guardar(UsuarioDTO var1) throws SQLException;

    public UsuarioDTO buscarPorNombre(String var1) throws SQLException;

    public boolean existe(String var1) throws SQLException;

    public void eliminar(String var1) throws SQLException;
}

