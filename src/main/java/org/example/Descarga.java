package org.example;
public class Descarga extends Thread {

    // Colorines
    public static final String AZUL = "\u001B[34m";
    public static final String FIN = "\u001B[0m";

    private String archivo;
    private int tiempoBloque;
    private int tiempoTotal;

    public int getTiempoTotal() {
        return tiempoTotal;
    }

    public void setTiempoTotal(int tiempoTotal) {
        this.tiempoTotal = tiempoTotal;
    }

    public Descarga(String archivo) {
        super(archivo);
        this.archivo = archivo;
    }

    @Override
    public void run() {
        System.out.println(AZUL + "["+archivo + "]" + FIN +" descarga iniciada...");

        for (int i = 1; i < 11; i++) {
            // Explicación del sleep aleatorio
            // 100 es el mínimo que puede tardar, en este caso 100ms,
            // y lo máximo es 100 + (numero random de 0 a 0.9999 * 400),
            // por lo tanto puede tardar desde 100ms a 499
            // el +(int) es para pasar el random, que es un double a int, para que no tenga decimales
            this.tiempoBloque = 100 + (int)(Math.random() * 400);

            // Añadimos lo que tardará este bloque al tiepo total del hilo
            setTiempoTotal(this.tiempoTotal += tiempoBloque);

            // Para implementar un sleep tendremos que hacer un try catch en caso de que se interrumpa el hilo.
            try {
                Thread.sleep(tiempoBloque);
                System.out.println(AZUL + "["+archivo + "]" + FIN +" Descargando : "+ i + "0%");
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        // Después de que se descarguen los bloques diremos que se ha completado la descarga
        System.out.println(AZUL + "["+archivo + "]" + FIN + " completada en " + getTiempoTotal() + "ms");
    }
}
