#!/bin/bash

dir=`dirname $0`
cd $dir


# build project
cd ..
mvn clean package

cd $dir
rm -rf ./app-service.war
cp ../target/app-service.war ./

# rm container and image
docker kill app-service-container 
docker rm app-service-container 
docker rmi app-service:1.0.0

# build new image
docker build . -t app-service:1.0.0
docker images -f "reference=app-service:1.0.0"

# run new container
docker run --env-file ./prod.env -d -p 9080:9080 -p 9443:9443 --name app-service-container app-service:1.0.0 
docker ps -f "name=app-service-container"


exit 0

