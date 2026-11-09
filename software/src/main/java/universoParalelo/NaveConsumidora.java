package universoParalelo;

import java.util.Random;

/**
 * Consumidor: pide una batería a la plataforma y la consume, en bucle.
 */
public class NaveConsumidora extends Thread {

    private final String nombre;
    private final PlataformaDeCarga plataforma;
    private final Random random = new Random();

    /**
     * @pre nombre != null && plataforma != null
     */
    public NaveConsumidora(String nombre, PlataformaDeCarga plataforma) {
        this.nombre = nombre;
        this.plataforma = plataforma;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println(nombre + " solicitando batería.");
                plataforma.retirar(nombre);
                Thread.sleep(500 + random.nextInt(1000)); // consume la batería
            }
        } catch (InterruptedException e) {
            System.out.println(nombre + " detenida.");
        }
    }
}
