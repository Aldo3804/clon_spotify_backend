# ====== STAGE 1: Construcción ======
FROM eclipse-temurin:21-jdk AS builder
LABEL authors="Lenovo LOQ"

WORKDIR /app

# Copiamos los archivos necesarios
COPY . .

# Damos permisos al wrapper (necesario en Linux)
RUN chmod +x gradlew

# Construimos el .jar (sin tests)
RUN ./gradlew clean bootJar -x test

# ====== STAGE 2: Imagen final ======
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copiamos el jar compilado
COPY --from=builder /app/build/libs/*.jar app.jar

# Exponemos el puerto
EXPOSE 8080

# Arrancamos el backend
ENTRYPOINT ["java", "-jar", "app.jar"]
