package org.example;

import java.util.ArrayList;
import java.util.List;

public class GestorTareas {

    static void main() throws InterruptedException {

        int tiempoTotalHilosSuma = 0;

        // Atributo para establecer cuando empieza el programa
        // ----------------------------------------------------------------
        // (ChatGpt me ayudó a entender el funcionamiento de esta variable)
        long inicio = System.nanoTime();

        List<Descarga> descargas = new ArrayList<>();

        descargas.add(new Descarga("cuarzos.png"));
        descargas.add(new Descarga("meditacion.mp4"));
        descargas.add(new Descarga("mantras.mp3"));
        descargas.add(new Descarga("horoscopo.pdf"));

        // NIVEL 2, crear Monitor con Runnable, por ello creamos el objeto Runnable insertándolo en Thread
        Monitor monitor = new Monitor(descargas);
        Thread hiloMonitor = new Thread(monitor);

        // NIVEL 3
        // Creamos Instalador con Runable
        Instalador instalador = new Instalador(descargas);
        Thread hiloInstalador = new Thread(instalador);

        // Bucle de iniciación de hilos
        for (Descarga descarga : descargas) {
            descarga.start();
        }

        // NIVEL 2
        // Una vez inicien los hilos, mientras estén en ejecución (antes del .join()) iniciaremos Monitor
        hiloMonitor.start();

        // NIVEL 3, ejecutamos la instalación, la cual esperará a mantras y meditación por su cuenta.
        hiloInstalador.start();

        // NIVEL3
        // Ejecutamos un bucle en main, donde esperaremos 3 segundos a
        for (Descarga descarga: descargas){
            if(descarga.getName() == "meditacion.mp4"){
                descarga.join(3000);
                if (descarga.isAlive()){
                    System.out.println("[Main] meditacion.mp4 sigue en segundo plano");
                }
                else{
                    System.out.println("[Main] meditacion.mp4 ha terminado antes de 4 segundos");
                }
            }

        }

        // Debemos de separar el start() del join() para que haya concurrencia y no sea secuencial (no me olvidé)
        for (Descarga descarga : descargas) {
            descarga.join();
        }

        // NIVEL 2
        // Una vez terminan los hilos de descarga, esperaremos a que termine el hilo Monitor
        hiloMonitor.join();


        // Cálcular cual fue el que tardó más tiempo (por lo tanto es lo mismo que tardó el programa)
        for (Descarga descarga : descargas){
            tiempoTotalHilosSuma += descarga.getTiempoTotal();
        }

        //Establecemos aquí que termina el prorgama, por lo que guardamos cuando fué
        long fin = System.nanoTime();

        // Ponemos el momento de inicio y fin del programa, sacando cuanto tardó en ms
        long tiempoTotal = fin - inicio;

        System.out.println("======================================================");
        System.out.println("Todas las descargas han terminado.");
        System.out.println("Tiempo real: " + tiempoTotal/1_000_000.0 + "ms") ;
        System.out.println("Si no hubiera concurrencia, el programa hubiera tardado: " + tiempoTotalHilosSuma + "ms");
    }
}
