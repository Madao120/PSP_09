package org.example;

public class GestorTareas {

    static void main() throws InterruptedException {

        Descarga d1 = new Descarga("meditación.mp4");
        Descarga d2 = new Descarga("albaricoque.mp4");
        Descarga d3 = new Descarga("patata.mp4");
        Descarga d4 = new Descarga("omóplato.mp4");

        d1.start();
        d2.start();
        d3.start();
        d4.start();

        d1.join();
        d2.join();
        d3.join();
        d4.join();

        int tiempoTotalPrograma = d1.getTiempoTotal() + d2.getTiempoTotal() + d3.getTiempoTotal() + d4.getTiempoTotal();

        System.out.println("Todas las descargas han terminado");
        System.out.println("El programa tardó " + Math.max(Math.max(d1.getTiempoTotal(), d2.getTiempoTotal()), Math.max(d3.getTiempoTotal(), d4.getTiempoTotal()))+ "ms") ;
        System.out.println("Si no hubiera concurrencia, el programa hubiera tardado:\n" + tiempoTotalPrograma);
    }
}
