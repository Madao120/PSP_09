package org.example;

import java.util.ArrayList;
import java.util.List;

public class GestorTareas {

    static void main() throws InterruptedException {

        int tiempoTotalPrograma = 0;

        // declaro esta variable apra saber cual es la descarga que tardó mas = lo que tardó el programa
        int descargaMayor = 0;

        List<Descarga> descargas = new ArrayList<>();

        descargas.add(new Descarga("meditación.mp4"));
        descargas.add(new Descarga("albaricoque.mp4"));
        descargas.add(new Descarga("patata.mp4"));
        descargas.add(new Descarga("omóplato.mp4"));

        // Bucle de iniciación de hilos
        for (Descarga descarga : descargas) {
            descarga.start();
        }

        // Debemos de separar el start() del join() para que haya concurrencia y no sea secuencial (no me olvidé)
        for (Descarga descarga : descargas) {
            descarga.join();
        }

        // Cálcular cual fue el que tardó más tiempo (por lo tanto es lo mismo que tardó el programa)
        for (Descarga descarga : descargas){
            tiempoTotalPrograma += descarga.getTiempoTotal();
        }

        // Calcular cuanto tardó el programa
        for (Descarga descarga : descargas){
            if (descarga.getTiempoTotal() > descargaMayor){
                descargaMayor = descarga.getTiempoTotal();
            }
        }

        System.out.println("======================================================");
        System.out.println("Todas las descargas han terminado");
        System.out.println("El programa tardó " + descargaMayor + "ms") ;
        System.out.println("Si no hubiera concurrencia, el programa hubiera tardado:\n" + tiempoTotalPrograma);
    }
}
