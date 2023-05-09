#Multi-stage docker file
# Build stage
FROM maven:3.8.3-openjdk-17-slim AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
WORKDIR /usr/src/app/
RUN mvn clean package -DskipTests
COPY target/*.jar /usr/src/app/sba-app.jar

# Deploy stage
FROM  openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /usr/src/app/sba-app.jar .
CMD ["java", "-jar", "sba-app.jar"]



#FROM eclipse-temurin:17-jdk-jammy
#COPY target/*.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]