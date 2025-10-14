# 🚀 Instrucciones de Compilación - Simple Driver App

## 📋 Estado del Proyecto

✅ **PROYECTO COMPLETAMENTE ACTUALIZADO Y LISTO PARA COMPILAR**

### Cambios Realizados (Octubre 2025):

1. **Gradle y Dependencias**
   - ✅ Gradle actualizado a 8.3
   - ✅ Android Gradle Plugin a 8.1.4
   - ✅ Todas las dependencias actualizadas a las últimas versiones estables
   - ✅ Target SDK actualizado a 34 (Android 14)
   - ✅ Min SDK aumentado a 21 (Android 5.0) para mejor compatibilidad

2. **Optimizaciones**
   - ✅ ProGuard configurado para reducción de tamaño del APK
   - ✅ Build types optimizados (release/debug)
   - ✅ Configuración de R8 para mejor minificación
   - ✅ Soporte para versionado automático desde CodeMagic

3. **Firebase**
   - ✅ Configuración verificada con BOM 32.6.0
   - ✅ Crashlytics integrado
   - ✅ Analytics configurado
   - ✅ Cloud Messaging actualizado

4. **CodeMagic CI/CD**
   - ✅ 3 workflows configurados:
     - `android-workflow`: Build de release con AAB
     - `android-debug-workflow`: Build rápido de debug
     - `production-deployment`: Despliegue a producción
   - ✅ Versionado automático desde Google Play
   - ✅ Distribución via Firebase App Distribution

5. **Permisos**
   - ✅ Permiso de CAMERA agregado
   - ✅ POST_NOTIFICATIONS para Android 13+
   - ✅ Todos los permisos críticos verificados

## 🔧 Compilación con CodeMagic

### Configuración Inicial

1. **Ve a CodeMagic.io**
   ```
   https://codemagic.io
   ```

2. **Conecta tu repositorio GitHub**
   - Click en "Add application"
   - Selecciona: `jclouds312/Taxiapp`
   - CodeMagic detectará automáticamente `codemagic.yaml`

3. **Configura las Variables de Entorno**
   
   En **App Settings → Environment variables**, agrega:

   ```bash
   # Firma del APK/AAB (Requerido para Release)
   KEYSTORE_REFERENCE = [Base64 de tu keystore.jks]
   CM_KEYSTORE_PASSWORD = [contraseña del keystore]
   CM_KEY_ALIAS = [alias de tu key]
   CM_KEY_PASSWORD = [contraseña de tu key]
   
   # Google Play (Opcional - para publicación automática)
   GCLOUD_SERVICE_ACCOUNT_CREDENTIALS = [JSON de cuenta de servicio]
   
   # Firebase (Opcional - para distribución de pruebas)
   FIREBASE_SERVICE_ACCOUNT = [JSON de Firebase]
   
   # Google Maps API
   GOOGLE_MAPS_API_KEY = [Tu API key de Google Maps]
   ```

### Generar APK de Debug (Rápido)

```bash
# En CodeMagic:
1. Selecciona workflow: "Android Debug Build (Fast)"
2. Click "Start new build"
3. Espera ~10-15 minutos
4. Descarga el APK desde Artifacts
```

### Generar AAB para Google Play

```bash
# En CodeMagic:
1. Selecciona workflow: "Android Simple Driver - Release Build"
2. Click "Start new build"
3. Espera ~20-30 minutos
4. El AAB se subirá automáticamente a Google Play (internal track)
```

## 🛠️ Compilación Local con Android Studio

### Requisitos

- Android Studio Arctic Fox o superior
- JDK 11 o 17
- Android SDK con API 34
- Mínimo 8GB RAM recomendado

### Pasos

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/jclouds312/Taxiapp.git
   cd Taxiapp
   git checkout v1  # o replit-agent
   ```

2. **Abrir en Android Studio**
   - File → Open → Selecciona la carpeta del proyecto
   - Espera a que Gradle sincronice

3. **Configurar API Keys**
   ```xml
   <!-- En app/src/main/res/values/strings.xml -->
   <!-- Reemplaza YOUR_API_KEY_HERE con tu Google Maps API key -->
   <string name="api_key">YOUR_GOOGLE_MAPS_API_KEY</string>
   ```

4. **Compilar APK de Debug**
   ```bash
   ./gradlew assembleDebug
   # APK en: app/build/outputs/apk/debug/app-debug.apk
   ```

5. **Compilar APK de Release**
   ```bash
   # Primero crear keystore (solo primera vez)
   keytool -genkey -v -keystore my-release-key.jks \
           -keyalg RSA -keysize 2048 -validity 10000 \
           -alias my-key-alias
   
   # Compilar release
   ./gradlew assembleRelease
   # APK en: app/build/outputs/apk/release/app-release.apk
   ```

## 🔍 Verificación del Proyecto

Ejecuta el script de verificación:

```bash
./verify_build.sh
```

Este script verifica:
- ✅ Archivos críticos presentes
- ✅ Configuración de Gradle correcta
- ✅ Firebase configurado
- ✅ Permisos necesarios
- ✅ ProGuard configurado
- ✅ CodeMagic listo

## 📱 Instalación en Dispositivo

### Opción 1: ADB (Debug)
```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```

### Opción 2: Firebase App Distribution
- Los testers recibirán el APK por email
- Pueden instalarlo directamente desde el link

### Opción 3: Google Play Internal Testing
- El AAB se sube automáticamente
- Los testers internos pueden descargarlo desde Play Console

## ⚠️ Importante

1. **API Keys**: Asegúrate de configurar tu propia Google Maps API key
2. **Backend**: La app se conecta a `http://techlabz.in` - verifica que esté activo
3. **Keystore**: NUNCA subas tu keystore al repositorio
4. **Secrets**: Usa variables de entorno en CodeMagic, no hardcodees

## 🐛 Solución de Problemas

### Error: SDK location not found
```bash
# Crear local.properties con la ruta de tu SDK
echo "sdk.dir=/path/to/Android/Sdk" > local.properties
```

### Error: Gradle sync failed
```bash
# Limpiar y reconstruir
./gradlew clean
./gradlew build --refresh-dependencies
```

### Error: Firebase configuration
- Verifica que `google-services.json` esté en `app/`
- El package name debe ser: `in.techware.lataxidriver`

## 📞 Soporte

- **Documentación**: Ver `replit.md` y `CODEMAGIC_SETUP.md`
- **Estado del Build**: Check CodeMagic dashboard
- **Logs**: Disponibles en CodeMagic Artifacts

---

**Última actualización**: Octubre 2025
**Estado**: ✅ LISTO PARA PRODUCCIÓN