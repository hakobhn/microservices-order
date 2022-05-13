#!/usr/bin/env bash

VERSION=$1
VERSION=${VERSION:-"1.0.0"}
cd ../../
cd apache
docker build -t hakobhn/microservice-order-apache:${VERSION} .  && cd ../
cd invoicing-microservice
docker build -t hakobhn/microservice-order-invoicing:${VERSION} .  && cd ../
cd ordering-microservice
docker build -t hakobhn/microservice-order-ordering:${VERSION} . && cd ../
cd shipping-microservice
docker build -t hakobhn/microservice-order-shipping:${VERSION} .

docker push hakobhn/microservice-order-apache:${VERSION}
docker push hakobhn/microservice-order-invoicing:${VERSION}
docker push hakobhn/microservice-order-ordering:${VERSION}
docker push hakobhn/microservice-order-shipping:${VERSION}