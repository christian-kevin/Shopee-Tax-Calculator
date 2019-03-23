FROM maven:3.5.4-jdk-10-slim
WORKDIR /shopee-app/code

COPY ./shopee-app/pom.xml ./pom.xml

RUN mvn dependency:go-offline -B

COPY ./shopee-app ./

RUN mvn package -DskipTests

WORKDIR /shopee-app/app
RUN cp /shopee-app/code/target/*.jar ./app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar","-agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n"]