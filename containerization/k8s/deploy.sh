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
kubectl apply -f ./postgres/volume.yaml
kubectl apply -f ./postgres/volume-claim.yaml
kubectl apply -f ./postgres/service.yaml --namespace=ordering
kubectl apply -f ./postgres/deployment.yaml --namespace=ordering


# Create messaging
kubectl apply -f ./zookeeper/zookeeper-services.yaml --namespace=ordering
kubectl apply -f ./zookeeper/zookeeper-deployment.yaml --namespace=ordering
kubectl apply -f ./kafka/kafka-service.yaml --namespace=ordering
kubectl apply -f ./kafka/kafka-deployment.yaml --namespace=ordering


# Create microservices
kubectl apply -f ./invocing/ms-deployment.yaml --namespace=ordering
kubectl apply -f ./ordering/ms-deployment.yaml --namespace=ordering
kubectl apply -f ./shipping/ms-deployment.yaml --namespace=ordering
