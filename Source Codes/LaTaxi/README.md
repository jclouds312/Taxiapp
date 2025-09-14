# LaTaxi - Evolución y Plan de Modernización

## 1. Visión General

Este repositorio contiene el código fuente de **LaTaxi**, una aplicación nativa de Android. El proyecto se encuentra en una fase de evolución para convertirse en una solución de movilidad multiplataforma moderna, utilizando Flutter para ofrecer una experiencia unificada en iOS y Android.

Este documento describe la nueva visión del proyecto, el estado actual de la base de código de Android y el plan de acción para su modernización.

---

## 2. Nueva Visión del Proyecto: Multiplataforma con Flutter

La meta es desarrollar una aplicación basada en el modelo de Uber, con funcionalidades orientadas a empresas y un ecosistema completo que incluye:

- **Aplicaciones móviles para pasajeros y conductores.**
- **Un módulo empresarial** para la gestión de viajes corporativos.
- **Un panel administrativo web** para la supervisión global.

### 2.1. Objetivos del Proyecto

*   **Servicio Eficiente:** Ofrecer una aplicación de movilidad intuitiva y confiable.
*   **Gestión para Conductores:** Brindar herramientas para gestionar viajes y ganancias.
*   **Control Empresarial:** Facilitar a las empresas el control de los viajes de sus empleados.
*   **Administración Centralizada:** Otorgar un panel web con supervisión, reportes y métricas.
*   **Escalabilidad y Seguridad:** Garantizar una arquitectura que permita futuras mejoras.

### 2.2. Alcance Funcional

#### Módulo de Pasajeros
- Registro (correo, teléfono, redes sociales).
- Solicitud de viajes en tiempo real.
- Cálculo automático de tarifas.
- Múltiples métodos de pago.
- Seguimiento de viaje en tiempo real.
- Historial de viajes y facturas.
- Calificación de conductores.
- Notificaciones push.

#### Módulo de Conductores
- Registro con validación de documentos.
- Recepción de solicitudes en tiempo real.
- Modo de disponibilidad (en línea / desconectado).
- Navegación integrada.
- Historial de viajes y ganancias.
- Estadísticas de desempeño.

#### Módulo Empresarial
- Registro de empresas.
- Creación de perfiles para empleados.
- Solicitud de viajes corporativos.
- Reportes por empleado y departamento.
- Facturación mensual consolidada.
- Descarga de reportes en PDF y Excel.

#### Panel Administrativo Web
- Dashboard con estadísticas en tiempo real.
- Gestión de usuarios (pasajeros, conductores, empresas).
- Configuración de tarifas y promociones.
- Supervisión de viajes en curso.
- Control de pagos y comisiones.
- Reportes exportables.

---

## 3. Estado Actual de la Aplicación Android

Un análisis inicial del código base nativo de Android ha revelado los siguientes puntos críticos que necesitan atención:

- **Versión de Gradle Obsoleta:** Utiliza una versión `3.0.0-beta7` del plugin de Android, lo cual es inestable y muy antiguo.
- **Repositorios Deprecados:** `jcenter()` está declarado, pero ya no es un repositorio de artefactos mantenido.
- **Dependencias Desactualizadas:**
  - **SDK de Android:** Compila con `targetSdkVersion 26`, una versión de 2017. Las aplicaciones nuevas deben apuntar al menos a la versión 33.
  - **Bibliotecas de Soporte:** Utiliza las bibliotecas `com.android.support` en lugar del moderno `AndroidX`.
  - **Firebase y Play Services:** Las versiones datan de 2017 (`11.4.2`).
  - **Fabric:** Usa Fabric para reportes de crashes, el cual ha sido reemplazado por Firebase Crashlytics.
- **Problemas de Entorno:**
  - El script `gradlew` tiene problemas de formato de fin de línea (Windows `CRLF`).
  - El entorno de desarrollo no tenía configurada la variable `JAVA_HOME`.

---

## 4. Plan de Acción para la Modernización de la App Android

Para estabilizar la base de código actual y prepararla para el futuro (ya sea como referencia para la app Flutter o para un mantenimiento continuo), se propone el siguiente plan de acción:

### 4.1. Configuración del Entorno y Sistema de Compilación
1.  **✔️ Corregir el Script `gradlew`:**
    -   Asegurar permisos de ejecución (`chmod +x gradlew`).
    -   Convertir los finales de línea de `CRLF` a `LF`.
2.  **✔️ Configurar JDK:**
    -   Instalar un JDK compatible (ej. JDK 8 o 11) y configurar la variable de entorno `JAVA_HOME`.
3.  **Actualizar Gradle:**
    -   Actualizar la versión del **Android Gradle Plugin** a una versión estable y reciente (ej. `7.x` o `8.x`).
    -   Actualizar la **versión de Gradle Wrapper** en `gradle-wrapper.properties`.
4.  **Limpiar Repositorios:**
    -   Reemplazar `jcenter()` y `maven { url 'https://maven.fabric.io/public' }` por `mavenCentral()`.

### 4.2. Migración de Dependencias
1.  **Migrar a AndroidX:**
    -   Usar la herramienta de migración de Android Studio (`Refactor > Migrate to AndroidX`).
2.  **Actualizar Dependencias Principales:**
    -   Actualizar las versiones de todas las bibliotecas de Firebase y Google Play Services a las últimas disponibles.
    -   Actualizar bibliotecas de terceros como `OkHttp`, `Glide` y `Gson`.
3.  **Reemplazar Fabric por Firebase Crashlytics:**
    -   Remover la dependencia de `io.fabric.tools:gradle` y `com.crashlytics.sdk.android:crashlytics`.
    -   Añadir y configurar la dependencia de `com.google.firebase:firebase-crashlytics`.

### 4.3. Actualización de SDK y API
1.  **Actualizar `targetSdkVersion` y `compileSdkVersion`:**
    -   Aumentar a la última versión estable (actualmente 34).
    -   Probar la aplicación para asegurar la compatibilidad con los cambios de comportamiento de las nuevas versiones de Android.

### 4.4. Refactorización y Buenas Prácticas
1.  **Manejo de Permisos:** Revisar y adaptar el manejo de permisos en tiempo de ejecución para cumplir con las nuevas normativas de Android.
2.  **Código Obsoleto:** Reemplazar llamadas a APIs obsoletas (ej. `tManager.getDeviceId()`) por alternativas modernas y seguras.
3.  **Modernizar Arquitectura:** Introducir componentes de arquitectura modernos como `ViewModel`, `LiveData`/`Flow` y corutinas para mejorar la gestión del ciclo de vida y las operaciones asíncronas.

## 5. Estructura del Proyecto

La estructura actual del proyecto sigue la organización estándar de una aplicación Android:

-   `app/`: Módulo principal de la aplicación.
    -   `src/main/java/`: Código fuente de la aplicación.
    -   `src/main/res/`: Recursos (layouts, imágenes, strings).
    -   `build.gradle`: Configuración de compilación del módulo.
-   `build.gradle`: Configuración de compilación a nivel de proyecto.
-   `gradlew`: Script de wrapper de Gradle.
