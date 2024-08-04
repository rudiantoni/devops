-- ##################################################
-- References
--
-- SQLServer 2019: https://learn.microsoft.com/en-us/sql/sql-server/?view=sql-server-ver15
--
-- Below queries are supported (and tested) in SQLServer 2019
--
-- ##################################################

-- ##################################################
-- # Major structure manipulation (function)
-- ##################################################
-- Creating and testing functions
-- --------------------------------------------------
-- Create a function that receives a string and returns all numbers inside that string
-- or null if there's no numbers otherwise
DROP FUNCTION dbo.get_numbers_or_null;
CREATE FUNCTION dbo.get_numbers_or_null(@STR_INPUT VARCHAR(MAX))
RETURNS VARCHAR(MAX)
AS
BEGIN
  DECLARE @RESULT VARCHAR(MAX) = '';
  DECLARE @SEARCH_IDX INT = 1;
  DECLARE @FOUND_IDX INT = 0;
  
  WHILE @SEARCH_IDX < LEN(@STR_INPUT) + 1 BEGIN
    SET @FOUND_IDX = PATINDEX('%[0-9]%', SUBSTRING(@STR_INPUT, @SEARCH_IDX, 1));
    IF @FOUND_IDX > 0 BEGIN
          SET @RESULT += SUBSTRING(@STR_INPUT, @SEARCH_IDX, 1);
        END
    
  SET @SEARCH_IDX += 1;
  END

    IF @RESULT = ''
        SET @RESULT = NULL;
    
    RETURN @RESULT;

END

-- Testing the created functions queries
SELECT dbo.get_numbers_or_null('a205s 1as56d1a 06d 1sklndajklnd\ sd! \@ #~') as func_result
SELECT dbo.get_numbers_or_null('as asda d sklndajklnd\ sd! \@ #~') as func_result

-- ##################################################
-- # Using native related functionality
-- ##################################################
-- Get database version
SELECT @@VERSION;

-- Get current time using function GETDATE(), returns a DATETIME type
SELECT GETDATE()

-- Detect a type from a data
SELECT SQL_VARIANT_PROPERTY(your_data_or_column, 'BaseType') AS data_type
SELECT SQL_VARIANT_PROPERTY(GETDATE(), 'BaseType') AS data_type

-- ##################################################
-- # Data
-- ##################################################
-- Extract time unit data from an INTERVAL, returns a INT type
SELECT YEAR(GETDATE())
SELECT MONTH(GETDATE())
SELECT DAY(GETDATE())
SELECT DATEPART(hour, GETDATE())
SELECT DATEPART(MINUTE, GETDATE())
SELECT DATEPART(SECOND, GETDATE())

-- ##################################################
-- Management
-- ##################################################
-- Analyse a query execution load and time (Run by steps)
-- --------------------------------------------------
-- Step 1: Enable I/O and time statistics
SET STATISTICS IO ON;
SET STATISTICS TIME ON;
-- Step 2: Run you query
-- The query result is showed in the normal prompt, but the statistics are shown in the system output stream
SELECT * FROM your_table
-- Step 3: Disable I/O and time statistics
SET STATISTICS IO OFF;
SET STATISTICS TIME OFF;

-- Analyse a query cost (Run by steps)
-- --------------------------------------------------
-- Step 1: Enable query execution plan profile
SET STATISTICS PROFILE ON;
-- Step 2: Run you query
-- The query result is showed in the normal prompt, and the statistics are shown in another prompt
SELECT * FROM your_table
-- Step 3: Disable query execution plan profile
SET STATISTICS PROFILE OFF;

-- 
-- Reserved words 
--
-- ADD, ALL, ALTER, AND, ANY, AS, ASC, AUTHORIZATION, BACKUP, BEGIN, BETWEEN, BREAK, BROWSE,
-- BULK, BY, CASCADE, CASE, CHECK, CHECKPOINT, CLOSE, CLUSTERED, COALESCE, COLLATE, COLUMN,
-- COMMIT, COMPUTE, CONSTRAINT, CONTAINS, CONTAINSTABLE, CONTINUE, CONVERT, CREATE, CROSS,
-- CURRENT, CURRENT_DATE, CURRENT_TIME, CURRENT_TIMESTAMP, CURRENT_USER, CURSOR, DATABASE,
-- DBCC, DEALLOCATE, DECLARE, DEFAULT, DELETE, DENY, DESC, DISK, DISTINCT, DISTRIBUTED,
-- DOUBLE, DROP, DUMP, ELSE, END, ERRLVL, ESCAPE, EXCEPT, EXEC, EXECUTE, EXISTS, EXIT,
-- EXTERNAL, FETCH, FILE, FILLFACTOR, FOR, FOREIGN, FREETEXT, FREETEXTTABLE, FROM, FULL,
-- FUNCTION, GOTO, GRANT, GROUP, HAVING, HOLDLOCK, IDENTITY, IDENTITYCOL, IDENTITY_INSERT,
-- IF, IN, INDEX, INNER, INSERT, INTERSECT, INTO, IS, JOIN, KEY, KILL, LEFT, LIKE, LINENO,
-- LOAD, NATIONAL, NOCHECK, NONCLUSTERED, NOT, NULL, NULLIF, OF, OFF, OFFSETS, ON, OPEN,
-- OPENDATASOURCE, OPENQUERY, OPENROWSET, OPENXML, OPTION, OR, ORDER, OUTER, OVER, PERCENT,
-- PLAN, PRECISION, PRIMARY, PRINT, PROC, PROCEDURE, PUBLIC, RAISERROR, READ, READTEXT,
-- RECONFIGURE, REFERENCES, REPLICATION, RESTORE, RESTRICT, RETURN, REVERT, REVOKE, RIGHT,
-- ROLLBACK, ROWCOUNT, ROWGUIDCOL, RULE, SAVE, SCHEMA, SELECT, SESSION_USER, SET, SETUSER,
-- SHUTDOWN, SOME, STATISTICS, SYSTEM_USER, TABLE, TEXTSIZE, THEN, TO, TOP, TRAN, TRANSACTION,
-- TRIGGER, TRUNCATE, TSEQUAL, UNION, UNIQUE, UPDATE, UPDATETEXT, USE, USER, VALUES, VARYING,
-- VIEW, WAITFOR, WHEN, WHERE, WHILE, WITH, WRITETEXT.
