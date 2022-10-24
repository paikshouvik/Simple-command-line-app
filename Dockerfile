#Build a jar file
FROM maven:3.6.3-jdk-8-slim AS build
WORKDIR /home/app
COPY . /home/app/
RUN mvn -f /home/app/pom.xml clean package install

# Create an image
From openjdk:8-jdk-alpine
COPY --from=build /home/app/target/Simple-command-line-app-0.0.1-SNAPSHOT-jar-with-dependencies.jar /home/app/Simple-command-line-app-0.0.1-SNAPSHOT-jar-with-dependencies.jar
ENTRYPOINT [ "sh", "-c", "java -jar /home/app/Simple-command-line-app-0.0.1-SNAPSHOT-jar-with-dependencies.jar" ]
