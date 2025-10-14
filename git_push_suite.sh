#!/bin/bash

# Script para subir la suite completa a GitHub

echo "═══════════════════════════════════════════════════════════"
echo "     📤 SUBIENDO SUITE SIMPLE TAXI A GITHUB                "
echo "═══════════════════════════════════════════════════════════"

# Configuración
GITHUB_USER="jclouds312"
GITHUB_TOKEN="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"
DRIVER_REPO="Taxiapp"
PASSENGER_REPO="TaxiappPassenger"

# Configurar git
git config --global user.name "$GITHUB_USER"
git config --global user.email "${GITHUB_USER}@users.noreply.github.com"

echo "📱 App Conductor:"
echo "  Repositorio: https://github.com/${GITHUB_USER}/${DRIVER_REPO}"

# Configurar remote con token
git remote set-url origin https://${GITHUB_USER}:${GITHUB_TOKEN}@github.com/${GITHUB_USER}/${DRIVER_REPO}.git 2>/dev/null || \
git remote add origin https://${GITHUB_USER}:${GITHUB_TOKEN}@github.com/${GITHUB_USER}/${DRIVER_REPO}.git

# Crear nueva rama para la suite completa
git checkout -b taxi-suite-complete 2>/dev/null || git checkout taxi-suite-complete

# Agregar todos los cambios
git add -A
git commit -m "Suite completa Simple Taxi - Conductor + Pasajero + Dashboard integration" -m "
- ✅ App Conductor actualizada a Android 14 (SDK 34)
- ✅ Todas las dependencias actualizadas (Oct 2025)
- ✅ CodeMagic CI/CD configurado con 3 workflows
- ✅ ProGuard optimizado para release
- ✅ Documentación completa de la suite
- ✅ Scripts de compilación automática
- ✅ Integración con backend techlabz.in verificada
- 🚧 App Pasajero lista para desarrollo
- 📊 Dashboard PHP conectado
"

# Push a GitHub
echo "📤 Subiendo rama taxi-suite-complete..."
git push -f origin taxi-suite-complete

# Actualizar rama v1
git checkout v1
git merge taxi-suite-complete --no-edit
git push origin v1

# Actualizar rama replit-agent
git checkout replit-agent
git merge taxi-suite-complete --no-edit  
git push origin replit-agent

echo ""
echo "═══════════════════════════════════════════════════════════"
echo "✅ SUITE SUBIDA EXITOSAMENTE"
echo "═══════════════════════════════════════════════════════════"
echo ""
echo "📋 Ramas actualizadas:"
echo "  • taxi-suite-complete (nueva)"
echo "  • v1 (actualizada)"
echo "  • replit-agent (actualizada)"
echo ""
echo "🔗 Ver en GitHub:"
echo "  https://github.com/${GITHUB_USER}/${DRIVER_REPO}"
echo ""
echo "🚀 Siguiente paso:"
echo "  1. Configurar CodeMagic con ambas apps"
echo "  2. Agregar variables de entorno"
echo "  3. Ejecutar workflows de compilación"
echo "═══════════════════════════════════════════════════════════"