# Documentación del Proyecto: LibreCritic

Este documento ofrece una visión general de la arquitectura y el propósito de los archivos principales del proyecto.

## Estructura de Paquetes y Clases

### 1. Configuración (`com.videogame.wiki.config`)
- **`WikiApplication.java`**: La clase principal que inicia la aplicación Spring Boot.
- **`SecurityConfig.java`**: Configura la seguridad de la aplicación. Define qué rutas son públicas (login, registro, recursos estáticos) y cuáles requieren autenticación o rol de ADMIN (crear/editar juegos). También gestiona el cifrado de contraseñas, el formulario de login y la desconexión (logout).
- **`DataInitializer.java`**: Clase que se ejecuta al arrancar la aplicación para poblar la base de datos con roles iniciales (ADMIN, USER), un usuario administrador por defecto y algunos videojuegos de prueba.
- **`MvcConfig.java`**: Configuración adicional de Spring MVC, como el mapeo de recursos externos (por ejemplo, para servir las imágenes subidas).

### 2. Controladores (`com.videogame.wiki.controller`)
- **`VideogameController.java`**: Punto neurálgico para la visualización y gestión de videojuegos. Maneja rutas estáticas y dinámicas, subida de imágenes (MultipartFile), uso de cookies para contar visitas y uso de sesión para recordar el último juego visitado.
- **`AuthController.java`**: Gestiona las rutas de autenticación (Login y Registro). Procesa los formularios de registro y valida si el nombre de usuario ya existe.
- **`ProfileController.java`**: Permite a los usuarios ver su perfil, editar su biografía y ver los perfiles de otros usuarios, además de gestionar las funciones de seguimiento (follow/unfollow).
- **`ReviewController.java`**: Maneja la creación y eliminación de reseñas de videojuegos.
- **`CustomErrorController.java`**: Captura errores comunes (404, 403, 500) y redirige a plantillas personalizadas para mejorar la experiencia del usuario.

### 3. Modelos y DTOs (`com.videogame.wiki.model` / `dto`)
- **`Videogame.java`**: Entidad que representa un videojuego con atributos como título, descripción, desarrollador, fecha de lanzamiento y ruta de la imagen.
- **`User.java`**: Entidad para los usuarios con su nombre de usuario, contraseña cifrada, biografía y relaciones con roles, seguidores y seguidos.
- **`Role.java`**: Representa los permisos (ROLE_USER, ROLE_ADMIN).
- **`Review.java`**: Representa una reseña escrita por un usuario para un juego específico, con calificación y comentario.
- **`UserRegistrationDto.java`**: Objeto de transferencia de datos usado específicamente para capturar los datos del formulario de registro de forma segura.

### 4. Repositorios (`com.videogame.wiki.repository`)
Interfaces que extienden `JpaRepository` para interactuar con la base de datos de forma sencilla (consultas, inserciones y borrados) sin necesidad de SQL manual complejo.

### 5. Servicios (`com.videogame.wiki.service`)
- **`UserService.java` / `VideogameService.java` / `ReviewService.java`**: Capas de servicio que contienen la lógica de negocio antes de llamar a los repositorios.
- **`CustomUserDetailsService.java`**: Implementación requerida por Spring Security para buscar usuarios en la base de datos durante el proceso de login.

## Vistas (Plantillas Thymeleaf)

- **`layout.html`**: Contiene fragmentos comunes como la barra de navegación (navbar) y el pie de página (footer).
- **`index.html`**: Página de inicio que muestra la lista de todos los juegos.
- **`videogame_detail.html`**: Muestra información detallada de un juego y sus reseñas.
- **`videogame_form.html`**: Formulario dinámico usado tanto para añadir nuevos juegos como para editar los existentes.
- **`login.html`** / **`register.html`**: Formularios de acceso y creación de cuenta.
- **`profile.html`**: Muestra el perfil del usuario, estadísticas de seguidores y sus reseñas publicadas.
- **`error.html`**, **`403.html`**, **`404.html`**: Páginas personalizadas para la gestión de errores.

## Funcionalidades Clave Implementadas
- **Rutas Dinámicas**: Uso de variables en la URL (p.ej. `/videogames/{id}`).
- **Formularios HTML**: Gestión de envíos POST con validación de datos.
- **Subida de Archivos**: Almacenamiento de carátulas de juegos en el servidor.
- **Persistencia**: Uso de Hibernate (ORM) y H2 (Base de Datos en Memoria).
- **Sesiones y Cookies**: Seguimiento de actividad y personalización.
- **Seguridad**: Control de acceso basado en roles y permisos.
