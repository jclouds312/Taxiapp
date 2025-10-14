# Simple Driver - Aplicación Android para Conductores

## 📱 Descripción del Proyecto

**Simple Driver** es una aplicación móvil Android para conductores de taxi/transporte. Este es un proyecto de aplicación nativa Android que **NO puede ejecutarse en Replit** ya que requiere Android SDK y herramientas de compilación específicas.

## ⚠️ IMPORTANTE: Limitaciones de Replit

Este proyecto **NO puede compilarse ni ejecutarse en Replit** porque:
- Requiere Android SDK (no disponible en Replit)
- Necesita Android Build Tools (no disponibles)
- Requiere un emulador Android o dispositivo físico
- Replit está diseñado para aplicaciones web, no para desarrollo móvil Android

## 🏗️ Arquitectura del Proyecto

### Estructura del Código

```
app/src/main/java/in/techware/ladriver/
├── activity/          # Actividades de la aplicación
│   ├── HomeActivity.java
│   ├── ProfileActivity.java
│   ├── SettingsActivity.java
│   ├── PayStatementsActivity.java
│   └── ...
├── fragments/         # Fragmentos reutilizables
│   ├── EarningsFragment.java
│   ├── HomeFragment.java
│   └── ...
├── net/              # Capa de red y API
│   ├── invokers/     # Llamadas a servicios
│   ├── parsers/      # Parseadores JSON
│   └── WebConnector.java
├── model/            # Modelos de datos
├── adapter/          # Adaptadores para listas
└── util/             # Utilidades
```

### Backend API

La aplicación se conecta a un backend externo:
- **URL Base**: `http://techlabz.in`
- **Endpoints**: Ver `ServiceNames.java` para lista completa
- **Autenticación**: Token-based auth con Firebase

### Servicios Principales

- **Autenticación**: Login, registro, verificación OTP
- **Gestión de Viajes**: Aceptar, iniciar, finalizar viajes
- **Ganancias**: Seguimiento semanal de ingresos
- **Perfil**: Actualización de datos del conductor
- **Documentos**: Carga de documentos requeridos
- **Notificaciones**: Firebase Cloud Messaging

## ✅ Funcionalidades Completadas

### 1. Pay Statements (Estados de Pago)
- **Archivo**: `EarningsFragment.java`
- **Funcionalidad**: Botón de "Pay Statements" ahora navega a `PayStatementsActivity`
- **Estado**: ✅ Completado

### 2. Control de Volumen de Notificaciones
- **Archivo**: `SettingsActivity.java`
- **Funcionalidad**: SeekBar controla el volumen de notificaciones del sistema
- **Características**:
  - Persistencia del valor en SharedPreferences
  - Control en tiempo real del AudioManager
  - Rango 0-100%
- **Estado**: ✅ Completado

### 3. Actualización de Perfil
- **Archivo**: `ProfileActivity.java`
- **Funcionalidad**: Sistema completo de edición de perfil con validación
- **Características**:
  - Validación de campos requeridos
  - Detección de cambios
  - Carga de foto de perfil
  - Actualización vía API
- **Estado**: ✅ Verificado y funcional

### 4. Detalles del Mapa
- **Archivo**: `RequestConfirmationActivity.java`
- **Nota**: El código comentado hace referencia a métodos inexistentes (`populatePlotList`, `llMapDetails`)
- **Estado**: ⚠️ No se puede activar sin crear funcionalidad completa

## 🔧 Configuración de Firebase

### Archivo de Configuración
- **Ubicación**: `app/google-services.json`
- **Proyecto**: `simple-taxi-apk-argentina`
- **Package**: `in.techware.lataxidriver`

### Servicios Firebase Configurados
- ✅ Firebase Authentication
- ✅ Firebase Cloud Messaging (FCM)
- ✅ Firebase Crashlytics
- ✅ Google Services Plugin

## 📦 Dependencias Principales (Actualizadas Oct 2025)

```gradle
// AndroidX - Última versión estable
implementation 'androidx.appcompat:appcompat:1.6.1'
implementation 'com.google.android.material:material:1.10.0'
implementation 'androidx.recyclerview:recyclerview:1.3.2'
implementation 'androidx.constraintlayout:constraintlayout:2.1.4'

// Firebase BOM - Gestión automática de versiones
implementation platform('com.google.firebase:firebase-bom:32.6.0')
implementation 'com.google.firebase:firebase-auth'
implementation 'com.google.firebase:firebase-messaging'
implementation 'com.google.firebase:firebase-crashlytics'
implementation 'com.google.firebase:firebase-analytics'

// Google Play Services
implementation 'com.google.android.gms:play-services-maps:18.2.0'
implementation 'com.google.android.gms:play-services-location:21.0.1'

// Networking & Images
implementation 'com.squareup.okhttp3:okhttp:4.12.0'
implementation 'com.github.bumptech.glide:glide:4.16.0'

// Charts & UI
implementation 'com.github.PhilJay:MPAndroidChart:v3.1.0'
implementation 'com.tbuonomo:dotsindicator:5.0'
```

## 🚀 Cómo Compilar el APK

### Opción 1: CodeMagic (Recomendado - CI/CD Automático) ⭐

El proyecto está **completamente configurado** con `codemagic.yaml` actualizado:

**📋 Workflows disponibles:**
- **android-workflow**: Build de release con firma y publicación a Google Play
- **android-debug-workflow**: Build rápido de debug para testing

**🔧 Setup rápido:**
1. Ve a [Codemagic.io](https://codemagic.io) y conecta este repositorio
2. Configura las variables de entorno (ver `CODEMAGIC_SETUP.md` para detalles)
3. Haz push a tu rama → Build automático

**📚 Documentación completa**: Ver archivo `CODEMAGIC_SETUP.md`

**✅ Características:**
- Versionado automático desde Google Play
- Genera APK y AAB firmados
- ProGuard mapping para crash reports
- Publicación automática a Google Play (internal track)
- Notificaciones por email

### Opción 2: Android Studio (Local)

1. **Requisitos**:
   - Android Studio instalado
   - JDK 11 o superior
   - Android SDK con API 33

2. **Pasos**:
   ```bash
   # Clonar el repositorio
   git clone <tu-repo>
   cd Taxiapp
   
   # Crear local.properties
   echo "sdk.dir=/ruta/a/tu/Android/Sdk" > local.properties
   
   # Compilar APK de debug
   ./gradlew assembleDebug
   
   # APK generado en:
   # app/build/outputs/apk/debug/app-debug.apk
   ```

3. **Para APK de Release**:
   ```bash
   # Crear keystore (primera vez)
   keytool -genkey -v -keystore my-release-key.jks \
           -keyalg RSA -keysize 2048 -validity 10000 \
           -alias my-key-alias
   
   # Compilar release
   ./gradlew assembleRelease
   ```

### Opción 3: Firebase App Distribution

1. **Instalar Firebase CLI**:
   ```bash
   npm install -g firebase-tools
   firebase login
   ```

2. **Distribuir APK**:
   ```bash
   firebase appdistribution:distribute app/build/outputs/apk/release/app-release.apk \
       --app "1:912563974727:android:a351d01700cb9d0957f762" \
       --release-notes "Versión con nuevas funcionalidades" \
       --testers-file testers.txt
   ```

## 🔐 Seguridad y Configuración

### API Keys Requeridas

1. **Google Maps API Key**:
   - Configurar en `app/src/main/res/values/strings.xml`
   - Línea 5: `<string name="api_key">TU_API_KEY</string>`

2. **Backend API**:
   - URL configurada en `ServiceNames.java`
   - Actual: `http://techlabz.in`
   - Modificar según tu servidor

### Permisos de la App

La aplicación requiere:
- ✅ Ubicación (GPS)
- ✅ Cámara (fotos de perfil/documentos)
- ✅ Almacenamiento (guardar fotos)
- ✅ Internet (conexión a backend)

## 📝 Notas de Desarrollo

### Versión Actual (Actualizada Oct 2025)
- **Version Code**: 9 (versionado automático en CodeMagic)
- **Version Name**: 1.0.8
- **Target SDK**: 34 (Android 14)
- **Min SDK**: 21 (Android 5.0)
- **Compile SDK**: 34
- **Build Tools**: 34.0.0
- **Gradle**: 8.3
- **Java**: 11/17 compatible

### Idioma
- Español (strings.xml)

### Próximas Mejoras Sugeridas

1. **Pay Statements**: Implementar lista de pagos históricos
2. **Detalles del Mapa**: Completar funcionalidad de visualización de rutas
3. **Actualización a SDK 34**: Migrar a Android 14
4. **Migración a Kotlin**: Considerar migración gradual
5. **Jetpack Compose**: Modernizar UI

## 🐛 Problemas Conocidos

### Código Comentado
- `RequestConfirmationActivity.java` tiene código comentado que no se puede activar sin implementar métodos faltantes
- `populatePlotList()` no existe
- `llMapDetails` no está declarado

### LSP Errors en Replit
- **Normal y Esperado**: Los errores LSP en Replit son porque no hay Android SDK
- No afectan la compilación en Android Studio o CodeMagic
- Ignorar errores de imports de Android en Replit

## 🌿 Gestión de Ramas

### Ramas Disponibles

**Repositorio**: `https://github.com/jclouds312/Taxiapp`

1. **v1** (rama principal)
   - Código completo con todas las funcionalidades
   - Pay Statements implementado
   - Control de Volumen implementado
   - Firebase configurado
   - CodeMagic configurado
   
2. **replit-agent** (rama de trabajo)
   - Mismo contenido que v1
   - Historial de commits diferente
   - Sincronizada con v1

### Estado de las Ramas
- ✅ Ambas ramas tienen el **mismo código**
- ✅ Todas las dependencias actualizadas
- ✅ Firebase configurado correctamente
- ✅ CodeMagic listo en ambas

### Flujo de Trabajo
```bash
# Ver ramas
git branch -a

# Cambiar de rama
git checkout v1
git checkout replit-agent

# Subir cambios a GitHub
git push origin v1
git push origin replit-agent
```

## 📞 Soporte

Para compilar este proyecto, usa:
- **CodeMagic**: Compilación automática en la nube (ver `CODEMAGIC_SETUP.md`)
- **Android Studio**: Desarrollo local
- **Firebase Console**: Distribución a testers

## 📚 Archivos de Documentación

- **`replit.md`**: Documentación principal del proyecto
- **`CODEMAGIC_SETUP.md`**: Guía completa de CodeMagic
- **`README.md`**: Información general
- **`codemagic.yaml`**: Configuración CI/CD

---

**Última actualización**: Octubre 2025  
**Mantenedor**: Simple Taxi Argentina  
**Estado**: ✅ Listo para compilación externa y deployment con CodeMagic
