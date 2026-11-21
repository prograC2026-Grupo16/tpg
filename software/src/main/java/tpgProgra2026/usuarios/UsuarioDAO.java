
package tpgProgra2026.usuarios;

// import java.sql.SQLException;

public interface UsuarioDAO {
    public void guardar(UsuarioDTO var1) throws DaoException;

    public UsuarioDTO buscarPorNombre(String var1) throws DaoException;

    public boolean existe(String var1) throws DaoException;

    public void eliminar(String var1) throws DaoException;
}

