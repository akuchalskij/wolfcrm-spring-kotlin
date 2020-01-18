FROM openjdk:11-jdk

COPY /build/libs/application-0.0.1-SNAPSHOT.jar app.jar

ENV JAVA_OPTS=""
ENV ARGS=""

EXPOSE 8080

CMD java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar $ARGS