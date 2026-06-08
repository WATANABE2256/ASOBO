# Railway deployment (monorepo root)
FROM maven:3.9-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY asobo-web/pom.xml .
COPY asobo-web/src ./src
RUN mvn -q -DskipTests package

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/asobo-web-*.jar app.jar

ENV SPRING_PROFILES_ACTIVE=prod
ENV PORT=8080
EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java -Dserver.port=${PORT} -jar app.jar"]
