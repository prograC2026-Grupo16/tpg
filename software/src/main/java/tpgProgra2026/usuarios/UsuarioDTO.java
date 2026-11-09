
package tpgProgra2026.usuarios;


public class UsuarioDTO {
    private final String nombre;
    private final String password;

    public UsuarioDTO(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getPassword() {
        return this.password;
    }
}
