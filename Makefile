.PHONY: integration-up integration-down db-up db-down db-soft-reset db-hard-reset

integration-up:
	docker compose --env-file .env.integration -f docker-compose.integration.yml up -d

integration-down:
	docker compose --env-file .env.integration -f docker-compose.integration.yml down

db-up:
	docker compose --env-file .env.integration -f docker-compose.db.yml up -d

db-down:
	docker compose --env-file .env.integration -f docker-compose.db.yml down

db-soft-reset:
	docker compose --env-file .env.integration -f docker-compose.db.yml down
	docker compose --env-file .env.integration -f docker-compose.db.yml up -d

db-hard-reset:
	docker compose --env-file .env.integration -f docker-compose.db.yml down -v
	docker compose --env-file .env.integration -f docker-compose.db.yml up -d