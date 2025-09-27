# Etapa 1: Build con Maven
FROM maven:3.9.9-eclipse-temurin-17 AS build

# Directorio de trabajo
WORKDIR /app

# Copiar los archivos de Maven
COPY pom.xml mvnw ./
COPY .mvn .mvn

# Copiar el código fuente
COPY src ./src

# Compilar el proyecto y generar el JAR (sin tests)
RUN ./mvnw clean package -DskipTests

# Etapa 2: Runtime con JDK
FROM eclipse-temurin:17-jdk

# Directorio de trabajo
WORKDIR /app

# Copiar el JAR generado desde la etapa build
COPY --from=build /app/target/*.jar app.jar

# Exponer puerto de Spring Boot
EXPOSE 8080

# Comando de inicio
ENTRYPOINT ["java","-jar","app.jar"]
