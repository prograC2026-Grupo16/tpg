package tpgProgra2026.usuarios;

import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Ubica el archivo usuarios.db sin depender del IDE ni de rutas absolutas escritas a mano.
 *
 * Cada IDE ejecuta con un directorio de trabajo distinto (IntelliJ, Eclipse y VS Code no coinciden), así que
 * un nombre relativo como "usuarios.db" crearía una base distinta según desde dónde se haga Run.
 * En cambio se parte de dónde están las clases compiladas y se sube hasta encontrar la carpeta del proyecto
 * Maven (la que tiene el pom.xml): ahí vive siempre el mismo usuarios.db.
 */
public final class RutaBaseDatos {

    private static final String NOMBRE = "usuarios.db";

    private RutaBaseDatos() { }

    /** @post devuelve la ruta completa de usuarios.db (el archivo puede no existir todavía: SQLite lo crea). */
    public static String obtener() {
        return carpeta().resolve(NOMBRE).toString();
    }

    /** @post devuelve la carpeta del proyecto si se la encuentra; si no, el directorio de trabajo actual. */
    static Path carpeta() {
        try {
            Path clases = Paths.get(RutaBaseDatos.class.getProtectionDomain().getCodeSource().getLocation().toURI())
                    .toAbsolutePath();
            for (Path d = clases; d != null; d = d.getParent()) {
                if (Files.exists(d.resolve("pom.xml"))) {
                    return d;                              // target/classes, bin, etc. dentro de software/
                }
                if (Files.exists(d.resolve("software").resolve("pom.xml"))) {
                    return d.resolve("software");          // out/production/... en la raíz del repositorio
                }
            }
        } catch (URISyntaxException | RuntimeException e) {
            // sin ubicación de clases (poco común): se usa el directorio de trabajo
        }
        return Paths.get(System.getProperty("user.dir")).toAbsolutePath();
    }
}
