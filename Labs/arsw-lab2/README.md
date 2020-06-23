# Arsw-lab2

## Integrantes:
 - Carlos Julian Gomez Ardila
 - Daniel Felipe Walteros Trujillo
 
 ## Parte 1
 
 ![](https://github.com/Silenrate/arsw-lab2/blob/master/images/CPU1.PNG)
 
 1) Why is this consumption? 
 
 R: Porque solamente se están creando dos hilos que actuan paralelamente.
 
 Which is the responsible class? 
 
 R: "StartProduction" porque es la clase que crea y dispara el hilo del productor y el hilo del consumidor.
 
 2)
 
 ![](https://github.com/Silenrate/arsw-lab2/blob/master/images/CPU2.PNG)
 
 3)
 
 ![](https://github.com/Silenrate/arsw-lab2/blob/master/images/CPU3.PNG)
 
 ## Parte 2
 
 2) Para N jugadores, la invariante o suma de las vidas es 100 * N, debido a que cada jugar inicia con 100 de vida.
 
 3) La invariante no se cumple, la suma de las vidas va aumentando en vez de mantenerse.
 
 5) Una vez implementado "Pause and Check" y "Resume", la invariante sigue sin cumplirse.
 
 6) Las regiones críticas que identificamos, son en las que se hacen operaciones con respecto a las vidas de cada inmortal (Fight).
 
 
 
