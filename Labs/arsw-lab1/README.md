# Arsw-lab1

## Integrantes:
 - Carlos Julian Gomez Ardila
 - Daniel Felipe Walteros Trujillo

### BBP FORMULA

#### Compile instructions

Para compilar la aplicacion por medio de Maven es necesario abrir la consola de comandos, ubicarse en la carpeta donde tenemos la aplicacion, en este caso, la carpeta es "BBP_FORMULA\PARALLELISM-JAVA_THREADS_MAVEN-INTRODUCTION_BBP_FORMULA" y aplicar el siguiente comando:

![](https://github.com/Silenrate/ARSW-2020-1/blob/master/Labs/arsw-lab1/Images/mvnPackage.PNG)

Y como resultado obtendremos lo siguiente:

![](https://github.com/Silenrate/ARSW-2020-1/blob/master/Labs/arsw-lab1/Images/mvnPackageResult.PNG)

#### Run instructions

La ejecucion de una aplicacion por medio de Maven consiste, al igual que la compilación, ubicarse primeramente en la carpeta de la aplicación y abrir la consola de comandos, seguidamente escribir el siguiente comando:

![](https://github.com/Silenrate/ARSW-2020-1/blob/master/Labs/arsw-lab1/Images/mvnExec.PNG)

Y como resultado de la primera ejecucion se descargarán dependencias, plugins, etc, y ejecutará el main del programa:

![](https://github.com/Silenrate/ARSW-2020-1/blob/master/Labs/arsw-lab1/Images/mvnExecResult.PNG)

#### Part I - Introduction to Java Threads

La diferencia entre usar el metodo run y el metodo start fue el orden de salida del programa, utilizando el metodo start inician de forma concurrente por lo que el resultado sale en cualquier orden, pero utilizando el metodo run se ejecutaron de forma secuencial.
 
 #### Part III - Performance Evaluation
 
 #### CON 1 HILO:
 
 ![](https://github.com/Silenrate/ARSW-2020-1/blob/master/Labs/arsw-lab1/Images/1%20hilo.png)
 
 #### CON 4 HILOS:
 
 ![](https://github.com/Silenrate/ARSW-2020-1/blob/master/Labs/arsw-lab1/Images/Con%204%20hilos.png)
 
 #### CON 8 HILOS:
 
 ![](https://github.com/Silenrate/ARSW-2020-1/blob/master/Labs/arsw-lab1/Images/Con%208%20hilos.png)
 
 #### CON 200 HILOS:
 
 ![](https://github.com/Silenrate/ARSW-2020-1/blob/master/Labs/arsw-lab1/Images/Con%20200%20hilos.PNG)
 
 #### CON 500 HILOS:
 
 ![](https://github.com/Silenrate/ARSW-2020-1/blob/master/Labs/arsw-lab1/Images/Con%20500%20hilos.PNG)
 
 #### TABLA - HILOS VS TIEMPO
 
 ![Tabla](https://user-images.githubusercontent.com/53835467/73324737-02b1a180-421a-11ea-9883-520770b2f074.PNG)
 
 #### GRAFICA - HILOS VS TIEMPO
 
 ![Grafica](https://user-images.githubusercontent.com/53835467/73324741-05ac9200-421a-11ea-9ca4-36dc75d99f86.PNG)
 
Como podemos ver desde que se usan 4 hilos los tiempos se hicieron constantes debido a que mayor numero de hilos requiere hacer mas cantidad de cambios de contexto o interliving

#### PREGUNTAS:

1) According to Amdahls law, where S (n) is the theoretical performance improvement, P the parallel fraction of the algorithm, and n the number of threads, the greater n, the greater the improvement should be. Why is the best performance not achieved with the 500 threads? How does this performance compare when 200 are used?. 

R: Con 500 hilos no se alcanza la mejor ejecucion debido a que el schedule de la CPU tiene que asignar cada hilo a un tiempo en particular haciendo qe los hilos compitan por el schedule.

2) How does the solution behave using as many processing threads as cores compared to the result of using twice as much?

R: Debería ser mas eficiente usando el doble porque no sobrecarga la CPU.

3) According to the above, if for this problem instead of 500 threads on a single CPU, 1 wire could be used on each of 500 hypothetical machines, would Amdahls's law be better applied? If, instead, c threads were used in 500 / c distributed machines (where c is the number of cores of said machines), would it be improved? Explain your answer.

R: Debería mejorar porque no hay schedule que interrumpa los procesos y cada maquina hará su parte.
 
 ### DOGS RACE
 
 #### Compile instructions

Para compilar la aplicacion por medio de Maven es necesario abrir la consola de comandos, ubicarse en la carpeta donde tenemos la aplicacion, en este caso, la carpeta es "CONCURRENT_PROGRAMMING-JAVA_MAVEN-DOGS_RACE\parte1" y aplicar el siguiente comando:

![](https://github.com/Silenrate/ARSW-2020-1/blob/master/Labs/arsw-lab1/Images/mvnPackage2.PNG)

Y como resultado obtendremos lo siguiente:

![](https://github.com/Silenrate/ARSW-2020-1/blob/master/Labs/arsw-lab1/Images/mvnPackageResult2.PNG)

#### Run instructions

La ejecucion de una aplicacion por medio de Maven consiste, al igual que la compilación, ubicarse primeramente en la carpeta de la aplicación y abrir la consola de comandos, seguidamente escribir el siguiente comando:

![](https://github.com/Silenrate/ARSW-2020-1/blob/master/Labs/arsw-lab1/Images/mvnExec2.PNG)

Y como resultado de la primera ejecucion se descargarán dependencias, plugins, etc, y ejecutará el main del programa:

 ![](https://github.com/Silenrate/ARSW-2020-1/blob/master/Labs/arsw-lab1/Images/mvnExecResult2.PNG)
 
 #### Part I
 
 ![](https://github.com/Silenrate/ARSW-2020-1/blob/master/Labs/arsw-lab1/Images/Cores.png)
 
