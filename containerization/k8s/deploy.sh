#!/bin/bash

kubectl delete --all deployments --namespace ordering
kubectl delete --all services --namespace ordering
kubectl delete --all pods --namespace ordering

# Create namespace
kubectl create -f ./namespace/namespace.yaml
#kubectl config set-context ordering --namespace=ordering-cluster --cluster=ordering --user=admin
#kubectl config use-context ordering

# Create ingress
#kubectl apply -f ./ingress/httpd.yaml
kubectl apply -f ./ingress/ingress.yaml


# Create db
kubectl apply -f ./postgres/secrets.yaml
kubectl apply -f ./postgres/volume.yaml
kubectl apply -f ./postgres/postgres-deployment.yaml
#kubectl port-forward service/postgres 5432:5432 -n ordering


# Create messaging
kubectl apply -f ./zookeeper/zookeeper-deployment.yaml
kubectl apply -f ./kafka/kafka-deployment.yaml


# Create microservices
kubectl apply -f ./invocing/ms-deployment.yaml
kubectl apply -f ./order/ms-deployment.yaml
kubectl apply -f ./shipping/ms-deployment.yaml
