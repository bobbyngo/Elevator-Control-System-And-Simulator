# Elevator Control System And Simulator

```
Carleton University 
Department of Systems and Computer Engineering 
SYSC 3303A Real-Time Concurrent Systems Winter 2023 
Iteration 1 - Establish Connections between the three subsystems.
@version 1.0, 02/04/23
```

## Group 7 Members:
* Ismail Zakaria
* Liu Patrick
* Nguyen Trong
* Elmokdad Hussein
* Ngo Huu Gia Bao

## Requirements & Dependencies

* Java JDK-17 or later version
* JUnit 4 for unit testing

No other external dependencies required.

## Compiling & Running the Application

Note that this application was built on Eclipse IDE release version 4.26.0. using Window 10 OS.

Download and extract the .zip file. Then import the source code directly and 
run the program in local IDE, otherwise the program can be compiled and 
executed via Command Prompt. Note that each program requires its own 
terminal. In other words, it must be able to run multiple main programs 
(projects) concurrently.

Navigate to `/src/main/Main.java` and run the `main` method.

## UML Diagrams
![UML-class](/documentation/P1-UML-class.drawio.png)
![UML-sequence](/documentation/P1-UML-sequence.drawio.png)

# Iteration 1

## Project structure:

The project is separated into `main` and `test` packages. Whereas, each packages has a specific functionality <br/>

```console
Iteration1
|   .classpath
|   .gitignore
|   .project
|   README.md
|   README.txt
|
+---bin
|   |   module-info.class
|   |
|   +---main
|   |   +---java
|   |   |   |   Main.class
|   |   |   |   package-info.class
|   |   |   |
|   |   |   +---dto
|   |   |   |       Direction.class
|   |   |   |       ElevatorRequest.class
|   |   |   |       package-info.class
|   |   |   |
|   |   |   +---elevator
|   |   |   |       Elevator.class
|   |   |   |       package-info.class
|   |   |   |
|   |   |   +---exception
|   |   |   |       ElevatorReqParamException.class
|   |   |   |       package-info.class
|   |   |   |
|   |   |   +---floor
|   |   |   |       Floor.class
|   |   |   |       package-info.class
|   |   |   |
|   |   |   +---parser
|   |   |   |       package-info.class
|   |   |   |       Parser.class
|   |   |   |
|   |   |   \---scheduler
|   |   |           package-info.class
|   |   |           Scheduler.class
|   |   |
|   |   \---resources
|   |           input.txt
|   |           package-info.class
|   |
|   \---test
|       +---java
|       |   |   package-info.class
|       |   |
|       |   +---dto
|       |   |       ElevatorRequestTest.class
|       |   |       package-info.class
|       |   |
|       |   +---elevator
|       |   |       package-info.class
|       |   |
|       |   +---floor
|       |   |       package-info.class
|       |   |
|       |   +---parser
|       |   |       package-info.class
|       |   |       ParserTest.class
|       |   |
|       |   \---scheduler
|       |           package-info.class
|       |
|       \---resources
|               incorrectInput.txt
|               input.txt
|               package-info.class
|
+---documentation
|       P1-UML-class.drawio.pdf
|       P1-UML-class.drawio.png
|       P1-UML-sequence.drawio.pdf
|       P1-UML-sequence.drawio.png
|       P1-UML.drawio
|       P1G7-Responsibilities.docx
|       P1G7-Responsibilities.pdf
|
\---src
    |   module-info.java
    |
    +---main
    |   +---java
    |   |   |   Main.java
    |   |   |   package-info.java
    |   |   |
    |   |   +---dto
    |   |   |       Direction.java
    |   |   |       ElevatorRequest.java
    |   |   |       package-info.java
    |   |   |
    |   |   +---elevator
    |   |   |       Elevator.java
    |   |   |       package-info.java
    |   |   |
    |   |   +---exception
    |   |   |       ElevatorReqParamException.java
    |   |   |       package-info.java
    |   |   |
    |   |   +---floor
    |   |   |       Floor.java
    |   |   |       package-info.java
    |   |   |
    |   |   +---parser
    |   |   |       package-info.java
    |   |   |       Parser.java
    |   |   |
    |   |   \---scheduler
    |   |           package-info.java
    |   |           Scheduler.java
    |   |
    |   \---resources
    |           input.txt
    |           package-info.java
    |
    \---test
        +---java
        |   |   package-info.java
        |   |
        |   +---dto
        |   |       ElevatorRequestTest.java
        |   |       package-info.java
        |   |
        |   +---elevator
        |   |       package-info.java
        |   |
        |   +---floor
        |   |       package-info.java
        |   |
        |   +---parser
        |   |       package-info.java
        |   |       ParserTest.java
        |   |
        |   \---scheduler
        |           package-info.java
        |
        \---resources
                incorrectInput.txt
                input.txt
                package-info.java
```

### main package
`dto:` Location for enums, shared resource buffer classes:
* ElevatorRequest.java: A class storing all the relevant information regarding passenger's elevator requests
* Direction.java: A class that storing the moving direction of the elevator in enum 

`scheduler:` Package for classes related to scheduler subsystem
* Scheduler.java: The server which responsible for handling the input from threads and route each elevator to requested floors and coordinating elevators

`exception:` Package for customize exception classes
* ElevatorReqParamException.java: Custom exception for elevator request error

`elevator:` Package for classes related to elevator subsystem
* Elevator.java: A consumer class dispatches requests of the scheduler after finishing the request

`floor:` Package for classes related to floor subsystem
* Floor.java: A producer class initiates requests to the scheduler for users wanting to travel up or down

`parser:` Package for classes related to parser 
* Parser.java: The parser that reads through a standard text file and exports the information in a specified format

### test package
`test:` Unit test package
* ParserTest.java: Test class for Parser class
* ElevatorRequestTest.java: Test class for ElevatorRequest class

### main class
* Main.java: A class for running the application

## Work Distribution

The work distribution is divided into tasks and displayed in this JIRA link:
[JIRA - Agile Board](https://sysc3303-project-group-7.atlassian.net/jira/software/c/projects/ECSS/boards/1)

## Documentation

Documenation folder contains the UML class diagram and the UML sequence diagram of the system.
It also contains the mockup of the work breakdown responsibility of the project for this iteration.

## Disclaimer

Copyright disclaimer under section 107 of the Copyright Act 1976, allowance is 
made for fair use for purposes such as criticism, comment, news reporting, 
teaching, scholarship, education and research.

Fair use is a use permitted by copyright statute that might otherwise be 
infringing.
