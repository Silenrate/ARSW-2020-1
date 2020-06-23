# Arsw-lab4

## Integrantes:
 - Carlos Julian Gomez Ardila
 - Daniel Felipe Walteros Trujillo


## Parte 2 

**Hacemos Post:**

![](https://github.com/Silenrate/arsw-lab4/blob/master/img/POST.png)

![](https://github.com/Silenrate/arsw-lab4/blob/master/img/FRUTA.png)

![](https://github.com/Silenrate/arsw-lab4/blob/master/img/Fruta2.png)

**Hacemos update:**

![](https://github.com/Silenrate/arsw-lab4/blob/master/img/UPDATE.png)

![](https://github.com/Silenrate/arsw-lab4/blob/master/img/ARBOL.png)

Si tratamos de hacer una actualizacion a un plano pero que no existe el usuario nos debe sacar una excepción:

![](https://github.com/Silenrate/arsw-lab4/blob/master/img/Error.png)

## Parte 3

### ¿Qué condiciones de carrera pueden ocurrir?

Al realizar un registro de un plano, puede ocurrir que al mismo tiempo se realice una consulta donde ese plano esté incluido, en ese caso no se encontrará el plano recien registrado a menos que se realice otra vez la consulta.

Al modificar los puntos de un plano, puede ocurrir que al mismo tiempo se realice una consulta donde ese plano esté incluido, en ese caso no se encontrará el plano con su ultima actualización a menos que se realice otra vez la consulta.

### ¿Cuales son las zonas críticas?

Con la implementación en memoria de los servicios, las zonas críticas ocurren al realizar inserciones, modificaciones o eliminaciones sobe el HashMap con los planos.

### Análisis

Con base en las zonas críticas, una buena forma de solucionar estos errores es utilizando la clase ConcurrentHashMap que soporta la mismas operaciones del anterior HashMap, pero también soporta las peticiones concurrentes.
También se podría utilizar un bloque de sincronía sobre el HashMap, pero esto puede ralentizar más el proceso que la solución previa.
