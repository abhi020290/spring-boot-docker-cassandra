#!bin/bash

clear
echo "Starting dockerize for spring boot application"
echo "Clearing  and removing old containers"
docker stop ${docker ps -aq}
docker rm ${docker ps -aq}

echo "Perform maven build"
./mvnw clean install -DskipTests

echo "Starting up container"
docker-compose build
docker-compose up -d
