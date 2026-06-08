# Java

- Useful links
  - [Java](https://www.oracle.com/java/)
    - [Java Documentation](https://docs.oracle.com/en/java/)
  - [OpenJDK](https://openjdk.org/)
- Content
  - [Prerequisites](#prerequisites)
  - [Small manual projects (JDK only)](#small-manual-projects-jdk-only)
    - [Scope](#scope)
    - [Creating the initial structure](#creating-the-initial-structure)
    - [Compiling to .class files](#compiling-to-class-files)
    - [Creating the .jar artifact](#creating-the-jar-artifact)
    - [Running the application](#running-the-application)
    - [Removing .class files](#removing-class-files)

## Prerequisites
[[Top]](#)<br />

- Requires Java 8+ (JDK, not only JRE)
- The commands `java`, `javac` and `jar` must be available in your terminal
- To check your installation
  - Run `$ java -version`
  - Run `$ javac -version`

## Small manual projects (JDK only)
[[Top]](#)<br />

This guide explains how to create, compile, package and run **small** Java projects **manually** — without an IDE project wizard, Maven, Gradle or any third-party library.

Use it for command-line utilities and scripts that rely **only on the JDK standard library** (`java.*`, `java.nio.*`, `java.time.*`, and so on).

> This workflow targets a **flat `src/` layout**: a `Main.java` entry point and other `.java` files placed directly under `src/`.
>
> Larger project layouts — such as reverse-domain packages (`com.company.myapp`), nested source folders and subpackages — are **not** covered here. Those follow different naming, folder and build conventions.

### Scope
[[Top]](#)<br />

**In scope**

- Projects with no external `.jar` dependencies
- Source code, compilation, executable JAR creation and local execution using only `javac`, `jar` and `java`

**Out of scope**

Projects that need third-party libraries are not covered here. Common examples:

- Database drivers (PostgreSQL, MySQL, Oracle, Microsoft SQL Server, SQLite)
- NoSQL drivers (MongoDB, Redis clients)
- HTTP clients (OkHttp, Apache HttpClient)
- JSON/XML libraries (Gson, Jackson, org.json)
- Logging frameworks (Log4j, SLF4J, Logback)
- Application frameworks (Spring Boot, Jakarta EE)

Those cases require adding JARs to the classpath (or a build tool such as Maven or Gradle) and follow a different workflow.

### Creating the initial structure
[[Top]](#)<br />

- Choose a parent directory and create the project folder
  - Example project name: *my-java-proj*
- Create the folders and empty files under that folder, the final structure should look like this:

```
my-java-proj/
  src/
    Main.java
    META-INF/
      MANIFEST.MF
```

After creating the initial file structure, you need to add some content to your files in order to have a minimal working project

- `src/Main.java`
  ```java
  public class Main {
    public static void main(String[] args) {
      System.out.println("Hello world!");
    }
  }
  ```

- `src/META-INF/MANIFEST.MF`
  ```
  Manifest-Version: 1.0
  Main-Class: Main

  ```

> `Main-Class` must match the public class that contains `public static void main(String[] args)`.
> Keep the blank line at the end of `MANIFEST.MF`.

### Compiling to .class files
[[Top]](#)<br />

> All commands below assume you are inside your project directory (eg `my-java-proj`).

Compiled `.class` files are written to an `out/` folder so `src/` stays source-only.

- Linux, Mac or Windows Git Bash
  ```
  mkdir -p out
  javac -d out src/*.java
  ```
- Windows Command Prompt
  ```
  mkdir out
  javac -d out src\*.java
  ```

After compilation, `out/` should contain files such as `out/Main.class`

> `javac` only compiles the `.java` files passed to the command. It does **not** remove stale `.class` files from a previous build.
>
> Example: if `src/` contains `DataA.java`, `DataB.java` and `DataC.java`, the first compile produces `DataA.class`, `DataB.class` and `DataC.class` in `out/`. If you later delete `DataB.java` and `DataC.java` and recompile with the same command, `DataB.class` and `DataC.class` will still be in `out/` unless you delete them manually.
>
> To avoid orphan `.class` files, remove `out/` or all of its contents before recompiling (see [Removing .class files](#removing-class-files)).

### Creating the .jar artifact
[[Top]](#)<br />

> All commands below assume you are inside your project directory (eg `my-java-proj`).

Build an executable JAR named `MyJavaProj.jar`

- Linux, Mac, Windows Git Bash or Windows Command Prompt
  - `jar cfm MyJavaProj.jar src/META-INF/MANIFEST.MF -C out .`

> If you run this command with an already existing JAR with the same name, it will be overwritten.

The resulting artifact is created in the project root `my-java-proj/MyJavaProj.jar`

### Running the application
[[Top]](#)<br />

> All commands below assume you are inside your project directory (eg `my-java-proj`).

Preferred way with a JAR

- Linux, Mac, Windows Git Bash or Windows Command Prompt
  ```
  java -jar MyJavaProj.jar
  ```

Alternative without JAR, useful while testing before packaging:
- Linux, Mac, Windows Git Bash or Windows Command Prompt
  ```
  java -cp out Main
  ```

### Removing .class files
[[Top]](#)<br />

> All commands below assume you are inside your project directory (eg `my-java-proj`).

After creating the JAR, remove compiled output from `out/`

- Linux, Mac or Windows Git Bash
  ```
  rm -rf out
  ```
- Windows Command Prompt
  ```
  rmdir /s /q out
  ```

To recompile later, create `out/` again and repeat the compile step
