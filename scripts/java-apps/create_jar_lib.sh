#!/usr/bin/env bash

#
# --------------------------------------------------
# Currently working on: Linux, Mac and Windows with Git Bash
# --------------------------------------------------
#
# This script compiles and packages a manual Java project into a fat (uber) JAR
# that bundles application classes and all dependency JARs from lib/.
#
# Copy this script to the root of your Java project, where the src, lib and
# META-INF directories are located.
# The project must contain:
#   lib/*.jar          (at least one dependency JAR)
#   src/*.java
#   src/META-INF/MANIFEST.MF
#
# By default, source files are read from src/, dependencies are extracted into
# fat_staging/, application classes are compiled into fat_staging/, and the fat
# JAR is created in the project root as MyJavaProj.jar.
# The fat_staging/ directory is removed after the JAR is created.
#
# Optional arguments:
#   --jar-name <name>      JAR output file name (default: MyJavaProj.jar)
#   --src-dir <path>       Source directory (default: src)
#   --lib-dir <path>       Dependencies directory (default: lib)
#   --staging-dir <path>   Staging directory for the fat JAR (default: fat_staging)
#   --help                 Show usage information
#
# Run with:
# bash create_jar_lib.sh
# bash create_jar_lib.sh --jar-name HelloWorld.jar
# bash create_jar_lib.sh --src-dir src
# bash create_jar_lib.sh --lib-dir lib
# bash create_jar_lib.sh --staging-dir fat_staging
# bash create_jar_lib.sh --help
#

show_help() {
  cat <<EOF
Usage: bash create_jar_lib.sh [options]

Compile and package a Java project with lib/ dependencies into a fat JAR file.

Options:
  --jar-name <name>      JAR output file name (default: MyJavaProj.jar)
  --src-dir <path>       Source directory (default: src)
  --lib-dir <path>       Dependencies directory (default: lib)
  --staging-dir <path>   Staging directory for the fat JAR (default: fat_staging)
  --help                 Show this help message
EOF
}

CURRENT_DIR=$(pwd)
JAR_NAME=MyJavaProj.jar
SRC_DIR=src
LIB_DIR=lib
STAGING_DIR=fat_staging

while [[ $# -gt 0 ]]; do
  case $1 in
    --jar-name)
      if [[ -z "$2" || "$2" == --* ]]; then
        echo "Error: --jar-name requires a value."
        exit 1
      fi
      JAR_NAME="$2"
      shift 2
      ;;
    --src-dir)
      if [[ -z "$2" || "$2" == --* ]]; then
        echo "Error: --src-dir requires a value."
        exit 1
      fi
      SRC_DIR="$2"
      shift 2
      ;;
    --lib-dir)
      if [[ -z "$2" || "$2" == --* ]]; then
        echo "Error: --lib-dir requires a value."
        exit 1
      fi
      LIB_DIR="$2"
      shift 2
      ;;
    --staging-dir)
      if [[ -z "$2" || "$2" == --* ]]; then
        echo "Error: --staging-dir requires a value."
        exit 1
      fi
      STAGING_DIR="$2"
      shift 2
      ;;
    --help)
      show_help
      exit 0
      ;;
    *)
      echo "Unknown option: $1"
      echo
      show_help
      exit 1
      ;;
  esac
done

strip_jar_signatures() {
  find "$STAGING_DIR/META-INF" -type f \( -name '*.SF' -o -name '*.DSA' -o -name '*.RSA' \) -delete 2>/dev/null || true
}

echo "Creating staging directory $CURRENT_DIR/$STAGING_DIR..."
rm -rf "$STAGING_DIR"
mkdir -p "$STAGING_DIR"

echo
echo "Extracting dependency JARs from $CURRENT_DIR/$LIB_DIR into $CURRENT_DIR/$STAGING_DIR..."
for dep_jar in "$LIB_DIR"/*.jar; do
  (cd "$STAGING_DIR" && jar xf "$CURRENT_DIR/$dep_jar") || exit 1
done

strip_jar_signatures

echo
echo "Compiling Java files from $CURRENT_DIR/$SRC_DIR into $CURRENT_DIR/$STAGING_DIR..."
javac -cp "$LIB_DIR/*" -d "$STAGING_DIR" "$SRC_DIR"/*.java || exit 1

echo
echo "Creating fat JAR file $CURRENT_DIR/$JAR_NAME..."
jar cfm "$JAR_NAME" "$SRC_DIR/META-INF/MANIFEST.MF" -C "$STAGING_DIR" . || exit 1

echo
echo "Removing staging directory $CURRENT_DIR/$STAGING_DIR..."
rm -rf "$STAGING_DIR"
