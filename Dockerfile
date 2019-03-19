FROM maven:3.5.4-jdk-10-slim
WORKDIR /shopee-app/code
COPY ./shopee-app /shopee-app/code/
RUN mvn package -DskipTests

WORKDIR /shopee-app/app
RUN cp /shopee-app/code/target/*.jar ./app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]