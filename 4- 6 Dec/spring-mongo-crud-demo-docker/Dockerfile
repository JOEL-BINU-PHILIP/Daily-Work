FROM eclipse-temurin:17-jdk AS build
WORKDIR /mongo_crud_demo
COPY . .
RUN ./mvnw -DskipTests clean package

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /mongo_crud_demo/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
