<h1 align="center">ScreenMatch</h1>

<p align="center">
   <img src="https://img.shields.io/badge/STATUS-EN%20DESARROLLO-blue">
   <img src="https://img.shields.io/badge/Java-21%2B-blue">
</p>

Proyecto desarrollado durante la Formación Java y Spring Framework G9 - ONE.

## 📝 Descripción
ScreenMatch es una aplicación diseñada para facilitar la administración y consulta de información relacionada con películas y series. Este proyecto busca resolver el problema del manejo desorganizado de datos sobre contenido audiovisual, permitiendo gestionar y acceder fácilmente a los detalles de cada material.

---

## ✨ Características
- 

---

## 🖥 Preview
<p align="center">
  <img src="./src/main/resources/img/screenshot.png" alt="Screenshot" with="200">
</p>

---

## 🛠️ Stack Tecnológico
<div align="center">
  
  | Tecnología  |                 Descrpción               |                              Icon                               |
  | :---------: | :--------------------------------------: | :-------------------------------------------------------------: |
  |    Java     |         Lenguaje de programación         |   <img src="https://skillicons.dev/icons?i=java" width="48">    |
  |     Git     |      Sistema de control de versiones     |    <img src="https://skillicons.dev/icons?i=git" width="48">    |
  |    Maven    |    gestión y construcción de proyecto    |   <img src="https://skillicons.dev/icons?i=maven" width="48">   |
  | Spring Boot |                 Framework                |   <img src="https://skillicons.dev/icons?i=spring" width="48">  |
  |  PostgreSQL | sistema almacenar y gestionar datos (DB) | <img src="https://skillicons.dev/icons?i=postgres" width="48">  |
   
</div>

---

## 📂 Estructura del Proyecto

### Descripcion de Paquetes

---

## ✅ Prerrequisitos
Antes de empezar, asegúrate de tener instalados:
- Java JDK 21+
- Maven (se recomienda utilizar el wrapper incluido: `./mvnw` para Linux/Mac o `mvnw.cmd` en Windows)
- Un gestor de base de datos (PostgreSQL)

---

##  Instalación y Configuración
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
