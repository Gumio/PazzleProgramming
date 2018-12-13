# 現行動くやつ
FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD /build/libs/pazpro-0.0.1-SNAPSHOT.jar pazpro.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom -Djava.net.preferIPv4Stack=true","-jar","/pazpro.jar"]
EXPOSE 4649
