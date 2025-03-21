FROM openjdk:17
WORKDIR /app
COPY target/social-media-0.0.1-SNAPSHOT.jar social-media.jar
COPY keystore.p12 keystore.p12
EXPOSE 8443
CMD ["java", "-jar", "social-media.jar"]
