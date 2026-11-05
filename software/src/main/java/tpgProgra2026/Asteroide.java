package tpgProgra2026;

import tpgProgra2026.TableroDestino.ElementoDelUniverso;

public class Asteroide implements ElementoDelUniverso {
    private final int danio;
    public Asteroide(int danio) {
        this.danio = danio;
    }
    public void interactuar(Asistente a) {
        a.sacarEnergia(danio);
    }

    public String getTipoElemento() {
        return "Asteroide";
    }
}
