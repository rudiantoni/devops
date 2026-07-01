#!/usr/bin/env bash

#
# --------------------------------------------------
# Shell: bash on Linux and Mac, or Git Bash on Windows
# --------------------------------------------------
#
# Use bash on Linux/Mac. On Windows, run these commands in Git Bash.
#
# This script compiles, packages, runs and cleans up a manual Java project.
# It is intended for small standalone projects with no external dependencies (JDK only).
#
# Copy this script to the root of your Java project, where the src and META-INF directories are located.
# The project must contain:
#   src/*.java
#   src/META-INF/MANIFEST.MF
#
# Workflow:
#   1. Compile Java files from src/ into out/
#   2. Create an executable JAR in the project root
#   3. Run the application with java -jar
#   4. Remove out/ and the JAR file by default
#
# Optional arguments:
#   --jar-name <name>    JAR output file name (default: MyJavaProj.jar)
#   --src-dir <path>     Source directory (default: src)
#   --out-dir <path>     Compiled output directory (default: out)
#   --keep-jar           Keep the JAR file after execution
#   --keep-out-dir       Keep the compiled output directory after execution
#   --silent             Suppress script messages (application output is still shown)
#   --help               Show usage information
#
# Run with bash (Linux, Mac) or Git Bash (Windows):
# bash run_full.sh
# bash run_full.sh --jar-name HelloWorld.jar
# bash run_full.sh --src-dir src
# bash run_full.sh --out-dir out
# bash run_full.sh --keep-jar
# bash run_full.sh --keep-out-dir
# bash run_full.sh --silent
# bash run_full.sh --help
#

show_help() {
  cat <<EOF
Usage: bash run_full.sh [options]

Requires bash. On Windows, use Git Bash.

Compile, package and run a manual Java project.

Options:
  --jar-name <name>    JAR output file name (default: MyJavaProj.jar)
  --src-dir <path>     Source directory (default: src)
  --out-dir <path>     Compiled output directory (default: out)
  --keep-jar           Keep the JAR file after execution
  --keep-out-dir       Keep the compiled output directory after execution
  --silent             Suppress script messages (application output is still shown)
  --help               Show this help message
EOF
}

CURRENT_DIR=$(pwd)
JAR_NAME=MyJavaProj.jar
KEEP_JAR=false
KEEP_OUT_DIR=false
SILENT=false
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
    --src-dir)
      if [[ -z "$2" || "$2" == --* ]]; then
        echo "Error: --src-dir requires a value."
        exit 1
      fi
      SRC_DIR="$2"
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
    --keep-jar)
      KEEP_JAR=true
      shift
      ;;
    --keep-out-dir)
      KEEP_OUT_DIR=true
      shift
      ;;
    --silent)
      SILENT=true
      shift
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

log() {
  if [[ "$SILENT" == false ]]; then
    echo "$@"
  fi
}

log "Creating output directory $CURRENT_DIR/$OUT_DIR..."
mkdir -p "$OUT_DIR"

log
log "Compiling Java files from $CURRENT_DIR/$SRC_DIR to $CURRENT_DIR/$OUT_DIR..."
javac -d "$OUT_DIR" "$SRC_DIR"/*.java || exit 1

log
log "Creating JAR file $CURRENT_DIR/$JAR_NAME..."
jar cfm "$JAR_NAME" "$SRC_DIR/META-INF/MANIFEST.MF" -C "$OUT_DIR" . || exit 1

log
log "Running Java application from $CURRENT_DIR/$JAR_NAME..."
log "----- APPLICATION OUTPUT START -----"
java -jar "$JAR_NAME" || exit 1
log "----- APPLICATION OUTPUT END -----"

if [[ "$KEEP_OUT_DIR" == false ]]; then
  log
  log "Removing output directory $CURRENT_DIR/$OUT_DIR..."
  rm -rf "$OUT_DIR"
fi

if [[ "$KEEP_JAR" == false ]]; then
  log
  log "Removing JAR file $CURRENT_DIR/$JAR_NAME..."
  rm -rf "$JAR_NAME"
fi
