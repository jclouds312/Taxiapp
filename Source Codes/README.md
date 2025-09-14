# LaTaxi & LaTaxiDriver

Este repositorio contiene el código fuente de dos aplicaciones de Android:

*   **LaTaxi:** Una aplicación para que los clientes soliciten viajes.
*   **LaTaxiDriver:** Una aplicación para que los conductores acepten y gestionen viajes.

Ambas aplicaciones utilizan Firebase para la autenticación y la base de datos en tiempo real.

## Gestión de Usuarios

El sistema de gestión de usuarios se basa en **Firebase Authentication**. Estas son las características clave:

*   **Registro:** Los nuevos usuarios (tanto clientes como conductores) se registran utilizando su dirección de correo electrónico y contraseña.
*   **Inicio de sesión:** Los usuarios existentes inician sesión con sus credenciales registradas.
*   **Seguridad:** Las contraseñas se almacenan de forma segura y se gestionan a través de los mecanismos de seguridad de Firebase. Como resultado, **no es posible buscar o listar usuarios directamente desde la base de datos** para proteger la privacidad del usuario.

## Configuración del Entorno

Para compilar y ejecutar estas aplicaciones, necesitarás:

*   **Java Development Kit (JDK):**
    *   El proyecto `LaTaxi` requiere **JDK 17**.
    *   El proyecto `LaTaxiDriver` requiere **JDK 11**.
*   **Android Studio:** Se recomienda la última versión estable.
*   **Firebase:** Deberás configurar un proyecto de Firebase y obtener los archivos de configuración `google-services.json` para cada aplicación.

## Compilación

1.  **Clona este repositorio:**

    ```bash
    git clone <URL_DEL_REPOSITORIO>
    cd Taxiapp/'Source Codes'
    ```

2.  **Compila la aplicación `LaTaxi`:**

    ```bash
    cd LaTaxi
    JAVA_HOME=<RUTA_A_JDK_17> ./gradlew assembleDebug
    ```

3.  **Compila la aplicación `LaTaxiDriver`:**

    ```bash
    cd ../LaTaxiDriver
    JAVA_HOME=<RUTA_A_JDK_11> ./gradlew assembleDebug
    ```

## Despliegue con Codemagic

Este repositorio está configurado para el despliegue continuo con Codemagic. El archivo `codemagic.yaml` en la raíz del repositorio define los pasos de compilación para ambas aplicaciones. Simplemente conecta este repositorio a tu cuenta de Codemagic para automatizar el proceso de compilación y despliegue.
