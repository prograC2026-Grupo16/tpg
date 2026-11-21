
package tpgProgra2026.usuarios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
public class UsuarioDaoSQLite implements UsuarioDAO{
    private static final String URL = "jdbc:sqlite:" + RutaBaseDatos.obtener(); // misma base desde cualquier IDE

    public UsuarioDaoSQLite() throws DaoException {
        this.crearTablaSiNoExiste();
    }

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    private void crearTablaSiNoExiste() throws DaoException {
        String sql = "CREATE TABLE IF NOT EXISTS usuarios (nombre TEXT PRIMARY KEY, password TEXT NOT NULL)";
        try (Connection c = this.conectar();
             Statement st = c.createStatement();){
            st.execute(sql);
        }
        catch( SQLException e ){
            throw new DaoException("No se pudo crear la tabla usuarios: " + e.getMessage());
        }
    }
    @Override
    public UsuarioDTO buscarPorNombre(String nombre) throws DaoException {
        String sql = "SELECT nombre, password FROM usuarios WHERE nombre = ?";
        try (Connection c = conectar();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new UsuarioDTO(rs.getString("nombre"), rs.getString("password"));
                }
                return null;
        }
    }
    catch( SQLException e ){
        throw new DaoException("Error al buscar el usuario: " + nombre);
    }
}
    @Override
    public void guardar(UsuarioDTO usuario) throws DaoException {
        String sql = "INSERT INTO usuarios(nombre, password) VALUES(?, ?)";
        try (Connection c = this.conectar();
             PreparedStatement ps = c.prepareStatement(sql);){
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getPassword());
            ps.executeUpdate();
        }
        catch( SQLException e  ){
            throw new DaoException("Error al guardar el usuario: " + usuario.getNombre());
        }
        
    }   
    @Override
    public void eliminar(String nombre) throws DaoException {
        String sql = "DELETE FROM usuarios WHERE nombre = ?";
        try (Connection c = this.conectar();
             PreparedStatement ps = c.prepareStatement(sql);){
            ps.setString(1, nombre);
            ps.executeUpdate();
        }
        catch(SQLException e){
            throw new DaoException("Error al eliminar el usuario: " + nombre);
        }
    }
    @Override
    public boolean existe(String nombre) throws DaoException {
        String sql = "SELECT 1 FROM usuarios WHERE nombre = ?";
        try (Connection c = conectar();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
        catch(SQLException e){
            throw new DaoException("Error al verificar si existe el usuario: " + nombre);
        }
    }
}
