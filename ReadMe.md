

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
![](/capturas/1.png)

Respuesta:
![](/capturas/2.png)
como se puede observar, los hilos se ejecutan uno despues del otro, esperando a que acabe el anterior, no a la vez

Resultado:
![](/capturas/3.png)
De hecho, se puede ver como tarda mucho más que si hubiera concurrencia<br>
El tiempo real, y tiempo sin concurrencia son casi lo mismo. Cercanos a 12100 ms.

# Nivel 2



# Nivel 3



#Incidencias

###Incidencia 1 
A la hora de sacar el tiempo total del programa, concurrentemente, pensé en sacar el tiempo del último hilo, pero esa opción no me paercía válida.<br>
Por lo que acabé aprendiendo gracias a las IA el uso de nanoTime(), el cual se puede declarar varias veces, lo declaré al principio y al final, luego, en el sout final hice el cálculo de final - pcincipio pasando la información a ms.


