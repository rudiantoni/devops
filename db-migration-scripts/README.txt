##################################################
create_migration.jar
##################################################
/**
 * Requires Java 8+
 * --------------------------------------------------
 * Run with
 * $ java -jar create_migration.jar
 * --------------------------------------------------
 *
 * This application creates files based in utc timestamp granting liabilty when creating database migrations.
 * By default, the file will be empty and placed inside a directory called migration.
 * Those must be in the same directory level as the application.
 *
 * If you want to create a rollback for this migration, use the create_migration_and_rollback.jar application instead.
 * If you want to change the output directory, change MIGRATION_OUTPUT_FOLDER.
 *
 * Just enter a name you want when asked, and it will be created with the following pattern:
 * OUTPUT_FILE_PREFIX + CURRENT_UTC_TIMESTAMP + "__" + MIGRATION_FILE_NAME + OUTPUT_FILE_SUFFIX
 */

##################################################
create_migration_and_rollback.jar
##################################################
/**
 * Requires Java 8+
 * --------------------------------------------------
 * Run with
 * $ java -jar create_migration_and_rollback.jar
 * --------------------------------------------------
 *
 * This application creates files based in utc timestamp granting liability when creating database migrations.
 * By default, the migration file will be empty but the rollback will be created with a warning text at the top.
 * They will be placed inside a directory called migration and rollback.
 * Those must be in the same directory level as the application.
 *
 * If you want to create a migration without rollback, use the create_migration.jar application instead.
 * If you want to change the output directory, change MIGRATION_OUTPUT_FOLDER and/or ROLLBACK_OUTPUT_FOLDER.
 *
 * Just enter a name you want when asked, and it will be created with the following pattern:
 * OUTPUT_FILE_PREFIX + CURRENT_UTC_TIMESTAMP + "__" + [MIGRATION_FILE_NAME or ROLLBACK_FILE_NAME] + OUTPUT_FILE_SUFFIX
 */