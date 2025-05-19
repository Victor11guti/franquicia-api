# Franquicia API

API REST para la gestión de franquicias. Desarrollada con Spring Boot WebFlux, MongoDB Atlas, documentada con Swagger y desplegada usando Docker en una instancia EC2 gratuita de AWS.

---

##  Tecnologías

-  Spring Boot 3 + WebFlux  
-  MongoDB Atlas (Free Cluster)  
-  Docker + Docker Compose  
-  Maven Wrapper  
-  Swagger UI con SpringDoc  
-  AWS EC2 (Amazon Linux)  

---

# Estructura del proyecto

```
franquicia-api/
├── .mvn/
├── infra/                  # Infraestructura MongoDB Atlas vía Terraform
├── src/
│   ├── main/java/com/accenture/franquicia/
│   └── resources/
├── Dockerfile
├── docker-compose.yml
├── application.properties
├── pom.xml
└── README.md


---

##  Configuración

###  Variables necesarias

La conexión se realiza con una URI de MongoDB Atlas. Define:

```properties
spring.data.mongodb.uri=${MONGO_URI}
```

Ejemplo:

```
MONGO_URI=mongodb+srv://franquicia_db:<PASSWORD>@franquicia-project.feiat59.mongodb.net/franquicia_db
```

---

## Ejecución local

```bash
# Compilar sin tests
./mvnw clean package -DskipTests

# Levantar en Docker
docker-compose up --build
```

Accede a:
- Swagger UI: `http://localhost:8080/swagger-ui.html`

---



## Infraestructura (Terraform)

```bash
cd infra
terraform init
terraform apply
```

Esto crea:
- Proyecto en MongoDB Atlas
- Usuario + cluster
- Configuración de IP Whitelist


## Pruebas

```bash
./mvnw test
```

Frameworks:
- JUnit 5
- Mockito
- Reactor Test

---

## Link despliegue en Aws con ec2 
- http://184.72.195.35:8080/webjars/swagger-ui/index.html#/

##  Autor

Desarrollado como parte de una prueba técnica.

-  Victor Gutiérrez  

---

##  Resultado

Proyecto completo funcional y desplegado en la nube (AWS EC2 + Docker + Mongo Atlas).

---
