# Proyecto Integrador UEM - MEMORIA 

- [Proyecto Integrador UEM - MEMORIA](#proyecto-integrador-uem---memoria)
  - [1. Descripción del proyecto](#1-descripción-del-proyecto)
  - [2. Enfoque](#2-enfoque)
  - [3. Navigación en la aplicación](#3-navigación-en-la-aplicación)

![LogoPharmaCode](Documentacion/Imagenes/LogoPharma.png)

**Integrantes de PharmaCode:**
- Cloud Master ----- JUN ZHOU
- Scrum Master ----- RUBEN BALBOA
- Designers ------ DIEGO RODRIGUEZ, JAIME ORTIGOSA


## 1. Descripción del proyecto
PharmaCode es un software desarrollado en Java usando la biblioteca Swing.
Se trata de una aplicación de escritorio backoffice dirigida a satisfacer las necesidades del sector farmacéutico en todo lo relativo a la gestión de una farmacia.

Como vemos en el siguiente diagrama de casos de uso, tendremos dos actores; por un lado, el empleado y por otro el administrador.

![DiagramaCasosUso](./Documentacion/Imagenes/DiagramaCasosUso.png)

## 2. Enfoque
El equipo de PharmaCode ha querido orientar la aplicación a la facilidad de uso para que todo sea autodescriptivo y sin pérdidas para el usuario final.
El objetivo de la aplicación es que los usuarios puedan administrar tanto proveedores, productos como ventas de la farmacia con la máxima facilidad.

La aplicación consta de un menú principal que de despliega en submenús con los diferentes paneles a los que puede acceder el usuario y, de esa forma, acceder a las diferentes áreas de la aplicación.

Como resultado de este objetivo, el usuario puede recorrer la aplicación y encontrar la información deseada de forma rápida.

Para desarrollar la misma, hemos utilizado el patrón MVC y, así, tener el objetivo de cada clase dividido para poder favorecer a la modularización, favorecer la realización de pruebas unitarias, hacer una aplicación más limpia y mantenible en el tiempo.

En lo relativo a los datos, hemos utilizado una BBDD relacional alojada en SQLite de forma local para dotar a la aplicación con información suficiente para poder trabajar.
Aquí vemos el modelo relacional de la aplicación:

![ModeloRelacional](Documentacion/Imagenes/MER.png)


## 3. Navigación en la aplicación

