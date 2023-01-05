#!/bin/bash

CURRENT_PG_USER=$POSTGRES_USER
CURRENT_PG_DB=$POSTGRES_DB
PG_ADD_DB_LIST=${POSTGRES_ADDITIONAL_DBS:-"none"}
ADDITIONAL_USER_BY_DB=${POSTGRES_ADDITIONAL_DBS_USER:-false}

create_additional_db() {
    local DB_NAME=$1
    
    if [ "$ADDITIONAL_USER_BY_DB" = true ]; then
        echo "Creating user ($DB_NAME) for database ($DB_NAME) and granting all privileges."
        psql --echo-all --username $CURRENT_PG_USER --dbname $CURRENT_PG_DB <<-EOSQL
            CREATE USER $DB_NAME;
            CREATE DATABASE $DB_NAME;
            GRANT ALL PRIVILEGES ON DATABASE $DB_NAME TO $DB_NAME;
EOSQL
    else
        echo "Creating database ($DB_NAME) with default user ($CURRENT_PG_USER)."
        psql --echo-all --username $CURRENT_PG_USER --dbname $CURRENT_PG_DB <<-EOSQL
            CREATE DATABASE $DB_NAME;
EOSQL
    fi
}

if [ "$PG_ADD_DB_LIST" = "none" ]; then
    echo "Additional databases creation not requested."
    return
fi

ADD_DB_NAME_LIST=$(echo "$PG_ADD_DB_LIST" | tr -s "," " " | tr -s " ")
ADD_DB_NAME_LIST=${ADD_DB_NAME_LIST%% }
ADD_DB_NAME_LIST=${ADD_DB_NAME_LIST## }

if [ -z "$ADD_DB_NAME_LIST" ]; then
    echo "Can't process a valid list of additional database names to create."
    echo "Passed list: ($PG_ADD_DB_LIST)."
    echo "Final processed list: ($ADD_DB_NAME_LIST)."
else
    for ADD_DB_NAME in $ADD_DB_NAME_LIST; do
        if [ -z "$ADD_DB_NAME" ]; then
            echo "Database name cannot be empty"
        else
            echo "Creating additional databases using default user ($CURRENT_PG_USER) and database ($CURRENT_PG_DB)."
            create_additional_db $ADD_DB_NAME
        fi
    done
fi
