#                                 __                 __
#    __  ______  ____ ___  ____ _/ /____  ____  ____/ /
#   / / / / __ \/ __ `__ \/ __ `/ __/ _ \/ __ \/ __  /
#  / /_/ / /_/ / / / / / / /_/ / /_/  __/ /_/ / /_/ /
#  \__, /\____/_/ /_/ /_/\__,_/\__/\___/\____/\__,_/
# /____                     matthewdavis.io, holla!
#
include .make/Makefile.inc

NS		?= default
VERSION	?= $(shell git rev-parse HEAD)
APP		?= k8specs-platform-api
IMAGE	?= gcr.io/matthewdavis-devops/$(APP):$(VERSION)

.PHONY: build

all: build push

build:

	./gradlew bootJar
	docker build -t $(IMAGE) .

run:

	docker run -p 8080:8080 $(IMAGE)

push:

	docker push $(IMAGE)

k8/install:

	kubectl apply -f manifests/

k8/delete:

	kubectl delete -f manifests/
