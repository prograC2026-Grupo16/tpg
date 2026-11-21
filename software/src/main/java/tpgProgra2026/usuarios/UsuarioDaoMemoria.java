package tpgProgra2026.usuarios;

import java.util.HashMap;
import java.util.Map;

/**
 * DAO en memoria: se usa solo como respaldo si no se puede abrir la base SQLite
 * (por ejemplo, si falta la librería sqlite-jdbc en el classpath). Los usuarios no se guardan al cerrar.
 */
public class UsuarioDaoMemoria implements UsuarioDAO {
    private final Map<String, UsuarioDTO> usuarios = new HashMap<String, UsuarioDTO>();

    @Override
    public void guardar(UsuarioDTO usuario) {
        usuarios.put(usuario.getNombre(), usuario);
    }

    @Override
    public UsuarioDTO buscarPorNombre(String nombre) {
        return usuarios.get(nombre);
    }

    @Override
    public boolean existe(String nombre) {
        return usuarios.containsKey(nombre);
    }

    @Override
    public void eliminar(String nombre) {
        usuarios.remove(nombre);
    }
}
