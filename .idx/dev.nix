{ pkgs, ... }: {
  channel = "stable-24.05";
  packages = [
    pkgs.jdk11
    pkgs.android-sdk
  ];
  env = {
    JAVA_HOME = "${pkgs.jdk11}";
    ANDROID_HOME = "${pkgs.android-sdk}";
  };
  idx = {
    workspace = {
      onStart = {
        accept-licenses = ''
          yes | $ANDROID_HOME/cmdline-tools/latest/bin/sdkmanager --licenses || \
          yes | $ANDROID_HOME/tools/bin/sdkmanager --licenses || \
          echo "Warning: could not accept licenses."
        '';
      };
    };
  };
}
