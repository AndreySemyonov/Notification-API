FROM openjdk:8
EXPOSE 8989
ADD target/gateway.jar gateway.jar
ENTRYPOINT ["java","-jar", "/gateway.jar"]