#!/bin/bash

CURRENT_PG_USER=double
CURRENT_PG_DB=double
SQL_FILE=/docker-entrypoint-initdb.d/.sql/init.sql

echo "Running SQL file ($SQL_FILE) to database ($CURRENT_PG_DB) with user ($CURRENT_PG_USER)."
psql --echo-all --username $CURRENT_PG_USER --dbname $CURRENT_PG_DB --file $SQL_FILE