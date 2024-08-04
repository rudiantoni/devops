-- ##################################################
-- References
--
-- PostgreSQL 14: https://www.postgresql.org/docs/14/index.html
-- PostgreSQL 15: https://www.postgresql.org/docs/15/index.html
-- PostgreSQL 16: https://www.postgresql.org/docs/16/index.html
-- Data Types: https://www.postgresql.org/docs/current/datatype.html
--
-- Below queries are supported (and tested) in PostgreSQL versions 14, 15 and 16
--
-- Notes: 
--   - Reserved words: check the end of the file
--   - Date format in query usage: 'yyyy-MM-dd HH:mm:ss.000'
--
-- ##################################################

-- ##################################################
-- # Major structure manipulation (database, role, table and sequence)
-- ##################################################
-- Creating a database, with a user and privileges
-- --------------------------------------------------
CREATE DATABASE my_database_name;
CREATE ROLE my_database_name
	NOSUPERUSER
	NOCREATEDB
	CREATEROLE
	INHERIT
	LOGIN
	REPLICATION
	BYPASSRLS
	PASSWORD 'my_database_name';

GRANT ALL PRIVILEGES ON DATABASE my_database_name TO my_database_name;

-- Creating and modifying a table with different setups
-- --------------------------------------------------
CREATE TABLE IF NOT EXISTS public.your_table_name (        -- public = schema
  id                BIGSERIAL NOT NULL,                    -- Example field for a FK (BIGINT with autoincrement) Alias: SERIAL8
  ex_uk             VARCHAR(255) NOT NULL,                 -- Example field for a UK
  ex_bigint_id      BIGINT,                                -- (references a BIGSERIAL from a FK) Alias: INT8
  ex_boolean        BOOLEAN,                               -- Alias: BOOL
  ex_vchar          VARCHAR(255),                          -- Name: CHARACTER VARYING
  ex_real           REAL,                                  -- Alias: FLOAT4
  ex_double         DOUBLE PRECISION,                      -- Alias: FLOAT8
  ex_numeric_a      NUMERIC(4,0),                          -- Numeric(precision, scale), from -9999 to 9999 without decimal
  ex_numeric_b      NUMERIC(8,4),                          -- From -9999.9999 to 9999.9999
  ex_num_int        NUMERIC(10,0),                         -- Pseudo-equivalent numeric to int
  ex_num_bigint     NUMERIC(19,0),                         -- Pseudo-equivalent numeric to bigin
  ex_num_real       NUMERIC(7,3),                          -- Pseudo-equivalent numeric to real
  ex_num_double     NUMERIC(15,6),                         -- Pseudo-equivalent numeric to double precision
  ex_text           TEXT,                                  -- Usage for stupids amount of text
  ex_ts             TIMESTAMP,                             -- Default for dates
  ex_not_null       VARCHAR(255) NOT NULL,                 -- Field that can't be NULL (everyone is NULL by default)
  ex_default        VARCHAR(255) DEFAULT 'Hello, World!',  -- Field that has a default value applied when nothing is inserted but can be null if defined
  ex_int_pk         BIGSERIAL PRIMARY KEY NOT NULL,        -- PK constraint definition on field creation
  ex_int_uk         VARCHAR(255) UNIQUE NOT NULL,          -- UK constraint definition on field creation
  -- ex_comp_uk_a      VARCHAR(255) NOT NULL,                 -- Example field for composite UK constraint
  -- ex_comp_uk_b      VARCHAR(255) NOT NULL,                 -- Example field for composite UK constraint
  -- ex_comp_pk_a      VARCHAR(255) NOT NULL,                 -- Example field for composite PK constraint
  -- ex_comp_pk_b      VARCHAR(255) NOT NULL,                 -- Example field for composite PK constraint

  def_created_at  TIMESTAMP DEFAULT NOW() NOT NULL, -- Default for create date field
  def_updated_at  TIMESTAMP DEFAULT NOW() NOT NULL, -- Default for create date field
    
  CONSTRAINT pk_your_table_name PRIMARY KEY (id),      -- Default simple PK definition
  CONSTRAINT uk_your_table_name_ex_uk UNIQUE (ex_uk),  -- Default simple UK definition
  CONSTRAINT fk_your_table_name_on_another_table FOREIGN KEY (ex_bigint_id) REFERENCES public.another_table(id),  -- Default FK definition
  -- CONSTRAINT pk_your_table_name PRIMARY KEY (ex_comp_pk_a, ex_comp_pk_b),                                      -- Default composite PK definition
  -- CONSTRAINT uk_your_table_name_ex_comp_uk_a_ex_comp_uk_b UNIQUE (ex_comp_uk_a, ex_comp_uk_b),                 -- Default composite UK definition
);

-- Creating an FK after the table has been created
ALTER TABLE public.your_table_name
  ADD CONSTRAINT fk_your_table_name_on_another_table FOREIGN KEY (ex_bigint_id) REFERENCES public.another_table(id);

-- Creating a table without BIGSERIAL id, you will need SEQUENCES
CREATE TABLE IF NOT EXISTS public.your_table_name (
  id              BIGINT NOT NULL,
  name            VARCHAR(255),

  CONSTRAINT pk_your_table_name PRIMARY KEY (id)
);
-- Create the sequence for the non-BIGSERIAL id table
CREATE SEQUENCE IF NOT EXISTS public.your_table_name_seq
  AS BIGINT -- Same datatype from the increment field on the database
  INCREMENT 1
  MINVALUE 1
  NO MAXVALUE
  NO CYCLE
  START WITH 1
  OWNED BY public.your_table_name.id; -- schema.table.field = Owner of the sequence
-- Set the table field default value as the sequence
ALTER TABLE public.your_table_name
  ALTER COLUMN id SET DEFAULT nextval('public.your_table_name_seq'::regclass); -- public.your_table_name_seq is your sequence name

-- Reset a sequence back to 1
ALTER SEQUENCE sequence_name RESTART WITH 1;

-- Manually set a sequence current value to 20, so next value would be 21
SELECT setval('sequence_name', 20, true);

-- ##################################################
-- # Counts
-- ##################################################
-- Count a column by its unique values amount
-- --------------------------------------------------
-- Using subquery with GROUP BY
SELECT COUNT(1) FROM (
  SELECT column_name FROM table_name GROUP BY column_name
) AS unique_result

-- Using subquery with DISTINCT
SELECT COUNT(1) FROM (
  SELECT DISTINCT column_name FROM table_name
) AS unique_count

-- Using COUNT DISTINCT
SELECT COUNT(DISTINCT column_name) AS unique_count FROM table_name;

-- ##################################################
-- # Mathematics
-- ##################################################
-- Return the absolute value (without positive or negative signal) from a number
SELECT ABS(-10)
SELECT ABS(10)
SELECT ABS(-10.5)
SELECT ABS(10.5)

-- Calc difference between dates as INTERVAL, (differencing negative and positive), returns a INTERVAL type
SELECT '2005-01-01 00:00:00'::TIMESTAMPTZ - '2000-01-01 00:00:00'::TIMESTAMPTZ
SELECT '2000-01-01 00:00:00'::TIMESTAMPTZ - '2005-01-01 00:00:00'::TIMESTAMPTZ

-- ##################################################
-- # Data
-- ##################################################
-- Convert VARCHAR (string) to TIMESTAMP WITH TIMEZONE (TIMESTAMPTZ)
SELECT '2005-01-01 00:00:00'::TIMESTAMPTZ

-- Convert VARCHAR (string) to INTERVAL
SELECT '3 years'::INTERVAL
SELECT '3 years 2 months'::INTERVAL
SELECT '3 years 2 months 9 days'::INTERVAL
SELECT '3 years 2 months 9 days 14 hours'::INTERVAL
SELECT '3 years 2 months 9 days 14 hours 25 minutes'::INTERVAL
SELECT '3 years 2 months 9 days 14 hours 25 minutes 35 seconds'::INTERVAL
SELECT '3 years 2 months 9 days 14 hours 25 minutes 35 seconds 258 milliseconds'::INTERVAL
SELECT '3 years 2 months 9 days 14 hours 25 minutes 35 seconds 258 milliseconds 359 microseconds'::INTERVAL
-- Other ways to write INTERVALs
SELECT '3 y 2 mon 9 d 14 h 25 min 35 sec'::INTERVAL

-- Extract time unit data from an INTERVAL, returns a NUMERIC type
SELECT EXTRACT(EPOCH FROM '3 years 2 months 3 days 14 hours 25 minutes 35 seconds'::INTERVAL); -- EPOCH (total number of seconds in the INTERVAL)
SELECT EXTRACT(CENTURY FROM '3 years 2 months 3 days 14 hours 25 minutes 35 seconds'::INTERVAL);
SELECT EXTRACT(DECADE FROM '3 years 2 months 3 days 14 hours 25 minutes 35 seconds'::INTERVAL);
SELECT EXTRACT(YEAR FROM '3 years 2 months 3 days 14 hours 25 minutes 35 seconds'::INTERVAL);
SELECT EXTRACT(QUARTER FROM '3 years 2 months 3 days 14 hours 25 minutes 35 seconds'::INTERVAL);
SELECT EXTRACT(MONTH FROM '3 years 2 months 3 days 14 hours 25 minutes 35 seconds'::INTERVAL);
SELECT EXTRACT(DAY FROM '3 years 2 months 3 days 14 hours 25 minutes 35 seconds'::INTERVAL);
SELECT EXTRACT(HOUR FROM '3 years 2 months 3 days 14 hours 25 minutes 35 seconds'::INTERVAL);
SELECT EXTRACT(MINUTE FROM '3 years 2 months 3 days 14 hours 25 minutes 35 seconds'::INTERVAL);
SELECT EXTRACT(SECOND FROM '3 years 2 months 3 days 14 hours 25 minutes 35 seconds'::INTERVAL);
SELECT EXTRACT(MILLISECOND FROM '3 years 2 months 3 days 14 hours 25 minutes 35 seconds'::INTERVAL);
SELECT EXTRACT(MICROSECOND FROM '3 years 2 months 3 days 14 hours 25 minutes 35 seconds'::INTERVAL);

-- ##################################################
-- # Management
-- ##################################################
-- Select all queries (with their PIDs) with duration (INTERVAL) higher then 5 minutes
-- --------------------------------------------------
SELECT
  pid,
  NOW() - pg_stat_activity.query_start AS duration,
  query,
  state
FROM pg_stat_activity
WHERE (NOW() - pg_stat_activity.query_start) > INTERVAL '5 minutes';

-- Cancel a running query by its PID (need to obtain the PID first)
SELECT PG_CANCEL_BACKEND(pid);

-- ##################################################
-- # Using native related functionality
-- ##################################################
-- Get database version
-- --------------------------------------------------
SELECT VERSION();

-- Get current time
-- --------------------------------------------------
-- Get current time using function NOW(), returns a TIMESTAMP WITH TIMEZONE (TIMESTAMPTZ) type
SELECT NOW()

-- Using variable CURRENT_TIMESTAMP, returns a TIMESTAMP WITH TIMEZONE (TIMESTAMPTZ) type
SELECT CURRENT_TIMESTAMP

-- Get table size
SELECT PG_SIZE_PRETTY(PG_TOTAL_RELATION_SIZE('table_name'));

-- Get database size
SELECT PG_SIZE_PRETTY(PG_DATABASE_SIZE('db_name'))

-- Cancel a running query by its PID (need to obtain the PID first)
SELECT PG_CANCEL_BACKEND(pid)

-- Detect a type from a data
SELECT PG_TYPEOF(data_or_column)
SELECT PG_TYPEOF((SELECT NOW()))
SELECT PG_TYPEOF(NOW())

--
-- Reserved words
-- 
-- ABORT, ABSOLUTE, ACCESS, ACTION, ADD, ADMIN, AFTER, AGGREGATE, ALL, ALSO, ALTER, ALWAYS, ANALYSE,
-- ANALYZE, AND, ANY, ARRAY, AS, ASC, ASSERTION, ASSIGNMENT, ASYMMETRIC, AT, ATOMIC, AUTHORIZATION,
-- BACKWARD, BEFORE, BEGIN, BETWEEN, BIGINT, BINARY, BIT, BOOLEAN, BOTH, BY, CACHE, CALLED, CASCADE,
-- CASCADED, CASE, CAST, CATALOG, CHAIN, CHAR, CHARACTER, CHARACTERISTICS, CHECK, CHECKPOINT, CLASS,
-- CLOSE, CLUSTER, COALESCE, COLLATE, COLLATION, COLUMN, COLUMNS, COMMENT, COMMENTS, COMMIT, COMMITTED,
-- CONCURRENTLY, CONFIGURATION, CONNECTION, CONSTRAINT, CONSTRAINTS, CONTENT, CONTINUE, CONVERSION,
-- COPY, COST, CREATE, CROSS, CSV, CUBE, CURRENT, CURRENT_CATALOG, CURRENT_DATE, CURRENT_ROLE,
-- CURRENT_SCHEMA, CURRENT_TIME, CURRENT_TIMESTAMP, CURRENT_USER, CURSOR, CYCLE, DATA, DATABASE,
-- DATETIME, DAY, DEALLOCATE, DEC, DECIMAL, DECLARE, DEFAULT, DEFAULTS, DEFERRABLE, DEFERRED, DEFINE,
-- DELETE, DELIMITER, DELIMITERS, DEPENDS, DESC, DETACH, DICTIONARY, DISABLE, DISCARD, DISTINCT, DO,
-- DOCUMENT, DOMAIN, DOUBLE, DROP, EACH, ELSE, EMPTY, ENABLE, ENCODING, ENCRYPTED, END, ENUM, ESCAPE,
-- EVENT, EXCEPT, EXCLUDE, EXCLUDING, EXCLUSIVE, EXECUTE, EXISTS, EXPLAIN, EXTERNAL, EXTRACT, FALSE,
-- FAMILY, FETCH, FILTER, FIRST, FLOAT, FOLLOWING, FOR, FORCE, FOREIGN, FORMAT, FORWARD, FREEZE, FROM,
-- FULL, FUNCTION, FUNCTIONS, GENERATED, GLOBAL, GRANT, GRANTED, GREATEST, GROUP, GROUPING, HANDLER,
-- HAVING, HEADER, HOLD, HOUR, ID, IDENTITY, IF, ILIKE, IMMEDIATE, IMMUTABLE, IMPLICIT, IN, INCLUDING,
-- INCREMENT, INDEX, INDEXES, INHERIT, INHERITS, INITIALLY, INLINE, INNER, INOUT, INPUT, INSENSITIVE,
-- INSERT, INSTEAD, INT, INT2, INT4, INT8, INTEGER, INTERSECT, INTERVAL, INTO, INVOKER, IS, ISNULL,
-- ISOLATION, JOIN, KEY, LANCOMPILER, LANGUAGE, LARGE, LAST, LATERAL, LEADING, LEAKPROOF, LEFT, LEVEL,
-- LIKE, LIKE_REGEX, LIMIT, LISTEN, LOAD, LOCAL, LOCALTIME, LOCALTIMESTAMP, LOCATION, LOCK, MAP, MAPPING,
-- MATCH, MATERIALIZED, MAXVALUE, MEMBER, MERGE, MESSAGE_LENGTH, MESSAGE_OCTET_LENGTH, MESSAGE_TEXT,
-- METHOD, MINUTE, MINVALUE, MODIFIES, MODIFY, MONTH, MOVE, MUMPS, NAMES, NATIONAL, NATURAL, NCHAR,
-- NEW, NEXT, NFC, NFD, NFKC, NFKD, NO, NONE, NORMALIZE, NORMALIZED, NOT, NOTHING, NOTIFY, NOTNULL,
-- NOWAIT, NULL, NULLABLE, NULLIF, NULLS, NUMBER, NUMERIC, OBJECT, OF, OFF, OFFSET, OIDS, ON, ONLY, OPEN,
-- OPERATOR, OPTION, OPTIONS, OR, ORDER, ORDINALITY, OTHERS, OUT, OUTER, OUTPUT, OVER, OVERLAPS, OVERLAY,
-- OVERRIDING, OWNED, OWNER, PACKED, PAD, PARALLEL, PARAMETER, PARAMETER_MODE, PARAMETER_NAME, PARAMETERS,
-- PARTIAL, PARTITION, PASCAL, PASSWORD, PATH, PERCENT, PERCENTILE_CONT, PERCENTILE_DISC, PERIOD,
-- PERMISSION, PLACING, PLANS, PLI, POSITION, POSTFIX, PRECISION, PREFIX, PREORDER, PREPARE, PRESERVE,
-- PRIMARY, PRIOR, PRIVILEGES, PROCEDURAL, PROCEDURE, PUBLIC, QUOTE, RANGE, RANK, READ, READS, REAL,
-- REASSIGN, RECHECK, RECURSIVE, REF, REFERENCES, REFERENCING, REINDEX, RELATIVE, RELEASE, RENAME,
-- REPEATABLE, REPLACE, RESET, RESTART, RESTRICT, RETURN, RETURNING, RETURNS, REVOKE, RIGHT, ROLE, ROLLBACK,
-- ROLLUP, ROUTINE, ROUTINE_CATALOG, ROUTINE_NAME, ROUTINE_SCHEMA, ROW, ROWS, ROW_COUNT, ROW_NUMBER, RULE,
-- SAVEPOINT, SCALE, SCHEMA, SCHEMA_NAME, SCHEMAS, SCOPE, SCOPE_CATALOG, SCOPE_NAME, SCOPE_SCHEMA, SCROLL,
-- SEARCH, SECOND, SECURITY, SELECT, SELF, SENSITIVE, SEQUENCE, SERIALIZABLE, SERVER_NAME, SESSION,
-- SESSION_USER, SET, SETOF, SETS, SHARE, SHOW, SIMILAR, SIMPLE, SIZE, SMALLINT, SOME, SOURCE, SPACE,
-- SPECIFIC, SPECIFICTYPE, SPECIFIC_NAME, SQL, SQLCODE, SQLERROR, SQLEXCEPTION, SQLSTATE, SQLWARNING, STABLE,
-- STANDALONE, START, STATE, STATEMENT, STATIC, STATISTICS, STDDEV_POP, STDDEV_SAMP, STORAGE, STRICT, STRIP,
-- SUBCLASS_ORIGIN, SUBLIST, SUBSTRING, SUM, SYMMETRIC, SYSTEM, SYSTEM_TIME, SYSTEM_USER, TABLE, TABLE_NAME,
-- TEMP, TEMPLATE, TEMPORARY, TEXT, THEN, TIES, TIME, TIMESTAMP, TIMEZONE_HOUR, TIMEZONE_MINUTE, TO, TOKEN,
-- TOP_LEVEL_COUNT, TRAILING, TRANSACTION, TRANSACTIONS_COMMITTED, TRANSACTIONS_ROLLED_BACK, TRANSFORM,
-- TREAT, TRIGGER, TRIGGER_CATALOG, TRIGGER_NAME, TRIGGER_SCHEMA, TRIM, TRUE, TRUNCATE, TRUSTED, TYPE, UESCAPE,
-- UNBOUNDED, UNCOMMITTED, UNDER, UNENCRYPTED, UNION, UNIQUE, UNKNOWN, UNLINK, UNLISTEN, UNLOGGED, UNNAMED,
-- UNTIL, UPDATE, UPPER, URI, USAGE, USER, USER_DEFINED_TYPE_CATALOG, USER_DEFINED_TYPE_CODE,
-- USER_DEFINED_TYPE_NAME, USER_DEFINED_TYPE_SCHEMA, USING, VACUUM, VALID, VALIDATE, VALIDATOR, VALUE, VALUES,
-- VARCHAR, VARIADIC, VARYING, VAR_POP, VAR_SAMP, VERBOSE, VERSION, VIEW, VIEWS, VOLATILE, WHEN, WHENEVER,
-- WHERE, WHITESPACE, WINDOW, WITH, WITHIN, WITHOUT, WORK, WRITE, YEAR, ZONE
