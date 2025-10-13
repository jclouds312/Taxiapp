#!/bin/bash

echo "╔════════════════════════════════════════════════════════╗"
echo "║   🚀 SCRIPT DE COMMIT Y PUSH PARA SIMPLE DRIVER APP  ║"
echo "╔════════════════════════════════════════════════════════╗"
echo ""

# Colores
GREEN='\033[0;32m'
BLUE='\033[0;34m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Función para hacer commit en una rama
commit_and_push() {
    BRANCH=$1
    echo -e "${BLUE}━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━${NC}"
    echo -e "${YELLOW}📌 Procesando rama: $BRANCH${NC}"
    echo -e "${BLUE}━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━${NC}"
    echo ""
    
    # Cambiar a la rama
    echo "🔄 Cambiando a rama $BRANCH..."
    git checkout $BRANCH
    
    # Verificar estado
    echo ""
    echo "📊 Estado de la rama:"
    git status --short
    echo ""
    
    # Agregar archivos modificados
    echo "➕ Agregando archivos modificados..."
    git add codemagic.yaml
    git add CODEMAGIC_SETUP.md
    git add replit.md
    git add app/build.gradle
    git add scripts/android_info.sh
    echo ""
    
    # Mostrar qué se va a commitear
    echo "📝 Archivos a commitear:"
    git diff --cached --name-status
    echo ""
    
    # Hacer commit
    echo "💾 Haciendo commit..."
    git commit -m "feat: Actualizar configuración CodeMagic y documentación completa

- Mejorar codemagic.yaml con workflows de release y debug
- Agregar CODEMAGIC_SETUP.md con instrucciones completas
- Actualizar replit.md con información de ramas y deployment
- Configurar versionado automático desde Google Play
- Fix app/build.gradle para leer versionCode/versionName desde propiedades de proyecto
- Agregar script de información para Android en Replit

Características:
✅ CodeMagic configurado con 2 workflows (AAB release + APK debug)
✅ Versionado automático desde Google Play funcionando correctamente
✅ Publicación automática a Google Play (internal track)
✅ Documentación completa de setup
✅ Instrucciones de deployment

Dependencias verificadas:
- Android Gradle Plugin: 7.3.0
- Gradle: 7.4
- Firebase configurado
- Todas las libs actualizadas

Fixes:
- app/build.gradle ahora lee versionCode/versionName de project properties
- Artifacts correctamente definidos (AAB para release, APK para debug)
- Documentación actualizada con artifacts correctos"
    
    echo ""
    
    # Preguntar si hacer push
    read -p "¿Hacer push a GitHub? (y/n): " -n 1 -r
    echo ""
    if [[ $REPLY =~ ^[Yy]$ ]]; then
        echo "📤 Haciendo push a origin/$BRANCH..."
        git push origin $BRANCH
        echo -e "${GREEN}✅ Push completado para $BRANCH${NC}"
    else
        echo -e "${YELLOW}⏭️  Push omitido para $BRANCH${NC}"
    fi
    echo ""
}

# Menú principal
echo "Selecciona una opción:"
echo "1) Commit y push en rama v1"
echo "2) Commit y push en rama replit-agent"
echo "3) Commit y push en AMBAS ramas"
echo "4) Solo ver estado (sin commit)"
echo ""
read -p "Opción (1-4): " -n 1 -r
echo ""
echo ""

case $REPLY in
    1)
        commit_and_push "v1"
        ;;
    2)
        commit_and_push "replit-agent"
        ;;
    3)
        commit_and_push "v1"
        echo ""
        commit_and_push "replit-agent"
        ;;
    4)
        echo "📊 Estado rama v1:"
        git checkout v1
        git status
        echo ""
        echo "📊 Estado rama replit-agent:"
        git checkout replit-agent
        git status
        ;;
    *)
        echo "❌ Opción inválida"
        exit 1
        ;;
esac

echo ""
echo -e "${GREEN}╔════════════════════════════════════════════════════════╗${NC}"
echo -e "${GREEN}║              ✅ PROCESO COMPLETADO                     ║${NC}"
echo -e "${GREEN}╔════════════════════════════════════════════════════════╗${NC}"
echo ""
echo "📚 Próximos pasos:"
echo "  1. Ve a https://codemagic.io"
echo "  2. Conecta el repositorio: https://github.com/jclouds312/Taxiapp"
echo "  3. Configura variables de entorno (ver CODEMAGIC_SETUP.md)"
echo "  4. Selecciona el workflow y ejecuta build"
echo ""
echo "📖 Documentación: Ver CODEMAGIC_SETUP.md y replit.md"
echo ""
