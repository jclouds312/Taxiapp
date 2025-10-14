#!/bin/bash

echo "╔════════════════════════════════════════════════════╗"
echo "║  SCRIPT PARA CREAR RAMA Y SUBIR A GITHUB          ║"
echo "╚════════════════════════════════════════════════════╝"
echo ""

# Colores
GREEN='\033[0;32m'
BLUE='\033[0;34m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Variables
REPO_URL="https://github.com/jclouds312/Taxiapp.git"
TOKEN="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"
BRANCH="apk-pasajeros"
USERNAME="jclouds312"
EMAIL="jclouds312@users.noreply.github.com"

echo -e "${BLUE}[1/6]${NC} Configurando Git..."
git config user.name "$USERNAME"
git config user.email "$EMAIL"
echo -e "${GREEN}✓${NC} Git configurado"
echo ""

echo -e "${BLUE}[2/6]${NC} Verificando estado actual..."
git status --short | head -10
echo ""

echo -e "${BLUE}[3/6]${NC} Creando rama '$BRANCH'..."
git checkout -b $BRANCH 2>/dev/null || git checkout $BRANCH
echo -e "${GREEN}✓${NC} Rama '$BRANCH' activa"
echo ""

echo -e "${BLUE}[4/6]${NC} Agregando archivos..."
git add .
echo -e "${GREEN}✓${NC} Archivos agregados"
echo ""

echo -e "${BLUE}[5/6]${NC} Creando commit..."
git commit -m "Reestructuración para CodeMagic - Proyecto Android en raíz

✨ Cambios principales:
- Movido proyecto de 'Source Codes/LaTaxi' a raíz
- Actualizado codemagic.yaml para trabajar desde raíz
- Agregado workflow 'android-workflow' requerido por CodeMagic
- Limpiada estructura del proyecto
- 209 archivos Java listos para compilación

🚀 Listo para build automático en CodeMagic"

echo -e "${GREEN}✓${NC} Commit creado"
echo ""

echo -e "${BLUE}[6/6]${NC} Subiendo a GitHub..."
git push https://$USERNAME:$TOKEN@github.com/$USERNAME/Taxiapp.git $BRANCH

if [ $? -eq 0 ]; then
    echo ""
    echo -e "${GREEN}╔════════════════════════════════════════════════════╗${NC}"
    echo -e "${GREEN}║        ✓ SUBIDO EXITOSAMENTE A GITHUB              ║${NC}"
    echo -e "${GREEN}╚════════════════════════════════════════════════════╝${NC}"
    echo ""
    echo -e "${YELLOW}🔗 Ver en GitHub:${NC}"
    echo "   https://github.com/jclouds312/Taxiapp/tree/$BRANCH"
    echo ""
    echo -e "${YELLOW}⚙️ Próximos pasos:${NC}"
    echo "   1. Ve a CodeMagic: https://codemagic.io/"
    echo "   2. Selecciona el repositorio 'Taxiapp'"
    echo "   3. Configura para monitorear rama '$BRANCH'"
    echo "   4. CodeMagic compilará automáticamente el APK"
    echo ""
    echo -e "${YELLOW}🔒 IMPORTANTE:${NC}"
    echo "   Revoca el token de GitHub ahora:"
    echo "   https://github.com/settings/tokens"
    echo ""
else
    echo ""
    echo -e "${YELLOW}❌ Error al subir. Verifica:${NC}"
    echo "   - Conexión a internet"
    echo "   - Token de GitHub válido"
    echo "   - Permisos del repositorio"
fi
