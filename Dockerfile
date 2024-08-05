# Usa una imagen base con Maven y OpenJDK 17 para la etapa de construcción
FROM openjdk:17.0.1-slim-buster

# Establecer un directorio de trabajo
WORKDIR /app

# Copiar el archivo pom.xml y descargar las dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar el código fuente al directorio de trabajo
COPY src /app/src

# Ejecutar Maven para construir el proyecto
RUN mvn clean package -DskipTests

# Usar una imagen base con OpenJDK 17 para la etapa de ejecución
FROM openjdk:17-jdk-slim

# Exponer el puerto que utilizará la aplicación
EXPOSE 8889

# Copiar el archivo JAR construido desde la etapa de construcción
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar /app/demo-0.0.1-SNAPSHOT.jar

# Establecer el punto de entrada para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/demo-0.0.1-SNAPSHOT.jar"]