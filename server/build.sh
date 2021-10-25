#!/bin/bash

# Create the dist folder.
rm -rf dist
mkdir -p dist/plugins

# Compile the server.
mvn clean package -DskipTests
if [[ "$?" -ne 0 ]] ; then
  echo 'Could build the server.'; exit 1
fi

# Copy the core.
cp core/target/core-*.zip dist/

# Copy the plugins.
for D in ./plugins/*; do
    if [ -d "${D}" ]; then
        cp ${D}/target/$(basename ${D})-*.jar dist/plugins/$(basename ${D}).jar
    fi
done
cp plugins/disabled.txt dist/plugins/

cd dist

# Unzip the core.
jar xf core-*.zip
rm core-*.zip
mv core-*.jar core.jar

cd -
