FROM openjdk:11-jre-slim
ADD build/libs/*.jar backend.jar
EXPOSE 3006
ENTRYPOINT ["java","-jar","backend.jar"]