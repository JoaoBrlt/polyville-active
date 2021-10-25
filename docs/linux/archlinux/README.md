# PolyVille Active: Archlinux

## I. Requirements

1. Download this repository, and place it somewhere on your computer.
2. Install `Docker Engine` and `Docker Compose`.
```bash
sudo pacman -S docker docker-compose
```
3. Add `docker` group to your user.
```bash
sudo usermod -aG docker $USER
```
4. Reboot your computer.

## II. (Option 1) Run the entire project

1. Start `Docker Engine`.
```bash
sudo systemctl start docker
```
2. Navigate to the root of this repository on your computer.
3. Build and run the project with `Docker Compose`.
```bash
docker-compose up --build
```
4. Wait for `Docker` to do your job (~5min).
5. Enjoy the project!

Client: `localhost:4200`<br>
Server: `localhost:8080`<br>
Database: `localhost:5432`

## II. (Option 2) Run the database only

1. Start `Docker Engine`.
```bash
sudo systemctl start docker
```
2. Navigate to the root of this repository on your computer.
3. Run the database with `Docker Compose`.
```bash
docker-compose up postgres
```
4. Wait for `Docker` to do your job (~1min).
5. Enjoy the database!

Host: `localhost:5432`<br>
Database: `ps7_database`<br>
Username: `ps7_user`<br>
Password: `ps7_password`

## III. Stop the project

1. Press `Ctrl+C` to stop all containers.
2. Wait for `Docker` to stop all containers.
3. Remove all the containers.
```bash
docker-compose down
```
