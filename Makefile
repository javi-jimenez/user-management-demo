all: run

run:
	./mvnw spring-boot:run

test-endpoint:
	curl http://localhost:8080/api/users/first/2025/ES

