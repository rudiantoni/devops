-- ##################################################
-- References
--
-- Oracle Database: https://docs.oracle.com/en/database/oracle/oracle-database/index.html
-- Oracle Database 11g Release 2: https://docs.oracle.com/cd/E11882_01/index.htm
--
-- Below queries are supported (and tested) in Oracle Database version 11g R2 XE
--
-- ##################################################

-- ##################################################
-- # Using native related functionality
-- ##################################################
-- Get database version
SELECT * FROM v$version;
SELECT banner FROM v$version;

--List all available schemas
SELECT username FROM all_users;

--Show current active database
SELECT name FROM v$database;
SELECT ora_database_name AS "Current Database" FROM dual;

-- List all tables from the active database
SELECT table_name FROM user_tables;

-- Check schemas owned by current user
SELECT username FROM user_users;
