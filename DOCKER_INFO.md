# Docker

- Content
  - [Common commands](#common-commands)
    - [Utility](#utility)
    - [Direct Docker commands](#direct-docker-commands)
  - [Common info](#common-info)
    - [General information](#general-information)
    - [Filter clauses for docker ps](#filter-clauses-for-docker-ps)
    - [Formatting markers for docker ps](#formatting-markers-for-docker-ps)
    - [Procedures](#procedures)

## Common commands
[[Top]](#)<br />

> You may need sudo access or administration rights from your terminal while running some commands

### Utility

| Description | Specification |
|-------------|:--------------|
| Print container configuration directory (overlay2) | `echo /var/lib/docker/containers/$(docker ps -af "name=[container_name]" -q --no-trunc)` |
| List files on container configuration directory (overlay2) | `ls -ahl /var/lib/docker/containers/$(docker ps -af "name=[container_name]" -q --no-trunc)` |
| List files on container configuration directory (snap) | `ls -ahl /var/snap/docker/common/var-lib-docker/containers/$(docker ps -af "name=[container_name]" -q --no-trunc)` |
| Browse to the container configuration directory (overlay2) | `cd /var/lib/docker/containers/$(docker ps -af "name=[container_name]" -q --no-trunc)` |

### Direct Docker commands

| Description | Specification |
|-------------|:--------------|
| Copy file from local machine to container | `docker cp [local_file_path] [container_name]:[container_file_path]` |
| Copy file from container to local machine | `docker cp [container_name]:[container_file_path] [local_file_path]` |
| Open bash terminal inside a container | `docker exec -it [container_name] bash` <br /> `docker exec -it [container_name] /bin/bash`|
| Open ash terminal inside a container | `docker exec -it [container_name] ash` <br /> `docker exec -it [container_name] /bin/ash`|
| Open sh terminal inside a container | `docker exec -it [container_name] sh` <br /> `docker exec -it [container_name] /bin/sh`|
| Read IPs file inside a UNIX-based container | `docker exec -it [container_name] bash -c 'cat /etc/hosts'` |
| Check daemon storage driver | `docker info \| grep 'Storage Driver' -i` |
| Lists all images | `docker images` |
| Remove all stopped containers, all unused networks, all unused images and all build cache  | `docker system prune -a` |
| Remove untagged images | `docker rmi -f $(docker images -q -f "dangling=true") 2> /dev/null` <br /> `docker images -q -f "dangling=true" \| xargs --no-run-if-empty docker rmi -f` |

## Common info
[[Top]](#)<br />

### General information

| Description | Specification |
|-------------|:--------------|
| Container configuration directory for overlay2 storage driver | */var/lib/docker/containers/[container_id]* |
| Container configuration directory for daemon installation through snap | */var/snap/docker/common/var-lib-docker/containers/[container_id]* |
| Container file configuration | */var/lib/docker/containers/[container_id]/hostconfig.json* |
| Padrão de caminho no host para mapeamento de volumes | *~/projects/docker/[name]/volumes/[container_name]/[container_directory]* |

### Filter clauses for docker ps

| Description | Specification |
|-------------|:--------------|
| *id* | Container ID. |
| *name* | Container name. |
| *label* | An arbitrary string representing either a key or a key-value pair. Expressed as *key* or *key=value*. |
| *exited* | An integer representing the container's exit (or stop) code. Only works when used with *--all*. |
| *status* | One of these *created*, *restarting*, *running*, *removing*, *paused*, *exited* or *dead*. |
| *ancestor* | Filters containers that share a given image as a base (parent, ancestor). Expressed as *image_name[:tag],* *image_id*, or *image@digest*. |
| *before* or *since* | Filters containers created before or after a given container ID or name. |
| *volume* | Filters running containers that have a given volume or mount related. |
| *network* | Filters running containers connected to a given network. |
| *publish* or *expose* | Filters containers that publish or expose a given port. Expressed as *port[/proto]* or *startport-endport/[proto]*. |
| *health* | Filters containers based on their health checks. One of these *starting*, *healthy*, *unhealthy* or *none*. |
| *isolation* | Windows daemon only. One of these *default*, *process* or *hyperv*. |
| *is-task* | Filters containers that are a task for a service. Boolean option (true or false). |

### Formatting markers for docker ps

| Description | Specification |
|-------------|:--------------|
| *.ID* | Container ID. |
| *.Image* | Image ID. |
| *.Command* | Quoted command. |
| *.CreatedAt* | Date and time when the container was created. |
| *.RunningFor* | Elapsed time since the container started. |
| *.Ports* | Exposed ports. |
| *.State* | Container status (e.g., *created*, *running*, *exited*). |
| *.Status* | Container status with details about duration and health status. |
| *.Size* | Disk size of the container. |
| *.Names* | Names of the containers. |
| *.Labels* | All labels assigned to the container. |
| *.Label* | Value of a specific label for the container (e.g., *'{{.Label "com.docker.swarm.cpu"}}'*). |
| *.Mounts* | Names of the volumes mounted on this container. |
| *.Networks* | Names of the networks attached to this container. |

### Procedures

#### Changing Port Mapping of an Existing Container

##### Quick Procedure

- Prepare the environment so that it is possible to restart the Docker service
- Navigate to the container's configuration directory:
  - `cd /var/lib/docker/containers/$(docker ps -af "name=[container_name]" -q --no-trunc)`
- Create a backup of the *hostconfig.json* file: `cp hostconfig.json bkp_hostconfig.json`
- Modify the *PortBindings* property in the *hostconfig.json* file: `nano hostconfig.json`
  - `"PortBindings":{"container_port/tcp":[{"HostIp":"host_address","HostPort":"host_port"}]}`
    - *container_port*: Port of the container to be mapped to the host
    - *host_address*: Host IP address or empty (default) for localhost
    - *host_port*: Host port that will receive this mapping from the container
    - Example: `"PortBindings":{"3306/tcp":[{"HostIp":"","HostPort":"3307"}]}` maps the container's port *3306* to the host's port *3307*
- Save the file
- Restart the Docker service: `systemctl restart docker`

##### Complete Procedure

- Prepare the environment so that it is possible to restart the Docker service
- List the files in the container's configuration directory to ensure the directory is correct
  - For *overlay2* storage driver: `ls -ahl /var/lib/docker/containers/$(docker ps -af "name=[container_name]" -q --no-trunc)`
  - For installations via *snap*: `ls -ahl /var/snap/docker/common/var-lib-docker/containers/$(docker ps -af "name=[container_name]" -q --no-trunc)`
- Navigate to the container's configuration directory
  - For *overlay2* storage driver: `cd /var/lib/docker/containers/$(docker ps -af "name=[container_name]" -q --no-trunc)`
  - For installations via *snap*: `cd /var/snap/docker/common/var-lib-docker/containers/$(docker ps -af "name=[container_name]" -q --no-trunc)`
  - To find your storage driver: *docker info | grep 'Storage Driver' -i*
  - If permission is denied to navigate to the directory
    - *UNIX-like OS*: Elevate your status to root with the *sudo -i* command; you need to know the root user's password
    - *Windows*: Run a terminal with administrator rights
- Check if the *hostconfig.json* file is in the directory: *ls -al*
  - If it does not exist, it is necessary to restart the steps or perform another procedure to locate it, as this is the file that needs to be edited
- It is recommended to create a backup of the *hostconfig.json* file: `cp hostconfig.json bkp_hostconfig.json`
- Modify the *PortBindings* property in the *hostconfig.json* file: `nano hostconfig.json`
  - `"PortBindings":{"container_port/tcp":[{"HostIp":"host_address","HostPort":"host_port"}]}`
    - *container_port*: Port of the container to be mapped to the host.
    - *host_address*: Host IP address or empty (default) for localhost.
    - *host_port*: Host port that will receive this mapping from the container.
    - Example: `"PortBindings":{"3306/tcp":[{"HostIp":"","HostPort":"3307"}]}` maps the container's port *3306* to the host's port *3307*.
- After editing, save the file.
  - *UNIX-like OS*: If logged in as root, log out: *exit*.
  - Restart the Docker service to clear the configuration cache and update it: `systemctl restart docker`.
