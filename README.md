# TestPayMind

App de autenticación y consulta de transacciones desarrollada para la prueba técnica de PayMind.

## Qué hace la app

La aplicación tiene 3 pantallas principales:

1. **Login**: Autenticación con username y contraseña
2. **Home**: Pantalla de bienvenida que muestra el nombre del usuario
3. **Transacciones**: Lista de transacciones con filtros por fecha y ordenamiento

## Cómo ejecutar el proyecto

### Requisitos previos

- Android Studio Narwhal 4 Feature Drop | 2025.1.4
- JDK 21
- SDK Android mínimo nivel 27

### Pasos

1. Clonar el repo:

```bash
git clone https://github.com/tu-usuario/TestPayMind.git
cd TestPayMind
```

2. Configurar las variables de entorno:

El proyecto necesita archivos `.env.dev` y `.env.prod` con las URLs del API. Copia los archivos de ejemplo:

```bash
cp .env.dev.example .env.dev
cp .env.prod.example .env.prod
```

Edita los archivos `.env.dev` y `.env.prod` con las URLs correctas:

```env
BASE_URL=https://tu-api-url.com/
DEBUG_MODE=true
```

3. Abrir el proyecto en Android Studio y dejar que sincronice las dependencias

4. Ejecutar en un emulador o dispositivo físico

## Arquitectura

Decidí usar **Clean Architecture con MVVM** porque me permite separar bien las responsabilidades y hace el código más fácil de mantener. La estructura tiene 3 capas principales:

### Capas

- **Presentation**: Todo lo relacionado con la UI (pantallas, ViewModels, estados)
- **Domain**: La lógica de negocio (casos de uso, modelos del dominio)
- **Data**: Implementación de repositorios y llamadas al API

```text
app/
├── core/                    # Cosas compartidas (network, di, errores)
├── feature/
│   ├── auth/               # Todo lo del login
│   └── transaction/        # Todo lo de transacciones
```

Esta separación hace que si mañana necesito cambiar algo del API o agregar una base de datos local, solo toco la capa de Data sin afectar la UI.

## Tecnologías usadas

- **Kotlin**: Lenguaje principal
- **Jetpack Compose**: Para la UI, me parece más limpio que XML
- **Hilt**: Inyección de dependencias
- **Retrofit + OkHttp**: Para las llamadas al API
- **Coroutines + StateFlow**: Para manejar operaciones asíncronas
- **Navigation Compose**: Navegación entre pantallas

## Patrones de diseño

### Repository Pattern

Lo uso para aislar la lógica de acceso a datos. Los ViewModels no saben si los datos vienen de un API, una BD local o un mock. Esto hace más fácil hacer tests y cambiar la fuente de datos.

```kotlin
interface AuthRepository {
    suspend fun login(username: String, password: String): Result<UserSession>
}
```

### Use Cases

Cada acción importante tiene su propio caso de uso. Por ejemplo, `LoginUseCase` encapsula toda la lógica del login. Me ayuda a tener las reglas de negocio en un solo lugar.

### Dependency Injection con Hilt

Uso Hilt porque maneja automáticamente los ciclos de vida y detecta errores en compilación. Es más robusto que hacerlo manualmente.

### Interceptor para JWT

Creé un interceptor que agrega automáticamente el token JWT a todas las peticiones. Así no tengo que estar pasando el token manualmente en cada llamada.

```kotlin
class AuthInterceptor : Interceptor {
    override fun intercept(chain: Chain): Response {
        val token = prefsManager.getAccessToken()
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
        return chain.proceed(request)
    }
}
```

### Sealed Classes para estados

Uso sealed classes para representar los diferentes estados de la UI (Loading, Success, Error). El compilador me obliga a manejar todos los casos, así no me olvido de ningún estado.

## Decisiones técnicas

### ¿Por qué 3 capas y no 5?

Porque el proyecto no lo necesita. Agregar más capas solo por agregarlas hace el código innecesariamente complejo. Con estas 3 capas tengo suficiente separación y flexibilidad.

### SessionViewModel para manejo de estado de autenticación

Creé un `SessionViewModel` que centraliza el estado de autenticación de la app. Maneja si el usuario está logueado o no usando un `StateFlow<Boolean>` que se observa desde la navegación principal. Esto me permite decidir la pantalla inicial (Login o Home) según el estado guardado en SharedPreferences, y también manejar el logout desde cualquier pantalla sin tener que pasar callbacks por toda la navegación.

### NetworkClient para manejo centralizado de errores

Implementé un `NetworkClient` que envuelve todas las llamadas al API con `safeApiCall`. Esta función captura cualquier excepción de red (timeout, sin internet, errores del servidor) y usa el `ErrorHandler` para convertirlas en mensajes legibles para el usuario. Así evito repetir try-catch en cada DataSource y mantengo el manejo de errores consistente en toda la app.

### BaseViewModel para manejo común de estado y errores

Todos los ViewModels heredan de un `BaseViewModel` que centraliza el manejo de estados de carga (`isLoading`) y errores (`error`). Esto permite que cualquier ViewModel pueda exponer estos estados de forma reactiva usando `StateFlow`, y reduce la repetición de lógica para mostrar loaders o mensajes de error en la UI. Además, la función `handleError` estandariza cómo se propagan los errores a la capa de presentación.

## Cosas que mejoraría con más tiempo

- Agregar tests unitarios y de UI
- Implementar caché local con Room para funcionar offline
- Añadir animaciones de transición
- Implementar pull-to-refresh
- Agregar biometría como opción de login

## Estructura de las pantallas

### Login

Valida que el username no tenga espacios y que la contraseña tenga mínimo 6 caracteres con al menos una letra minúscula y un número. Maneja diferentes tipos de errores (credenciales incorrectas, timeout, sin internet, etc).

### Transacciones

- Lista paginada (10 transacciones por página)
- Filtros por rango de fechas usando DatePickers de Material 3
- Ordenamiento por fecha o monto (ascendente/descendente)
- Muestra estados de carga y error apropiados

Separé los componentes en archivos distintos (TransactionCard, PaginationBar, ModalBottomSheetFilter) para que sean reutilizables y más fáciles de mantener.

---

Desarrollado por **Misael Villar** para la evaluación técnica de PayMind.

