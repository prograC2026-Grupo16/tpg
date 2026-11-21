package universoParalelo;

/**
 * Prueba por consola de la concurrencia entre generadoras, naves y plataforma.
 */
public class PruebaUniverso {

    public static void main(String[] args) throws InterruptedException {
        final PlataformaDeCarga plataforma = new PlataformaDeCarga(5);
        // sin interfaz gráfica: un observador imprime cada evento nuevo en consola
        plataforma.agregarObservador(() -> {
            java.util.List<String> eventos = plataforma.getEventos();
            System.out.println(eventos.get(eventos.size() - 1));
        });

        new GeneradoraDeEnergia("Generadora 1", plataforma).start();
        new NaveConsumidora("Nave 1", plataforma).start();
        new NaveConsumidora("Nave 2", plataforma).start();
        new NaveConsumidora("Nave 3", plataforma).start();

        Thread.sleep(4000);
        new GeneradoraDeEnergia("Generadora 2", plataforma).start(); // se agrega en vivo

        Thread.sleep(6000);
        System.exit(0); // los hilos son infinitos: corta todo al terminar la prueba
    }
}
