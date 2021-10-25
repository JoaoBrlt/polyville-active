# Build the client.
FROM node:lts-slim AS build
WORKDIR /app
COPY package.json package-lock.json /app/
RUN npm install --silent --no-progress --no-audit --no-optional --no-fund
COPY . /app

# Run the client.
EXPOSE 4200
ENTRYPOINT ["npm", "start"]
