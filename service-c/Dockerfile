# docker pull adoptopenjdk/openjdk11
FROM adoptopenjdk/openjdk11:latest

ARG JAR_FILE=target/service-c-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
#CMD ["echo", "Hello World"]

#docker run -it  -p 8080:8080 my-tests/service-c /bin/bash