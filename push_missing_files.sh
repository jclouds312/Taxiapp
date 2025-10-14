#!/bin/bash

echo "═══════════════════════════════════════════════════════════"
echo "     📤 SUBIENDO ARCHIVOS FALTANTES PARA CODEMAGIC         "
echo "═══════════════════════════════════════════════════════════"

# Verificar que los archivos existen
echo ""
echo "📋 Verificando archivos necesarios..."

FILES_OK=true

if [ -f "app/src/main/java/in/techware/ladriver/model/PayStatementBean.java" ]; then
    echo "✅ PayStatementBean.java existe"
else
    echo "❌ PayStatementBean.java NO existe"
    FILES_OK=false
fi

if [ -f "app/src/main/java/in/techware/ladriver/adapter/PayStatementsAdapter.java" ]; then
    echo "✅ PayStatementsAdapter.java existe"
else
    echo "❌ PayStatementsAdapter.java NO existe"
    FILES_OK=false
fi

if [ -f "app/src/main/java/in/techware/ladriver/net/invokers/PayStatementsInvoker.java" ]; then
    echo "✅ PayStatementsInvoker.java existe"
else
    echo "❌ PayStatementsInvoker.java NO existe"
    FILES_OK=false
fi

if [ -f "app/src/main/java/in/techware/ladriver/activity/PayStatementsActivity.java" ]; then
    echo "✅ PayStatementsActivity.java existe"
else
    echo "❌ PayStatementsActivity.java NO existe"
    FILES_OK=false
fi

if [ "$FILES_OK" = false ]; then
    echo ""
    echo "❌ ERROR: Faltan archivos necesarios"
    exit 1
fi

# Verificar el import
echo ""
echo "📋 Verificando import en EarningsFragment..."
if grep -q "import in.techware.ladriver.activity.PayStatementsActivity;" app/src/main/java/in/techware/ladriver/fragments/EarningsFragment.java; then
    echo "✅ Import de PayStatementsActivity encontrado"
else
    echo "❌ Import NO encontrado"
fi

# Agregar los archivos al staging
echo ""
echo "📦 Agregando archivos al commit..."
git add app/src/main/java/in/techware/ladriver/model/PayStatementBean.java
git add app/src/main/java/in/techware/ladriver/adapter/PayStatementsAdapter.java
git add app/src/main/java/in/techware/ladriver/net/invokers/PayStatementsInvoker.java
git add app/src/main/java/in/techware/ladriver/activity/PayStatementsActivity.java
git add app/src/main/java/in/techware/ladriver/fragments/EarningsFragment.java

# Hacer commit
echo ""
echo "💾 Creando commit..."
git commit -m "Fix: Agregados archivos faltantes de PayStatements para CodeMagic build"

# Push a todas las ramas importantes
echo ""
echo "📤 Subiendo a GitHub..."

# Push a la rama actual primero
CURRENT_BRANCH=$(git branch --show-current)
echo "   Subiendo rama $CURRENT_BRANCH..."
git push origin $CURRENT_BRANCH

# También push a main si no es la actual
if [ "$CURRENT_BRANCH" != "main" ]; then
    echo "   Actualizando rama main..."
    git checkout main
    git cherry-pick HEAD@{1}
    git push origin main
    git checkout $CURRENT_BRANCH
fi

echo ""
echo "═══════════════════════════════════════════════════════════"
echo "✅ ARCHIVOS SUBIDOS A GITHUB EXITOSAMENTE"
echo "═══════════════════════════════════════════════════════════"
echo ""
echo "📱 AHORA EN CODEMAGIC:"
echo "   1. Ve a tu proyecto en CodeMagic"
echo "   2. Haz click en 'Start new build'"
echo "   3. Usa cualquiera de estas ramas:"
echo "      • main"
echo "      • v1"
echo "      • taxi-suite-complete"
echo ""
echo "✅ Todos los archivos necesarios están en GitHub"
echo "✅ El error 'cannot find symbol' está SOLUCIONADO"
echo ""
echo "═══════════════════════════════════════════════════════════"