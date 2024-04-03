FROM openjdk:23-slim

WORKDIR /app

COPY target/book_RestApi-0.0.1-SNAPSHOT.jar /app/book_RestApi-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "book_RestApi-0.0.1-SNAPSHOT.jar"]