# devops

This repository is used for any devops related project.

- [Quick info](./INFO.md)

- Content
  - [Database](#database)
    - [PostgreSQL](#postgresql)
      - [Custom images](#custom-images)
      - [CLI operations for Linux Ubuntu (postgres)](#cli-operations-for-linux-ubuntu-postgres)
    - [MongoDB](#mongodb)
      - [CLI operations for Linux Ubuntu (mongo)](#cli-operations-for-linux-ubuntu-mongo)

# Database

## PostgreSQL

### Custom images
- [PostgreSQL 15](https://github.com/rudiantoni/devops/tree/main/postgres/15)
  - Reference: [PostgreSQL Official Docker Image](https://hub.docker.com/_/postgres)
  - Docker image based on the default PostgreSQL Docker Official image, which allows you to initialize with multiple database while keeping the PostgreSQL compatibility and much more.

### CLI operations for Linux Ubuntu (postgres)
- CLI tools: *pg_dump*, *pg_restore*, *psql*

#### General automation
- Run .sql script file in a database by connection with psql
  - `PGPASSWORD=[pass] psql --echo-all --host [host] --port [port] --username [user] --dbname [db] --file [file_name]`

#### DUMP backups (C format)
- Fixed full backup
  - `pg_dump --dbname=postgresql://[user]:[pass]@[host]:[port]/[db] --verbose --format c --file [db]_$(date +%Y-%m-%d_%H-%M-%S).dump`
- Dynamic full structure and data backup
  - `pg_dump --dbname=postgresql://[user]:[pass]@[host]:[port]/[db] --verbose --no-owner --no-privileges --no-tablespaces --no-unlogged-table-data --inserts --disable-triggers --format c --file [db]_$(date +%Y-%m-%d_%H-%M-%S).full.dump`
- Dynamic partial structure only backup
  - `pg_dump --dbname=postgresql://[user]:[pass]@[host]:[port]/[db] --verbose --no-owner --no-privileges --no-tablespaces --no-unlogged-table-data --schema-only --format c --file [db]_$(date +%Y-%m-%d_%H-%M-%S).schema.dump`
- Dynamic partial data only backup
  - `pg_dump --dbname=postgresql://[user]:[pass]@[host]:[port]/[db] --verbose --inserts --data-only --disable-triggers --format c --file [db]_$(date +%Y-%m-%d_%H-%M-%S).data.dump`

#### SQL backups (plain text format)
- Fixed full backup
  - `pg_dump --dbname=postgresql://[user]:[pass]@[host]:[port]/[db] --verbose --file [db]_$(date +%Y-%m-%d_%H-%M-%S).sql`
- Dynamic full structure and data backup
  - `pg_dump --dbname=postgresql://[user]:[pass]@[host]:[port]/[db] --verbose --no-owner --no-privileges --no-tablespaces --no-unlogged-table-data --inserts --disable-triggers --file [db]_$(date +%Y-%m-%d_%H-%M-%S).full.sql`
- Dynamic partial structure only backup
  - `pg_dump --dbname=postgresql://[user]:[pass]@[host]:[port]/[db] --verbose --no-owner --no-privileges --no-tablespaces --no-unlogged-table-data --schema-only --file [db]_$(date +%Y-%m-%d_%H-%M-%S).schema.sql`
- Dynamic partial data only backup
  - `pg_dump --dbname=postgresql://[user]:[pass]@[host]:[port]/[db] --verbose --inserts --data-only --disable-triggers --file [db]_$(date +%Y-%m-%d_%H-%M-%S).data.sql`

#### DUMP restores (C format)
- Fixed full restore (database and user must be the same from the dump)
  - `pg_restore --clean --verbose --dbname=postgresql://[user]:[pass]@[host]:[port]/[db] [file_name].dump`
- Dynamic/fixed full structure and data restore (database needs to exist)
  - `pg_restore --clean --verbose --no-owner --no-privileges --role=[user] --dbname=postgresql://[user]:[pass]@[host]:[port]/[db] [file_name](.full).dump`
- Dynamic/fixed partial structure only erstore (database needs to exist)
  - `pg_restore --clean --verbose --no-owner --no-privileges --no-tablespaces --schema-only --role=[user] --dbname=postgresql://[user]:[pass]@[host]:[port]/[db] [file_name](.schema).dump`
- Dynamic/fixed partial data only (database needs to exist)
  - `pg_restore --clean --verbose --no-owner --no-privileges --disable-triggers --role=[user] --dbname=postgresql://[user]:[pass]@[host]:[port]/[db] [file_name](.data).dump`

## MongoDB

### CLI operations for Linux Ubuntu (mongo)
- CLI tools: *mongodump*, *mongorestore*

#### DUMP backup
- Fixed full backup
  - `mongodump -vv --authenticationDatabase="admin" --uri="mongodb://[host]:[port]" --username="[user]" --password="[pass]" --db="[db]" --archive="[db]_$(date +%Y-%m-%d_%H-%M-%S).dump"`

#### DUMP restore
- Fixed full restore
  - `mongorestore -vv --authenticationDatabase="admin" --uri="mongodb://[host]:[port]" --username="[user]" --password="[pass]" --db="[db]" --archive="[file_name].dump"`

