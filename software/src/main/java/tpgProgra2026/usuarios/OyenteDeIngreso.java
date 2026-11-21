package tpgProgra2026.usuarios;

/**
 * Lo que ocurre cuando el ingreso es válido. ControladorLogin solo avisa "entró este usuario";
 * quién arma y abre las ventanas siguientes lo decide quien arma la aplicación (Aplicacion).
 */
public interface OyenteDeIngreso {
    void ingresoValido(String usuario);
}
