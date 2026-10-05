package org.example;

import java.util.List;

public class Instalador implements Runnable{

    List<Descarga> descargas;

    public Instalador (List<Descarga> descargas) {
        this.descargas = descargas;
    }

    @Override
    public void run() {
        try {
            for( Descarga descarga : descargas){
                if (descarga.getName() == "mantras.mp3" || descarga.getName() == "mantras.mp3" ){
                    descarga.join();
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        System.out.println("[Instalador] Meditación y mantras listos: instalando...");
        System.out.println("[Instalador] Instalación terminada");
    }
}
