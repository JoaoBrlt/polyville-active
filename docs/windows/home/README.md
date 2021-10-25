# PolyVille Active: Windows Home

## Warning
Windows Home is by far the hardest to use for Docker.
Please consider, using the Windows Subsystem for Linux with Ubuntu and following the installation guide for Ubuntu.
However, if you follow along, there will be no problem!

## I. Requirements

1. Download this repository, and place it somewhere on your computer.
2. Download the latest [Virtualbox](https://www.virtualbox.org/wiki/Downloads) release (6.0.0+).
3. Download the latest [Docker Toobox](https://github.com/docker/toolbox/releases) release (19.0.0+).

## II. Installation

1. Install `Virtualbox`.
2. Install `Docker Toobox`.
3. Disable `Virtualbox` during the installation of `Docker Toobox`.
4. Open `Docker Quickstart Terminal`.
5. Wait for `Docker Toolbox` to install all dependencies.
6. Close `Docker Quickstart Terminal`.
7. Open `Virtualbox`, and configure the virtual machine called `default`.
8. Navigate to `Network` > `Advanced`, and select `Port forwarding`.
9. Add two port forwarding rules.
```
Name: client
Protocol: TCP
Host IP: 127.0.0.1
Host Port: 4200
Guest Port: 4200
```
```
Name: server
Protocol: TCP
Host IP: 127.0.0.1
Host Port: 3000
Guest Port: 3000
```
10. (optional but recommended) Allocate more memory.
11. (optional but recommended) Allocate more CPU cores.

## III. (Option 1) Run the entire project

1. Re-open `Docker Quickstart Terminal`.
2. Navigate to the root of this repository on your computer.
3. Enable conversion from Windows paths to Unix paths.
```
export COMPOSE_CONVERT_WINDOWS_PATHS=1
```
4. Build and run the project with `Docker Compose`.
```bash
docker-compose up --build
```
NB: If Docker fails, restart the terminal and rerun the command.

5. Wait for `Docker Toolbox` to do your job (~10min).
6. Enjoy the project!

Client: `localhost:4200`<br>
Server: `localhost:8080`<br>
Database: `localhost:5432`

## III. (Option 2) Run the database only

1. Re-open `Docker Quickstart Terminal`.
2. Navigate to the root of this repository on your computer.
3. Enable conversion from Windows paths to Unix paths.
```
export COMPOSE_CONVERT_WINDOWS_PATHS=1
```
4. Run the database with `Docker Compose`.
```bash
docker-compose up postgres
```
NB: If Docker fails, restart the terminal and rerun the command.

5. Wait for `Docker Toolbox` to do your job (~1min).
6. Enjoy the database!

Host: `localhost:5432`<br>
Database: `ps7_database`<br>
Username: `ps7_user`<br>
Password: `ps7_password`

## IV. Stop the project

1. Press `Ctrl+C` to stop all containers.
2. Wait for `Docker Toolbox` to stop all containers.
3. Remove all the containers.
```bash
docker-compose down
```