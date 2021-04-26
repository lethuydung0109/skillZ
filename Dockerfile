FROM openjdk:11-oracle
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} *.jar
ENTRYPOINT ["java","-jar","/*.jar"]
