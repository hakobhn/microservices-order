#!/bin/bash

echo "Deploying to docker"
$DOCKER_USER="hakobhn"
$DOCKER_PASS="***"
$TAG="1.0.0"

echo "Check docker version"
docker --version

echo "Login to dockerhub"
docker login -u $DOCKER_USER -p $DOCKER_PASS
# docker login -u hakobhn

export REPO=hakobhn/microservice-order-apache

echo "Build docker image"
docker build -f Dockerfile -t $REPO:$TAG .
# docker build -f Dockerfile -t hakobhn/microservice-order-apache:1.0.0 .
echo "Tag docker images as latest"
docker tag $REPO:$TAG $REPO:latest
# docker tag hakobhn/microservice-order-apache:1.0.0 hakobhn/microservice-order-apache:latest
echo "Push to dockerhub"
docker push $REPO:$TAG
# docker push hakobhn/microservice-order-apache:1.0.0
docker push $REPO:latest