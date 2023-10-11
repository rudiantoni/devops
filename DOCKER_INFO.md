# Docker

## Default ports
- MongoDB (database): 27017
- Microsoft SQL Server (database): 1433
- MySQL (database): 3306
- Oracle Database (database): 1521
- PostgreSQL (database): 5432
- Redis (database): 6379
- Samba (file server): 139 e 445
- SFTP e SSH (communication): 22

## Common SQL/NoSQL database queries

### MySQL

| Description | Specification |
|-------------|:--------------|
| Check database version | `select version();` |

### Microsoft SQL Server

| Description | Specification |
|-------------|:--------------|
| Check database version | `SELECT @@VERSION;` |

### Oracle

| Description | Specification |
|-------------|:--------------|
| Check database version | `select * from v$version;` <br /> `select banner from v$version;` |

### PostgreSQL

| Description | Specification |
|-------------|:--------------|
| Check database version | `select version();` |
| Check database size | `SELECT pg_size_pretty(pg_database_size('[db_name]'));` |
| Check database table | `SELECT pg_size_pretty(pg_total_relation_size('[table_name]'));` |
| Reset a sequence | `ALTER SEQUENCE [sequence_name] RESTART WITH 1;` |
| Set sequence value | `SELECT setval('[sequence_name]', 20, true); -- Next value would be 21;` |
| Date format in query usage | `'yyyy-MM-dd HH:mm:ss.000'` |

### SQLite

| Description | Specification |
|-------------|:--------------|
| Check database version | `select sqlite_version();` |

## Common docker commands

| Description | Specification |
|-------------|:--------------|
| Open shell inside a container | `sudo docker exec -it container_name bash` <br /> `sudo docker exec -it container_name /bin/bash` |
| Copy file from local machine to container | `sudo docker cp [local_file_path] [container_name]:[container_file_path]` |
| Read IPs file inside a UNIX-based container | `sudo docker exec container_name cat /etc/hosts` |
| Check daemon storage driver | `sudo docker info | grep 'Storage Driver’ -i` |
| Print container configuration directory (overlay2) | `echo /var/lib/docker/containers/$(sudo docker ps -af "name=[container_name]" -q --no-trunc)` |
| List files on container configuration directory (overlay2) | `sudo ls -ahl /var/lib/docker/containers/$(sudo docker ps -af "name=[container_name]" -q --no-trunc)` |
| List files on container configuration directory (snap) | `sudo ls -ahl /var/snap/docker/common/var-lib-docker/containers/$(sudo docker ps -af "name=[container_name]" -q --no-trunc)` |
| Browse to the container configuration directory (overlay2) | `cd /var/lib/docker/containers/$(sudo docker ps -af "name=container_name" -q --no-trunc)` |

## Common docker info

| Description | Specification |
|-------------|:--------------|
| Container configuration directory for overlay2 storage driver | */var/lib/docker/containers/[container_id]* |
| Container configuration directory for daemon installation through snap | */var/snap/docker/common/var-lib-docker/containers/[container_id]* |
| Container file configuration | */var/lib/docker/containers/[container_id]/hostconfig.json* |
| Padrão de caminho no host para mapeamento de volumes | *~/projects/docker/[name]/volumes/[container_name]/[container_directory]* |
