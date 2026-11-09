package universoParalelo;

import java.util.Random;

/**
 * Productor: produce una batería y la deposita en la plataforma, en bucle.
 */
public class GeneradoraDeEnergia extends Thread {

    private final String nombre;
    private final PlataformaDeCarga plataforma;
    private final Random random = new Random();

    /**
     * @pre nombre != null && plataforma != null
     */
    public GeneradoraDeEnergia(String nombre, PlataformaDeCarga plataforma) {
        this.nombre = nombre;
        this.plataforma = plataforma;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(500 + random.nextInt(1000));
                System.out.println("Batería producida por " + nombre + ".");
                plataforma.depositar(nombre);
            }
        } catch (InterruptedException e) {
            System.out.println(nombre + " detenida.");
        }
    }
}
