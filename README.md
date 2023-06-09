# ![Logo-template](img/logo.png)
# Recurso Java: Reto técnico

>El objetivo de este documento es proveer información relacionada del Proyecto que ha sido implementado sobre Java, MongoDB, Swagger.

### 1.  Funcionalidad
>Este proyecto Java tiene como objetivo devolver la cantidad de viajes totales, cantidad de viajes totales por ciudad, cantidad de viajes totales por país, crear un viaje, actualizar un viaje y consultar los viajes actuales.

### 2.  Preparación de ambiente
>Este **proyecto** brinda diversas capacidades a los aplicativos para poder recuperar datos de manera segura y fácil mediante los siguientes pasos:

#### 2.1. MongoDB: 
>Programa que es necesario para el funcionamiento y conexión de la aplicación.

##### 2.1.1 Credenciales

| # | Propiedad     | Valor   |
| :----|:-------------:| -----:|
|1| database | Reto   |
|2| username | tekbees   |
|3| password | 8mh2JWCwdf9rMP2F   |
|4| URL Conexión | mongodb+srv://tekbees:8mh2JWCwdf9rMP2F@tekbees.9vixudx.mongodb.net/?retryWrites=true&w=majority   |

#### 2.2. Java: 
>Programa que es necesario para el funcionamiento y compilación de la aplicación.

##### 2.2.1 Credenciales

| # | Propiedad     | Valor   |
| :----|:-------------:| -----:|
|1| Versión | JDK 17   |
|2| Puerto | 7559   |
|3| URL descarga | https://jdk.java.net/archive/   |

#### 2.3. Swagger: 
>Programa que es necesario para la documentación de la aplicación.

##### 2.3.1 Credenciales

| # | Propiedad     | Valor   |
| :----|:-------------:| -----:|
|1| URL local | http://localhost:7559/swagger-ui.html   |

### 3.  GitFlow
En este apartado se detalla un historial de cambios o *changelog* por cada rama implementada del recurso.

#### 3.1. Develop: 
Rama de desarrollo en la cual se han implementado todos los commits.

#### 3.2. Main: 
Rama principal en la cual se ha poblado con la aprobación de pull request de Develop hacia Main.

### 4.  Versiones
En este apartado se detalla un historial de cambios o *changelog* por cada version implementada del recurso.

+ Versión 1.0.0: Esta versión permite listar, insertar y actualizar campos.

### 5.  Diagrama de Arquitectura
# ![diagrama](img/Diagrama.png)

### 6.  Pasos a seguir y recomendaciones
En este apartado se detalla los pasos a seguir y las recomendaciones implementadas del recurso.

#### 6.1. Pasos a seguir: 
+ Instalar JDK: Se requiere de la versión Java 17 para poder ejecutar el proyecto correctamente.
+ Postman: Se requiere del software Postman para las pruebas de los end-points.
+ Puerto local: Se requiere que el puerto 7559 esta sin uso.

#### 6.2. Recomendaciones: 
+ Postman: Se ha dejado un archivo llamado "Tekbees.postman_collection.json", el cual se puede importar en el programa Postman y hacer las pruebas a los end-points.
+ Swagger: Una vez levantado el proyecto, se recomienda acceder a la ruta "http://localhost:7559/swagger-ui.html" para la lectura del funcionamiento de cada end-point.

