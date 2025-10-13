# 🚖 Simple Taxi Argentina - Suite Completa

## 📊 Arquitectura del Sistema

```
┌─────────────────────────────────────────────────┐
│          DASHBOARD PHP (Admin Panel)             │
│         http://techlabz.in/admin                 │
│    • Gestión de conductores y pasajeros          │
│    • Monitoreo de viajes en tiempo real          │
│    • Reportes y estadísticas                     │
│    • Configuración de tarifas                    │
└─────────────────────┬───────────────────────────┘
                      │
                      │ API REST
                      │
        ┌─────────────┴────────────┐
        │   BACKEND API SERVER      │
        │   http://techlabz.in      │
        │   • Autenticación          │
        │   • Gestión de viajes      │
        │   • Firebase messaging     │
        │   • Pagos y facturación    │
        └─────┬────────────┬────────┘
              │            │
              │            │
    ┌─────────▼──┐    ┌───▼─────────┐
    │ APP DRIVER │    │ APP PASSENGER│
    │  (Android) │    │  (Android)  │
    └────────────┘    └─────────────┘
```

## 🚗 APP CONDUCTOR (Driver) - COMPLETADA ✅

**Estado**: Lista para compilar
**Package**: `in.techware.lataxidriver`
**Versión**: 1.0.8
**GitHub**: https://github.com/jclouds312/Taxiapp

### Funcionalidades:
- ✅ Login y registro de conductores
- ✅ Aceptar/rechazar viajes
- ✅ Navegación GPS al destino
- ✅ Gestión de ganancias
- ✅ Estados de pago (Pay Statements)
- ✅ Perfil actualizable
- ✅ Notificaciones push

## 🚕 APP PASAJERO (Passenger) - POR CREAR

**Package previsto**: `in.techware.lataxipassenger`
**Versión inicial**: 1.0.0

### Funcionalidades necesarias:
- 📱 Login y registro de pasajeros
- 🗺️ Selección de origen y destino en mapa
- 🚖 Solicitud de taxi
- 💳 Métodos de pago
- ⭐ Calificación de conductores
- 📋 Historial de viajes
- 🔔 Notificaciones de estado del viaje

## 🖥️ DASHBOARD PHP - Backend

**URL Base**: http://techlabz.in
**Admin Panel**: http://techlabz.in/admin (estimado)

### Endpoints API principales:

#### Autenticación:
- POST `/api/login` - Login usuarios
- POST `/api/register` - Registro nuevos usuarios
- POST `/api/verify-otp` - Verificación OTP

#### Gestión de Viajes:
- POST `/api/request-ride` - Solicitar viaje (pasajero)
- GET `/api/available-rides` - Ver viajes disponibles (conductor)
- POST `/api/accept-ride` - Aceptar viaje (conductor)
- POST `/api/start-ride` - Iniciar viaje
- POST `/api/complete-ride` - Completar viaje
- POST `/api/cancel-ride` - Cancelar viaje

#### Pagos:
- GET `/api/earnings` - Ver ganancias (conductor)
- GET `/api/payment-history` - Historial de pagos
- POST `/api/process-payment` - Procesar pago

#### Perfiles:
- GET `/api/profile` - Obtener perfil
- POST `/api/update-profile` - Actualizar perfil
- POST `/api/upload-documents` - Subir documentos

## 🔧 Configuración Técnica Compartida

### Firebase:
```json
{
  "project_id": "simple-taxi-apk-argentina",
  "project_number": "912563974727",
  "firebase_url": "https://simple-taxi-apk-argentina.firebaseio.com"
}
```

### Google Maps API:
- Ambas apps necesitan API key de Google Maps
- Configurar en strings.xml

### Dependencias comunes:
```gradle
// Firebase BOM
implementation platform('com.google.firebase:firebase-bom:32.6.0')
implementation 'com.google.firebase:firebase-auth'
implementation 'com.google.firebase:firebase-messaging'
implementation 'com.google.firebase:firebase-crashlytics'

// Google Play Services
implementation 'com.google.android.gms:play-services-maps:18.2.0'
implementation 'com.google.android.gms:play-services-location:21.0.1'

// Networking
implementation 'com.squareup.okhttp3:okhttp:4.12.0'
implementation 'com.google.code.gson:gson:2.10.1'
```

## 🚀 Plan de Implementación

### Fase 1: App Conductor ✅
- [x] Actualizar dependencias
- [x] Configurar CodeMagic
- [x] Preparar para compilación

### Fase 2: App Pasajero 🔄
- [ ] Clonar estructura base del conductor
- [ ] Adaptar UI para pasajeros
- [ ] Implementar flujo de solicitud de viaje
- [ ] Integrar pagos
- [ ] Configurar CodeMagic

### Fase 3: Integración 📡
- [ ] Verificar conexión con backend
- [ ] Probar flujo completo conductor-pasajero
- [ ] Configurar notificaciones push bidireccionales
- [ ] Testing end-to-end

### Fase 4: Dashboard 🖥️
- [ ] Verificar acceso al panel admin
- [ ] Documentar endpoints API
- [ ] Configurar webhooks si necesario

## 📱 Compilación de APKs

### App Conductor:
```bash
# CodeMagic workflow
android-workflow -> Genera AAB para Google Play
android-debug-workflow -> APK de prueba
```

### App Pasajero (cuando esté lista):
```bash
# CodeMagic workflow  
passenger-workflow -> Genera AAB para Google Play
passenger-debug-workflow -> APK de prueba
```

## 🔐 Variables de Entorno Necesarias

```bash
# Firebase
GOOGLE_SERVICES_JSON_DRIVER = [google-services.json del conductor]
GOOGLE_SERVICES_JSON_PASSENGER = [google-services.json del pasajero]

# Google Maps
GOOGLE_MAPS_API_KEY = [Tu API key]

# Backend
API_BASE_URL = http://techlabz.in
API_KEY = [Si existe autenticación API]

# Keystore (para release)
KEYSTORE_FILE = [keystore.jks]
KEYSTORE_PASSWORD = [contraseña]
KEY_ALIAS = [alias]
KEY_PASSWORD = [contraseña]
```

## 📊 Estado Actual

| Componente | Estado | Notas |
|------------|--------|-------|
| App Conductor | ✅ Lista | Actualizada, compilable |
| App Pasajero | ⚠️ Pendiente | Necesita crear/importar |
| Backend API | ✅ Activo | techlabz.in funcionando |
| Dashboard PHP | ❓ Por verificar | Necesita credenciales |
| Firebase | ✅ Configurado | Proyecto activo |

## 🔗 Enlaces Importantes

- **GitHub Conductor**: https://github.com/jclouds312/Taxiapp
- **Backend API**: http://techlabz.in
- **Firebase Console**: https://console.firebase.google.com/project/simple-taxi-apk-argentina
- **CodeMagic CI/CD**: https://codemagic.io

---

**Última actualización**: Octubre 2025
**Suite**: Simple Taxi Argentina