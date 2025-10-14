#!/bin/bash

# Script automático de push con credenciales incluidas
echo "═══════════════════════════════════════════════════════════"
echo "         🚀 AUTO PUSH A GITHUB                            "
echo "═══════════════════════════════════════════════════════════"
echo ""

# Configurar credenciales
git config --global user.name "jclouds312"
git config --global user.email "jclouds312@users.noreply.github.com"

# URL con token incluido
REPO_URL="https://jclouds312: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@github.com/jclouds312/Taxiapp.git"

echo "📌 Subiendo rama v1..."
git checkout v1
git remote set-url origin "$REPO_URL"
git push origin v1
echo "✅ Rama v1 subida"
echo ""

echo "📌 Subiendo rama replit-agent..."
git checkout replit-agent
git push origin replit-agent
echo "✅ Rama replit-agent subida"
echo ""

echo "═══════════════════════════════════════════════════════════"
echo "         ✅ PUSH COMPLETADO                               "
echo "═══════════════════════════════════════════════════════════"
echo ""
echo "Verifica en: https://github.com/jclouds312/Taxiapp"