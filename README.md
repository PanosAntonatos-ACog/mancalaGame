# Mancala Game.

## This is an implementation of the well known Mancala or Kalah game.

<a href="https://www.linkedin.com/in/panosantonatos/">
<img alt="LinkedIn" src="https://github.com/PanosAntonatos-ACog/mancalaGame/blob/main/mancala-ngx/src/assets/linkedin.png"/>
</a>


### Description

Welcome Onboard fellow Developer,

In this project you can find a backend solution impelmented with Java (Spring Boot) and a frontend solution developed in Angular.
The respective projects are named mancala and mancala-ngx.

Feel free to roam around!

### Tech Stack Specifications

*For starters Clone the project locally*


### Avoid the hustle 

**Important** To avoid all the installations (mentioned above), 
use the existing docker-compose to run and serve all the necessary components of the application.

Assuming that Docker exists and is running, go to the folder that you cloned the repo open a git bash and run:
```
docker-compose up
```

Thus, creating all the needed docker images for the application to run.
Then, visit:
```
localhost:8083
```
And have fun playing the game!

#### Angular v.13.3.1

**Note**
I am not a front end expert thus, I followed as closely as possible the <a href="https://angular.io/docs">official Angular Docs</a>.

Run the project:

```
$ ng serve
```
Which will start the project at:

```
localhost:4200
```
If you want to specify port you can run:

```
$ ng serve --port 4201
```
Which will start the project at:

```
localhost:4201
```
...or try any other port you want.
#### Spring Boot

The Java project is a Spring Boot project with Gradle, it has MongoDB to store the necessary data and Redis for caching.
Versions of the tools used in the project are the following:
- *Java version 17.0.2*
- *Spring Boot version 2.6.5*
- *Gradle version 7.4*
- *MongoDB version 5.0.6*
- *Redis Server version 6.2.6*

Import the code to your favorite IDE and build the Gradle App by running:

```
gradle build -x test
```
We need to build excluding the tests (Improvements for the tests will be implemented)

To get the project running you need to have up and running your <a href="https://www.mongodb.com/try/download/community">MongoDB</a> 
as well as, your <a href="https://redis.io/">Redis</a>.

Then start the Mancala application, this starts the application at:

```
localhost:8080
```

### Exposed APIs

*You can find all the exposed APIs in the swagger*

#### For docker 
```
localhost:8082/swagger-ui/index.html
```

#### For running on the local machine 
```
localhost:8080/swagger-ui/index.html
```
