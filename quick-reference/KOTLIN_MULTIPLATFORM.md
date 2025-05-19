# Flutter

- Useful links
  - [Kotlin Programming Language](https://kotlinlang.org/)
  - [Get started with Kotlin Multiplatform | Kotlin Multiplatform Development Documentation](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)
    - [Set up an environment | Kotlin Multiplatform Development Documentation](https://www.jetbrains.com/help/kotlin-multiplatform-dev/multiplatform-setup.html)
    - [Create your Kotlin Multiplatform app | Kotlin Multiplatform Development Documentation](https://www.jetbrains.com/help/kotlin-multiplatform-dev/multiplatform-create-first-app.html)
    - [Create your Compose Multiplatform app | Kotlin Multiplatform Development Documentation](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-multiplatform-create-first-app.html)
  - [Kotlin Multiplatform Wizard | JetBrains](https://kmp.jetbrains.com/)
- Content
  - [Starting a project](#starting-a-project)
    - [Creating](#creating-a-project-in-android-studio-and-running-in-a-emulated-android-device)

## Starting a project
[[Top]](#)<br />

### Creating a project in Android Studio and running in a emulated Android device
[[Top]](#)<br />

For this section, you must have an Android device setup in Android Studio. If you don't, see the useful links for the installation process.

Shortcuts to some actions:

- To create the project
  - Open any internet browser in *Kotlin Multiplatform Wizard* website
  - Enter the project name in *Project Name* (like *KotlinProject*)
  - Enter the project ID in *Project ID* (like *org.example.project*)
  - Select the desired platforms you want to target the application for
  - Click the download button to save the file in your machine
  - After downloading extract the project folder in your main projects folder
  - Open Android Studio
  - Open the project by selecting the project's root folder
  - Wait until all the automatic process of loading and indexing files are finished
  - Select one of the main files to start the application.
    - For the Android platform, navigate to *composeApp/commonMain/App.kt*
  - In the Toolbar, select the appropriate device and then *Run*.
