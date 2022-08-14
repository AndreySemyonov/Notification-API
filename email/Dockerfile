FROM openjdk:8
EXPOSE 8081
ADD target/email.jar email.jar
ENTRYPOINT ["java","-jar", "/email.jar"]