FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/transactions-1.0.jar ./
COPY target/classes ./classes/
COPY target/generated-sources ./generated-sources/

EXPOSE 8080

ENV SPRING_PROFILES_ACTIVE=dev

CMD ["java", "-cp", ".:./classes:./generated-sources", "-jar", "transactions-1.0.jar"]
