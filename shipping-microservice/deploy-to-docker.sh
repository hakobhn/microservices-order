#!/bin/bash

echo "Deploying to docker"
$DOCKER_USER="hakobhn"
$DOCKER_PASS="Piramidananoelq1980"
$TAG="1.0.0"

echo "Check docker version"
docker --version

echo "Login to dockerhub"
docker login -u $DOCKER_USER -p $DOCKER_PASS
# docker login -u hakobhn -p Piramidananoelq1980

export REPO=hakobhn/microservice-order-shipping

echo "Build docker image"
docker build -f Dockerfile -t $REPO:$TAG .
# docker build -f Dockerfile -t hakobhn/microservice-order-shipping:1.0.0 .
echo "Tag docker images as latest"
docker tag $REPO:$TAG $REPO:latest
# docker tag hakobhn/microservice-order-shipping:1.0.0 hakobhn/microservice-order-shipping:latest
echo "Push to dockerhub"
docker push $REPO:$TAG
# docker push hakobhn/microservice-order-shipping:1.0.0
docker push $REPO:latest