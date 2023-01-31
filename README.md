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
* Make sure you use Java 17 or later
* Eclipse as IDE
* Navigate to ```src/main/Main.java``` class and run the ```main``` method

## UML Diagrams
ZAK PLS ADD THE UML DIAGRAM HERE PLS BRO PLS 

# Iteration 1
## Project structure:
The project is separated into different packages, each module has a specific functionality <br/>
### Packages:
* ```dto```: Location for enums, shared resource buffer classes
* ```scheduler```: Package for classes related to scheduler subsystem
* ```exception```: Packge for customize exception classes
* ```elevator```: Package for classes related to elevator subsystem
* ```floor```: Package for classes related to floor subsystem
* ```parser```: Package for classes related to parser 

## File names:
* ```Scheduler.java```: The server which responsible for handling the input from threads and route each elevator to requested floors and coordinating elevators
* ```Floor.java```: A producer class initiates requests to the scheduler for users wanting to travel up or down
* ```Elevator.java```: A consumer class dispatches requests of the scheduler after finishing the request
* ```Parser.java```: The Parser that reads through a standard text file and exports the information in a specified format
* ```Main.java```: A class for running the application

## Work distribution
The work distribution is divided into tasks and displayed in this JIRA link:
<br/>
https://sysc3303-project-group-7.atlassian.net/jira/software/c/projects/ECSS/boards/1
<br/>
