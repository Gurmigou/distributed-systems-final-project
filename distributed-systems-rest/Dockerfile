FROM openjdk:17-alpine3.14
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} ds-project.jar
ENTRYPOINT ["java","-jar","/ds-project.jar"]