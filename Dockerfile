FROM openjdk:8-jre-alpine

# App exposed ports
EXPOSE 9058

# Create underprivileged user for running app
RUN adduser -D -h /app app
# Create the application workdir
WORKDIR /app
# Run as underprivileged user
USER app

# Add Application and dependencies
COPY target/coding-electricity-consumption-0.0.1-SNAPSHOT.jar .
COPY target/config/application.properties .


# Start Application on container startup
ENTRYPOINT ["/sbin/tini", "--"]
CMD ["java", "-Djava.security.egd=file:/dev/./urandom","-jar", "coding-electricity-consumption-0.0.1-SNAPSHOT.jar"]
