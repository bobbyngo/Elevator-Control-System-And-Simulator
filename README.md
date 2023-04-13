# Elevator Control System And Simulator

```
Carleton University 
Department of Systems and Computer Engineering 
SYSC 3303A Real-Time Concurrent Systems Winter 2023 
Iteration 1 - Establish Connections between the three subsystems.
Iteration 2 - Adding the Scheduler and Elevator Subsystems.
Iteration 3 - Multiple Cars and System Distribution.
Iteration 4 – Adding Error detection and correction.
Iteration 5 – Measuring the Scheduler and predicting the performance.
@version 1.0, 02/04/23
@version 2.0, 02/27/23
@version 3.0, 03/11/23
@version 4.0, 03/25/23
@version 5.0, 04/10/23
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

* documentation/P5G7-Responsibilities.pdf

## Documentation

The UML class diagram and the UML sequence diagram of the system is contained in
the documentation folder labeled: 

* documentation/diagrams/P5-UML-class.drawio.pdf
* documentation/diagrams/P5-UML-sequence.drawio.pdf

## Requirements & Dependencies

* Java JDK-17 or later version
* JUnit 4 for unit testing

No other external dependencies required.

## Compiling & Running the Application
There are 2 ways of running the program
</br>
Shortscript of running 3 processes at once with GUI:
</br>
Navigate to Main.java -> Run the main method
</br>
For multiple processes:
</br>
Navigate to SchedulerSubsystem.java -> Run the main method
</br>
Navigate to ElevatorSubsystem.java -> Run the main method
</br>
Navigate to FloorSubsystem.java -> Run the main method

The output should be in the console of eclipse and in the GUI

## Compiling & Running the JUnit
Run each test programs separately to avoid port in use error since JUnit run classes test in parallel and methods in sequential. Many test classes will not 
access to the port that the config file assign since the other class took it

## UML Diagrams
![UML-class](/documentation/diagrams/P5-UML-class.drawio.png)
![UML-sequence](/documentation/diagrams/P5-sequence.drawio.png)

# Iteration 1
Set up an application for 3 subsystems: Floor, Scheduler, and Elevator. 
Floor thread send the request to the Scheduler and the Scheduler assign the request to the Elevator. 
The Elevator will notify the Scheduler to notify the Floor that the request has been finished. 
The ElevatorRequest as a shared object that is used for threads to communicate.

# Iteration 2
Adding State Machine functionality for the Scheduler subsystem and Elevator subsystem. Demonstrating the state changes of subsystems when there is an action or event that trigger it

# Iteration 3
Split up system to separate programs that can be run on three separate computers and communicate with each other using UDP.
The Scheduler will now be used to coordinate the movement of cars such that each car carries roughly the same number of passengers as all of the others and so that the waiting time for passengers at floors is minimized.
The state machines for each car should execute independently of each other, but they will all have to share their position with the scheduler. The scheduler will choose which elevator will be used to service a given request.
![UML-elevatorState-class](/documentation/diagrams/P5-UML-elevatorState.drawio.png)
![UML-schedulerState-sequence](/documentation/diagrams/P4-UML-schedulerState.drawio.png)

# Iteration 4
Adding error states for DOOR_STUCK and ELEVATOR_STUCK by determining the floor would case these state to happen. If the elevator reaches those floor the error state would appear. 
Adding the state machine pattern, making the system configurable

# Iteration 5
Adding GUI for the program to demonstrate to represent the workflow </br>
Adding the elevator and components logics such as the arrival sensor, buttons, and lamps
Improving assigning requests algorithm to the Scheduler </br>
Adjust the error handling by adding a new column to the input.txt file </br>
Adding unit tests and fix bugs

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
+---ArchiveSubmission
|       A3G7_milestone_1.zip
|       A3G7_milestone_2.zip
|       A3G7_milestone_3.zip
|       A3G7_milestone_4.zip
+---documentation
|   |   P3G7-Responsibilities.docx
|   |   P3G7-Responsibilities.pdf
|   |   SYSC3302-G7-FinalReport.docx
|   |   SYSC3302-G7-FinalReport.pdf
|   |
|   +---diagrams
|              P3-UML-class.drawio.pdf
|              P3-UML-class.drawio.png
|              P3-UML-elevatorState.drawio.pdf
|              P3-UML-schedulerState.drawio.pdf
|              P3-UML-sequence.drawio.pdf
|              P3-UML-sequence.drawio.png
|              P3-UML.drawio
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
    |   |   |   SimulatorConfiguration.java
    |   |   |   UDPClient.java
    |   |   |
    |   |   +---dto
    |   |   |       AssignedElevatorRequest.java
    |   |   |       ElevatorGuiData.java
    |   |   |       ElevatorRequest.java
    |   |   |       ElevatorStatus.java
    |   |   |       FloorGuiData.java
    |   |   |   SerializableEncoder.java
    |   |   |       package-info.java
    |   |   |
    |   |   +---elevator
    |   |   |       Direction.java
    |   |   |       Door.java
    |   |   |       ElevatorContext.java
    |   |   |       ElevatorError.java
    |   |   |       ElevatorSubsystem.java
    |   |   |       Motor.java
    |   |   |       RequestListenerTask.java
    |   |   |       package-info.java
    |   |   |
    |   |   |   +---state
    |   |   |   |       DoorsClosedState.java
    |   |   |   |       DoorsOpenState.java
    |   |   |   |       DoorsStuckState.java
    |   |   |   |       ElevatorState.java
    |   |   |   |       ElevatorStateEnum.java
    |   |   |   |       ElevatorStuckState.java
    |   |   |   |       HomingDoorsClosedState.java
    |   |   |   |       IdleMotorState.java
    |   |   |   |       IdleState.java
    |   |   |   |       MovingDownState.java
    |   |   |   |       MovingState.java
    |   |   |   |       MovingUpState.java
    |   |   |   |       StateTimeoutTask.java
    |   |   |   |       StoppedState.java
    |   |   |   |       TimeoutEvent.java
    |   |   |   |       package-info.java
    |   |   |
    |   |   +---exception
    |   |   |       ElevatorReqParamException.java
    |   |   |       package-info.java
    |   |   |
    |   |   +---floor
    |   |   |   |   Floor.java
    |   |   |   |   FloorComponents.java
    |   |   |   |   FloorSubsystem.java
    |   |   |   |   package-info.java
    |   |   |   |
    |   |   |   \---parser
    |   |   |           package-info.java
    |   |   |           Parser.java
    |   |   |
    |   |   +---gui
    |   |   |   |   GUI.java
    |   |   |   |   LogConsole.java
    |   |   |   |
    |   |   +---scheduler
    |   |           package-info.java
    |   |           SchedulerContext.java
    |   |           SchedulerSubsystem.java
    |   |   |   +---state
    |   |   |           package-info.java
    |   |   |           IdleState.java
    |   |   |           InServiceState.java
    |   |   |           SchedulerState.java
    |   |
    |   \---resources
    |   |   |   +--- assests
    |   |   |   |       active-floor-down.png
    |   |   |   |       active-floor-up.png
    |   |   |   |       blank.png
    |   |   |   |       close.png
    |   |   |   |       down.png
    |   |   |   |       favicon.png
    |   |   |   |       idle.png
    |   |   |   |       inactive-floor-down.png
    |   |   |   |       inactive-floor-up.png
    |   |   |   |       open.png
    |   |   |   |       shutdown.png
    |   |   |   |       stop.png
    |   |   |   |       stuck.png
    |   |   |   |       up.png
    |   |   |   input.txt
    |   |   |   GenerateEvents.java
    |   |   |   package-info.java
    |   |   |   config.properties
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
        |           SchedulerContextTest.java
        |
        \---resources
                config.properties
                incorrectInput.txt
                input.txt
                package-info.java
                single-test-input.txt
```

### main package
`dto:` Location for enums, shared resource buffer classes:
* AssignedElevatorRequest.java: Subclass for ElevatorRequest
* ElevatorGuiData.java: Storing the information needed for GUI of the Elevator
* ElevatorRequest.java: A class storing all the relevant information regarding passenger's elevator requests
* ElevatorStatus.java: A class that transfer the data of elevator to Scheduler
* FloorGuiData.java: Storing the information needed for GUI of the Floor
* SerializableEncoder.java: Serializing class for transfering byte data between UDP communication method


`scheduler:` Package for classes related to scheduler subsystem
* SchedulerSubsystem.java: Subsystem class that containing 3 threads for listening to the request and sending requests
* SchedulerContext.java: Entity class

`scheduler.states:` Package for classes related to scheduler subsystem

`exception:` Package for customize exception classes
* ElevatorReqParamException.java: Custom exception for elevator request error

`elevator:` Package for classes related to elevator subsystem
* ElevatorComponents.java: A class containing elevator components that will be used in the UI integration with Static Model of Domain
* ElevatorEntity.java: Entity class
* ElevatorSubsystem.java: Subsystem has 1 thread for listening to the request from Scheduler
* Direction.java: Enum class for direction of elevator
* Door.java: Enum class for door closing
* RequestListenerTask.java: Listener thread

`elevator.state:` Package for elevator states

`floor:` Package for classes related to floor subsystem
* Floor.java: A producer class initiates requests to the scheduler for users wanting to travel up or down
* FloorSubsystem.java: Subsystem class for having many Floor instances. Have functionality for sending request and listening the response
* FloorComponenets.java: A class containing floor components that will be used in the UI integration with Static Model of Domain

`parser:` Package for classes related to parser 
* Parser.java: The parser that reads through a standard text file and exports the information in a specified format

`gui:` Package for classes related to GUI
* GUI.java: main GUI class to displays the overall subsystem
* LogConsole.java: Display the information logs of all the available Elevator

### test package
`test:` Unit test package
* ParserTest.java: Test class for Parser class
* ElevatorRequestTest.java: Test class for ElevatorRequest class
* SystemTest.java: Test class for the behaviors of the system
* FloorTest.java: Test class for the floor system
* SchedulerContextTest.java: Test class for Scheduler state subsystem
* ElevatorStateTest.java: Test class for Elevator state subsystem
* ElevatorTest.java: Test class for Scheduler subsystem

## System Features at a Glance
The elevator subsystem features a wide range of states and dynamic state transitions. For example, when the elevator is at a DOORS_CLOSED state, it can transition back to the DOORS_OPEN state in the event that a request at the same floor and same direction is received. This example behaviour is modeled after a common scenario observed at the Canal Building elevators, where when a passenger presses a floor button just after the elevator door closes, the elevator promptly opens its doors instead of leaving the passenger behind. An exhaustive description of all the state transitions can be found on the state diagram submission attached in the project.

A feature included in the elevator subsystem and the scheduler subsystem is that there is ability to re-schedule tasks to another elevator in the event of a fault. When an elevator reached a DOORS_STUCK or ELEVATOR_STUCK state, it will return all the requests for the passengers that it has not yet picked up for re-assignment to another elevator. This allows the elevator system to service requests smoothly without suffering from large delays due to faults, regardless of the duration of the fault. For example, if the DOOR_STUCK event occurred on an elevator, the passengers that the elevator was assigned to pickup would not suffer from any delays due to the fault because their request would be re-scheduled to the next best available elevator; only the passengers inside of the elevator at the time will suffer from delays from the fault.

The elevators’ movement algorithm is modeled after the C-SCAN disk scheduling algorithm and the scheduler’s algorithm complements this. The goal of the elevator is to keep moving as high as possible until there are no more requests to serve and then to keep moving as low possible until there are no more requests to serve. This allows passengers going in the same direction to be picked up in larger batches by a single elevator, which is very efficient.
A key feature of the scheduler’s algorithm is that it will never assign two or more elevators to service requests with the same-source and same-direction at the same time. Any same-source and same-direction requests that have both not been picked up yet are guaranteed to be service3d at the same time by the same elevator.

The floor subsystem has been implemented to fire requests at wall-clock time based on the timestamps described in the input file.

The simulation as whole is highly configurable. The user can customize the elevator loading, moving, doors open, doors closed, & doors stuck fault durations. The user can also customize the number of elevators and floors from the configuration file. Moreover, the host addresses and ports of the Elevator, Scheduler, Floor, and GUI subsystems can be configured.

The simulation follows a distributed architecture and can run as 4 different processes interconnected on a Local Area Network. Each subsystem consists of a control class which handles the network communications between each subsystem and entity classes which contain the subsystem’s application data.


## Disclaimer

Copyright disclaimer under section 107 of the Copyright Act 1976, allowance is 
made for fair use for purposes such as criticism, comment, news reporting, 
teaching, scholarship, education and research.

Fair use is a use permitted by copyright statute that might otherwise be 
infringing.
