# Personal Project: Race Results Tracker

## Project Proposal

This application will keep track of an athlete's race results and allow them to view their progress over time with 
respect to their goals. The key idea is that athletes and their coaches will be able to use the app to analyze the 
athlete's results and progress in the following ways:
- get an overview of all their results for a particular **event**
- determine how they have progressed in a particular **event** by seeing their current personal best with 
respect to all past personal bests and their goal time
- get statistics that will help them work towards their goal times

Additionally, easy access to race result statistics is useful when registering for competitions or filling out 
applications. While this project will focus on track races specifically, it could be 
expanded upon by adding functionality that can provide similar analysis for other categories of events, such as field 
events, road races, or cross-country running.
 
 
This project is of interest to me because I myself am a runner, and am interested in tracking my own results in 
this way. The idea for this project stemmed from wanting to extend upon similar features in existing
fitness tracking apps such as Garmin Connect, filling in missing functionality that I wished existed. I want to allow for 
greater flexibility with regard to events and better show progress over time. 
Specifically, while Garmin only tracks one's current personal best for a few preset distances, I want to extend support 
to any track event and include both past and current personal bests.

### Definitions:
- **event:** a certain track discipline (e.g. 100m, 1500m, 3000m)
- **race:** a particular occurrence of participating in an **event** (e.g. a particular 100m that the athlete ran)


## User Stories
- As a user, I want to be able to add a new event for myself
- As a user, I want to be able to add a race to an event, recording statistics including date, result time, and placement
- As a user, I want to be able to set a goal time for an event
- As a user, I want to be able to determine what my lap splits needs to be to achieve my goal time
- As a user, I want to be able to view all my races for a specific event
- As a user, I want to be able to view my current and all past personal bests for a specific event
- As a user, I want to have the option to save my added events and races to a file
- As a user, when I start the application, I want to have the option to load event and race data from a file

## Instructions for Grader

- You can view all events added to the athlete on the main page (the main page is displayed once you have 
created a new athlete or load an athlete from file on the title screen)
- You can view all races added to an event by selecting one of the events in the list on the main page (all races for 
that event will pop up in a table)
- You can generate the first required action related to adding Xs to a Y by filling out the text input fields 
"Distance" and "Category" and pressing the "Add Event" button
- You can generate the second required action related to adding Xs to a Y by filling out the text input fields "Date", 
"Time", and "Placement" and pressing the "Add Race" button on the form that pops up when an event is selected
- You can locate my visual component (the track logo) on the title screen displayed upon starting the application 
- You can save the state of my application by pressing the "Save" button on the bottom of the main page 
- You can reload the state of my application by pressing the "Load Athlete From File" button on the title screen 
displayed upon starting the application

## Citations
- code adapted from [TellerApp project](https://github.students.cs.ubc.ca/CPSC210/TellerApp.git) used in ResultsTracker 
class in the ui package
- code taken or adapted from [JsonSerializationDemo project](https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)
used throughout project, but particularly in JsonReader, JsonWriter, and Writable classes in the persistence package

## Image Sources
- [creazilla](https://creazilla.com/nodes/38602-track-and-field-clipart)
