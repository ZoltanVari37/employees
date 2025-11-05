# Use an official Maven image as the base image
FROM eclipse-temurin:17 as build

COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .
COPY src src

RUN --mount=type=cache,target=/root/.m2,rw ./mvnw -B package 

FROM eclipse-temurin:17
COPY --from=build target/employees-1.0.0.jar .

ENTRYPOINT ["/bin/bash"]