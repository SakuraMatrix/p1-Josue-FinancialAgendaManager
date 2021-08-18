# Project 1:Financial Agenda Manager

Financial Agenda Manager is an app that will help users keep track of important tasks needed to complete.

## Features
- Create tasks associated with a task set to keep tasks grouped as needed.
- Delete tasks.
- Update description and due date of a task.
- Update the status of a task.

## Technical
- Java 8
- Maven
- Docker (cassandra container)
- Reactor Netty
- RESTful API standards
- Logging (Not using Log libraries, log will be externally saved in txt file and displayed on console as std out)
- JUnit 

## Database Schema
Table Name: task
| setname     | description | duedate     | status      |
| ----------- | ----------- | ----------- | ----------- |
| text        | text        | text        | text        |

## Usage
N/A

## RESTful API Endpoints
- GET `/` directs user to a landing page where to a form to create tasks and to other options such as searching for task sets.
- GET `/task-set` will respond with a retrival of all exisitng records from the database under any task sets.
- GET `/task-set/{params}` specifying a task set will respond with a retrival of records from the database that are only associated to the specified task set.
- POST `/task-set` will send a request to the server being made up of a string from HTML in order to have it inserted into the database.
- POST `/task-set/remove` will send a string from HTML to the server that will delete a record from the database.

## To-Do
- Add CRUD functionality.
- Create schema.
- Create query endpoints.
