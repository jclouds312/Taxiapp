# Para obtener más información sobre cómo usar Nix para configurar tu entorno,
# consulta: https://firebase.google.com/docs/studio/customize-workspace
{ pkgs, ... }: {
  # Qué canal de nixpkgs usar.
  channel = "stable-24.05"; # o "unstable"

  # Usa https://search.nixos.org/packages para encontrar paquetes
  packages = [
    pkgs.jdk11 # Requerido por LaTaxiDriver
    # pkgs.jdk17 # Requerido por LaTaxi
    pkgs.android-sdk
  ];

  # Establece variables de entorno en el espacio de trabajo
  env = {
    JAVA_HOME = "${pkgs.jdk11}";
    ANDROID_HOME = "${pkgs.android-sdk}";
    ANDROID_SDK_ROOT = "${pkgs.android-sdk}";
  };

  idx = {
    # Busca las extensiones que quieras en https://open-vsx.org/ y usa "publisher.id"
    extensions = [
      "redhat.java"Build APK
      1m 30s
      
      
      
      Show command
      Showing the last 50 lines. Click this button to load the full log
                                       ^
                                       /Users/builder/clone/app/src/main/java/in/techware/ladriver/activity/OnTripActivity.java:15: error: package android.support.design.widget does not exist
                                       import android.support.design.widget.BottomSheetBehavior;
                                                                           ^
                                                                           /Users/builder/clone/app/src/main/java/in/techware/ladriver/activity/OnTripActivity.java:16: error: package android.support.design.widget does not exist
                                                                           import android.support.design.widget.Snackbar;
                                                                                                               ^
                                                                                                               /Users/builder/clone/app/src/main/java/in/techware/ladriver/activity/OnTripActivity.java:17: error: cannot find symbol
                                                                                                               import android.support.v4.app.ActivityCompat;
                                                                                                                                            ^
                                                                                                                                              symbol:   class ActivityCompat
                                                                                                                                                location: package android.support.v4.app
                                                                                                                                                /Users/builder/clone/app/src/main/java/in/techware/ladriver/activity/OnTripActivity.java:18: error: cannot find symbol
                                                                                                                                                import android.support.v4.app.FragmentManager;
                                                                                                                                                                             ^
                                                                                                                                                                               symbol:   class FragmentManager
                                                                                                                                                                                 location: package android.support.v4.app
                                                                                                                                                                                 /Users/builder/clone/app/src/main/java/in/techware/ladriver/activity/OnTripActivity.java:19: error: package android.support.v4.content does not exist
                                                                                                                                                                                 import android.support.v4.content.ContextCompat;
                                                                                                                                                                                                                  ^
                                                                                                                                                                                                                  /Users/builder/clone/app/src/main/java/in/techware/ladriver/activity/OnTripActivity.java:102: error: cannot find symbol
                                                                                                                                                                                                                      private FragmentManager myFragmentManager;
                                                                                                                                                                                                                                  ^
                                                                                                                                                                                                                                    symbol:   class FragmentManager
                                                                                                                                                                                                                                      location: class OnTripActivity
                                                                                                                                                                                                                                      Note: Some input files use or override a deprecated API.
                                                                                                                                                                                                                                      Note: Recompile with -Xlint:deprecation for details.
                                                                                                                                                                                                                                      Note: Some input files use unchecked or unsafe operations.
                                                                                                                                                                                                                                      Note: Recompile with -Xlint:unchecked for details.
                                                                                                                                                                                                                                      Note: Some messages have been simplified; recompile with -Xdiags:verbose to get full output
                                                                                                                                                                                                                                      100 errors
                                                                                                                                                                                                                                      
                                                                                                                                                                                                                                      FAILURE: Build failed with an exception.
                                                                                                                                                                                                                                      
                                                                                                                                                                                                                                      * What went wrong:
                                                                                                                                                                                                                                      Execution failed for task ':app:compileReleaseJavaWithJavac'.
                                                                                                                                                                                                                                      > Compilation failed; see the compiler error output for details.
                                                                                                                                                                                                                                      
                                                                                                                                                                                                                                      * Try:
                                                                                                                                                                                                                                      Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output. Run with --scan to get full insights.
                                                                                                                                                                                                                                      
                                                                                                                                                                                                                                      * Get more help at https://help.gradle.org
                                                                                                                                                                                                                                      
                                                                                                                                                                                                                                      BUILD FAILED in 1m 29s
                                                                                                                                                                                                                                      27 actionable tasks: 27 executed
                                                                                                                                                                                                                                      
                                                                                                                                                                                                                                      
                                                                                                                                                                                                                                      Build failed :|
                                                                                                                                                                                                                                      Step 3 script `Build APK` exited with status code 1
                                                                                                                                                                                                                                      
                                                                                                                                                                                                                                      Applications
                                                                                                                                                                                                                                      Builds
                                                                                                                                                                                                                                      OTA Updates
                                                                                                                                                                                                                                      NEW
                                                                                                                                                                                                                                      App Preview
                                                                                                                                                                                                                                      Teams
                                                                                                                                                                                                                                      Billing
                                                                                                                                                                                                                                      
                                                                                                                                                                                                                                      
                                                                                                                                                                                                                                      Taxiapp
                                                                                                                                                                                                                                      Taxiapp
                                                                                                                                                                                                                                      github.com/jclouds312/Taxiapp
                                                                                                                                                                                                                                      
                                                                                                                                                                                                                                      
                                                                                                                                                                                                                                      Start new build
                                                                                                                                                                                                                                      Build overview
                                                                                                                                                                                                                                      Click on the build steps for details.
                                                                                                                                                                                                                                      Preparing build machine
                                                                                                                                                                                                                                      25s
                                                                                                                                                                                                                                      Fetching app sources
                                                                                                                                                                                                                                      2s
                                                                                                                                                                                                                                      Decode keystore
                                                                                                                                                                                                                                      < 1s
                                                                                                                                                                                                                                      Publishing
                                                                                                                                                                                                                                      < 1s
                                                                                                                                                                                                                                      Cleaning up
                                                                                                                                                                                                                                      < 1s
                                                                                                                                                                                                                                      Step 3 script `Build APK` exited with status code 1
                                                                                                                                                                                                                                      
                                                                                                                                                                                                                                      Build APK
      1m 26s
      
      
      
      Show command
      Showing the last 50 lines. Click this button to load the full log
      /Users/builder/clone/app/src/main/java/in/techware/ladriver/activity/ProfileActivity.java:61: error: cannot find symbol
          private TextInputLayout tilCity;
                      ^
                        symbol:   class TextInputLayout
                          location: class ProfileActivity
                          /Users/builder/clone/app/src/main/java/in/techware/ladriver/activity/ProfileActivity.java:62: error: cannot find symbol
                              private TextInputLayout tilState;
                                          ^
                                            symbol:   class TextInputLayout
                                              location: class ProfileActivity
                                              /Users/builder/clone/app/src/main/java/in/techware/ladriver/activity/ProfileActivity.java:63: error: cannot find symbol
                                                  private TextInputLayout tilPostalCode;
                                                              ^
                                                                symbol:   class TextInputLayout
                                                                  location: class ProfileActivity
                                                                  /Users/builder/clone/app/src/main/java/in/techware/ladriver/activity/ProfileActivity.java:64: error: cannot find symbol
                                                                      private TextInputEditText etxtAddress1;
                                                                                  ^
                                                                                    symbol:   class TextInputEditText
                                                                                      location: class ProfileActivity
                                                                                      /Users/builder/clone/app/src/main/java/in/techware/ladriver/activity/ProfileActivity.java:65: error: cannot find symbol
                                                                                          private TextInputEditText etxtCity;
                                                                                                      ^
                                                                                                        symbol:   class TextInputEditText
                                                                                                          location: class ProfileActivity
                                                                                                          Note: Some input files use or override a deprecated API.
                                                                                                          Note: Recompile with -Xlint:deprecation for details.
                                                                                                          Note: Some input files use unchecked or unsafe operations.
                                                                                                          Note: Recompile with -Xlint:unchecked for details.
                                                                                                          Note: Some messages have been simplified; recompile with -Xdiags:verbose to get full output
                                                                                                          100 errors
                                                                                                          
                                                                                                          FAILURE: Build failed with an exception.
                                                                                                          
                                                                                                          * What went wrong:
                                                                                                          Execution failed for task ':app:compileReleaseJavaWithJavac'.
                                                                                                          > Compilation failed; see the compiler error output for details.
                                                                                                          
                                                                                                          * Try:
                                                                                                          Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output. Run with --scan to get full insights.
                                                                                                          
                                                                                                          * Get more help at https://help.gradle.org
                                                                                                          
                                                                                                          BUILD FAILED in 1m 25s
                                                                                                          27 actionable tasks: 27 executed
                                                                                                          
                                                                                                          
                                                                                                          Build failed :|
                                                                                                          Step 3 script `Build APK` exited with status code 1
                                                                                                          
                                                                                                          Applications
                                                                                                          Builds
                                                                                                          OTA Updates
                                                                                                          NEW
                                                                                                          App Preview
                                                                                                          Teams
                                                                                                          Billing
                                                                                                          
                                                                                                          
                                                                                                          Taxiapp
                                                                                                          Taxiapp
                                                                                                          github.com/jclouds312/Taxiapp
                                                                                                          
                                                                                                          
                                                                                                          Start new build
                                                                                                          Build overview
                                                                                                          Click on the build steps for details.
                                                                                                          Preparing build machine
                                                                                                          28s
                                                                                                          Fetching app sources
                                                                                                          2s
                                                                                                          Decode keystore
                                                                                                          < 1s
                                                                                                          Publishing
                                                                                                          < 1s
                                                                                                          Cleaning up
                                                                                                          < 1s
                                                                                                          Step 3 script `Build APK` exited with status code 1
                                                                                                          
                                                                                                          ,
      "vscjava.vscode-java-debug"
    ];

    # Habilita previsualizaciones y personaliza los puertos
    previews = {
      enable = true;
      previews = {
        android = {
          # Compila y ejecuta la aplicación de Android y la muestra en
          # el panel de previsualización de Android de IDX
          command = ["./gradlew", ":app:installDebug"];
          manager = "android";
        };
      };
    };

    # Hooks del ciclo de vida del espacio de trabajo
    workspace = {
      # Se ejecuta cuando se crea un espacio de trabajo por primera vez
      onCreate = {
        # Crea local.properties para Gradle
        setup-gradle = ''
          echo "sdk.dir=${pkgs.android-sdk}" > local.properties
        '';

        # Crea un google-services.json de ejemplo si no existe
        setup-firebase = ''
          if [ ! -f app/google-services.json ]; then
              echo '{
                "project_info": { "project_id": "replace-with-your-project-id" },
                "client": [
                  {
                    "client_info": {
                      "mobilesdk_app_id": "replace-with-your-app-id",
                      "android_client_info": { "package_name": "in.techware.lataxidriver" }
                    },
                    "api_key": [{ "current_key": "replace-with-your-api-key" }]
                  }
                ]
              }' > app/google-services.json
              echo "********************************************************************************************************"
              echo "IMPORTANTE: Reemplaza el contenido de \'\'\'app/google-services.json\'\'\' con el de tu proyecto de Firebase."
              echo "********************************************************************************************************"
          fi
        '';
      };

      # Se ejecuta cuando el espacio de trabajo se (re)inicia
      onStart = {
        # Acepta las licencias del SDK de Android para poder compilar.
        accept-licenses = ''
          yes | $ANDROID_HOME/cmdline-tools/latest/bin/sdkmanager --licenses || \
          yes | $ANDROID_HOME/tools/bin/sdkmanager --licenses || \
          echo "Advertencia: No se pudieron aceptar las licencias del SDK."
        '';
      };
    };
  };
}
