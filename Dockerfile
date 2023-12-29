FROM openjdk:8
VOLUME /tmp
EXPOSE 8024
ADD ./target/spring-boot-webflu-ms-op-credito-0.0.1-SNAPSHOT.jar ms-op-credito.jar
ENTRYPOINT ["java","-jar","/ms-op-credito.jar"]