#!/bin/bash
set -e
export JAVA_HOME=/opt/nix/store/h6fh0q7s3ln9h6qnlyaf59g8ahqj65gl-openjdk-8u442-b06/lib/openjdk
WRITABLE_SDK_ROOT=/home/user/android-sdk-writable

# Create a minimal, writable SDK directory
mkdir -p "$WRITABLE_SDK_ROOT"

# Tell Gradle to use this new SDK root
echo "sdk.dir=$WRITABLE_SDK_ROOT" > local.properties

# Use the system's sdkmanager to download and install only what's needed
# The licenses will be accepted and stored in the writable directory
yes | /nix/store/fplyqgcbdpqcl72nplfdqj4bdggg8jfr-androidsdk/libexec/android-sdk/tools/bin/sdkmanager --sdk_root="$WRITABLE_SDK_ROOT" "platforms;android-30" "build-tools;30.0.2"

# Stop any lingering Gradle daemons
./gradlew --stop

# Build the app
./gradlew assembleRelease
