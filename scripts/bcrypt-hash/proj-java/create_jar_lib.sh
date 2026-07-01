#!/usr/bin/env bash

#
# --------------------------------------------------
# Shell: bash on Linux and Mac, or Git Bash on Windows
# --------------------------------------------------
#
# Use bash on Linux/Mac. On Windows, run these commands in Git Bash.
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
# out/, application classes are compiled into out/, and the fat JAR is created
# in the project root as MyJavaProj.jar.
# The out/ directory is removed after the JAR is created.
#
# Optional arguments:
#   --jar-name <name>    JAR output file name (default: MyJavaProj.jar)
#   --src-dir <path>     Source directory (default: src)
#   --lib-dir <path>     Dependencies directory (default: lib)
#   --out-dir <path>     Output directory for the fat JAR (default: out)
#   --help               Show usage information
#
# Run with bash (Linux, Mac) or Git Bash (Windows):
# bash create_jar_lib.sh
# bash create_jar_lib.sh --jar-name HelloWorld.jar
# bash create_jar_lib.sh --src-dir src
# bash create_jar_lib.sh --lib-dir lib
# bash create_jar_lib.sh --out-dir out
# bash create_jar_lib.sh --help
#

show_help() {
  cat <<EOF
Usage: bash create_jar_lib.sh [options]

Requires bash. On Windows, use Git Bash.

Compile and package a Java project with lib/ dependencies into a fat JAR file.

Options:
  --jar-name <name>    JAR output file name (default: MyJavaProj.jar)
  --src-dir <path>     Source directory (default: src)
  --lib-dir <path>     Dependencies directory (default: lib)
  --out-dir <path>     Output directory for the fat JAR (default: out)
  --help               Show this help message
EOF
}

CURRENT_DIR=$(pwd)
JAR_NAME=MyJavaProj.jar
SRC_DIR=src
LIB_DIR=lib
OUT_DIR=out

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
    --out-dir)
      if [[ -z "$2" || "$2" == --* ]]; then
        echo "Error: --out-dir requires a value."
        exit 1
      fi
      OUT_DIR="$2"
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
  find "$OUT_DIR/META-INF" -type f \( -name '*.SF' -o -name '*.DSA' -o -name '*.RSA' \) -delete 2>/dev/null || true
}

echo "Creating output directory $CURRENT_DIR/$OUT_DIR..."
rm -rf "$OUT_DIR"
mkdir -p "$OUT_DIR"

echo
echo "Extracting dependency JARs from $CURRENT_DIR/$LIB_DIR into $CURRENT_DIR/$OUT_DIR..."
for dep_jar in "$LIB_DIR"/*.jar; do
  (cd "$OUT_DIR" && jar xf "$CURRENT_DIR/$dep_jar") || exit 1
done

strip_jar_signatures

echo
echo "Compiling Java files from $CURRENT_DIR/$SRC_DIR into $CURRENT_DIR/$OUT_DIR..."
javac -cp "$LIB_DIR/*" -d "$OUT_DIR" "$SRC_DIR"/*.java || exit 1

echo
echo "Creating fat JAR file $CURRENT_DIR/$JAR_NAME..."
jar cfm "$JAR_NAME" "$SRC_DIR/META-INF/MANIFEST.MF" -C "$OUT_DIR" . || exit 1

echo
echo "Removing output directory $CURRENT_DIR/$OUT_DIR..."
rm -rf "$OUT_DIR"
