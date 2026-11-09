package universoParalelo;

/**
 * Recurso compartido (monitor) del universo paralelo.
 * Capacidad limitada y acceso exclusivo: un solo hilo opera a la vez.
 *
 * @invariante 0 <= baterias && baterias <= capacidad
 */
public class PlataformaDeCarga {

    private final int capacidad;
    private int baterias = 0;

    /**
     * @pre  capacidad > 0
     * @post baterias == 0
     */
    public PlataformaDeCarga(int capacidad) {
        this.capacidad = capacidad;
    }

    /**
     * Deposita una batería. Si la plataforma está llena, espera.
     * @pre  nombreGeneradora != null
     * @post baterias == cantidad anterior + 1
     */
    public synchronized void depositar(String nombreGeneradora) throws InterruptedException {
        if (baterias == capacidad)
            System.out.println(nombreGeneradora + " esperando a que se libere espacio en la Plataforma.");
        while (baterias == capacidad)
            wait();

        baterias++;
        System.out.println(nombreGeneradora + " depositó una batería. (Plataforma: " + baterias + "/" + capacidad + ")");
        notifyAll();
    }

    /**
     * Retira una batería. Si la plataforma está vacía, espera.
     * @pre  nombreNave != null
     * @post baterias == cantidad anterior - 1
     */
    public synchronized void retirar(String nombreNave) throws InterruptedException {
        if (baterias == 0)
            System.out.println(nombreNave + " esperando por falta de baterías en la Plataforma.");
        while (baterias == 0)
            wait();

        baterias--;
        System.out.println("Batería retirada y utilizada por " + nombreNave + ". (Plataforma: " + baterias + "/" + capacidad + ")");
        notifyAll();
    }
}
