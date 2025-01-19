# Challenge Forohub - Programa ONE 
---

## Descripción

Este proyecto es mi versión del challenge "FOROHUB" del programa ONE de Alura Latam y Oracle. Desarrollé una API RESTful funcional para la gestión de publicaciones, respuestas, y usuarios en una página de foros, implementando conceptos clave como el mapeo de solicitudes HTTP, exclusión lógica, validaciones y acceso a datos con Spring Data JPA. Además, incorpora funcionalidades avanzadas como autenticación y autorización utilizando JWT (JSON Web Token) y la integración de Spring Security para proteger los endpoints de la API. 

Como extra, se implementó Spring Doc utilizando Swagger y OpenAPI para generar una documentación interactiva y visualmente atractiva de la API.

La base de datos está construida en **MySQL**, y se utiliza **Flyway** para gestionar las migraciones de base de datos de manera eficiente, manteniendo un control de versiones claro para los cambios en la estructura de la base de datos.

---

## ¿Qué hace esta API?

La API permite realizar operaciones CRUD sobre los tópicos y las respuestas del foro, así como gestionar los usuarios. A continuación, se detallan los endpoints principales:

### Tópico
  - `POST /topics`: Crear un nuevo tópico.
  - `GET /topics`: Obtener un listado de tópicos.
  - `GET /topics/{id}`: Buscar un tópico por ID.
  - `PUT /topics/{id}`: Actualizar la información de un tópico (título, mensaje, o curso).
  - `DELETE /topics/{id}`: Eliminar un tópico de forma lógica (se marca como inactivo con un campo `activo`).

### Respuesta
  - `POST /responses`: Responder a un tópico.
  - `PUT /responses/{id}`: Actualizar la información de una respuesta (mensaje).
  - `DELETE /responses/{id}`: Eliminar una respuesta de forma lógica (se marca como inactiva).

### Usuario
  - `POST /users`: Registrar un nuevo usuario (con nombre de usuario y contraseña).

### Login
  - `POST /login`: Autenticación de usuario. Se ingresa el ID del usuario y su contraseña para obtener un token JWT. Este token es necesario para realizar todas las operaciones CRUD mencionadas anteriormente, excepto para el registro de nuevos usuarios.

---

## Tecnologías implementadas

- **Java 17**: Versión utilizada para el desarrollo del backend.
- **Spring Boot 3.x**: Framework principal para la creación de la API RESTful.
  - **Spring Web**: Para manejar solicitudes HTTP y exponer los endpoints.
  - **Spring Data JPA**: Para interactuar con la base de datos utilizando el ORM Hibernate.
  - **Spring Validation**: Para la validación de los datos de entrada.
  - **Spring Security**: Para la protección de los endpoints mediante autenticación basada en JWT.
  - **Flyway**: Para gestionar las migraciones de base de datos de manera eficiente, con versionado y trazabilidad.
- **Hibernate**: Para la persistencia de datos y gestión de entidades.
- **MySQL**: Base de datos utilizada para almacenar la información del foro.
- **Insomnia**: Herramienta utilizada para probar los endpoints de la API.
- **Maven**: Para la gestión de dependencias y construcción del proyecto.
- **Lombok**: Para simplificar la escritura de código, reduciendo el boilerplate (por ejemplo, getters, setters, constructores).
- **Auth0 Java JWT**: Para la generación y validación de tokens JWT, utilizados en la autenticación y autorización de los usuarios.

---

## ¿Cómo usarlo?

### 1. Clonar el repositorio

Primero, debes clonar el repositorio de este proyecto a tu máquina local:

bash
git clone https://github.com/tu-usuario/forohub-api.git

## ¿Cómo usarlo?

### 2. Configurar la base de datos

Este proyecto utiliza MySQL como base de datos. Asegúrate de tener MySQL instalado y configurado en tu máquina. Luego, crea una base de datos llamada `forohub` y configura el archivo `application.properties` para que coincida con tu configuración de base de datos:

**properties**
spring.datasource.url=jdbc:mysql://TU LOCALHOST Y SU PUERTO/EL NOMBRE DE TU BASE DE DATOS
spring.datasource.username=TU USUARIO
spring.datasource.password=TU CONTRASEÑA
sspring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.error.include-stacktrace=never
spring.jpa.hibernate.ddl-auto=update

api.security.secret=TU VARIABLE JWT --> Configura tu variable de entorno a un valor por defecto

###3. Ejecutar el proyecto
Una vez que hayas configurado la base de datos y las dependencias, puedes ejecutar el archivo `ForohubApplication.java` desde tu IDE preferido (como IntelliJ IDEA o VSCode).

### 4. Probar los endpoints

Puedes probar los endpoints de la API utilizando herramientas como **Insomnia** o **Postman**. Asegúrate de tener el token JWT para autenticarte al realizar operaciones CRUD.

#### Ejemplo de obtener un token JWT:

Para obtener un token, realiza una solicitud `POST` a `/login` con el `id` y `contraseña` del usuario:

{
  "id": "usuario123",
  "password": "contraseña"
}

El servidor devolverá un token JWT que debes incluir en el encabezado Authorization de tus solicitudes a los endpoints protegidos.

Asegúrate de primero tener un usuario registrado. Verifica bien los endpoints en los archivos del package `controller`.

## ¿Cómo es escalable este proyecto?

Este proyecto está diseñado para ser escalable de diversas maneras:

- **Modelo de Base de Datos**: Utilizando Spring Data JPA, la integración con la base de datos es flexible, lo que permite añadir nuevas entidades o relaciones entre ellas sin dificultad. Flyway facilita el control de versiones en la base de datos, permitiendo realizar cambios sin perder consistencia.

- **Arquitectura RESTful**: La API está construida bajo los principios REST, lo que facilita su escalabilidad. Los endpoints son independientes y pueden gestionarse de manera autónoma a medida que la aplicación crece.

- **Autenticación y Seguridad**: La implementación de JWT y Spring Security asegura que los endpoints puedan protegerse de manera eficiente, facilitando la incorporación de nuevos roles y permisos en el futuro, sin afectar el rendimiento de la API.

- **Modularidad**: El uso de Spring Boot y la organización del proyecto en paquetes hace que el sistema sea fácil de mantener y extender. Nuevas funcionalidades pueden ser añadidas sin necesidad de reestructurar la aplicación existente.

- **Microservicios**: A medida que el proyecto crezca, es posible dividir la funcionalidad en microservicios para distribuir la carga de trabajo y mejorar el rendimiento y la disponibilidad.

---

## Conclusión

El proyecto **Forohub** demuestra la integración de tecnologías modernas como Spring Boot, Spring Security, Flyway, y JWT para construir una API RESTful robusta y segura. La base de datos MySQL y el uso de herramientas como Insomnia para probar los endpoints contribuyen a un entorno de desarrollo eficiente y controlado. La escalabilidad del proyecto es una prioridad, lo que facilita la expansión a medida que aumentan los requisitos o la carga de usuarios.

Este es un excelente ejemplo de cómo estructurar una aplicación moderna utilizando las mejores prácticas en términos de seguridad, gestión de datos y diseño de API.





