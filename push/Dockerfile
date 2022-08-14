FROM openjdk:8
EXPOSE 8082
ADD target/push.jar push.jar
ENTRYPOINT ["java","-jar", "/push.jar"]