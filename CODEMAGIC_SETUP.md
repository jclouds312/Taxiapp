# 🚀 Configuración de CodeMagic para Simple Driver

## 📋 Requisitos Previos

1. Cuenta en [CodeMagic.io](https://codemagic.io)
2. Repositorio en GitHub conectado
3. Archivo keystore para firma de APK
4. Cuenta de Google Play Console (para publicación)

## 🔐 Variables de Entorno Requeridas

### En CodeMagic, configura estas variables de entorno:

#### 1. **Keystore (Firma de APK)**
```bash
# Convertir keystore a Base64
base64 -i tu-keystore.jks -o keystore_base64.txt

# En CodeMagic, agregar como variable:
KEYSTORE_REFERENCE = <contenido de keystore_base64.txt>
CM_KEYSTORE_PASSWORD = <contraseña del keystore>
CM_KEY_ALIAS = <alias de la clave>
CM_KEY_PASSWORD = <contraseña de la clave>
```

#### 2. **Google Services (Firebase)**
```bash
# Ya está en el proyecto: app/google-services.json
# CodeMagic lo detectará automáticamente
```

#### 3. **Google Play Publishing (Opcional)**
```bash
GCLOUD_SERVICE_ACCOUNT_CREDENTIALS = <JSON de cuenta de servicio>
```

## 📱 Workflows Disponibles

### 1. **android-workflow** (Release - Producción)
- Genera **AAB (Android App Bundle)** firmado para Google Play
- Incrementa versionCode automáticamente desde Google Play
- Publica en Google Play (track: internal)
- Genera ProGuard mapping para crash reports
- **Duración**: ~60 min máx
- **Nota**: Usa AAB porque es el formato requerido por Google Play

### 2. **android-debug-workflow** (Debug - Testing)
- Genera APK de debug rápido
- Sin firma de release
- Para testing interno
- **Duración**: ~30 min máx

## 🎯 Cómo Usar

### Opción 1: Build Automático
1. Haz `git push` a tu rama
2. CodeMagic detectará el cambio
3. Ejecutará el workflow automáticamente
4. APK/AAB disponible en Artifacts

### Opción 2: Build Manual
1. Ve a CodeMagic Dashboard
2. Selecciona el proyecto
3. Elige el workflow deseado
4. Click en "Start new build"

## 📦 Artifacts Generados

Después de cada build exitoso, encontrarás:

```
app/build/outputs/
├── bundle/release/
│   └── app-release.aab          # Bundle para Google Play (workflow release)
├── apk/debug/
│   └── app-debug.apk            # APK de debug (workflow debug)
└── mapping/release/
    └── mapping.txt              # ProGuard mapping (crash reports)
```

## 🔄 Versionado Automático

El workflow **android-workflow** incrementa automáticamente el `versionCode`:
- Consulta el último build en Google Play
- Incrementa +1
- Usa ese número para el nuevo build
- Formato: `1.0.X` donde X es el build number

## 📧 Notificaciones

Los workflows están configurados para enviar emails:
- ✅ Build exitoso
- ❌ Build fallido

**Actualiza el email en `codemagic.yaml`:**
```yaml
publishing:
  email:
    recipients:
      - tu-email@ejemplo.com  # ← Cambiar aquí
```

## 🐛 Troubleshooting

### Error: "SDK not found"
```yaml
# Ya está configurado en scripts:
echo "sdk.dir=$ANDROID_SDK_ROOT" > "$CM_BUILD_DIR/local.properties"
```

### Error: "Keystore not found"
- Verifica que `KEYSTORE_REFERENCE` esté en Base64
- Confirma que las contraseñas sean correctas

### Error: "Google Services missing"
- Asegúrate que `app/google-services.json` existe
- Verifica que el package name coincida: `in.techware.lataxidriver`

### Build muy lento
- Usa `android-debug-workflow` para testing
- El workflow de release es más lento por ProGuard y firma

## 📊 Pasos del Build

### Workflow Release:
1. ✅ Checkout código
2. ✅ Setup Java 11
3. ✅ Configurar local.properties
4. ✅ Verificar versión de Gradle
5. ✅ Obtener último build number de Google Play
6. ✅ Incrementar versionCode
7. ✅ Build bundle release (AAB)
8. ✅ Generar artifacts
9. ✅ Publicar en Google Play (internal track)
10. ✅ Enviar notificación por email

### Workflow Debug:
1. ✅ Checkout código
2. ✅ Setup Java 11
3. ✅ Configurar local.properties
4. ✅ Build APK debug
5. ✅ Generar artifacts
6. ✅ Enviar notificación por email

## 🎛️ Configuración Avanzada

### Cambiar Track de Google Play
```yaml
google_play:
  track: internal  # Opciones: internal, alpha, beta, production
```

### Deshabilitar publicación automática
```yaml
google_play:
  submit_as_draft: true  # Se guardará como borrador
```

### Ajustar timeout
```yaml
max_build_duration: 60  # minutos
```

## ✅ Checklist Pre-Build

Antes de hacer push, verifica:

- [ ] `google-services.json` existe en `app/`
- [ ] `versionCode` y `versionName` en `app/build.gradle`
- [ ] Variables de entorno configuradas en CodeMagic
- [ ] Email de notificaciones actualizado
- [ ] Keystore configurado correctamente
- [ ] Package name correcto: `in.techware.lataxidriver`

## 🔗 Enlaces Útiles

- **CodeMagic Dashboard**: https://codemagic.io/apps
- **Documentación**: https://docs.codemagic.io/
- **Google Play Console**: https://play.google.com/console
- **Firebase Console**: https://console.firebase.google.com/

---

**Última actualización**: Octubre 2025  
**Versión actual**: 1.0.7 (versionCode 8)
