FROM gradle:7.4.2-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build -x test

FROM openjdk:17
ENV APP_HOME=/usr/mancala/
WORKDIR $APP_HOME
VOLUME /mancala
COPY --from=build /home/gradle/src/build/libs/*.jar mancala.jar
CMD ["java", "-jar", "mancala.jar"]
EXPOSE 8080