package tpgProgra2026;

import tpgProgra2026.TableroDestino.ElementoDelUniverso;

public class Asteroide implements ElementoDelUniverso {
    private final int DANIO = 4; // En la especificacion no me dicen que cantidad debe sacar el asteroide solo que es constante

    public Asteroide() {}

    public void interactuar(Asistente a) {
        a.cargarEnergia(DANIO);
    }

    public String getTipoElemento() {
        return "Asteroide";
    }
}

