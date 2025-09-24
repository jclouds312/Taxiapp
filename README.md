# Proyecto LaTaxiDriver - Plan de Modernización

## 1. Objetivo General

El objetivo es modernizar la aplicación del conductor (`LaTaxiDriver`) para que utilice prácticas y dependencias de desarrollo de Android actuales, resolver problemas de compilación y, finalmente, analizar e implementar funcionalidades de autenticación de usuarios. Este trabajo se realizará en paralelo con la aplicación del pasajero (`LaTaxi`).

## 2. Estado Inicial del Proyecto

La base de código original presentaba varios desafíos que impedían su compilación y mantenimiento:

- **Dependencias de Gradle Obsoletas:** Utilizaba configuraciones deprecadas como `compile` y `testCompile`.
- **Versiones de SDK Antiguas:** Apuntaba a versiones de SDK de Android antiguas.
- **Dependencias Desactualizadas:** Las bibliotecas de Firebase, Play Services y otras eran de versiones muy antiguas.
- **Configuración de Entorno Incompleta:** El proyecto no se podía compilar debido a la falta de configuración de las rutas del SDK de Android y de Java (JAVA_HOME).

## 3. Acciones Realizadas

1.  **Restauración del Entorno Paralelo:** Se restauró el proyecto `LaTaxi` (pasajero) desde la rama `origin/passenger` para permitir el trabajo en ambos proyectos.
2.  **Actualización de `build.gradle`:** Se modificó el archivo `app/build.gradle` para reemplazar las configuraciones de dependencias obsoletas por sus contrapartes modernas:
    - `compile` -> `implementation`
    - `androidTestCompile` -> `androidTestImplementation`
    - `testCompile` -> `testImplementation`
3.  **Creación de `local.properties`:** Se crearon archivos `local.properties` tanto en `LaTaxiDriver` como en `LaTaxi` con una ruta de marcador de posición para el SDK de Android (`sdk.dir=/opt/android-sdk`).

## 4. Bloqueadores Actuales

La compilación de **ambos proyectos** (`LaTaxiDriver` y `LaTaxi`) está actualmente bloqueada por problemas de configuración del entorno:

1.  **Ruta del SDK de Android Desconocida:** La ruta `sdk.dir` en `local.properties` es un marcador de posición. Se necesita la ruta real del SDK de Android instalado en este entorno.
2.  **Variable `JAVA_HOME` no Definida:** El sistema no puede encontrar una instalación de Java (JDK), lo cual es indispensable para ejecutar Gradle.

## 5. Próximos Pasos

Para continuar, es **imprescindible** que se proporcionen las siguientes rutas del entorno de desarrollo:

- **La ruta de instalación del SDK de Android.**
- **La ruta de instalación de Java (JDK).**

Una vez que estas rutas sean configuradas, los siguientes pasos serán:
1.  Intentar compilar ambos proyectos de nuevo.
2.  Resolver cualquier otro problema de compilación que surja.
3.  Analizar la funcionalidad de autenticación de usuarios (`LoginActivity`).
4.  Implementar o verificar las funciones de creación de usuarios y recuperación de contraseñas.
