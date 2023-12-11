# Docker

- Content
  - [Common docker commands](#common-commands)
  - [Common docker info](#common-info)

## Common commands

| Description | Specification |
|-------------|:--------------|
| Open shell inside a container | `sudo docker exec -it container_name bash` <br /> `sudo docker exec -it container_name /bin/bash` |
| Copy file from local machine to container | `sudo docker cp [local_file_path] [container_name]:[container_file_path]` |
| Read IPs file inside a UNIX-based container | `sudo docker exec container_name cat /etc/hosts` |
| Check daemon storage driver | `sudo docker info \| grep 'Storage Driver' -i` |
| Print container configuration directory (overlay2) | `echo /var/lib/docker/containers/$(sudo docker ps -af "name=[container_name]" -q --no-trunc)` |
| List files on container configuration directory (overlay2) | `sudo ls -ahl /var/lib/docker/containers/$(sudo docker ps -af "name=[container_name]" -q --no-trunc)` |
| List files on container configuration directory (snap) | `sudo ls -ahl /var/snap/docker/common/var-lib-docker/containers/$(sudo docker ps -af "name=[container_name]" -q --no-trunc)` |
| Browse to the container configuration directory (overlay2) | `cd /var/lib/docker/containers/$(sudo docker ps -af "name=container_name" -q --no-trunc)` |
| Remove all stopped containers, all unused networks, all unused images and all build cache  | `sudo docker system prune -a` |


## Common info

| Description | Specification |
|-------------|:--------------|
| Container configuration directory for overlay2 storage driver | */var/lib/docker/containers/[container_id]* |
| Container configuration directory for daemon installation through snap | */var/snap/docker/common/var-lib-docker/containers/[container_id]* |
| Container file configuration | */var/lib/docker/containers/[container_id]/hostconfig.json* |
| Padrão de caminho no host para mapeamento de volumes | *~/projects/docker/[name]/volumes/[container_name]/[container_directory]* |
