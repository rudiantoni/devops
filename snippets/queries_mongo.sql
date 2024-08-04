-- ##################################################
-- References
--
-- MongoDB: https://www.mongodb.com/pt-br/docs/
--
-- Below queries are supported (and tested) in MongoDB version 5
--
-- ##################################################

-- ##################################################
-- # Using native related functionality
-- ##################################################
-- Get database version
version();

-- ##################################################
-- Management
-- ##################################################
-- List all available databases
show dbs;

-- Define an active database
use db_name;

-- Show current active database
db;

-- List all collections from the active database
show collections;
db.getCollectionNames();
