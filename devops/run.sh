#!/bin/bash

cd `dirname $0`
cp ../target/app-service.war ./
docker build . -t app-service:1.0.0
docker images -f "reference=app-service:1.0.0"
docker run -d -p 9080:9080 -p 9443:9443 --name app-service-container app-service:1.0.0
docker ps -f "name=app-service-container"


exit 0

