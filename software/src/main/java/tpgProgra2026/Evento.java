package tpgProgra2026;
import java.time.LocalDateTime;

public class Evento {

    private String mensaje;
    private String tipo;
    private LocalDateTime fecha;

    public Evento(String mensaje, String tipo) {
        this.mensaje = mensaje;
        this.tipo = tipo;
        this.fecha = LocalDateTime.now();
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getTipo() {
        return tipo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

}
