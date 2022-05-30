# Demo Spring Boot for Modis
The purpouse of this project is make a example for spring boot rest controller.

# Starting 🚀
First step is clone this repository from github to your local machine.

# Requirements 📋
To run in yout machine you should be install next requiriments:

1. If you like test this project in a docker container you should be installed Docker. The Dockerfile creates two containers, first a container with Maven whitch compile the project and generate *jar* file. Next this jar file going to the second container, a open-jdk-8 whitch containers run the application.

Run the following command to generate container in the project folder

```bash
docker -t demo-modis .
```

2. On the other hand, if you prefer compile and run in yout local machine you may be installed Java in version 8 and Maven.

Run the following command to generate container in the project folder

```bash
mvn package -f pom.xml
```
# Installation 🔧

To run this project with maven do the following command:

```bash
mvn spring-boot:run
```

On the other side, to deploy docker container:

```bash
docker run -p 8082:8082 demo-modis -d
```