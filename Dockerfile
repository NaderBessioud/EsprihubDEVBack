FROM openjdk:8-jdk-alpine
RUN yum update -y && yum install -y ftp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} esprithub.jar
ENTRYPOINT ["java","-jar","/esprithub.jar"]
EXPOSE 8082
