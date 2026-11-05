package tpgProgra2026;

import tpgProgra2026.TableroDestino.ElementoDelUniverso;

public class EstacionesDeCarga implements ElementoDelUniverso {
    private final int combustible;
    private final int energia;
    public EstacionesDeCarga(int combustible, int energia) {
        this.combustible = combustible;
        this.energia = energia;
    }

    public void interactuar(Asistente a) {
        a.cargarCombustible(combustible);
        a.cargarEnergia(energia);
    }

    public String getTipoElemento() {
        return "Estacion de carga";
    }
}
