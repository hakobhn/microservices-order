#!/bin/bash

kubectl delete --all deployments --namespace ordering
kubectl delete --all services --namespace ordering
kubectl delete --all pods --namespace ordering

# Create namespace
kubectl create -f ./namespace/namespace.yaml
#kubectl config set-context ordering --namespace=ordering-cluster --cluster=ordering --user=admin
#kubectl config use-context ordering

# Create ingress
kubectl apply -f ./ingress/httpd.yaml
kubectl apply -f ./ingress/ingress.yaml


# Create db
kubectl apply -f ./postgres/secrets.yaml
docker volume create postgres-pv
kubectl apply -f ./postgres/volume.yaml
kubectl apply -f ./postgres/deployment.yaml


# Create messaging
kubectl apply -f ./zookeeper/zookeeper-deployment.yaml
kubectl apply -f ./kafka/kafka-deployment.yaml


# Create microservices
kubectl apply -f ./invocing/ms-deployment.yaml
kubectl apply -f ./ordering/ms-deployment.yaml
kubectl apply -f ./shipping/ms-deployment.yaml
