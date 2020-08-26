# Arsw-lab3

## Integrantes:
 - Carlos Julian Gomez Ardila
 - Daniel Felipe Walteros Trujillo
 
 ## Introducción A Spring
 
 Por medio de etiqutas el framework de Spring realiza la inyección de dependencias de forma automática, en el archivo xml se configuraron    los beans de las inyecciones respectivas:
 
 ![Archivo de Configuracion XML](https://github.com/Silenrate/ARSW-2020-1/blob/master/Labs/arsw-lab3/img/introxml.PNG)
 
 Además en las clases de estos beans se deben poner las etiquetas:
  - @Service con el identificador del servicio
  - @Autowired, sobre el atributo que va a ser inyectado
  - @Qualifier, para especificar la etiqueta de Servicio perteneciente a la clase que va a ser inyectada
  
 ![Configuracion English Spell Checker](https://github.com/Silenrate/ARSW-2020-1/blob/master/Labs/arsw-lab3/img/introenglish.PNG)
 
 ![Configuracion Atributo Inyectado con Preuba](https://github.com/Silenrate/ARSW-2020-1/blob/master/Labs/arsw-lab3/img/introinyeccion.PNG)
 
 Para cambiar la inyeccion de dependencias basta con borrar las etiquetas de la clase que se estaba inyectandor y ponerlas en la nueva clase
 
 ![Configuracion Spanish Spell Checker](https://github.com/Silenrate/ARSW-2020-1/blob/master/Labs/arsw-lab3/img/introspanish.PNG)
 
 ![Resultados cambio de ejecución](https://github.com/Silenrate/ARSW-2020-1/blob/master/Labs/arsw-lab3/img/introparte2.PNG)
 
 ## Blueprint Management Part 1
 
 Se implementó la inyección de dependencias y se generó un programa para probar las funconalidades:
 
 ![Prueba programa de Servicios](https://github.com/Silenrate/ARSW-2020-1/blob/master/Labs/arsw-lab3/img/MAIN.PNG)
 
 Se probó la inyección por cada filtro y se realizaron las pruebas respectivas:
 
![Inyeccion de Atributos de Servicio](https://github.com/Silenrate/ARSW-2020-1/blob/master/Labs/arsw-lab3/img/blueprintservices.PNG)
 
 ### Redundancy Filter
 
 ![Inyeccion de Redundancy Filter](https://github.com/Silenrate/ARSW-2020-1/blob/master/Labs/arsw-lab3/img/redundancy.PNG)
 
 ![Prueba de filtro redundante](https://github.com/Silenrate/ARSW-2020-1/blob/master/Labs/arsw-lab3/img/redundancymain.PNG)
 
 ### Subsampler Filter
 
 ![Inyeccion de Subsampler Filter](https://github.com/Silenrate/ARSW-2020-1/blob/master/Labs/arsw-lab3/img/subsampler.PNG)
 
 ![Prueba de filtro de submuestreo](https://github.com/Silenrate/ARSW-2020-1/blob/master/Labs/arsw-lab3/img/subsamplermain.PNG)
 
