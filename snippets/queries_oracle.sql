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
-- --------------------------------------------------
SELECT * FROM v$version;
SELECT banner FROM v$version;

-- Others
-- --------------------------------------------------
-- Check current database in use
SELECT ora_database_name AS "Current Database" FROM dual;

-- Check available schemas for current user
SELECT username FROM all_users;

-- Check schemas owned by current user
SELECT username FROM user_users;
