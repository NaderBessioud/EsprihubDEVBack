FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} esprithub.jar
ENTRYPOINT ["java","-jar","/esprithub.jar"]
EXPOSE 8082
