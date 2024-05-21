1- Build the Project:
./mvnw clean package

2- Build and Run the Docker Containers:
docker-compose up --build




Steps to Deploy

    Create the ConfigMap and Secret:
    kubectl apply -f k8s-postgres-configmap.yaml
kubectl apply -f k8s-postgres-secret.yaml
Deploy PostgreSQL:
kubectl apply -f k8s-postgres-deployment.yaml
kubectl apply -f k8s-postgres-service.yaml
Deploy the Spring Boot Application:
Ensure you have built and pushed your Docker image to a Docker registry (e.g., Docker Hub) before applying the deployment.
kubectl apply -f k8s-app-deployment.yaml
kubectl apply -f k8s-app-service.yaml
