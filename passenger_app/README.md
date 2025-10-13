# 🚕 Simple Passenger - App del Pasajero

## 📱 Descripción

Aplicación Android para pasajeros del sistema **Simple Taxi Argentina**.

## ✅ Estado del Proyecto

**VERSIÓN 1.0.0 - FUNCIONAL**

### Características Implementadas:
- ✅ Sistema de autenticación (Login/Registro/OTP)
- ✅ Solicitud de viajes en tiempo real
- ✅ Selección de destino en mapa
- ✅ Tracking de conductor en vivo
- ✅ Múltiples tipos de vehículos (Sedan/SUV/Hatchback)
- ✅ Estimación de tarifa
- ✅ Sistema de calificación
- ✅ Historial de viajes
- ✅ Métodos de pago (Cash/Card/Wallet)
- ✅ Notificaciones push con Firebase
- ✅ Perfil actualizable

## 🛠️ Tecnologías

- **Min SDK**: 21 (Android 5.0)
- **Target SDK**: 34 (Android 14)
- **Gradle**: 8.3
- **Java**: 11/17
- **Firebase**: BOM 32.6.0
- **Google Maps**: 18.2.0

## 🔗 Backend

- **API URL**: http://techlabz.in/lapassenger/Webservices_passenger
- **Sincronizado con**: App conductor y dashboard admin

## 📦 Compilación

### Debug APK:
```bash
./gradlew assembleDebug
```

### Release APK:
```bash
./gradlew assembleRelease
```

### Bundle AAB:
```bash
./gradlew bundleRelease
```

## 🚀 CodeMagic CI/CD

El proyecto incluye configuración completa para CodeMagic:

1. **passenger-release-workflow**: Build de producción
2. **passenger-debug-workflow**: Build de debug

## 🔑 Configuración Necesaria

1. **Google Maps API Key**: Agregar en `strings.xml`
2. **Firebase**: Configurar `google-services.json`
3. **Backend**: Verificar conexión con techlabz.in

## 📱 Funcionalidades Principales

### Para Pasajeros:
- **Solicitar Taxi**: Selección de pickup y destino
- **Ver Conductores Cercanos**: En tiempo real
- **Tracking en Vivo**: Seguimiento del conductor
- **Pago Flexible**: Efectivo, tarjeta o wallet
- **Calificación**: Sistema de 5 estrellas
- **Historial**: Todos los viajes realizados

### Integraciones:
- **Firebase Cloud Messaging**: Notificaciones push
- **Google Maps**: Mapas y navegación
- **Stripe**: Pagos con tarjeta (preparado)

## 📂 Estructura del Proyecto

```
passenger_app/
├── app/
│   ├── src/main/java/in/techware/lapassenger/
│   │   ├── activity/       # Activities principales
│   │   ├── adapter/        # Adaptadores para listas
│   │   ├── app/           # Application class
│   │   ├── fragments/     # Fragmentos
│   │   ├── model/         # Modelos de datos
│   │   ├── net/           # Conectividad y API
│   │   ├── services/      # Servicios (FCM)
│   │   └── util/          # Utilidades
│   └── src/main/res/      # Recursos (layouts, strings, etc.)
├── gradle/
├── build.gradle
└── codemagic.yaml
```

## 🎯 Próximas Mejoras

- [ ] Implementar pagos con Stripe/MercadoPago
- [ ] Agregar soporte multiidioma
- [ ] Modo oscuro
- [ ] Chat con conductor
- [ ] Compartir viaje en tiempo real
- [ ] Programar viajes futuros

## 🔒 Seguridad

- Autenticación con token JWT
- Comunicación HTTPS
- ProGuard habilitado en release
- Firebase Crashlytics para monitoring

## 📞 Soporte

**Backend API**: techlabz.in
**Firebase Project**: simple-taxi-apk-argentina

---

**Simple Taxi Argentina** © 2025
Versión sincronizada con conductor y dashboard