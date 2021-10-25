# PolyVille Active: Windows Pro

## I. Requirements

1. Download this repository, and place it somewhere on your computer.
2. Download the latest [Docker Desktop for Windows](https://hub.docker.com/editions/community/docker-ce-desktop-windows/) release.
3. Install `Docker Desktop for Windows`.
4. Keep the default configuration during the installation.
5. Reboot your computer.

## II. (Option 1) Run the entire project

1. Open `Docker Desktop for Windows`.
2. When `Docker Desktop` is ready, you will receive a notification.
3. Open `Windows PowerShell`.
4. Navigate to the root of this repository on your computer.
5. Build and run the project with `Docker Compose`.
```bash
docker-compose up --build
```
6. When you are asked to share the access to the `C:\.` drive, press the `Share it` button.
7. Wait for `Docker` to do your job (~5min).
8. Enjoy the project!

Client: `localhost:4200`<br>
Server: `localhost:8080`<br>
Database: `localhost:5432`

## II. (Option 2) Run the database only

1. Open `Docker Desktop for Windows`.
2. When `Docker Desktop` is ready, you will receive a notification.
3. Open `Windows PowerShell`.
4. Navigate to the root of this repository on your computer.
5. Run the database with `Docker Compose`.
```bash
docker-compose up postgres
```
6. When you are asked to share the access to the `C:\.` drive, press the `Share it` button.
7. Wait for `Docker` to do your job (~1min).
8. Enjoy the database!

Host: `localhost:5432`<br>
Database: `ps7_database`<br>
Username: `ps7_user`<br>
Password: `ps7_password`

## III. Stop the project

1. Press `Ctrl+C` to stop all containers.
2. Wait for Docker to stop all containers.
3. Remove all the containers.
```bash
docker-compose down
```
