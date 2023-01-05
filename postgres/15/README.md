# postgres-15
- Reference: [PostgreSQL Official Docker Image](https://hub.docker.com/_/postgres)

This docker image allows you to create an PostgreSQL database with additional databases other than the default.

It's also possible to create user for each new addtional database with automatically access granted.

Based on the official PostgreSQL Official Docker Image.

## Environment variables

It uses all the default PostgreSQL environment variables with some additions, details in the following section(s).

### POSTGRES_ADDITIONAL_DBS

A *comma (,) separated list* identifying all the additional database(s) name(s).

- Running a container with the additional database name list specified:
```
docker run
    ... \
    -e POSTGRES_ADDITIONAL_DBS="additional_db,another_db" \
    -d rudiantoni/postgres:15
```

### POSTGRES_ADDITIONAL_DBS_USER
> Default value: false

A simple *boolean* environment variable which define different users for each additional database created when set to *true*.

If you set this to *true*, for each additional database will be created a new user with the same name of the database, and it will have all permissions to this database.

- Running a container with the additional database name list and the database dedicated user specified:
```
docker run
    ... \
    -e POSTGRES_ADDITIONAL_DBS="additional_db,another_db" \
    -e POSTGRES_ADDITIONAL_DBS_USER=true
    -d rudiantoni/postgres:15
```
- The database will contain the default database (not specified above), and two more databases: *additional_db* and *another_db*.
- Each database will be accessible for the default database user, and a dedicated user created with the same database name:
    - *additional_db* will be accessible for the default user and the user *additional_db* (newly created).
    - *another_db* will be accessible for the default user and the user *another_db* (newly created).

## Other examples

Since this image is based on the PostgreSQL official image, you can specify all the environment variables that the official one uses:

- Running a default postgres container with a additional database list specified:
```
docker run \
    --name pg-additional \
    -p 5432:5432 \
    -e POSTGRES_DB=postgres \
    -e POSTGRES_USER=postgres \
    -e POSTGRES_PASSWORD=postgres \
    -e POSTGRES_ADDITIONAL_DBS="another_db,even_other" \
    -d rudiantoni/postgres:15
```

If you want to use non-standard database names like hyphen (-), you need to escape double quotes at each name not including the commas (keep in mind that if you set **POSTGRES_ADDITIONAL_DBS_USER** to *true*, all the additional users will receive the non-standard name too):

- Running a default postgres container with a additional database list specified using non-standard names:
```
sudo docker run \
    --name pg-additional \
    -p 5432:5432 \
    -e POSTGRES_DB=postgres \
    -e POSTGRES_USER=postgres \
    -e POSTGRES_PASSWORD=postgres \
    -e POSTGRES_ADDITIONAL_DBS="\"another-db\",even_other" \
    -d rudiantoni/postgres:15
```