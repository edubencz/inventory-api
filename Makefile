DOCKER_COMPOSE_FILE=./docker/docker-compose.yml
CONTAINER=inventory-api

build:
	mvn clean install -DskipTests

start:
	docker-compose -f $(DOCKER_COMPOSE_FILE) up --build

down:
	docker-compose -f $(DOCKER_COMPOSE_FILE) down
	make _remove_inventory_image

_remove_inventory_image:
	docker rmi $(CONTAINER)

test:
	mvn test
