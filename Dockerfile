FROM openjdk:8
EXPOSE 8080
ADD target/user-project.jar user-project.jar 
ENTRYPOINT ["java", "-jar", "/user-project.jar"]