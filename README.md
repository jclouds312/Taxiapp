# Proyecto Simple Driver

## 1. Objetivo General

El objetivo es modernizar la aplicación del conductor, ahora llamada **Simple**, para que utilice prácticas y dependencias de desarrollo de Android actuales. Esto incluye resolver problemas de compilación, actualizar la identidad de la aplicación (nombre y logo) y configurar un flujo de despliegue continuo.

## 2. Configuración para Compilación Local (En Ordenador)

Compilar esta aplicación directamente en un smartphone (ej. con Termux) no es una opción viable debido a los altos requerimientos de hardware y software. Sigue estos pasos en un ordenador Windows, macOS o Linux.

### Prerrequisitos

- **Java Development Kit (JDK) versión 11 o superior:** Puedes descargarlo desde [Adoptium](https://adoptium.net/).
- **Android Studio:** Es la forma más sencilla de obtener el Android SDK y todas las herramientas de línea de comandos necesarias. Descárgalo desde la [web oficial de Android](https://developer.android.com/studio).

### Pasos de Configuración

1.  **Clona el repositorio:**
    ```bash
    git clone https://github.com/jclouds312/Taxiapp.git
    cd Taxiapp
    ```

2.  **Crea el archivo `local.properties`:**
    Este archivo le dice a Gradle dónde encontrar el Android SDK. Dentro de la carpeta del proyecto, crea un archivo llamado `local.properties` con el siguiente contenido. **Asegúrate de cambiar la ruta para que apunte a la ubicación de tu Android SDK**.

    *   **Ejemplo para macOS:**
        ```properties
        sdk.dir=/Users/TU_USUARIO/Library/Android/sdk
        ```
    *   **Ejemplo para Linux:**
        ```properties
        sdk.dir=/home/TU_USUARIO/Android/Sdk
        ```
    *   **Ejemplo para Windows:**
        ```properties
        sdk.dir=C:\Users\TU_USUARIO\AppData\Local\Android\Sdk
        ```

3.  **Crea el archivo `google-services.json` (¡MUY IMPORTANTE!):**
    El proyecto no compilará sin este archivo. 
    - Ve a la **[Consola de Firebase](https://console.firebase.google.com/)** y selecciona tu proyecto.
    - En la configuración del proyecto, descarga tu archivo `google-services.json`.
    - Coloca este archivo dentro de la carpeta `app/` de tu proyecto. La ruta final debe ser `app/google-services.json`.

### Compilar la Aplicación

Abre una terminal en la raíz del proyecto y ejecuta los siguientes comandos de Gradle:

- **Para crear un APK de depuración (debug):**
  ```bash
  ./gradlew assembleDebug
  ```
  El APK generado lo encontrarás en `app/build/outputs/apk/debug/`.

- **Para crear un APK de lanzamiento (release):**
  Este proceso requiere un keystore de firma. Debes crear el keystore y configurar las variables en el archivo `build.gradle` de la app.
  ```bash
  ./gradlew assembleRelease
  ```
  El APK firmado lo encontrarás en `app/build/outputs/apk/release/`.

## 3. Despliegue (Deployment)

### Método 1: Despliegue Automático con Codemagic (Recomendado)

El repositorio está configurado para un flujo de Integración y Despliegue Continuo (CI/CD) con Codemagic.

1.  **Conecta tu repositorio a Codemagic.io.**
2.  **Configura las variables de entorno en Codemagic:**
    - `GOOGLE_SERVICES`: El contenido de tu archivo `google-services.json` codificado en Base64.
    - `KEYSTORE`: Tu archivo de keystore de Android codificado en Base64.
    - `KEYSTORE_PASSWORD`, `KEY_ALIAS`, `KEY_PASSWORD`: Las contraseñas y alias de tu keystore.
3.  **Inicia una nueva compilación en Codemagic.** El flujo de trabajo definido se encargará de compilar, firmar y generar el APK de lanzamiento.

### Método 2: Despliegue Manual a Firebase App Distribution

Si tienes un APK generado localmente, puedes distribuirlo a tus testers usando la CLI de Firebase.

1.  **Instala la Firebase CLI.**
2.  **Autentícate:**
    ```bash
    firebase login
    ```
3.  **Distribuye el APK:**
    ```bash
    firebase appdistribution:distribute app/build/outputs/apk/release/app-release.apk \
        --app "TU_FIREBASE_APP_ID" \
        --release-notes "Notas de esta versión" \
        --testers-file "testers.txt"
    ```
    - Reemplaza `TU_FIREBASE_APP_ID` por el ID de tu app de Firebase (ej: `1:1234567890:android:0a1b2c3d4e5f67890`).
    - `testers.txt` es un archivo opcional que contiene una lista de correos electrónicos de tus testers.
