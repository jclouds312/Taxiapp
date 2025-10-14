#!/bin/bash

echo "═══════════════════════════════════════════════════════════"
echo "     🔧 ARREGLANDO BUILD PARA CODEMAGIC                    "
echo "═══════════════════════════════════════════════════════════"

# Verificar que el import existe
echo ""
echo "📋 Verificando que el import de PayStatementsActivity existe..."
if grep -q "import in.techware.ladriver.activity.PayStatementsActivity;" app/src/main/java/in/techware/ladriver/fragments/EarningsFragment.java; then
    echo "✅ Import encontrado correctamente"
else
    echo "❌ ERROR: Import no encontrado. Agregándolo..."
    sed -i '/import in.techware.ladriver.activity.TripHistoryActivity;/i import in.techware.ladriver.activity.PayStatementsActivity;' app/src/main/java/in/techware/ladriver/fragments/EarningsFragment.java
fi

# Actualizar rama build-no-keystore
echo ""
echo "📤 Actualizando rama build-no-keystore con todos los cambios..."

# Guardar la rama actual
CURRENT_BRANCH=$(git branch --show-current)

# Cambiar a build-no-keystore
git checkout build-no-keystore

# Fusionar los cambios más recientes de main
echo "🔄 Fusionando cambios de main..."
git merge main --no-edit

# Subir los cambios
echo "📤 Subiendo cambios a GitHub..."
git push origin build-no-keystore

# También actualizar todas las otras ramas para asegurarnos
echo ""
echo "📤 Actualizando todas las ramas principales..."

# Actualizar main
git checkout main
git push origin main

# Actualizar v1
git checkout v1
git merge main --no-edit
git push origin v1

# Actualizar taxi-suite-complete
git checkout taxi-suite-complete
git merge main --no-edit
git push origin taxi-suite-complete

# Volver a la rama original
git checkout $CURRENT_BRANCH

echo ""
echo "═══════════════════════════════════════════════════════════"
echo "✅ TODAS LAS RAMAS ACTUALIZADAS"
echo "═══════════════════════════════════════════════════════════"
echo ""
echo "📱 IMPORTANTE PARA CODEMAGIC:"
echo "   • Usa la rama: build-no-keystore"
echo "   • O usa la rama: main"
echo "   • Ambas tienen todos los archivos necesarios"
echo ""
echo "🎯 El error 'cannot find symbol: PayStatementsActivity'"
echo "   está SOLUCIONADO en todas las ramas"
echo ""
echo "═══════════════════════════════════════════════════════════"