/**
 * Requires NodeJS >= 18
 * --------------------------------------------------
 * Run with
 * $ node create_migration.js
 * --------------------------------------------------
 * 
 * This script creates files based in utc timestamp granting liabilty when creating database migrations.
 * By default, the file will be empty and placed inside a directory called migration.
 * Those must be in the same directory level as the script.
 * 
 * If you want to create a rollback for this migration, use the create_migration_and_rollback.js script instead.
 * If you want to change the output directory, change MIGRATION_OUTPUT_FOLDER.
 * 
 * Just enter a name you want when asked, and it will be created with the following pattern:
 * OUTPUT_FILE_PREFIX + CURRENT_UTC_TIMESTAMP + "__" + MIGRATION_FILE_NAME + OUTPUT_FILE_SUFFIX
 */
const readline = require('node:readline/promises');
const fs = require('fs/promises');
const fsSync = require('fs');
const path = require('path');
const { stdin, stdout } = require('node:process');

// SETTINGS START
const MIGRATION_OUTPUT_FOLDER = './migration';
const OUTPUT_FILE_PREFIX = 'V';
const CURRENT_UTC_TIMESTAMP = Math.trunc(new Date().getTime() / 1000);
const OUTPUT_FILE_SUFFIX = '.sql';
// SETTINGS END

const run = async () => {
  const rl = readline.createInterface({ input: stdin, output: stdout });
  console.log('--------------------------------------------------');
  console.log('CREATE MIGRATION FILE');
  console.log('--------------------------------------------------');
  console.log('Type a name for your file (blank spaces are not supported):');
  console.log('---- Use snake_case_in_your_name to separate words.');
  console.log('---- SIGINT (CTRL + C) to cancel.');

  const migrationPath = path.join(__dirname, MIGRATION_OUTPUT_FOLDER);
  const branchName = await rl.question('');

  rl.close();

  const migrationFileName = `${OUTPUT_FILE_PREFIX}${CURRENT_UTC_TIMESTAMP}__${branchName}${OUTPUT_FILE_SUFFIX}`;

  console.log(`Creating migration output folder ${MIGRATION_OUTPUT_FOLDER} (if not exists)`)
  if (!fsSync.existsSync(migrationPath)) {
    fsSync.mkdirSync(migrationPath, { recursive: true });
  }
  
  console.log(`Creating migration file ${migrationFileName} in ${MIGRATION_OUTPUT_FOLDER}`)
  const migrationFileCreationPath = path.join(migrationPath, migrationFileName);
  await fs.writeFile(migrationFileCreationPath, '');
  console.log('--------------------------------------------------');
  console.log('Finished creating migration file.');
  console.log('--------------------------------------------------');
}

run().catch((err) => {
  console.error('Error creating migration');
  console.error(err);
});
