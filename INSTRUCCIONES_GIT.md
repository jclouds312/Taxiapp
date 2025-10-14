# 📋 Instrucciones para Subir a GitHub - Rama APK Pasajeros

## ✅ Cambios Realizados

El proyecto ha sido reestructurado correctamente:

- ✓ Todos los archivos Android movidos a la raíz del repositorio
- ✓ `codemagic.yaml` actualizado para funcionar desde la raíz
- ✓ `.gitignore` configurado para la nueva estructura
- ✓ `gradlew` funcionando correctamente
- ✓ Estructura lista para CodeMagic

## 📁 Nueva Estructura del Proyecto

```
/ (raíz)
├── app/                    # Módulo principal de la app
├── gradle/                 # Wrapper de Gradle
├── build.gradle           # Configuración principal
├── settings.gradle        # Configuración de módulos
├── gradlew               # Gradle wrapper (Linux/Mac)
├── gradlew.bat          # Gradle wrapper (Windows)
├── codemagic.yaml       # Configuración de CodeMagic ✨
├── .gitignore           # Archivos ignorados
└── app/
    ├── build.gradle     # Configuración del módulo
    ├── src/             # Código fuente
    └── ...
```

## 🚀 Comandos para Subir a GitHub

**Abre el Shell de Replit** y ejecuta estos comandos:

### Paso 1: Configurar Git (solo primera vez)
```bash
git config user.name "jclouds312"
git config user.email "jclouds312@users.noreply.github.com"
```

### Paso 2: Crear la nueva rama "apk-pasajeros"
```bash
# Crear y cambiar a la nueva rama
git checkout -b apk-pasajeros

# Verificar en qué rama estás
git branch
```

### Paso 3: Agregar todos los cambios
```bash
# Ver qué archivos han cambiado
git status

# Agregar TODOS los archivos
git add .

# Verificar los archivos agregados
git status
```

### Paso 4: Crear el commit
```bash
git commit -m "Reestructuración del proyecto - Android en raíz para CodeMagic"
```

### Paso 5: Subir a GitHub
```bash
# Subir la nueva rama al repositorio
git push https://jclouds312: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@github.com/jclouds312/Taxiapp.git apk-pasajeros
```

## 📊 Verificar en GitHub

Después del push:

1. Ve a: https://github.com/jclouds312/Taxiapp
2. Cambia a la rama **"apk-pasajeros"** en el selector de ramas
3. Verifica que los archivos estén en la raíz:
   - ✓ `gradlew` debe estar visible
   - ✓ `app/` debe estar visible
   - ✓ `codemagic.yaml` debe estar en la raíz
   - ✓ No debe haber carpeta "Source Codes/"

## ⚙️ Configurar CodeMagic

### 1. Ir a CodeMagic
Ve a: https://codemagic.io/

### 2. Seleccionar el Repositorio
- Selecciona: `jclouds312/Taxiapp`

### 3. Configurar la Rama
- **Rama a monitorear**: `apk-pasajeros`
- **Workflow**: `android-workflow` (se detectará automáticamente)

### 4. Iniciar Build
- CodeMagic detectará el `codemagic.yaml` en la raíz
- Ejecutará el workflow `android-workflow`
- Compilará el APK automáticamente

## 🔍 Workflows Disponibles

El archivo `codemagic.yaml` contiene 3 workflows:

1. **`android-workflow`** (Principal)
   - Build de release con versionamiento automático
   - Genera APK firmado

2. **`android-passenger-workflow`**
   - Build específico para pasajeros
   - Mismo proceso que android-workflow

3. **`android-debug`**
   - Build de debug rápido
   - Sin ofuscación ni optimización

## ✉️ Notificaciones

Recibirás un email cuando:
- ✅ La compilación sea exitosa
- ❌ La compilación falle

Los APKs compilados estarán disponibles en:
- CodeMagic → Builds → Artifacts

## 🔐 Seguridad - MUY IMPORTANTE

**Después de hacer el push, REVOCA el token de GitHub:**

1. Ve a: https://github.com/settings/tokens
2. Busca el token que termina en `4PnZfg`
3. Click en **"Delete"** o **"Revoke"**
4. Genera un nuevo token para uso futuro
5. Guárdalo en un lugar seguro (no en el chat)

## ❓ Solución de Problemas

### Si CodeMagic no detecta el workflow:
1. Verifica que `codemagic.yaml` esté en la raíz
2. Verifica la sintaxis YAML (usa yamllint.com)
3. Asegúrate de estar en la rama correcta

### Si el build falla:
1. Revisa los logs en CodeMagic
2. Verifica que el `gradlew` tenga permisos de ejecución
3. Asegúrate de que `app/build.gradle` esté correcto

### Si los archivos no aparecen:
1. Ejecuta: `git status` para ver qué se va a subir
2. Asegúrate de hacer `git add .` antes del commit
3. Verifica que no estén en `.gitignore`

## 📝 Próximos Pasos

Después de que CodeMagic compile exitosamente:

1. Descarga el APK desde CodeMagic
2. Prueba el APK en un dispositivo Android o emulador
3. Si funciona correctamente, configura el keystore para firmar releases
4. Publica en Google Play Store (cuando esté listo)

---

**¡Todo listo para subir a GitHub y compilar en CodeMagic! 🚀**
