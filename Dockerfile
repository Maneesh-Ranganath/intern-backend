# --- Stage 1: Build Stage ---
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app

# Copy Maven wrapper and POM first to leverage layer caching
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

# Copy source code and build the executable JAR
COPY src ./src
RUN ./mvnw clean package -DskipTests

# --- Stage 2: Safe Runtime Stage ---
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Create a non-root system user for security
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copy the built JAR from stage 1
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]