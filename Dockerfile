FROM openjdk:8-jdk-alpine
RUN /usr/bin/yum update -y && /usr/bin/yum install -y ftp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} esprithub.jar
ENTRYPOINT ["java","-jar","/esprithub.jar"]
EXPOSE 8082
