FROM maven:3.6.3-ibmjava-8-alpine AS appServer
WORKDIR /usr/src/auth
COPY . .
RUN ["mvn", "package", "-DskipTests"]

FROM openjdk:8-jdk-alpine
COPY entrypoint.sh /entrypoint.sh
COPY --from=appServer /usr/src/auth/target/rent-service-0.0.1-SNAPSHOT.jar rent-service-0.0.1-SNAPSHOT.jar
RUN chmod +x /entrypoint.sh
CMD ["/entrypoint.sh"]