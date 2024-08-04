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
  - [SQL snippets for Microsoft SQL Server](./snippets/queries_sqlserver.sql)
  - [SQL snippets for PostgreSQL](./snippets/queries_postgres.sql)

- Info
  - [Common info](./COMMON_INFO.md)
  - [Docker info](./DOCKER_INFO.md)

- Content
  - [MySQL](#mysql)
    - [Common queries (mysql)](#common-queries-mysql)
  - [Microsoft SQL Server](#microsoft-sql-server)
    - [Common queries (sql server)](#common-queries-sql-server)
    - [CLI operations for Linux Ubuntu (sql server)](#cli-operations-for-linux-ubuntu-sql-server)
    - [Reserved words (sql server)](#reserved-words-sql-server)
  - [MongoDB](#mongodb)  
    - [CLI operations for Linux Ubuntu (mongo)](#cli-operations-for-linux-ubuntu-mongo)
  - [Oracle](#oracle)
    - [Common queries (oracle)](#common-queries-oracle)
  - [PostgreSQL](#postgresql)
    - [Common queries (postgres)](#common-queries-postgres)
    - [Custom images](#custom-images)
    - [CLI operations for Linux Ubuntu (postgres)](#cli-operations-for-linux-ubuntu-postgres)
    - [Reserved words (postgres)](#reserved-words-postgres)
  - [SQLite](#sqlite)
    - [Common queries (sqlite)](#common-queries-sqlite)

# Database

## MySQL
[[Top]](#)<br />

### Common queries (mysql)

| Description | Specification |
|-------------|:--------------|
| Check database version | `select version();` |

## Microsoft SQL Server
[[Top]](#)<br />

### Common queries (sql server)

| Description | Specification |
|-------------|:--------------|
| Check database version | `SELECT @@VERSION;` |

### CLI operations for Linux Ubuntu (sql server)

- CLI tools: *sqlcmd*

The only possible backup in a Linux machine is a internal database backup, generated via a query sent to the database, generating a file inside the same machine (or container) where the database is located. That's why you should share a dedicated backup folder in a SQL Server docker container.

#### DUMP backup

| Description | Specification |
|-------------|:--------------|
| Fixed full backup | `( CMD=/opt/mssql-tools18/bin/sqlcmd && BKP_DIR=[backup_dir] && BKP_FILE=[db]_$(date +%Y-%m-%d_%H-%M-%S).dump && $CMD -C -S [host],[port] -U [user] -P [pass] -Q "BACKUP DATABASE [[db]] TO DISK = N'$BKP_DIR/$BKP_FILE' WITH NOFORMAT, NOINIT, NAME = '[db]', SKIP, NOREWIND, NOUNLOAD, STATS = 10" && chmod 775 $BKP_DIR/$BKP_FILE )` |

#### DUMP restore

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

### Reserved words (sql server)

```sql
ADD, ALL, ALTER, AND, ANY, AS, ASC, AUTHORIZATION, BACKUP, BEGIN, BETWEEN, BREAK, BROWSE,
BULK, BY, CASCADE, CASE, CHECK, CHECKPOINT, CLOSE, CLUSTERED, COALESCE, COLLATE, COLUMN,
COMMIT, COMPUTE, CONSTRAINT, CONTAINS, CONTAINSTABLE, CONTINUE, CONVERT, CREATE, CROSS,
CURRENT, CURRENT_DATE, CURRENT_TIME, CURRENT_TIMESTAMP, CURRENT_USER, CURSOR, DATABASE,
DBCC, DEALLOCATE, DECLARE, DEFAULT, DELETE, DENY, DESC, DISK, DISTINCT, DISTRIBUTED,
DOUBLE, DROP, DUMP, ELSE, END, ERRLVL, ESCAPE, EXCEPT, EXEC, EXECUTE, EXISTS, EXIT,
EXTERNAL, FETCH, FILE, FILLFACTOR, FOR, FOREIGN, FREETEXT, FREETEXTTABLE, FROM, FULL,
FUNCTION, GOTO, GRANT, GROUP, HAVING, HOLDLOCK, IDENTITY, IDENTITYCOL, IDENTITY_INSERT,
IF, IN, INDEX, INNER, INSERT, INTERSECT, INTO, IS, JOIN, KEY, KILL, LEFT, LIKE, LINENO,
LOAD, NATIONAL, NOCHECK, NONCLUSTERED, NOT, NULL, NULLIF, OF, OFF, OFFSETS, ON, OPEN,
OPENDATASOURCE, OPENQUERY, OPENROWSET, OPENXML, OPTION, OR, ORDER, OUTER, OVER, PERCENT,
PLAN, PRECISION, PRIMARY, PRINT, PROC, PROCEDURE, PUBLIC, RAISERROR, READ, READTEXT,
RECONFIGURE, REFERENCES, REPLICATION, RESTORE, RESTRICT, RETURN, REVERT, REVOKE, RIGHT,
ROLLBACK, ROWCOUNT, ROWGUIDCOL, RULE, SAVE, SCHEMA, SELECT, SESSION_USER, SET, SETUSER,
SHUTDOWN, SOME, STATISTICS, SYSTEM_USER, TABLE, TEXTSIZE, THEN, TO, TOP, TRAN, TRANSACTION,
TRIGGER, TRUNCATE, TSEQUAL, UNION, UNIQUE, UPDATE, UPDATETEXT, USE, USER, VALUES, VARYING,
VIEW, WAITFOR, WHEN, WHERE, WHILE, WITH, WRITETEXT.
```


## MongoDB
[[Top]](#)<br />

### CLI operations for Linux Ubuntu (mongo)
- CLI tools: *mongodump*, *mongorestore*

#### DUMP backup

| Description | Specification |
|-------------|:--------------|
| Fixed full backup | `mongodump -vv --authenticationDatabase="admin" --uri="mongodb://[host]:[port]" --username="[user]" --password="[pass]" --db="[db]" --archive="[db]_$(date +%Y-%m-%d_%H-%M-%S).dump"` |

#### DUMP restore

| Description | Specification |
|-------------|:--------------|
| Fixed full restore | `mongorestore -vv --authenticationDatabase="admin" --uri="mongodb://[host]:[port]" --username="[user]" --password="[pass]" --db="[db]" --archive="[file_name].dump"` |

## Oracle
[[Top]](#)<br />

### Common queries (oracle)

| Description | Specification |
|-------------|:--------------|
| Check database version | `SELECT * FROM v$version;` <br /> `SELECT banner FROM v$version;` |
| Check current database in use | `SELECT ora_database_name AS "Current Database" FROM dual;` |
| Check available schemas for current user | `SELECT username FROM all_users;` |
| Check schemas owned by current user | `SELECT username FROM user_users;` |

### CLI operations for Linux Ubuntu (oracle)
- CLI tools: *sqlplus*

#### General

<!-- TODO: adicionar como conectar com o banco de dados oracle com sqlplus, adicionar padrão do docker ou deixar isso para o docker? -->

## PostgreSQL
[[Top]](#)<br />

### Common queries (postgres)

<!-- TODO: remover essas queries e passar para os snippets de db -->

| Description | Specification |
|-------------|:--------------|
| Check database version | `select version();` |
| Check database size | `SELECT pg_size_pretty(pg_database_size('[db_name]'));` |
| Check database table | `SELECT pg_size_pretty(pg_total_relation_size('[table_name]'));` |
| Reset a sequence | `ALTER SEQUENCE [sequence_name] RESTART WITH 1;` |
| Set sequence value | `SELECT setval('[sequence_name]', 20, true); -- Next value would be 21;` |
| Date format in query usage | `'yyyy-MM-dd HH:mm:ss.000'` |

### CLI operations for Linux Ubuntu (postgres)
- CLI tools: *pg_dump*, *pg_restore*, *psql*

#### General

| Description | Specification |
|-------------|:--------------|
| Run .sql script file in a database by connection with psql | `PGPASSWORD=[pass] psql --echo-all --host [host] --port [port] --username [user] --dbname [db] --file [file_name]` |

#### DUMP backups (C format)

| Description | Specification |
|-------------|:--------------|
| Fixed full backup | `pg_dump --dbname=postgresql://[user]:[pass]@[host]:[port]/[db] --verbose --format c --file [db]_$(date +%Y-%m-%d_%H-%M-%S).dump` |
| Dynamic full structure and data backup | `pg_dump --dbname=postgresql://[user]:[pass]@[host]:[port]/[db] --verbose --no-owner --no-privileges --no-tablespaces --no-unlogged-table-data --inserts --disable-triggers --format c --file [db]_$(date +%Y-%m-%d_%H-%M-%S).full.dump` |
| Dynamic partial structure only backup | `pg_dump --dbname=postgresql://[user]:[pass]@[host]:[port]/[db] --verbose --no-owner --no-privileges --no-tablespaces --no-unlogged-table-data --schema-only --format c --file [db]_$(date +%Y-%m-%d_%H-%M-%S).schema.dump` |
| Dynamic partial data only backup | `pg_dump --dbname=postgresql://[user]:[pass]@[host]:[port]/[db] --verbose --inserts --data-only --disable-triggers --format c --file [db]_$(date +%Y-%m-%d_%H-%M-%S).data.dump` |

#### SQL backups (plain text format)

| Description | Specification |
|-------------|:--------------|
| Fixed full backup | `pg_dump --dbname=postgresql://[user]:[pass]@[host]:[port]/[db] --verbose --file [db]_$(date +%Y-%m-%d_%H-%M-%S).sql` |
| Dynamic full structure and data backup | `pg_dump --dbname=postgresql://[user]:[pass]@[host]:[port]/[db] --verbose --no-owner --no-privileges --no-tablespaces --no-unlogged-table-data --inserts --disable-triggers --file [db]_$(date +%Y-%m-%d_%H-%M-%S).full.sql` |
| Dynamic partial structure only backup | `pg_dump --dbname=postgresql://[user]:[pass]@[host]:[port]/[db] --verbose --no-owner --no-privileges --no-tablespaces --no-unlogged-table-data --schema-only --file [db]_$(date +%Y-%m-%d_%H-%M-%S).schema.sql` |
| Dynamic partial data only backup | `pg_dump --dbname=postgresql://[user]:[pass]@[host]:[port]/[db] --verbose --inserts --data-only --disable-triggers --file [db]_$(date +%Y-%m-%d_%H-%M-%S).data.sql` |

#### DUMP restores (C format)

| Description | Specification |
|-------------|:--------------|
| Fixed full restore (database and user must be the same from the dump) | `pg_restore --clean --verbose --dbname=postgresql://[user]:[pass]@[host]:[port]/[db] [file_name].dump` |
| Dynamic/fixed full structure and data restore (database needs to exist) | `pg_restore --clean --verbose --no-owner --no-privileges --role=[user] --dbname=postgresql://[user]:[pass]@[host]:[port]/[db] [file_name](.full).dump` |
| Dynamic/fixed partial structure only erstore (database needs to exist) | `pg_restore --clean --verbose --no-owner --no-privileges --no-tablespaces --schema-only --role=[user] --dbname=postgresql://[user]:[pass]@[host]:[port]/[db] [file_name](.schema).dump` |
| Dynamic/fixed partial data only (database needs to exist) | `pg_restore --clean --verbose --no-owner --no-privileges --disable-triggers --role=[user] --dbname=postgresql://[user]:[pass]@[host]:[port]/[db] [file_name](.data).dump` |

### Reserved words (postgres)

```sql
ABORT, ABSOLUTE, ACCESS, ACTION, ADD, ADMIN, AFTER, AGGREGATE, ALL, ALSO, ALTER, ALWAYS, ANALYSE,
ANALYZE, AND, ANY, ARRAY, AS, ASC, ASSERTION, ASSIGNMENT, ASYMMETRIC, AT, ATOMIC, AUTHORIZATION,
BACKWARD, BEFORE, BEGIN, BETWEEN, BIGINT, BINARY, BIT, BOOLEAN, BOTH, BY, CACHE, CALLED, CASCADE,
CASCADED, CASE, CAST, CATALOG, CHAIN, CHAR, CHARACTER, CHARACTERISTICS, CHECK, CHECKPOINT, CLASS,
CLOSE, CLUSTER, COALESCE, COLLATE, COLLATION, COLUMN, COLUMNS, COMMENT, COMMENTS, COMMIT, COMMITTED,
CONCURRENTLY, CONFIGURATION, CONNECTION, CONSTRAINT, CONSTRAINTS, CONTENT, CONTINUE, CONVERSION,
COPY, COST, CREATE, CROSS, CSV, CUBE, CURRENT, CURRENT_CATALOG, CURRENT_DATE, CURRENT_ROLE,
CURRENT_SCHEMA, CURRENT_TIME, CURRENT_TIMESTAMP, CURRENT_USER, CURSOR, CYCLE, DATA, DATABASE,
DATETIME, DAY, DEALLOCATE, DEC, DECIMAL, DECLARE, DEFAULT, DEFAULTS, DEFERRABLE, DEFERRED, DEFINE,
DELETE, DELIMITER, DELIMITERS, DEPENDS, DESC, DETACH, DICTIONARY, DISABLE, DISCARD, DISTINCT, DO,
DOCUMENT, DOMAIN, DOUBLE, DROP, EACH, ELSE, EMPTY, ENABLE, ENCODING, ENCRYPTED, END, ENUM, ESCAPE,
EVENT, EXCEPT, EXCLUDE, EXCLUDING, EXCLUSIVE, EXECUTE, EXISTS, EXPLAIN, EXTERNAL, EXTRACT, FALSE,
FAMILY, FETCH, FILTER, FIRST, FLOAT, FOLLOWING, FOR, FORCE, FOREIGN, FORMAT, FORWARD, FREEZE, FROM,
FULL, FUNCTION, FUNCTIONS, GENERATED, GLOBAL, GRANT, GRANTED, GREATEST, GROUP, GROUPING, HANDLER,
HAVING, HEADER, HOLD, HOUR, ID, IDENTITY, IF, ILIKE, IMMEDIATE, IMMUTABLE, IMPLICIT, IN, INCLUDING,
INCREMENT, INDEX, INDEXES, INHERIT, INHERITS, INITIALLY, INLINE, INNER, INOUT, INPUT, INSENSITIVE,
INSERT, INSTEAD, INT, INT2, INT4, INT8, INTEGER, INTERSECT, INTERVAL, INTO, INVOKER, IS, ISNULL,
ISOLATION, JOIN, KEY, LANCOMPILER, LANGUAGE, LARGE, LAST, LATERAL, LEADING, LEAKPROOF, LEFT, LEVEL,
LIKE, LIKE_REGEX, LIMIT, LISTEN, LOAD, LOCAL, LOCALTIME, LOCALTIMESTAMP, LOCATION, LOCK, MAP, MAPPING,
MATCH, MATERIALIZED, MAXVALUE, MEMBER, MERGE, MESSAGE_LENGTH, MESSAGE_OCTET_LENGTH, MESSAGE_TEXT,
METHOD, MINUTE, MINVALUE, MODIFIES, MODIFY, MONTH, MOVE, MUMPS, NAMES, NATIONAL, NATURAL, NCHAR,
NEW, NEXT, NFC, NFD, NFKC, NFKD, NO, NONE, NORMALIZE, NORMALIZED, NOT, NOTHING, NOTIFY, NOTNULL,
NOWAIT, NULL, NULLABLE, NULLIF, NULLS, NUMBER, NUMERIC, OBJECT, OF, OFF, OFFSET, OIDS, ON, ONLY, OPEN,
OPERATOR, OPTION, OPTIONS, OR, ORDER, ORDINALITY, OTHERS, OUT, OUTER, OUTPUT, OVER, OVERLAPS, OVERLAY,
OVERRIDING, OWNED, OWNER, PACKED, PAD, PARALLEL, PARAMETER, PARAMETER_MODE, PARAMETER_NAME, PARAMETERS,
PARTIAL, PARTITION, PASCAL, PASSWORD, PATH, PERCENT, PERCENTILE_CONT, PERCENTILE_DISC, PERIOD,
PERMISSION, PLACING, PLANS, PLI, POSITION, POSTFIX, PRECISION, PREFIX, PREORDER, PREPARE, PRESERVE,
PRIMARY, PRIOR, PRIVILEGES, PROCEDURAL, PROCEDURE, PUBLIC, QUOTE, RANGE, RANK, READ, READS, REAL,
REASSIGN, RECHECK, RECURSIVE, REF, REFERENCES, REFERENCING, REINDEX, RELATIVE, RELEASE, RENAME,
REPEATABLE, REPLACE, RESET, RESTART, RESTRICT, RETURN, RETURNING, RETURNS, REVOKE, RIGHT, ROLE, ROLLBACK,
ROLLUP, ROUTINE, ROUTINE_CATALOG, ROUTINE_NAME, ROUTINE_SCHEMA, ROW, ROWS, ROW_COUNT, ROW_NUMBER, RULE,
SAVEPOINT, SCALE, SCHEMA, SCHEMA_NAME, SCHEMAS, SCOPE, SCOPE_CATALOG, SCOPE_NAME, SCOPE_SCHEMA, SCROLL,
SEARCH, SECOND, SECURITY, SELECT, SELF, SENSITIVE, SEQUENCE, SERIALIZABLE, SERVER_NAME, SESSION,
SESSION_USER, SET, SETOF, SETS, SHARE, SHOW, SIMILAR, SIMPLE, SIZE, SMALLINT, SOME, SOURCE, SPACE,
SPECIFIC, SPECIFICTYPE, SPECIFIC_NAME, SQL, SQLCODE, SQLERROR, SQLEXCEPTION, SQLSTATE, SQLWARNING, STABLE,
STANDALONE, START, STATE, STATEMENT, STATIC, STATISTICS, STDDEV_POP, STDDEV_SAMP, STORAGE, STRICT, STRIP,
SUBCLASS_ORIGIN, SUBLIST, SUBSTRING, SUM, SYMMETRIC, SYSTEM, SYSTEM_TIME, SYSTEM_USER, TABLE, TABLE_NAME,
TEMP, TEMPLATE, TEMPORARY, TEXT, THEN, TIES, TIME, TIMESTAMP, TIMEZONE_HOUR, TIMEZONE_MINUTE, TO, TOKEN,
TOP_LEVEL_COUNT, TRAILING, TRANSACTION, TRANSACTIONS_COMMITTED, TRANSACTIONS_ROLLED_BACK, TRANSFORM,
TREAT, TRIGGER, TRIGGER_CATALOG, TRIGGER_NAME, TRIGGER_SCHEMA, TRIM, TRUE, TRUNCATE, TRUSTED, TYPE, UESCAPE,
UNBOUNDED, UNCOMMITTED, UNDER, UNENCRYPTED, UNION, UNIQUE, UNKNOWN, UNLINK, UNLISTEN, UNLOGGED, UNNAMED,
UNTIL, UPDATE, UPPER, URI, USAGE, USER, USER_DEFINED_TYPE_CATALOG, USER_DEFINED_TYPE_CODE,
USER_DEFINED_TYPE_NAME, USER_DEFINED_TYPE_SCHEMA, USING, VACUUM, VALID, VALIDATE, VALIDATOR, VALUE, VALUES,
VARCHAR, VARIADIC, VARYING, VAR_POP, VAR_SAMP, VERBOSE, VERSION, VIEW, VIEWS, VOLATILE, WHEN, WHENEVER,
WHERE, WHITESPACE, WINDOW, WITH, WITHIN, WITHOUT, WORK, WRITE, YEAR, ZONE
```

## SQLite
[[Top]](#)<br />

### Common queries (sqlite)

| Description | Specification |
|-------------|:--------------|
| Check database version | `select sqlite_version();` |



<!-- 
| Description | Specification |
|-------------|:--------------|
| col1 | col2 |
 -->


