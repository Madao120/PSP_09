

# Nivel 1
| Ejecución | Descarga más lenta | Tiempo real (ms) | Suma (ms) |
| :--- | :--- | :--- | :---|
| **1** | 3402ms | 3422.37ms | 12380ms |
| **2** | 3636ms | 3654.42ms | 11477ms |
| **3** | 3357ms | 3370.40ms | 11784ms |
##### En Tiempo real trunqué en escrito los decimales a 2.

## ● ¿Por qué el tiempo real es mucho menor que la suma?
Es debido a que la suma de estos tiempos simula que pasaría si un hilo fuera detras de otro, no a la vez.
Por eso mismo en este programa es más rápido, ya que se ejecutan a la vez, no por turnos.

## ● ¿Qué pasa si hacéis start() y join() dentro del mismo bucle? Probadlo y
poned el tiempo real que os sale.

Al hacer start y join estaríamos rompiendo al concurrencia, esto debido a que en un bucle, si declaramos start, ejecutaremos un hilo, pero si inmediatamente después hacemos join, estaríamos parando los hilos para que vayan por turnos NO CUMPLIENDO LA CONCURRENCIA (de que funcionen a la vez).

Aquí el ejemplo:

Código (incumpliendo concurrencia):
![img1](/capturas/1.png)

Respuesta:
![img2](/capturas/2.png)
como se puede observar, los hilos se ejecutan uno despues del otro, esperando a que acabe el anterior, no a la vez

Resultado:
![img3](/capturas/3.png)
De hecho, se puede ver como tarda mucho más que si hubiera concurrencia<br>
El tiempo real, y tiempo sin concurrencia son casi lo mismo. Cercanos a 12100 ms.

# Nivel 2
Realizaré la ejecución del programa con argumentos, mostrando una salida distinta con los nombres cambiados, en ved de ser mantra, meditacion, etc serán:
Hola Bien Gracias
![img4](/capturas/4.png)

A demás de eso, observaremos como Monitor estará imprimiendo las descargas activas, y en caso de que no queden, indicará que no hay más descargas activas
![img5](/capturas/5.png)

# Nivel 3
Para estas demostraciones, no usaré los args, debido a que instalador solo buscará meditacion y mantra, si pusieramos args personalizados; Instalador funcionaría.

Inicia como cualquier otro nivel
![img6](/capturas/6.png)

A los 3 segundos, MAIN saltará indicando si en estos 3 segundos terminó meditación.<br>
En este caso, meditación tardó más de 3 segundos, por lo que indicará que meditacon.mp4 sigue en segundo plano
![img7](/capturas/7.png)

Aquí una captura de lo que sucede e caso de que sí termine antes de 3 segundos (tuve que ejecutarlo 6 veces)
![img8](/capturas/8.png)

# Incidencias

### Incidencia 1 
A la hora de sacar el tiempo total del programa, concurrentemente, pensé en sacar el tiempo del último hilo, pero esa opción no me paercía válida.<br>
Por lo que acabé aprendiendo gracias a las IA el uso de nanoTime(), el cual se puede declarar varias veces, lo declaré al principio y al final, luego, en el sout final hice el cálculo de final - pcincipio pasando la información a ms.

### Incidencia 2
Esta incidencia venía derivada de la anterior, debido a que usando nanoTime devolvía el tiempo total, por lo que tenía que dividirlo en 1_000_000.0, eso me daba muchos decimales, <br>
y al comparar el tiempo total entre lo que hubiera tardado de forma secuencial; podría dar confusión al ver un número tan grande, por lo que opté por usar String.format<br>
en el cual dejamos el resultado igual hasta el ., ("%."), luego del punto solo permitimos 0 unidades más (.0f") por lo que la operación total fue esta
String.format("%.0f", tiempoTotal / 1_000_000.0)

**Antes:**
Tiempo real: 4048.6488ms
Si no hubiera concurrencia, el programa hubiera tardado: 11802ms

**Ahora**
Tiempo real: 4048ms
Si no hubiera concurrencia, el programa hubiera tardado: 11802ms

Se observa mucho mejor la diferencia entre cifras sin los decimales.

