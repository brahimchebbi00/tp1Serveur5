FROM openjdk:17-jdk-slim-buster
EXPOSE 8040
ADD target/Server.jar Server.jar
ENTRYPOINT ["java","-jar","/Server.jar"]