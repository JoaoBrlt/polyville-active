# Build the server.
FROM maven:3.6.3-openjdk-11-slim AS build
WORKDIR /app
COPY . /app
RUN ./build.sh

# Package the server.
FROM openjdk:11-jre-slim AS run
WORKDIR /app
COPY --from=build /app/dist /app

# Run the server.
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "core.jar"]
