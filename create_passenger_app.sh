#!/bin/bash

# Script para crear la estructura de la app de pasajero basada en la del conductor

echo "═══════════════════════════════════════════════════════════"
echo "     🚕 CREANDO APP PASAJERO - SIMPLE TAXI                 "
echo "═══════════════════════════════════════════════════════════"

# Colores
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
RED='\033[0;31m'
NC='\033[0m'

# Verificar si existe la carpeta passenger
if [ -d "passenger_app" ]; then
    echo -e "${YELLOW}⚠️ La carpeta passenger_app ya existe${NC}"
    read -p "¿Deseas eliminarla y crear una nueva? (s/n): " -n 1 -r
    echo
    if [[ $REPLY =~ ^[Ss]$ ]]; then
        rm -rf passenger_app
        echo -e "${GREEN}✅ Carpeta anterior eliminada${NC}"
    else
        echo -e "${RED}❌ Operación cancelada${NC}"
        exit 1
    fi
fi

# Crear estructura de directorios
echo -e "\n📁 Creando estructura de directorios..."
mkdir -p passenger_app
cd passenger_app

# Copiar archivos base del conductor (si existen)
if [ -d "../app" ]; then
    echo -e "${GREEN}✅ Copiando estructura base del conductor...${NC}"
    cp -r ../app .
    cp -r ../gradle .
    cp ../build.gradle .
    cp ../settings.gradle .
    cp ../gradle.properties .
    cp ../gradlew .
    cp ../gradlew.bat .
    cp ../.gitignore .
    
    # Renombrar package de conductor a pasajero
    echo -e "\n🔄 Adaptando para pasajero..."
    
    # Cambiar el applicationId en build.gradle
    sed -i.bak 's/in.techware.lataxidriver/in.techware.lataxipassenger/g' app/build.gradle
    sed -i.bak 's/Simple Driver/Simple Passenger/g' app/src/main/res/values/strings.xml
    
    # Cambiar el package en AndroidManifest.xml
    sed -i.bak 's/in.techware.ladriver/in.techware.lapassenger/g' app/src/main/AndroidManifest.xml
    
    # Renombrar directorios de package
    if [ -d "app/src/main/java/in/techware/ladriver" ]; then
        mv app/src/main/java/in/techware/ladriver app/src/main/java/in/techware/lapassenger
    fi
    
    echo -e "${GREEN}✅ Estructura adaptada para pasajero${NC}"
else
    echo -e "${RED}❌ No se encontró la app del conductor para copiar${NC}"
    echo -e "${YELLOW}Creando estructura básica desde cero...${NC}"
    
    # Crear estructura básica
    mkdir -p app/src/main/java/in/techware/lapassenger
    mkdir -p app/src/main/res/layout
    mkdir -p app/src/main/res/values
    mkdir -p app/src/main/res/drawable
    mkdir -p app/src/main/res/mipmap-hdpi
    mkdir -p app/src/main/res/mipmap-mdpi
    mkdir -p app/src/main/res/mipmap-xhdpi
    mkdir -p app/src/main/res/mipmap-xxhdpi
    mkdir -p app/src/main/res/mipmap-xxxhdpi
fi

# Crear archivo README específico para pasajero
cat > README.md << 'EOF'
# 🚕 Simple Passenger - App de Pasajero

## Descripción

Aplicación Android para pasajeros del sistema Simple Taxi Argentina.

## Características

- 📱 Solicitud de viajes
- 🗺️ Selección de destino en mapa
- 💳 Múltiples métodos de pago
- ⭐ Calificación de conductores
- 📋 Historial de viajes
- 🔔 Notificaciones en tiempo real

## Configuración

1. **Firebase**: Configurar `google-services.json` para pasajeros
2. **Google Maps**: Agregar API key en `strings.xml`
3. **Backend**: Verificar conexión a `http://techlabz.in`

## Compilación

```bash
# Debug APK
./gradlew assembleDebug

# Release APK
./gradlew assembleRelease
```

## Backend API

URL Base: `http://techlabz.in`

### Endpoints principales:
- `/api/request-ride` - Solicitar viaje
- `/api/track-ride` - Seguimiento en tiempo real
- `/api/rate-driver` - Calificar conductor
- `/api/payment-methods` - Métodos de pago

## Estado

⚠️ **En desarrollo** - Adaptando desde app conductor

---

Simple Taxi Argentina © 2025
EOF

# Crear codemagic.yaml para pasajero
cat > codemagic.yaml << 'EOF'
workflows:
  passenger-workflow:
    name: Passenger App - Release Build
    max_build_duration: 60
    instance_type: mac_mini_m1
    environment:
      android_signing:
        - passenger_keystore
      groups:
        - google_play_passenger
      vars:
        PACKAGE_NAME: "in.techware.lataxipassenger"
        APP_NAME: "Simple Passenger"
      java: 17
    scripts:
      - name: Set up local.properties
        script: | 
          echo "sdk.dir=$ANDROID_SDK_ROOT" > "$CM_BUILD_DIR/local.properties"
      
      - name: Build Passenger AAB
        script: | 
          ./gradlew bundleRelease --stacktrace
          
    artifacts:
      - app/build/outputs/**/*.aab
      - app/build/outputs/**/*.apk
      
    publishing:
      email:
        recipients:
          - jclouds312@gmail.com
      google_play:
        credentials: $GCLOUD_SERVICE_ACCOUNT_CREDENTIALS
        track: internal
        
  passenger-debug-workflow:
    name: Passenger App - Debug Build
    max_build_duration: 30
    instance_type: mac_mini_m1
    environment:
      java: 17
    scripts:
      - name: Build Debug APK
        script: | 
          ./gradlew assembleDebug
    artifacts:
      - app/build/outputs/**/*.apk
EOF

echo -e "\n${GREEN}✅ Estructura de app pasajero creada${NC}"
echo -e "\n📋 SIGUIENTES PASOS:"
echo -e "1. Adaptar las Activities para flujo de pasajero"
echo -e "2. Crear UI de solicitud de viaje"
echo -e "3. Implementar tracking en tiempo real"
echo -e "4. Integrar métodos de pago"
echo -e "5. Configurar Firebase para pasajeros"

echo -e "\n═══════════════════════════════════════════════════════════"
echo -e "${GREEN}✅ COMPLETADO - App pasajero lista para desarrollo${NC}"
echo -e "═══════════════════════════════════════════════════════════"