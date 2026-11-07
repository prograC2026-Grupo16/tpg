package tpgProgra2026;

import tpgProgra2026.TableroDestino.ElementoDelUniverso;

public class EstacionesDeCarga implements ElementoDelUniverso {
    private final int COMBUSTIBLE = 20;
    private final int ENERGIA = 20; // no dice la cantidad en la especificación solo que es constante

    public EstacionesDeCarga() {}

    public void interactuar(Asistente a) {
        a.cargarCombustible(COMBUSTIBLE);
        a.cargarEnergia(ENERGIA);
    }

    public String getTipoElemento() {
        return "Estacion de carga";
    }
}
