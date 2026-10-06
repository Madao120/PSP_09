package org.example;

import java.util.ArrayList;
import java.util.List;

public class GestorTareas {

    // Colorines    Main-Amarillo, Azul-Descarga, Morado-Monitor, Verde-Instalador
    public static final String FIN = "\u001B[0m";
    public static final String AMARILLO = "\u001B[33m";

    static void main(String[] args) throws InterruptedException {

        int tiempoTotalHilosSuma = 0;

        // Atributo para establecer cuando empieza el programa
        // ----------------------------------------------------------------
        // (ChatGpt me ayudó a entender el funcionamiento de esta variable)
        long inicio = System.nanoTime();

        List<Descarga> descargas = new ArrayList<>();

        /* NIVEL 1, en nivel 1 directamente creaba los nombres directamente
        descargas.add(new Descarga("cuarzos.png"));
        descargas.add(new Descarga("meditacion.mp4"));
        descargas.add(new Descarga("mantras.mp3"));
        descargas.add(new Descarga("horoscopo.pdf"));
        */

        // NIVEL 2, ahora pasamos los nombres de descarga por argumentos
        // y en caso de que no se introduzca nada, haremos como antes, escribiendo directamente los nombres
        String[] nombresArgumentos;

        if (args.length == 0) {
            nombresArgumentos = new String[]{
                    "cuarzos.png",
                    "meditacion.mp4",
                    "mantras.mp3",
                    "horoscopo.pdf"
            };
        } else {
            nombresArgumentos = args;
        }

        // Ahora una vez obtenido los nombre de args o directamente poniendo los default, crearemos los hilos

        for (int i = 0; i < nombresArgumentos.length; i++) {

            descargas.add(new Descarga(nombresArgumentos[i]));
        }

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
                    System.out.println(AMARILLO + "[Main]" + FIN + " meditacion.mp4 sigue en segundo plano");
                }
                else{
                    System.out.println(AMARILLO + "[Main]" + FIN + " meditacion.mp4 ha terminado antes de 3 segundos");
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
        // Para truncar los decimales me acabó ayudando la IA, no tenía los conocimientos para hacer la operación en una sola línea
        System.out.println("Tiempo real: " + String.format("%.0f", tiempoTotal / 1_000_000.0) + "ms") ;
        System.out.println("Si no hubiera concurrencia, el programa hubiera tardado: " + tiempoTotalHilosSuma + "ms");
    }
}
