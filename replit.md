# LaTaxi - Android Passenger Application

## Project Overview
This is an **Android mobile application** for a taxi/ride-sharing service called "LaTaxi". It's a passenger-side app that allows users to:
- Register and login
- Request rides
- Track driver location
- View ride history
- Manage payment methods
- Rate drivers

## Technology Stack
- **Platform**: Android (Native Java)
- **Build System**: Gradle 7.2.2
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

## Backend Integration
The app connects to an external backend API:
- **Base URL**: `http://techlabz.in/lataxi/Webservices`
- Uses HTTP/HTTPS for API communication
- Implements REST-style endpoints for:
  - User authentication (login, registration, OTP)
  - Ride requests and management
  - Location services
  - Payment processing
  - Trip history

## ⚠️ Important: Replit Environment Limitations

**This Android application CANNOT run in Replit** because:

1. **No Android SDK**: Replit doesn't provide Android SDK or build tools required to compile Android apps
2. **No Emulator**: Android apps need an emulator or physical device to run, which Replit doesn't support
3. **Mobile-Specific**: This is a mobile app, not a web application or backend service

## Recommended Development Environments

To work with this Android app, you should use:

1. **Android Studio** (Recommended)
   - Official IDE for Android development
   - Includes Android SDK, emulators, and all necessary tools
   - Download: https://developer.android.com/studio

2. **VS Code with Android Extensions**
   - Requires manual Android SDK setup
   - Less feature-complete than Android Studio

3. **Command Line Build**
   - Requires Android SDK installation
   - Build with: `./gradlew assembleDebug`

## Build Instructions

If you have Android SDK installed locally:

```bash
cd "Source Codes/LaTaxi"

# Set up local.properties (update path to your SDK)
echo "sdk.dir=/path/to/your/android/sdk" > local.properties

# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease
```

## Project Structure
```
Source Codes/LaTaxi/
├── app/
│   ├── src/main/
│   │   ├── java/in/techware/lataxi/
│   │   │   ├── activity/      # UI screens
│   │   │   ├── adapter/       # RecyclerView adapters
│   │   │   ├── net/          # Networking layer
│   │   │   ├── model/        # Data models
│   │   │   ├── services/     # Background services
│   │   │   └── util/         # Utilities
│   │   ├── res/              # Resources (layouts, drawables, etc.)
│   │   └── AndroidManifest.xml
│   ├── build.gradle          # App-level build config
│   └── google-services.json  # Firebase config (mock)
├── build.gradle              # Project-level build config
└── settings.gradle
```

## Firebase Configuration
The current `google-services.json` contains mock/placeholder data. For production:
1. Create a Firebase project at https://console.firebase.google.com
2. Add your Android app with package name: `in.techware.lataxi`
3. Download the real `google-services.json`
4. Replace the existing file

## Google Maps API
The app requires Google Maps API key for:
- Location display
- Route calculation
- Place search

Set up in Google Cloud Console and add to AndroidManifest.xml.

## What Can Be Done in Replit

While the Android app itself cannot run here, you could potentially:

1. **Develop a Backend API** - Build the server-side services that the app connects to
2. **Create a Web Version** - Build a web-based version of the taxi app
3. **Build Admin Panel** - Create a web dashboard to manage the taxi service
4. **Documentation** - Write API documentation and user guides

## Current Status
- ✅ Project structure intact
- ✅ Build configuration present
- ✅ Dependencies defined
- ❌ Cannot build (no Android SDK)
- ❌ Cannot run (requires Android environment)
- ⚠️ Firebase config is mock data

## Next Steps (Outside Replit)

1. Install Android Studio
2. Open this project
3. Sync Gradle files
4. Configure Firebase with real credentials
5. Add Google Maps API key
6. Configure backend API endpoints if needed
7. Build and test on emulator or device
