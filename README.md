# Elevator Control System And Simulator

```
Carleton University 
Department of Systems and Computer Engineering 
SYSC 3303A Real-Time Concurrent Systems Winter 2023 
Iteration 1 - Establish Connections between the three subsystems.
Iteration 2 - Adding the Scheduler and Elevator Subsystems.
Iteration 3 – Multiple Cars and System Distribution.
@version 1.0, 02/04/23
@version 2.0, 02/27/23
@version 3.0, 03/11/23
```

## Group 7 Members:
* Ismail Zakaria
* Liu Patrick
* Nguyen Trong
* Elmokdad Hussein
* Ngo Huu Gia Bao

## Work Distribution

The work distribution is divided into tasks and displayed in this JIRA link:
[JIRA - Agile Board](https://sysc3303-project-group-7.atlassian.net/jira/software/c/projects/ECSS/boards/1)

The work breakdown responsibility of the project for the iteration is located:

* documentation/P3G7-Responsibilities.pdf

## Documentation

The UML class diagram and the UML sequence diagram of the system is contained in
the documentation folder labeled: 

* documentation/P3-UML-class.drawio.pdf
* documentation/P3-UML-sequence.drawio.pdf

## Requirements & Dependencies

* Java JDK-17 or later version
* JUnit 4 for unit testing

No other external dependencies required.

## Compiling & Running the Application

Note that this application was built on Eclipse IDE release version 4.26.0. using Window 10 OS.

The program can be compiled and executed via Command Prompt. Note that each program requires its own 
terminal. In other words, it must be able to run multiple main programs 
(projects) concurrently.

```console
> cd C:\..\..\src\				// Navigate to the src directory	
> javac *.java					// Compile the source code
> cd 							// Change directory to .class files
> java -cp . Scheduler			// Set classpath to run application
> java -cp . Floor				// Set classpath to run application
> java -cp . Elevator			// Set classpath to run application
```

1. Download and extract the .zip file.
2. Import the source code and run the program in local IDE. 
3. Right click and select to "Run As" Java Application.
4. Run Scheduler, Floor, Elevator in this sequence.

## UML Diagrams
![UML-class](/documentation/P3-UML-class.drawio.png)
![UML-sequence](/documentation/P3-UML-sequence.drawio.png)

# Iteration 1
Set up an application for 3 subsystems: Floor, Scheduler, and Elevator. 
Floor thread send the request to the Scheduler and the Scheduler assign the request to the Elevator. 
The Elevator will notify the Scheduler to notify the Floor that the request has been finished. 
The ElevatorRequest as a shared object that is used for threads to communicate.

# Iteration 2
Adding State Machine functionality for the Scheduler subsystem and Elevator subsystem. Demonstrating the state changes of subsystems when there is an action or event that trigger it

# Iteration 3
Split up system to separate programs that can be run on three separate computers and communicate with each other using UDP using Remote Procedure Calls.
The Scheduler will now be used to coordinate the movement of cars such that each car carries roughly the same number of passengers as all of the others and so that the waiting time for passengers at floors is minimized.
The state machines for each car should execute independently of each other, but they will all have to share their position with the scheduler. The scheduler will choose which elevator will be used to service a given request.

## Project structure:

The project is separated into `main` and `test` packages, where each package has a specific functionality <br/>

```console
ELEVATOR-CONTROL-SYSTEM-AND-SIMULATOR
|   .classpath
|   .gitignore
|   .project
|   README.md
|   README.txt
|
+---documentation
|       P3-UML-class.drawio.pdf
|       P3-UML-class.drawio.png
|       P3-UML-elevatorState.drawio.pdf
|       P3-UML-schedulerState.drawio.pdf
|       P3-UML-sequence.drawio.pdf
|       P3-UML-sequence.drawio.png
|       P3-UML.drawio
|       P3G7-Responsibilities.docx
|       P3G7-Responsibilities.pdf
|
\---src
    |   module-info.java
    |
    +---main
    |   |   .gitignore
    |   |
    |   +---java
    |   |   |   Main.java
    |   |   |   package-info.java
    |   |   |
    |   |   +---dto
    |   |   |       Direction.java
    |   |   |       ElevatorRequest.java
    |   |   |       package-info.java
    |   |   |       RPC.java
    |   |   |
    |   |   +---elevator
    |   |   |       Elevator.java
    |   |   |       ElevatorState.java
    |   |   |       package-info.java
    |   |   |
    |   |   +---exception
    |   |   |       ElevatorReqParamException.java
    |   |   |       package-info.java
    |   |   |
    |   |   +---floor
    |   |   |   |   Floor.java
    |   |   |   |   package-info.java
    |   |   |   |
    |   |   |   \---parser
    |   |   |           package-info.java
    |   |   |           Parser.java
    |   |   |
    |   |   \---scheduler
    |   |           package-info.java
    |   |           Scheduler.java
    |   |           SchedulerState.java
    |   |
    |   \---resources
    |           input.txt
    |           package-info.java
    |
    \---test
        +---java
        |   |   package-info.java
        |   |   SystemTest.java
        |   |
        |   +---dto
        |   |       ElevatorRequestTest.java
        |   |       package-info.java
        |   |
        |   +---elevator
        |   |       ElevatorStateTest.java
        |   |       ElevatorTest.java
        |   |       package-info.java
        |   |
        |   +---floor
        |   |       FloorTest.java
        |   |       package-info.java
        |   |
        |   +---parser
        |   |       package-info.java
        |   |       ParserTest.java
        |   |
        |   \---scheduler
        |           package-info.java
        |           SchedulerStateTest.java
        |           SchedulerTest.java
        |
        \---resources
                incorrectInput.txt
                input.txt
                package-info.java
                single-test-input.txt
```

### main package
`dto:` Location for enums, shared resource buffer classes:
* ElevatorRequest.java: A class storing all the relevant information regarding passenger's elevator requests
* Direction.java: A class that storing the moving direction of the elevator in enum 
* RPC.java: A class that is responsible for the remote procedure call communication using UDP

`scheduler:` Package for classes related to scheduler subsystem
* SchedulerState.java: Enum class provides the available states of the Scheduler subsystem
* Scheduler.java: The server which responsible for handling the input from threads and route each elevator to requested floors and coordinating elevators

`exception:` Package for customize exception classes
* ElevatorReqParamException.java: Custom exception for elevator request error

`elevator:` Package for classes related to elevator subsystem
* ElevatorComponents.java: A class containing elevator components that will be used in the UI integration with Static Model of Domain
* ElevatorState.java: Enum class provides the available states of the Elevator subsystem
* Elevator.java: A consumer class dispatches requests of the scheduler after finishing the request

`floor:` Package for classes related to floor subsystem
* Floor.java: A producer class initiates requests to the scheduler for users wanting to travel up or down
* FloorComponenets.java: A class containing floor components that will be used in the UI integration with Static Model of Domain

`parser:` Package for classes related to parser 
* Parser.java: The parser that reads through a standard text file and exports the information in a specified format

### test package
`test:` Unit test package
* ParserTest.java: Test class for Parser class
* ElevatorRequestTest.java: Test class for ElevatorRequest class
* SystemTest.java: Test class for the behaviors of the system
* FloorTest.java: Test class for the floor system
* SchedulerStateTest.java: Test class for Scheduler state subsystem
* ElevatorStateTest.java: Test class for Elevator state subsystem
* ElevatorTest.java: Test class for Scheduler subsystem

### main class
* Main.java: A class for running the application

## Disclaimer

Copyright disclaimer under section 107 of the Copyright Act 1976, allowance is 
made for fair use for purposes such as criticism, comment, news reporting, 
teaching, scholarship, education and research.

Fair use is a use permitted by copyright statute that might otherwise be 
infringing.
