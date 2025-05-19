# Flutter

- Useful links
  - [Flutter](https://flutter.dev/)
    - [Install | Flutter](https://docs.flutter.dev/get-started/install)
    - [Write your first app | Flutter](https://docs.flutter.dev/get-started/codelab)
- Content
  - [Starting a project](#starting-a-project)
    - [Creating](#creating-a-project-in-vscode-and-running-in-a-emulated-android-device)

## Starting a project
[[Top]](#)<br />

### Creating a project in VSCode and running in a emulated Android device
[[Top]](#)<br />

For this section, you must first be able to create a Flutter project in your VSCode, and have an Android device setup in Android Studio. If you don't, see the useful links for the installation process.

It is not recommended to start the emulator from VSCode, because you can manage it entirely in Android Studio, like start and stop operations.

Shortcuts to some actions:
- Android Studio
  - Open Device Manager
    - From *Welcome to Android Studio* dialog, go to *More Actions > Virtual Device Manager*
    - From an open project, go to *Tools > Device Manager*
- VSCode
  - Open Command Palette: *Help > Show All Commands* or *Ctrl + Shift + P*
  - Run without debugging: *Run > Run Without Debugging* or *Ctrl + F5*
  - Run debugging: *Run > Start Debugging* or *F5*


- To create the project
  - Open VSCode
  - In the *Command Palette* type *Flutter: New Project* then press *Enter*
  - Select *Application* then press *Enter*
  - In the file browser, select the folder to create you project then press *Select a folder to create the project in*.
  - Give a name to the application *project_name* then press *Enter*
    - A *project_name* directory will be created inside the chosen folder.
- To test if it's working
  - Open Android Studio
  - Open *Device Manager*
  - Click *Start* in the device you want to run then wait for it
  - Wait for the emulator to start and notice the connected device name in the status bar (at bottom right)
    - If the connected device is not the one you want to run the application with you need to select the correct device
    - In the *Command Palette* type *Flutter: Select Device* then press *Enter*
    - Select the device you want to use then press *Enter*
    - Repeat the process to start the emulator
  - Now, you can either run debugging (*F5*) or run without debugging (*Ctrl + F5*)


