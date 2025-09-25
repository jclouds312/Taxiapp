#!/bin/bash
set -e
export JAVA_HOME=/opt/nix/store/h6fh0q7s3ln9h6qnlyaf59g8ahqj65gl-openjdk-8u442-b06/lib/openjdk
ORIGINAL_SDK_ROOT=/nix/store/fplyqgcbdpqcl72nplfdqj4bdggg8jfr-androidsdk/libexec/android-sdk
WRITABLE_SDK_ROOT=/home/user/android-sdk-writable

# Create a writable SDK root and deep copy the original SDK content
mkdir -p "$WRITABLE_SDK_ROOT"
cp -rL "$ORIGINAL_SDK_ROOT"/* "$WRITABLE_SDK_ROOT"/

# Tell Gradle to use the new writable SDK root
echo "sdk.dir=$WRITABLE_SDK_ROOT" > local.properties

# Install the required packages, accepting licenses as we go
yes | "$WRITABLE_SDK_ROOT/tools/bin/sdkmanager" --sdk_root="$WRITABLE_SDK_ROOT" "platforms;android-30" "build-tools;30.0.2"

# Stop any lingering Gradle daemons
./gradlew --stop

# Build the app
./gradlew assembleRelease
