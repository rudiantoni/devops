/**
 * Requires Java 8+
 * --------------------------------------------------
 * Run with
 * $ java -jar CreateMigrationAndRollback.jar
 * --------------------------------------------------
 *
 * This application creates files based in utc timestamp granting liability when creating database migrations.
 * By default, the migration file will be empty but the rollback will be created with a warning text at the top.
 * They will be placed inside a directory called migration and rollback.
 * Those must be in the same directory level as the application.
 *
 * If you want to create a migration without rollback, use the CreateMigration.jar application instead.
 * If you want to change the output directory, change MIGRATION_OUTPUT_FOLDER and/or ROLLBACK_OUTPUT_FOLDER.
 *
 * Just enter a name you want when asked, and it will be created with the following pattern:
 * OUTPUT_FILE_PREFIX + CURRENT_UTC_TIMESTAMP + "__" + [MIGRATION_FILE_NAME or ROLLBACK_FILE_NAME] + OUTPUT_FILE_SUFFIX
 */

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.util.Scanner;

public class Main {
  // SETTINGS START
  private static final String MIGRATION_OUTPUT_FOLDER = "./migration";
  private static final String ROLLBACK_OUTPUT_FOLDER = "./rollback";
  private static final String OUTPUT_FILE_PREFIX = "V";
  private static final Long CURRENT_UTC_TIMESTAMP = Instant.now().getEpochSecond();
  private static final String OUTPUT_FILE_SUFFIX = ".sql";
  // SETTINGS END

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    try {
      System.out.println("--------------------------------------------------");
      System.out.println("CREATE MIGRATION AND ROLLBACK FILE");
      System.out.println("--------------------------------------------------");
      System.out.println("Type a name for your file (blank spaces are not supported):");
      System.out.println("---- Use snake_case_in_your_name to separate words.");
      System.out.println("---- SIGINT (CTRL + C) to cancel.");
      String branchName = sc.nextLine();

      String migrationFileName = String.format("%s%s__%s%s", OUTPUT_FILE_PREFIX, CURRENT_UTC_TIMESTAMP, branchName, OUTPUT_FILE_SUFFIX);
      String rollbackFileName = String.format("%s%s__rollback_%s%s", OUTPUT_FILE_PREFIX, CURRENT_UTC_TIMESTAMP, branchName, OUTPUT_FILE_SUFFIX);
      Path migrationFilePath = Paths.get(String.format("%s/%s", MIGRATION_OUTPUT_FOLDER, migrationFileName));
      Path rollbackFilePath = Paths.get(String.format("%s/%s", ROLLBACK_OUTPUT_FOLDER, rollbackFileName));

      System.out.printf("Creating migration output folder %s (if not exists)\n", MIGRATION_OUTPUT_FOLDER);
      Files.createDirectories(migrationFilePath.getParent());
      System.out.printf("Creating rollback output folder %s (if not exists)\n", ROLLBACK_OUTPUT_FOLDER);
      Files.createDirectories(rollbackFilePath.getParent());

      System.out.printf("Creating migration file %s in %s\n", migrationFileName, MIGRATION_OUTPUT_FOLDER);
      Files.createFile(migrationFilePath);
      System.out.printf("Creating rollback file %s in %s\n", rollbackFileName, ROLLBACK_OUTPUT_FOLDER);
      Files.createFile(rollbackFilePath);

      String rollbackFileStarterContent = "-- --------------------------------------------\n";
      rollbackFileStarterContent += "-- WARNING: THIS IS A DATABASE ROLLBACK SCRIPT!\n";
      rollbackFileStarterContent += "-- --------------------------------------------\n";
      Files.write(rollbackFilePath, rollbackFileStarterContent.getBytes(), StandardOpenOption.WRITE);

      System.out.println("--------------------------------------------------");
      System.out.println("Finished creating migration and rollback files.");
      System.out.print("--------------------------------------------------");

    } catch (Exception e) {
      System.out.println("Error creating migration");
      e.printStackTrace();
    } finally {
      sc.close();
    }
  }
}