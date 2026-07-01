#!/usr/bin/env bash

#
# --------------------------------------------------
# Shell: bash on Linux and Mac, or Git Bash on Windows
# --------------------------------------------------
#
# Use bash on Linux/Mac. On Windows, run these commands in Git Bash.
#
# This script compiles and packages a manual Java project into an executable JAR file.
# It is intended for small standalone projects with no external dependencies (JDK only).
#
# Copy this script to the root of your Java project, where the src and META-INF directories are located.
# The project must contain:
#   src/*.java
#   src/META-INF/MANIFEST.MF
#
# By default, source files are read from src/, compiled classes are written to out/,
# and the JAR is created in the project root as MyJavaProj.jar.
# The out/ directory is removed after the JAR is created.
#
# Optional arguments:
#   --jar-name <name>    Change the output JAR file name (default: MyJavaProj.jar)
#   --help               Show usage information
#
# Run with bash (Linux, Mac) or Git Bash (Windows):
# bash create_jar.sh
# bash create_jar.sh --jar-name HelloWorld.jar
# bash create_jar.sh --help
#

show_help() {
  cat <<EOF
Usage: bash create_jar.sh [options]

Requires bash. On Windows, use Git Bash.

Compile and package a manual Java project into a JAR file.

Options:
  --jar-name <name>    JAR output file name (default: MyJavaProj.jar)
  --help               Show this help message
EOF
}

CURRENT_DIR=$(pwd)
JAR_NAME=MyJavaProj.jar
SRC_DIR=src
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

echo "Creating output directory $CURRENT_DIR/$OUT_DIR..."
mkdir -p "$OUT_DIR"

echo
echo "Compiling Java files from $CURRENT_DIR/$SRC_DIR to $CURRENT_DIR/$OUT_DIR..."
javac -d "$OUT_DIR" "$SRC_DIR"/*.java || exit 1

echo
echo "Creating JAR file $CURRENT_DIR/$JAR_NAME..."
jar cfm "$JAR_NAME" "$SRC_DIR/META-INF/MANIFEST.MF" -C "$OUT_DIR" . || exit 1

echo
echo "Removing output directory $CURRENT_DIR/$OUT_DIR..."
rm -rf "$OUT_DIR"
