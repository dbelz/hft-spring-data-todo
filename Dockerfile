FROM openjdk:8-alpine
RUN mkdir -p /opt/todo
WORKDIR /opt/todo
COPY target/hello-spring-data-0.0.1-SNAPSHOT.jar /opt/todo
CMD ["java", "-jar", "hello-spring-data-0.0.1-SNAPSHOT.jar"]