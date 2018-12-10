# use alpine as base image
FROM java:openjdk-8-jdk-alpine

# recommended by spring boot
VOLUME /tmp

# create directory for application
RUN mkdir /app
COPY ./pazpro /app
WORKDIR /app

# jar target
ENV JAR_TARGET "pazpro-0.0.1-SNAPSHOT.jar"

# set entrypoint to execute spring boot application
ENTRYPOINT ["sh","-c","java -jar -Dspring.profiles.active=docker build/libs/${JAR_TARGET}"]

EXPOSE 8080
