
### Descripción de la Aplicación

La aplicación "Gato Hacker" es un bot de Telegram que permite a los usuarios embarcarse en una aventura como un gato hacker. El bot guía a los usuarios a través de varios niveles donde deben completar tareas para ganar fama. Cada nivel presenta un desafío único que el usuario debe superar para avanzar al siguiente nivel.

### Requisitos

Para ejecutar esta aplicación, necesitas:

-   Java 8 o superior
-   Gradle o Maven para gestionar dependencias
-   Una cuenta de bot en Telegram con el token correspondiente


### Instalación y Configuración

1.  **Clonar el Repositorio**
    
    ```
    git clone https://github.com/tu_usuario/telegram-bot-gato-hacker.git
    cd telegram-bot-gato-hacker 
    ```
    
2.  **Configurar el Token del Bot**
    
    Abre el archivo `MyFirstTelegramBot.java` y reemplaza `TOKEN` con el token de tu bot proporcionado por BotFather.
    
    `public static final String NAME = "NOMBRE_DEL_BOT";`
    `public static final String TOKEN = "TU_TOKEN_DE_TELEGRAM";` 
    
3.  **Compilar y Ejecutar**
    
    Si estás usando Gradle, ejecuta:
      
    ```
    ./gradlew build
    ./gradlew run
    ``` 
    
    Si estás usando Maven, ejecuta:
    ```
    mvn package
    mvn exec:java -Dexec.mainClass="es.codegym.telegrambot.MyFirstTelegramBot"
    ``` 
    

### Uso

1.  **Iniciar el Bot**
    
    Una vez que el bot esté ejecutándose, abre Telegram y busca tu bot por su nombre de usuario. Inicia una conversación enviando el comando `/start`.
    
2.  **Interacción**
    
    El bot te guiará a través de varios niveles:
    
    -   **Nivel 1:** Hackear la nevera digital para obtener comida.
    -   **Nivel 2:** Derrotar a la nevera y elegir una recompensa.
    -   **Nivel 3:** Hackear al robot aspiradora.
    -   **Nivel 4:** Ponerse una GoPro y grabar.
    -   **Nivel 5:** Hackear la contraseña de la computadora y transferir el material grabado.
    
    Cada nivel incluye mensajes y fotos temáticas que mejoran la experiencia del usuario.
    

### Estructura del Código

-   **TelegramBotContent.java:** Contiene los textos de cada nivel del juego.
-   **MyFirstTelegramBot.java:** Implementa la lógica principal del bot, gestionando los eventos de actualización y las interacciones con el usuario.
-   **MultiSessionTelegramBot.java:** Proporciona métodos auxiliares para enviar mensajes y gestionar la gloria de los usuarios.


### Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo `LICENSE` para más detalles.
