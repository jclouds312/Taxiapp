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
      "redhat.java",
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
