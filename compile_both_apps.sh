#!/bin/bash

# Script para compilar ambas apps (conductor y pasajero)

echo "═══════════════════════════════════════════════════════════"
echo "     🚖 COMPILACIÓN SUITE SIMPLE TAXI ARGENTINA           "
echo "═══════════════════════════════════════════════════════════"

# Colores
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
RED='\033[0;31m'
BLUE='\033[0;34m'
NC='\033[0m'

# Variables
DRIVER_DIR="."
PASSENGER_DIR="passenger_app"
BUILD_TYPE=${1:-debug}  # debug o release

echo -e "\n${BLUE}📱 Modo de compilación: ${BUILD_TYPE}${NC}\n"

# Función para compilar app
compile_app() {
    local APP_NAME=$1
    local APP_DIR=$2
    local APP_TYPE=$3
    
    echo -e "\n${YELLOW}════════════════════════════════════════${NC}"
    echo -e "${YELLOW}Compilando $APP_NAME${NC}"
    echo -e "${YELLOW}════════════════════════════════════════${NC}"
    
    cd "$APP_DIR" || exit 1
    
    # Verificar que existe gradlew
    if [ ! -f "./gradlew" ]; then
        echo -e "${RED}❌ No se encontró gradlew en $APP_DIR${NC}"
        return 1
    fi
    
    # Hacer gradlew ejecutable
    chmod +x gradlew
    
    # Limpiar proyecto
    echo -e "${BLUE}🧹 Limpiando proyecto...${NC}"
    ./gradlew clean
    
    # Compilar según el tipo
    if [ "$BUILD_TYPE" == "release" ]; then
        echo -e "${BLUE}🔨 Compilando AAB de Release...${NC}"
        ./gradlew bundleRelease
        
        if [ $? -eq 0 ]; then
            echo -e "${GREEN}✅ AAB de $APP_NAME compilado exitosamente${NC}"
            echo -e "${GREEN}📁 Ubicación: $APP_DIR/app/build/outputs/bundle/release/${NC}"
        else
            echo -e "${RED}❌ Error compilando AAB de $APP_NAME${NC}"
            return 1
        fi
        
        echo -e "${BLUE}🔨 Compilando APK de Release...${NC}"
        ./gradlew assembleRelease
        
        if [ $? -eq 0 ]; then
            echo -e "${GREEN}✅ APK de Release de $APP_NAME compilado${NC}"
            echo -e "${GREEN}📁 Ubicación: $APP_DIR/app/build/outputs/apk/release/${NC}"
        fi
    else
        echo -e "${BLUE}🔨 Compilando APK de Debug...${NC}"
        ./gradlew assembleDebug
        
        if [ $? -eq 0 ]; then
            echo -e "${GREEN}✅ APK de Debug de $APP_NAME compilado exitosamente${NC}"
            echo -e "${GREEN}📁 Ubicación: $APP_DIR/app/build/outputs/apk/debug/${NC}"
            
            # Copiar APK a directorio principal
            mkdir -p ../compiled_apks
            cp app/build/outputs/apk/debug/*.apk "../compiled_apks/${APP_TYPE}_debug.apk"
            echo -e "${GREEN}📦 APK copiado a: compiled_apks/${APP_TYPE}_debug.apk${NC}"
        else
            echo -e "${RED}❌ Error compilando APK de $APP_NAME${NC}"
            return 1
        fi
    fi
    
    cd - > /dev/null
}

# Verificar backend
echo -e "\n${BLUE}🔍 Verificando conexión con backend...${NC}"
if command -v php &> /dev/null; then
    php backend_verification.php
else
    echo -e "${YELLOW}⚠️ PHP no instalado, saltando verificación de backend${NC}"
fi

# Compilar App Conductor
if [ -d "$DRIVER_DIR/app" ]; then
    compile_app "App Conductor" "$DRIVER_DIR" "driver"
else
    echo -e "${RED}❌ No se encontró la app del conductor${NC}"
fi

# Compilar App Pasajero
if [ -d "$PASSENGER_DIR/app" ]; then
    compile_app "App Pasajero" "$PASSENGER_DIR" "passenger"
else
    echo -e "${YELLOW}⚠️ App de pasajero no encontrada${NC}"
    echo -e "${YELLOW}   Ejecuta ./create_passenger_app.sh primero${NC}"
fi

# Resumen final
echo -e "\n${BLUE}═══════════════════════════════════════════════════════════${NC}"
echo -e "${BLUE}                    📊 RESUMEN FINAL                       ${NC}"
echo -e "${BLUE}═══════════════════════════════════════════════════════════${NC}"

if [ -d "compiled_apks" ]; then
    echo -e "\n${GREEN}✅ APKs compilados disponibles en:${NC}"
    ls -la compiled_apks/*.apk 2>/dev/null | while read line; do
        echo -e "   ${GREEN}• ${line}${NC}"
    done
fi

echo -e "\n${BLUE}🚀 SIGUIENTE PASO:${NC}"
echo -e "   1. Subir a CodeMagic para firma y distribución"
echo -e "   2. O instalar directamente en dispositivo con:"
echo -e "      ${YELLOW}adb install compiled_apks/driver_debug.apk${NC}"
echo -e "      ${YELLOW}adb install compiled_apks/passenger_debug.apk${NC}"

echo -e "\n${BLUE}📱 Para generar APKs firmados de producción:${NC}"
echo -e "   ${YELLOW}./compile_both_apps.sh release${NC}"

echo -e "\n${BLUE}═══════════════════════════════════════════════════════════${NC}"
echo -e "${GREEN}✅ PROCESO COMPLETADO${NC}"
echo -e "${BLUE}═══════════════════════════════════════════════════════════${NC}"