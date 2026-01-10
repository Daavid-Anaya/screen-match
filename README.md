# Screen Match

Proyecto desarrollado durante la Formación Java y Spring Framework G9 - ONE.

## Descripción
Screen Match es una aplicación diseñada para facilitar la administración y consulta de información relacionada con películas y series. Este proyecto busca resolver el problema del manejo desorganizado de datos sobre contenido audiovisual, permitiendo gestionar y acceder fácilmente a los detalles de cada material.

## Stack Tecnológico
- **Lenguaje:** Java (JDK 17)
- **Framework:** Spring Boot 3.x
- **Herramienta de Build:** Maven
- **Base de Datos:** Por definir durante la configuración (ej. PostgreSQL, MySQL, etc.)
- **Otras Librerías:** Lombok, Spring Security, JWT

## Prerrequisitos
Antes de empezar, asegúrate de tener instalados:
- Java JDK 17
- Maven (se recomienda utilizar el wrapper incluido: `./mvnw` para Linux/Mac o `mvnw.cmd` en Windows)
- Un gestor de base de datos (PostgreSQL, MySQL, o similar)

## Instalación y Configuración
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/Daavid-Anaya/screen-match.git
   ```
2. Configurar las variables de entorno o properties del proyecto. Crear un archivo `application.properties` en `src/main/resources/` con las siguientes claves (ejemplo):
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/screenmatch
   spring.datasource.username=usuario
   spring.datasource.password=contraseña

   jwt.secret=claveSecretaParaJWT
   ```

## Ejecución
Para ejecutar el proyecto, utiliza el siguiente comando:
```bash
./mvnw spring-boot:run
```
(O bien `mvnw.cmd spring-boot:run` en Windows).

## Documentación de la API
- **GET /movies:** Obtiene la lista de todas las películas.
- **POST /movies:** Agrega una nueva película (requiere autenticación).
- **GET /series:** Obtiene la lista de todas las series.
- **POST /series:** Agrega una nueva serie (requiere autenticación).

Para más detalles, consulta la documentación interna una vez desplegado el proyecto.