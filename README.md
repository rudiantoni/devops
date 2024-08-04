# DevOps

- [Docker Compose templates](./compose-templates/)
  - [Apache HTTP Server 2.4.57 (Alpine 3.18)](./compose-templates/httpd-2.4.57/docker-compose.yml)
  - [mongo-5.0.3](./compose-templates/mongo-5.0.3/docker-compose.yml)
  - [mysql-8](./compose-templates/mysql-8/docker-compose.yml)
  - [postgres-14](./compose-templates/postgres-14/docker-compose.yml)
  - [postgres-15](./compose-templates/postgres-15/docker-compose.yml)
  - [postgres-16](./compose-templates/postgres-16/docker-compose.yml)
  - [sqlserver-2019](./compose-templates/sqlserver-2019/docker-compose.yml)

- [Custom docker images](./custom-docker-images/)
  - [PostgreSQL 15](./custom-docker-images/postgres-15/)
    - Reference: [PostgreSQL Official Docker Image](https://hub.docker.com/_/postgres): Allows you to initialize with multiple database while keeping the PostgreSQL compatibility and much more.

- Snippets
  - [Java 8 snippets](./snippets/java8/Snippets.java)
  - [JavaScript snippets](./snippets/snippets.js)
  - [Python snippets](./snippets/snippets.py)
  - [SQL snippets for Microsoft SQL Server](./snippets/queries_micsqlserver.sql)
  - [SQL snippets for MySQL](./snippets/queries_mysql.sql)
  - [SQL snippets for Oracle Database](./snippets/queries_oracle.sql)
  - [SQL snippets for PostgreSQL](./snippets/queries_postgres.sql)
  - [SQL snippets for SQLite](./snippets/queries_sqlite.sql)

- Info
  - [Common info](./COMMON_INFO.md)
  - [Docker info](./DOCKER_INFO.md)

- Content
  - [Microsoft SQL Server](#microsoft-sql-server)
  - [MongoDB](#mongodb)
  - [MySQL](#mysql)
  - [Oracle](#oracle)
  - [PostgreSQL](#postgresql)


# Database


## Microsoft SQL Server
[[Top]](#)<br />

### General

<!-- TODO: adicionar como conectar com o banco de dados com cli e rodar queries em tabelas -->

- CLI tools: *sqlcmd*

The only possible backup in a Linux machine is a internal database backup, generated via a query sent to the database, generating a file inside the same machine (or container) where the database is located. That's why you should share a dedicated backup folder in a SQL Server docker container.

### DUMP backup

| Description | Specification |
|-------------|:--------------|
| Fixed full backup | `( CMD=/opt/mssql-tools18/bin/sqlcmd && BKP_DIR=[backup_dir] && BKP_FILE=[db]_$(date +%Y-%m-%d_%H-%M-%S).dump && $CMD -C -S [host],[port] -U [user] -P [pass] -Q "BACKUP DATABASE [[db]] TO DISK = N'$BKP_DIR/$BKP_FILE' WITH NOFORMAT, NOINIT, NAME = '[db]', SKIP, NOREWIND, NOUNLOAD, STATS = 10" && chmod 775 $BKP_DIR/$BKP_FILE )` |

### DUMP restore

| Description | Specification |
|-------------|:--------------|
| Fixed full restore on master database<br />Needs to start database as single user mode<br />Database will shutdown after running successfully | `/opt/mssql-tools18/bin/sqlcmd -C -S [host],[port] -U [user] -P [pass] -Q "RESTORE DATABASE [master] FROM DISK = N'[file_name]' WITH FILE = 1, NOUNLOAD, REPLACE, STATS = 5"` |
| Fixed full restore on other database | `/opt/mssql-tools18/bin/sqlcmd -C -S [host],[port] -U [user] -P [pass] -Q "RESTORE DATABASE [[db]] FROM DISK = N'[file_name]' WITH FILE = 1, NOUNLOAD, REPLACE, STATS = 5"` |

- Always replace ALL OCCURRENCES of the placeholders shown in previous commands with the correct info
  - `[backup_dir]`: backup directory eg.: */opt/backup*
  - `[db]`: database name eg.: *localhost*
  - `[host]`: database port eg.: *1433*
  - `[user]`: database user
  - `[pass]`: database password


## MongoDB
[[Top]](#)<br />

### General

<!-- TODO: adicionar como conectar com o banco de dados com cli e rodar queries em tabelas -->

- CLI tools: *mongodump*, *mongorestore*

### DUMP backup

| Description | Specification |
|-------------|:--------------|
| Fixed full backup | `mongodump -vv --authenticationDatabase="admin" --uri="mongodb://[host]:[port]" --username="[user]" --password="[pass]" --db="[db]" --archive="[db]_$(date +%Y-%m-%d_%H-%M-%S).dump"` |

### DUMP restore

| Description | Specification |
|-------------|:--------------|
| Fixed full restore | `mongorestore -vv --authenticationDatabase="admin" --uri="mongodb://[host]:[port]" --username="[user]" --password="[pass]" --db="[db]" --archive="[file_name].dump"` |

## MySQL
[[Top]](#)<br />

- CLI tools: *mysql*

### General

<!-- TODO: adicionar como conectar com o banco de dados com cli e rodar queries em tabelas -->

## Oracle
[[Top]](#)<br />

- CLI tools: *sqlplus*

### General

<!-- TODO: adicionar como conectar com o banco de dados com cli (sqlplus) e rodar queries em tabelas -->


## PostgreSQL
[[Top]](#)<br />

- CLI tools: *pg_dump*, *pg_restore*, *psql*

### General

<!-- TODO: adicionar como conectar com o banco de dados com cli e rodar queries em tabelas -->

| Description | Specification |
|-------------|:--------------|
| Run .sql script file in a database by connection with psql | `PGPASSWORD=[pass] psql --echo-all --host [host] --port [port] --username [user] --dbname [db] --file [file_name]` |

### DUMP backups (C format)

| Description | Specification |
|-------------|:--------------|
| Fixed full backup | `pg_dump --dbname=postgresql://[user]:[pass]@[host]:[port]/[db] --verbose --format c --file [db]_$(date +%Y-%m-%d_%H-%M-%S).dump` |
| Dynamic full structure and data backup | `pg_dump --dbname=postgresql://[user]:[pass]@[host]:[port]/[db] --verbose --no-owner --no-privileges --no-tablespaces --no-unlogged-table-data --inserts --disable-triggers --format c --file [db]_$(date +%Y-%m-%d_%H-%M-%S).full.dump` |
| Dynamic partial structure only backup | `pg_dump --dbname=postgresql://[user]:[pass]@[host]:[port]/[db] --verbose --no-owner --no-privileges --no-tablespaces --no-unlogged-table-data --schema-only --format c --file [db]_$(date +%Y-%m-%d_%H-%M-%S).schema.dump` |
| Dynamic partial data only backup | `pg_dump --dbname=postgresql://[user]:[pass]@[host]:[port]/[db] --verbose --inserts --data-only --disable-triggers --format c --file [db]_$(date +%Y-%m-%d_%H-%M-%S).data.dump` |

### SQL backups (plain text format)

| Description | Specification |
|-------------|:--------------|
| Fixed full backup | `pg_dump --dbname=postgresql://[user]:[pass]@[host]:[port]/[db] --verbose --file [db]_$(date +%Y-%m-%d_%H-%M-%S).sql` |
| Dynamic full structure and data backup | `pg_dump --dbname=postgresql://[user]:[pass]@[host]:[port]/[db] --verbose --no-owner --no-privileges --no-tablespaces --no-unlogged-table-data --inserts --disable-triggers --file [db]_$(date +%Y-%m-%d_%H-%M-%S).full.sql` |
| Dynamic partial structure only backup | `pg_dump --dbname=postgresql://[user]:[pass]@[host]:[port]/[db] --verbose --no-owner --no-privileges --no-tablespaces --no-unlogged-table-data --schema-only --file [db]_$(date +%Y-%m-%d_%H-%M-%S).schema.sql` |
| Dynamic partial data only backup | `pg_dump --dbname=postgresql://[user]:[pass]@[host]:[port]/[db] --verbose --inserts --data-only --disable-triggers --file [db]_$(date +%Y-%m-%d_%H-%M-%S).data.sql` |

### DUMP restores (C format)

| Description | Specification |
|-------------|:--------------|
| Fixed full restore (database and user must be the same from the dump) | `pg_restore --clean --verbose --dbname=postgresql://[user]:[pass]@[host]:[port]/[db] [file_name].dump` |
| Dynamic/fixed full structure and data restore (database needs to exist) | `pg_restore --clean --verbose --no-owner --no-privileges --role=[user] --dbname=postgresql://[user]:[pass]@[host]:[port]/[db] [file_name](.full).dump` |
| Dynamic/fixed partial structure only erstore (database needs to exist) | `pg_restore --clean --verbose --no-owner --no-privileges --no-tablespaces --schema-only --role=[user] --dbname=postgresql://[user]:[pass]@[host]:[port]/[db] [file_name](.schema).dump` |
| Dynamic/fixed partial data only (database needs to exist) | `pg_restore --clean --verbose --no-owner --no-privileges --disable-triggers --role=[user] --dbname=postgresql://[user]:[pass]@[host]:[port]/[db] [file_name](.data).dump` |


<!-- 
| Description | Specification |
|-------------|:--------------|
| col1 | col2 |
 -->
