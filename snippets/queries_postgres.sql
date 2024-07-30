-- ##################################################
-- Annotations
-- 
-- Official docs: https://www.postgresql.org/docs/16/index.html
--   - Data Types: https://www.postgresql.org/docs/current/datatype.html
--
-- Default datatype for the function NOW(): TIMESTAMP WITH TIMEZONE (TIMESTAMPTZ)
-- Default datatype for date subtraction: INTERVAL
--
-- ##################################################

-- ##################################################
-- DDLs (Data Definition Languages)
-- ##################################################
--
-- CREATE TABLE defaults
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
  -- CONSTRAINT pk_your_table_name PRIMARY KEY (ex_comp_pk_a, ex_comp_pk_b),                       -- Default composite PK definition
  -- CONSTRAINT uk_your_table_name_ex_comp_uk_a_ex_comp_uk_b UNIQUE (ex_comp_uk_a, ex_comp_uk_b),  -- Default composite UK definition
);

-- Default FK definition
ALTER TABLE public.your_table_name
  ADD CONSTRAINT fk_your_table_name_another_table FOREIGN KEY (ex_bigint_id) REFERENCES public.another_table(id);

-- For tables without BIGSERIAL, you need to create SEQUENCES
-- Create the table
CREATE TABLE IF NOT EXISTS public.your_table_name (
  id              BIGINT NOT NULL,
  name            VARCHAR(255),

  CONSTRAINT pk_your_table_name PRIMARY KEY (id)
);
-- Create the sequence for the table
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


-- ##################################################
-- Current time
-- ##################################################
-- Using function NOW()
SELECT NOW()

-- Using variable CURRENT_TIMESTAMP
SELECT CURRENT_TIMESTAMP

-- ##################################################
-- Query shortcuts and defaults
-- ##################################################
-- 
-- Count a column by its unique values amount
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
-- Mathematics
-- ##################################################
--
-- Return the absolute value (without positive or negative signal) from a number
SELECT ABS(-10)
SELECT ABS(10)
SELECT ABS(-10.5)
SELECT ABS(10.5)


-- ##################################################
-- Other operations
-- ##################################################s
--
-- Detect a type from a data
SELECT PG_TYPEOF(your_data_or_column)
SELECT PG_TYPEOF(NOW())

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

-- Calc difference between dates as INTERVAL, (differencing negative and positive)
SELECT '2005-01-01 00:00:00'::TIMESTAMPTZ - '2000-01-01 00:00:00'::TIMESTAMPTZ
SELECT '2000-01-01 00:00:00'::TIMESTAMPTZ - '2005-01-01 00:00:00'::TIMESTAMPTZ

-- Extract time unit data from an INTERVAL
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
-- Management operations
-- ##################################################
--
-- Select all queries (with their PIDs) with duration (INTERVAL) higher then 5 minutes
SELECT
  pid,
  NOW() - pg_stat_activity.query_start AS duration,
  query,
  state
FROM pg_stat_activity
WHERE (NOW() - pg_stat_activity.query_start) > INTERVAL '5 minutes';

-- Cancel a running query by its PID (see previous query on how to obtain)
SELECT pg_cancel_backend(pid);


-- TODO: Atualizar com especificações, testes, versões de db, etc
-- Criar banco de dados, usuário e dar acesso para ele
CREATE DATABASE market;
CREATE ROLE market
	NOSUPERUSER
	NOCREATEDB
	CREATEROLE
	INHERIT
	LOGIN
	REPLICATION
	BYPASSRLS
	PASSWORD 'market';

GRANT ALL PRIVILEGES ON DATABASE market TO market;
-- Fim TODO
