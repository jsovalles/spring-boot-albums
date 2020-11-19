# spring-boot-albums
Proyecto en Spring Boot acerca de álbumes usando APIs terceras con uso de bases de datos en memoria H2.

## Instalación/Despliegue

Se descarga el proyecto desde GitHub y después se realiza los siguientes comandos
```
mvn clean install

mvn spring-boot:run
```
Después de que el comando termine de desplegar se puede consumir los servicios correspondientes.

## Descripción de los diferentes servicios con ejemplos

**listUsers:** Servicio web encargado de mostrar todos los clientes registrados

```
listUsers (GET) - http://localhost:8080/albums/v0/users
```

**listPhotos:** Servicio web encargado de mostrar las fotos registradas de todos los álbumes

```
listPhotos (GET) - http://localhost:8080/albums/v0/photos
```

**listAlbums:** Servicio web encargado de mostrar todos los álbumes registrados

```
listAlbums (GET) - http://localhost:8080/albums/v0/albums
```

**listAlbumsFromUser:** Servicio web encargado de mostrar todos los álbumes registrados hacia un cliente en particular

```
listAlbumsFromUser (GET) - http://localhost:8080/albums/v0/users/3/albums
```

**listPhotosFromUser:** Servicio web encargado de mostrar todos los álbumes registrados hacia un cliente en particular

Este servicio hacia dos llamados a la API externa ya que la información persiste desde **JSONPlaceHolder** y no desde una base de datos definida

```
listPhotosFromUser (GET) - http://localhost:8080/albums/v0/users/4/photos
```

**createUserPermissions:** Servicio web encargado de crear los permisos entre un usuario y un álbum el cual no sea propio

Si en el proceso de creación se observa que el usuario tiene el álbum propio genera un error; de igual forma si se trata de generar el mismo permiso entre el usuario y el álbum.

Los permisos existentes son solamente dos:
-	1 (READ_ONLY)
-	2 (WRITE_READ)

```
createUserPermissions (POST) - http://localhost:8080/albums/v0/permissions

Body:
{
    "userId": 2,
    "albumId": 6,
    "role":{
        "id": 2
    }
}
```

**updateUserPermissions:** Servicio web encargado de actualizar los permisos entre un usuario y un álbum el cual no sea propio

Si en el proceso de actualización se observa que el permiso no existe en la base de datos se indica como un error que se debe primero crear este recurso de permisos.

Los permisos existentes son solamente dos:
-	1 (READ_ONLY)
-	2 (WRITE_READ)

```
updateUserPermissions (PUT) - http://localhost:8080/albums/v0/permissions

Body:
{
    "userId": 2,
    "albumId": 6,
    "role":{
        "id": 2
    }
}
```

**listUsersWithAlbumPermissions:** Servicio web encargado de mostrar los usuarios que tienen un permiso determinado respecto a un álbum específico.

Este servicio hace dos llamados, uno a la base de datos H2 de los registros de todos los usuarios con el permiso especificado y después a partir de la identificación de cada usuario lo busca mediante **JSONPlaceHolder** y lo guardar en una lista de usuarios para obtener la respuesta.

```
listUsersWithAlbumPermissions (GET) - http://localhost:8080/albums/v0/permissions/albums/24?role=READ_ONLY
```

**listUserComments:** Servicio web encargado de mostrar todos los comentarios registrados de un cliente.

Este servicio tiene dos queries, uno para buscar de acuerdo al campo **name** y otro indicando la identificación del usuario; en el segundo caso hace llamado a dos servicios de **JSONPlaceHolder**, uno para obtener todos los Posts del usuario especificado y otro a partir del Id de cada post obtener los comentarios.

```
listUserComments (GET with name query) - http://localhost:8080/albums/v0/comments?name=id labore ex et quam laborum

listUserComments (GET with userId query) - http://localhost:8080/albums/v0/comments?userId=2
```

Para observar  la bases de datos H2 de la API, se puede acceder a la consola en la siguiente ruta:
```
http://localhost:8080/h2-console
```
*Donde:*
- Ruta de la base de datos: jdbc:h2:mem:albumsdb
- Usuario: sebastian
- Clave: so

Ejemplo:

![enter image description here](https://i.ibb.co/vQPNZML/H2database.png)

## Descripción del proyecto

El objetivo es desarrollar una API para consumir un servicio externo y utilizar su información, además se tiene que ampliar el scope del desarrollo agregando una funcionalidad más que permita persistir información nueva el sistema relacionada con los datos de la API externa.

El único requisito obligatorio es usar Java con Spring Boot como tecnología de desarrollo y alojar en un repositorio en Github o Gitlab, como asi también ir commiteando a medida que se realizan avances, inclusive si estos contemplan correcciones/cambios que se van realizando a lo largo de la resolución, y no todo en un solo commit. Como regla general, 10-20 commits suele ser razonable, siendo 1-5 muy poco y más de 30 demasiado.

### Problema de negocio
Se tiene que consumir información del siguiente servicio externo que cuenta con los datos de usuarios, sus álbumes y sus fotos; además de sus posts y los comentarios de otros usuarios sobre ellos: [JSONPlaceHolder](https://jsonplaceholder.typicode.com/)

De la información del servicio se tiene que poder acceder a través de nuestra API a:
- Los usuarios.
- Las fotos.
- Los álbumes del sistema y de cada usuario.
- **Plus:** Las fotos de un usuario.

De esta forma la API va a poder concentrar esta información para luego poder extenderla a una nueva funcionalidad, la cual consiste en implementar una gestión de permisos básica a cada albúm de fotos (lectura y escritura) para compartir álbumes entre los usuarios de la plataforma. De esta manera se debe registrar qué usuarios tienen acceso a álbumes que no son propios y los permisos de dicho usuario para ese álbum. Para persistir y consumir esta información nueva el sistema debe permitir:
- Registrar un álbum compartido con un usuario y sus permisos.
- Cambiar los permisos de un usuario para un álbum determinado.
- Traer todos los usuarios que tienen un permiso determinado respecto a un
álbum específico.

En cuanto a los comentarios, se espera que la aplicación pueda traerlos del servicio externo brindando la posibilidad de filtrar por el campo “name” o por el usuario que realizó dicho comentario.

# Desarrollado en

* [IntelliJ IDEA](https://www.jetbrains.com/idea/) - The Java IDE
* [Maven](https://maven.apache.org/) - Dependency Management
* Java 11 JDK
