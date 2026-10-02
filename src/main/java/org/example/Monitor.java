package org.example;

import java.util.ArrayList;
import java.util.List;

public class Monitor  implements Runnable {

    private List<Descarga> descargas;
    Boolean hayDescargasVivas = true;

    // Creamos un nuevo constructor para pasar los hilos
    public Monitor(List<Descarga> descargas) {
        this.descargas = descargas;
    }

    @Override
    public void run() {

        while (hayDescargasVivas) {

            // Iniciamos la variable activas para imprimir cuantas descargas siguen en proceso.
            // La iniciamos en 0 para que por cada iteración se reinicie la variable
            int activas = 0;

            // Por vada descarga en descarga, comprobamos cuantas están activas, utilizando .isAlive()
            for (Descarga descarga : descargas) {
                if (descarga.isAlive()) {
                    activas++;
                }
            }

            // En caso de que ya no haya ninguna descarga viva en descargas, imprimiremos el mensaje de que han terminado, rompiendo el bucle
            if (activas == 0){
                System.out.println("[Monitor] No queda ninduna descarga en curso");
                hayDescargasVivas = false;
            }
            else{

                // Ahora esperaremos 500 ms para la siguiente iteración, que sguirá comprobando hasta que no haya más descargas activas
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        System.out.println("[Monitor] No queda ninguna descarga en curso");
    }
}
