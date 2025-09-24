# Proyecto Simple Driver

## 1. Objetivo General

El objetivo es modernizar la aplicación del conductor, ahora llamada **Simple**, para que utilice prácticas y dependencias de desarrollo de Android actuales. Esto incluye resolver problemas de compilación, actualizar la identidad de la aplicación (nombre y logo) y configurar un flujo de despliegue continuo.

## 2. Configuración del Entorno

Para compilar y ejecutar esta aplicación, necesitarás:

*   **Java Development Kit (JDK):** Este proyecto requiere **JDK 11**. El entorno de IDX ya está configurado para usarlo.
*   **Android Studio:** Se recomienda la última versión estable si trabajas fuera de IDX.
*   **Firebase:** Debes configurar un proyecto de Firebase y obtener tu propio archivo de configuración `google-services.json`.
    1.  Ve a la [consola de Firebase](https://console.firebase.google.com/).
    2.  Crea un nuevo proyecto o usa uno existente.
    3.  Añade una aplicación de Android con el nombre de paquete: `in.techware.lataxidriver`.
    4.  Descarga el archivo `google-services.json` y colócalo en el directorio `app/`.

## 3. Pasos para Actualizar el Logo

Este paso es **manual** y debes realizarlo tú:

1.  Prepara tu nuevo logo en diferentes tamaños para las distintas densidades de pantalla de Android.
2.  Reemplaza los archivos `ic_launcher.png` (logo normal) y `ic_launcher_round.png` (logo redondeado) en las siguientes carpetas con tus nuevas imágenes:
    *   `app/src/main/res/mipmap-hdpi/`
    *   `app/src/main/res/mipmap-mdpi/`
    *   `app/src/main/res/mipmap-xhdpi/`
    *   `app/src/main/res/mipmap-xxhdpi/`
    *   `app/src/main/res/mipmap-xxxhdpi/`

## 4. Compilación

Para compilar la aplicación y generar un APK de depuración, ejecuta el siguiente comando en la terminal. El entorno ya está configurado para que funcione directamente:

```bash
./gradlew assembleDebug
```

El APK generado se encontrará en `app/build/outputs/apk/debug/`.

## 5. Despliegue con Codemagic

Este repositorio contiene un archivo `codemagic.yaml` que define el flujo de trabajo para el despliegue continuo.

1.  **Conecta tu repositorio a Codemagic.io.**
2.  **Configura las variables de entorno en Codemagic:**
    *   Añade tu archivo `google-services.json` como una variable de entorno segura.
    *   Configura tu **keystore de Android** en Codemagic para firmar las versiones de lanzamiento. El archivo `codemagic.yaml` ya está preparado para usar estas variables.
3.  **Inicia una nueva compilación en Codemagic.** El flujo de trabajo definido se encargará de compilar, firmar y generar el artefacto de tu aplicación (APK o AAB).
