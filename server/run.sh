#!/bin/bash

# Build the server.
bash ./build.sh
if [[ "$?" -ne 0 ]] ; then
  exit 1
fi

cd dist

# Run the server.
java -jar core.jar

cd -
