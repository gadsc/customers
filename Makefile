dd:
	@echo running dev-down and dev-bootstrap
	@$(MAKE) dev-down
	@$(MAKE) dev-bootstrap

UNAME := $(shell uname)
DATASOURCE_URL = ""
PARAMETER_STORE_ENABLED = false

ifeq ($(UNAME), Linux)
    export DATASOURCE_URL = customers_postgres
else
    export DATASOURCE_URL = host.docker.internal
endif

local-without-application: ## Executa local environment without application
	@$(MAKE) dev-down
	@docker-compose up -d postgres localstack elasticsearch
	@sleep 3
	docker exec -it customers_postgres psql -U postgres -X -c "create database customers_test"
	@docker ps

dev-down:  ## Destroy all dependencies
	@docker-compose down --v --remove-orphans
	@docker ps
