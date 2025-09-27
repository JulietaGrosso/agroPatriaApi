# Etapa 1: Build
FROM maven:3.9.9-eclipse-temurin-17 AS build

# Definir directorio de trabajo
WORKDIR /app

# Copiar el código fuente (la carpeta agropatriaapi) al contenedor
COPY agropatriaapi ./agropatriaapi

# Entrar en la carpeta del proyecto y compilar el jar
WORKDIR /app/agropatriaapi
RUN mvn clean package -DskipTests

# Etapa 2: Runtime
FROM eclipse-temurin:17-jdk

# Directorio de trabajo
WORKDIR /app

# Copiar el jar compilado desde la etapa anterior
COPY --from=build /app/agropatriaapi/target/*.jar app.jar

# Exponer el puerto (Spring Boot usa 8080 por defecto)
EXPOSE 8080

# Comando de inicio
ENTRYPOINT ["java","-jar","app.jar"]
