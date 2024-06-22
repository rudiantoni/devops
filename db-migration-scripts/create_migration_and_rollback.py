#
# Requires Python 3+
# --------------------------------------------------
# Run with
# $ python3/python/py create_migration_and_rollback.py
# --------------------------------------------------
# 
# This script creates files based in utc timestamp granting liability when creating database migrations.
# By default, the migration file will be empty but the rollback will be created with a warning text at the top.
# They will be placed inside a directory called migration and rollback.
# Those must be in the same directory level as the script.
# 
# If you want to create a migration without rollback, use the create_migration.py script instead.
# If you want to change the output directory, change MIGRATION_OUTPUT_FOLDER and/or ROLLBACK_OUTPUT_FOLDER.
# 
# Just enter a name you want when asked, and it will be created with the following pattern:
# OUTPUT_FILE_PREFIX + CURRENT_UTC_TIMESTAMP + "__" + [MIGRATION_FILE_NAME or ROLLBACK_FILE_NAME] + OUTPUT_FILE_SUFFIX
# 

import time, os

# SETTINGS START
MIGRATION_OUTPUT_FOLDER = './migration'
ROLLBACK_OUTPUT_FOLDER = './rollback'
OUTPUT_FILE_PREFIX = 'V'
CURRENT_UTC_TIMESTAMP = int(time.time())
OUTPUT_FILE_SUFFIX = '.sql'
# SETTINGS END

def main():
    print("--------------------------------------------------")
    print("CREATE MIGRATION AND ROLLBACK FILE")
    print("--------------------------------------------------")
    print("Type a name for your file (blank spaces are not supported):")
    print("---- Use snake_case_in_your_name to separate words.")
    print("---- SIGINT (CTRL + C) to cancel.")
    branch_name = input("")

    migration_file_name = f'{OUTPUT_FILE_PREFIX}{CURRENT_UTC_TIMESTAMP}__{branch_name}{OUTPUT_FILE_SUFFIX}'
    rollback_file_name = f'{OUTPUT_FILE_PREFIX}{CURRENT_UTC_TIMESTAMP}__rollback_{branch_name}{OUTPUT_FILE_SUFFIX}'
    migration_file_path = f'{MIGRATION_OUTPUT_FOLDER}/{migration_file_name}'
    rollback_file_path = f'{ROLLBACK_OUTPUT_FOLDER}/{rollback_file_name}'
    
    print(f'Creating migration output folder {MIGRATION_OUTPUT_FOLDER} (if not exists)')
    os.makedirs(os.path.dirname(migration_file_path), exist_ok=True)
    print(f'Creating rollback output folder {ROLLBACK_OUTPUT_FOLDER} (if not exists)')
    os.makedirs(os.path.dirname(rollback_file_path), exist_ok=True)

    print(f'Creating migration file {migration_file_name} in {MIGRATION_OUTPUT_FOLDER}')
    with open(migration_file_path, 'w') as file:
        file.write('');
    print(f'Creating rollback file {rollback_file_name} in {ROLLBACK_OUTPUT_FOLDER}')
    rollback_file_starter_content = '-- --------------------------------------------\n'
    rollback_file_starter_content += '-- WARNING: THIS IS A DATABASE ROLLBACK SCRIPT!\n'
    rollback_file_starter_content += '-- --------------------------------------------\n'
    with open(rollback_file_path, 'w') as file:
        file.write(rollback_file_starter_content);

    print("--------------------------------------------------")
    print("Finished creating migration and rollback files.")
    print("--------------------------------------------------")

main()
