FROM openjdk:8

LABEL maintainer='zhizhufan@foxmail.com'

COPY main/target/main.jar /app.jar

EXPOSE 80

ENTRYPOINT ["java","-jar","/app.jar"]
