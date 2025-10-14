# LaTaxi - Android Passenger Application

## 📱 Project Overview
Android mobile application for "LaTaxi" taxi/ride-sharing service. Passenger-side app with features:
- User registration and login
- Real-time ride requests
- Driver location tracking
- Ride history and payments
- Driver rating system

## 🛠️ Technology Stack
- **Platform**: Android Native (Java)
- **Build System**: Gradle 7.3.3
- **Min SDK**: 19 (Android 4.4 KitKat)
- **Target SDK**: 30 (Android 11)
- **Compile SDK**: 31

### Key Dependencies
- Google Play Services (Maps, Location, Places)
- Firebase (Authentication, Messaging)
- Material Design Components
- OkHttp for networking
- Glide for image loading
- Gson for JSON parsing

## 🏗️ Project Structure (Updated)

```
/ (raíz del repositorio)
├── app/                      # Módulo principal Android
│   ├── src/main/
│   │   ├── java/in/techware/lataxi/
│   │   │   ├── activity/    # Pantallas UI
│   │   │   ├── adapter/     # RecyclerView adapters
│   │   │   ├── net/         # Capa de networking
│   │   │   ├── model/       # Modelos de datos
│   │   │   └── util/        # Utilidades
│   │   ├── res/             # Recursos (layouts, drawables)
│   │   └── AndroidManifest.xml
│   └── build.gradle         # Configuración del módulo
├── gradle/                   # Gradle wrapper
├── build.gradle             # Configuración raíz
├── settings.gradle          # Configuración de módulos
├── gradlew                  # Gradle wrapper ejecutable
├── codemagic.yaml          # ✨ Configuración CI/CD
└── .gitignore              # Git ignore actualizado
```

## 🔄 Recent Changes (October 2025)

**✅ Restructured for CodeMagic CI/CD**
- Moved entire Android project to repository root
- Updated `codemagic.yaml` to work from root directory
- Removed nested "Source Codes/LaTaxi" structure
- All build scripts now execute from root
- Ready for automated builds on branch `apk-pasajeros`

## 🚀 CI/CD Configuration

### CodeMagic Workflows

#### 1. `android-workflow` (Main)
- Builds release APK with automatic versioning
- Generates signed APK
- Email notifications on success/failure

#### 2. `android-passenger-workflow`
- Passenger-specific build
- Same process as main workflow

#### 3. `android-debug`
- Fast debug builds
- No obfuscation or optimization

### Build Commands
```bash
# Debug build
./gradlew assembleDebug

# Release build
./gradlew assembleRelease
```

## 🔗 Backend Integration
- **API Base**: `http://techlabz.in/lataxi/Webservices`
- REST endpoints for:
  - Authentication (login, registration, OTP)
  - Ride requests and management
  - Location services
  - Payment processing
  - Trip history

## ⚙️ Firebase & Google Services
- **Firebase**: Mock config present (`app/google-services.json`)
- **Google Maps API**: Required for location features
- For production: Replace with real Firebase credentials

## 🔐 Important Notes

### Replit Environment
- ⚠️ **Android apps CANNOT run in Replit** (no Android SDK/emulator)
- This project is configured for **CodeMagic CI/CD**
- Replit is used for code editing and git operations only

### Development Options
1. **CodeMagic** - Automated builds on git push (Recommended for CI/CD)
2. **Android Studio** - Full local development (Recommended for coding)
3. **Command Line** - With Android SDK installed locally

## 📝 Git Workflow

### Current Repository
- **URL**: https://github.com/jclouds312/Taxiapp.git
- **Main Branch**: `passenger`
- **New Branch**: `apk-pasajeros` (for restructured version)

### Push to GitHub
See `INSTRUCCIONES_GIT.md` for detailed instructions

## 🎯 Next Steps

1. **Push to GitHub** (branch: apk-pasajeros)
2. **Configure CodeMagic** to monitor the branch
3. **Automatic APK builds** on every push
4. **Download APKs** from CodeMagic artifacts
5. **Test on device/emulator**
6. **Configure keystore** for signed releases
7. **Publish to Google Play** (when ready)

## 📊 Project Status
- ✅ Project restructured for CI/CD
- ✅ CodeMagic configuration ready
- ✅ Build system verified (Gradle working)
- ✅ Git ready for push
- ⏳ Awaiting GitHub push
- ⏳ Awaiting CodeMagic build

## 🔒 Security Reminders
- Never commit API keys or secrets
- Use environment variables for sensitive data
- Revoke exposed GitHub tokens immediately
- Use Firebase/Google Cloud for key management

---

Last updated: October 14, 2025
