# Conversor de monedas

![Status](https://img.shields.io/badge/status-en%20preparación-yellow)
![Last Commit](https://img.shields.io/badge/last%20commit-Mayo%202024-blue)
![Java Version](https://img.shields.io/badge/Java-JDK%2017-orange)
![IDE](https://img.shields.io/badge/IDE-IntelliJ%20IDEA-purple)

## Índice
1. [Descripción del proyecto](#descripción-del-proyecto)
2. [Funcionalidades](#funcionalidades)
3. [API utilizada](#api-utilizada)
4. [Estructura del proyecto](#estructura-del-proyecto)
5. [Cómo correr la aplicación](#cómo-correr-la-aplicación)
6. [Contribuciones](#contribuciones)
7. [Agradecimientos](#agradecimientos)

## Descripción del proyecto
Este proyecto de conversor de monedas fue desarrollado como parte de un desafío propuesto por la plataforma educativa Alura Latam en convenio con Oracle Next Education, en el marco de las formaciones brindadas por el Programa ONE. El objetivo principal del proyecto es proporcionar una herramienta que permita a los usuarios convertir valores entre diferentes monedas utilizando cotizaciones actualizadas proporcionadas por una API externa. Es importante mencionar que esta aplicación es de consola, por lo que se ejecuta en la línea de comandos sin una interfaz gráfica de usuario.

## Funcionalidades
La aplicación ofrece las siguientes funcionalidades a través de un menú interactivo:
- **`Monedas admitidas`**: Permite al usuario consultar la lista de monedas admitidas para la conversión.
- **`Conversión de divisas`**: Permite realizar la conversión entre dos monedas especificadas por el usuario.
- **`Historial de solicitudes`**: Muestra un historial de todas las solicitudes de conversión realizadas previamente por el usuario.

## API Utilizada
El conversor de monedas utiliza la API de [ExchangeRate-API](https://www.exchangerate-api.com/), la cual proporciona acceso a tasas de cambio actualizadas. La API permite obtener información sobre las monedas admitidas y realizar conversiones entre distintas monedas.

## Estructura del proyecto
El proyecto está estructurado en varios paquetes, diseñados para separar las responsabilidades y facilitar el mantenimiento y la escalabilidad:
- **`main`**: Contiene la clase principal que sirve como punto de entrada del programa, siendo responsable de iniciar la aplicación.
- **`entities`**: Se encuentran las clases que representan las entidades o modelos de dominio del proyecto, como `Currency` y `CurrencyExchange`. Estas clases modelan los objetos con los que opera la aplicación, definiendo sus atributos y métodos para manipular los datos.
- **`services`**: Alberga las clases que encapsulan la lógica de negocio y la interacción con servicios externos. Incluye el manejo de la comunicación con la API de `ExchangeRate-API` para obtener las tasas de cambio y realizar las conversiones.
- **`ui`**: Encargado de la interfaz de usuario, este paquete contiene la clase `Menu` que gestiona la interacción directa con el usuario. Maneja la entrada y salida de datos a través de la consola y muestra los resultados de las operaciones solicitadas.
- **`config`**: Incluye la clase `ConfigManager` que se ocupa de la gestión de la clave API. Asegura que la clave API se carga correctamente y esté disponible para las clases que requieren acceso a servicios externos.

## Cómo correr la aplicación
Para correr la aplicación en tu computadora, sigue estos pasos:

1. **Clonar el repositorio**
   - Usa Git para clonar el repositorio en tu entorno local:
     ```bash
     git clone https://github.com/javote94/currency_exchange
     ```

2. **Configuración de la API Key**
   - Gestionar la creación de una API Key desde el sitio web de [ExchangeRate-API](https://www.exchangerate-api.com/).
   - Crea un archivo `config.properties` en el directorio raíz del proyecto para alojar la clave:
     ```
     api_key=tu_clave_api_aqui
     ```

3. **Preparación del entorno de trabajo**
   - Asegúrate de tener instalado Java JDK 17. Si no, puedes descargarlo e instalarlo desde el sitio web de [Oracle](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html).
   - También se recomienda utilizar IntelliJ IDEA para abrir y ejecutar el proyecto. Puedes descargarlo desde el sitio web de [IntelliJ IDEA](https://www.jetbrains.com/idea/download/).

4. **Ejecución del proyecto**
   - Abre el proyecto en IntelliJ IDEA.
   - Ejecuta el archivo `Main.java` para iniciar la aplicación y seguir las instrucciones del menú.

## Contribuciones
Este proyecto está en preparación. Cualquier feedback es bienvenido y si estás interesado en contribuir, estamos abiertos a pull requests o puedes [abrir un issue](https://github.com/javote94/currency_exchange/issues) para discutir posibles cambios.

## Agradecimientos
Mis agradecimientos a las organizaciones Alura Latam y Oracle Next Education por proporcionar el contexto educativo para el desarrollo del proyecto. El apoyo y recursos de formación brindados han sido fundamentales en la realización de esta aplicación.
