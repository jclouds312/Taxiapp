#!/bin/bash

# Script de verificación de compilación - Simple Driver App
echo "═══════════════════════════════════════════════════════════"
echo "         🔍 VERIFICACIÓN DE COMPILACIÓN                   "
echo "═══════════════════════════════════════════════════════════"

# Colores para output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m'

# Contador de errores
ERRORS=0
WARNINGS=0

# 1. Verificar Java
echo -e "\n📌 Verificando Java..."
if command -v java &> /dev/null; then
    JAVA_VERSION=$(java -version 2>&1 | head -n 1)
    echo -e "${GREEN}✅ Java instalado: $JAVA_VERSION${NC}"
else
    echo -e "${RED}❌ Java no está instalado${NC}"
    ((ERRORS++))
fi

# 2. Verificar Android SDK
echo -e "\n📌 Verificando Android SDK..."
if [ -z "$ANDROID_HOME" ] && [ -z "$ANDROID_SDK_ROOT" ]; then
    echo -e "${YELLOW}⚠️ ANDROID_HOME o ANDROID_SDK_ROOT no configurado${NC}"
    ((WARNINGS++))
else
    echo -e "${GREEN}✅ Android SDK configurado${NC}"
fi

# 3. Verificar archivos críticos
echo -e "\n📌 Verificando archivos críticos..."
CRITICAL_FILES=(
    "app/google-services.json"
    "app/build.gradle"
    "build.gradle"
    "gradle.properties"
    "settings.gradle"
    "gradle/wrapper/gradle-wrapper.properties"
    "codemagic.yaml"
)

for file in "${CRITICAL_FILES[@]}"; do
    if [ -f "$file" ]; then
        echo -e "${GREEN}✅ $file existe${NC}"
    else
        echo -e "${RED}❌ $file NO ENCONTRADO${NC}"
        ((ERRORS++))
    fi
done

# 4. Verificar dependencias en build.gradle
echo -e "\n📌 Verificando configuración de Gradle..."
if [ -f "app/build.gradle" ]; then
    # Verificar versiones críticas
    if grep -q "compileSdkVersion 34" app/build.gradle; then
        echo -e "${GREEN}✅ compileSdkVersion correcto (34)${NC}"
    else
        echo -e "${YELLOW}⚠️ compileSdkVersion no es 34${NC}"
        ((WARNINGS++))
    fi
    
    if grep -q "targetSdkVersion 34" app/build.gradle; then
        echo -e "${GREEN}✅ targetSdkVersion correcto (34)${NC}"
    else
        echo -e "${YELLOW}⚠️ targetSdkVersion no es 34${NC}"
        ((WARNINGS++))
    fi
    
    if grep -q "minSdkVersion" app/build.gradle; then
        echo -e "${GREEN}✅ minSdkVersion configurado${NC}"
    else
        echo -e "${RED}❌ minSdkVersion no configurado${NC}"
        ((ERRORS++))
    fi
fi

# 5. Verificar Firebase configuration
echo -e "\n📌 Verificando Firebase..."
if [ -f "app/google-services.json" ]; then
    if grep -q "simple-taxi-apk-argentina" app/google-services.json; then
        echo -e "${GREEN}✅ Firebase configurado correctamente${NC}"
    else
        echo -e "${RED}❌ Firebase project ID incorrecto${NC}"
        ((ERRORS++))
    fi
fi

# 6. Verificar ProGuard
echo -e "\n📌 Verificando ProGuard..."
if [ -f "app/proguard-rules.pro" ]; then
    if grep -q "in.techware.ladriver" app/proguard-rules.pro; then
        echo -e "${GREEN}✅ ProGuard configurado para el proyecto${NC}"
    else
        echo -e "${YELLOW}⚠️ ProGuard puede necesitar ajustes${NC}"
        ((WARNINGS++))
    fi
fi

# 7. Verificar permisos en AndroidManifest
echo -e "\n📌 Verificando permisos..."
MANIFEST="app/src/main/AndroidManifest.xml"
if [ -f "$MANIFEST" ]; then
    REQUIRED_PERMISSIONS=(
        "android.permission.INTERNET"
        "android.permission.ACCESS_FINE_LOCATION"
        "android.permission.ACCESS_COARSE_LOCATION"
        "android.permission.CAMERA"
    )
    
    for permission in "${REQUIRED_PERMISSIONS[@]}"; do
        if grep -q "$permission" "$MANIFEST"; then
            echo -e "${GREEN}✅ Permiso $permission presente${NC}"
        else
            echo -e "${RED}❌ Permiso $permission FALTANTE${NC}"
            ((ERRORS++))
        fi
    done
fi

# 8. Verificar CodeMagic
echo -e "\n📌 Verificando CodeMagic..."
if [ -f "codemagic.yaml" ]; then
    if grep -q "android-workflow" codemagic.yaml && \
       grep -q "android-debug-workflow" codemagic.yaml; then
        echo -e "${GREEN}✅ CodeMagic workflows configurados${NC}"
    else
        echo -e "${RED}❌ CodeMagic workflows incompletos${NC}"
        ((ERRORS++))
    fi
fi

# 9. Intentar compilación de prueba (si Gradle está disponible)
echo -e "\n📌 Intentando compilación de prueba..."
if [ -f "./gradlew" ]; then
    echo -e "${YELLOW}Ejecutando: ./gradlew tasks${NC}"
    if ./gradlew tasks &> /dev/null; then
        echo -e "${GREEN}✅ Gradle funciona correctamente${NC}"
    else
        echo -e "${RED}❌ Error al ejecutar Gradle${NC}"
        ((ERRORS++))
    fi
else
    echo -e "${YELLOW}⚠️ gradlew no encontrado (normal en Replit)${NC}"
fi

# RESUMEN
echo -e "\n═══════════════════════════════════════════════════════════"
echo -e "                     📊 RESUMEN                           "
echo -e "═══════════════════════════════════════════════════════════"

if [ $ERRORS -eq 0 ] && [ $WARNINGS -eq 0 ]; then
    echo -e "${GREEN}✅ PROYECTO LISTO PARA COMPILAR${NC}"
    echo -e "${GREEN}   No se encontraron errores${NC}"
elif [ $ERRORS -eq 0 ]; then
    echo -e "${YELLOW}⚠️ PROYECTO PUEDE COMPILAR${NC}"
    echo -e "${YELLOW}   $WARNINGS advertencias encontradas${NC}"
else
    echo -e "${RED}❌ PROYECTO TIENE PROBLEMAS${NC}"
    echo -e "${RED}   $ERRORS errores encontrados${NC}"
    echo -e "${YELLOW}   $WARNINGS advertencias encontradas${NC}"
fi

echo -e "\n═══════════════════════════════════════════════════════════"
echo -e "💡 SIGUIENTE PASO:"
echo -e "   1. Sube el código a GitHub"
echo -e "   2. Configura CodeMagic con las variables de entorno"
echo -e "   3. Ejecuta el workflow 'android-workflow' para AAB"
echo -e "   4. O ejecuta 'android-debug-workflow' para APK de prueba"
echo -e "═══════════════════════════════════════════════════════════"

exit $ERRORS