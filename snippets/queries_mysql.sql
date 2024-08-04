-- ##################################################
-- References
--
-- MySQL: https://www.postgresql.org/docs/14/index.html
--
-- Below queries are supported (and tested) in MySQL version 8
--
-- ##################################################

-- ##################################################
-- # Using native related functionality
-- ##################################################
-- Get database version
SELECT VERSION();

-- ##################################################
-- Management
-- ##################################################
-- List all available databases
SHOW DATABASES;

-- Define an active database
USE mysqldb;

-- Show current active database
SELECT DATABASE();

-- List all collections from the active database
SHOW TABLES;
