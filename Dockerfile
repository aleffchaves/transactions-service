FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/transactions-1.0.jar ./

CMD ["java", "-jar", "transactions-1.0.jar"]
