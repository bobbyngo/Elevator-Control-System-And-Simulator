# Elevator Control System And Simulator
```
SYSC 3303 at Carleton University 
```

## Group 7 Members:
* Ismail Zakaria
* Liu Patrick
* Nguyen Trong
* Elmokdad Hussein
* Ngo Huu Gia Bao

## Set up Instructions
* Make sure you use Java 17 or later version
* JUnit 4 is required for runing unit tests
* Eclipse as IDE
* Navigate to ```src/main/Main.java``` class and run the ```main``` method

## UML Diagrams
ZAK PLS ADD THE UML DIAGRAM HERE PLS BRO PLS 

# Iteration 1
## Project structure:
The project is separated into ```main``` and ```test``` packages. Whereas, each packages has a specific functionality <br/>
### main package:<br/>
```dto:``` Location for enums, shared resource buffer classes:
* ElevatorRequest.java: A class storing all the relevant information regarding passenger's elevator requests
* Direction.java: A class that storing the moving direction of the elevator in enum 

```scheduler:``` Package for classes related to scheduler subsystem
* Scheduler.java: The server which responsible for handling the input from threads and route each elevator to requested floors and coordinating elevators

```exception:``` Packge for customize exception classes
* ElevatorReqParamException.java: Custom exception for elevator request error

```elevator:``` Package for classes related to elevator subsystem
* Elevator.java: A consumer class dispatches requests of the scheduler after finishing the request

```floor:``` Package for classes related to floor subsystem
* Floor.java: A producer class initiates requests to the scheduler for users wanting to travel up or down

```parser:``` Package for classes related to parser 
* Parser.java: The Parser that reads through a standard text file and exports the information in a specified format

### test package:
```test:``` Unit test package
* ParserTest.java: Test class for Parser class
* ElevatorRequestTest.java: Test class for ElevatorRequest class

### main class
* Main.java: A class for running the application

## Work distribution
The work distribution is divided into tasks and displayed in this JIRA link:
<br/>
https://sysc3303-project-group-7.atlassian.net/jira/software/c/projects/ECSS/boards/1
<br/>
