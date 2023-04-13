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
- As a user, I want to be able to add a new track event for myself
- As a user, I want to be able to add a race to an event, recording statistics including date, result time, and placement
- As a user, I want to be able to set a goal time for an event
- As a user, I want to be able to determine what my lap splits needs to be to achieve my goal time
- As a user, I want to be able to view all my races for a specific event
- As a user, I want to be able to view my current and all past personal bests for a specific event
- As a user, I want to have the option to save my added events and races to a file
- As a user, when I start the application, I want to have the option to load track event and race data from a file

## Instructions for Grader

- You can view all events added to the athlete on the main page (the main page is displayed once you have 
created a new athlete or load an athlete from file on the title screen)
- You can view all races added to an event by selecting one of the events in the list on the main page (all races for 
that event will pop up in a table)
- You can generate the first required action related to adding Xs to a Y by filling out the text input fields 
"Distance" and "Category" and pressing the "Add Event" button on the main page
- You can generate the second required action related to adding Xs to a Y by filling out the text input fields "Date", 
"Time", and "Placement" and pressing the "Add Race" button on the form that pops up when an event is selected
- You can generate a third action related to adding Xs to a Y by clicking the "Filter for PBs" checkbox on that appears 
with the table of races
- You can locate my visual component (the track logo) on the title screen displayed upon starting the application 
- You can save the state of my application by pressing the "Save" button on the bottom of the main page 
- You can reload the state of my application by pressing the "Load Athlete From File" button on the title screen 
displayed upon starting the application

## Phase 4: Task 2
Fri Apr 07 10:06:35 PDT 2023  
Added new event: 1500m  
Fri Apr 07 10:06:39 PDT 2023  
Accessed all races for 1500m  
Fri Apr 07 10:07:19 PDT 2023  
Added new 1500m race  
Fri Apr 07 10:07:19 PDT 2023  
Accessed all races for 1500m  
Fri Apr 07 10:08:00 PDT 2023  
Added new 1500m race  
Fri Apr 07 10:08:00 PDT 2023  
Accessed all races for 1500m  
Fri Apr 07 10:08:29 PDT 2023  
Added new 1500m race  
Fri Apr 07 10:08:29 PDT 2023  
Accessed all races for 1500m  
Fri Apr 07 10:08:32 PDT 2023  
Accessed all PB races for 1500m  
Fri Apr 07 10:08:33 PDT 2023  
Accessed all races for 1500m  
Fri Apr 07 10:08:47 PDT 2023  
Added new event: 800m  
Fri Apr 07 10:09:18 PDT 2023  
Accessed all races for 800m  
Fri Apr 07 10:10:11 PDT 2023  
Added new 800m race  
Fri Apr 07 10:10:11 PDT 2023  
Accessed all races for 800m

## Phase 4: Task 3
The section of my design that would most benefit from refactoring is the ui package, particularly the graphical user 
interface. Currently, there is a lot of coupling both within the ui package and also from the ui package to external 
classes. Additionally, some of the classes are not the most cohesive and take on multiple roles that are related but 
distinct. 
One way to improve coupling and cohesion in my ui would be to create a class that handles saving and loading. 
This class could be used in both the console ui and gui, making it easier to maintain saving and loading functionality 
across both version of the ui. It would alse increase cohesion in classes that are currently handling this 
functionality. Furthermore, it would significantly reduce coupling, both within the ui package and to external classes. 
Currently, the only reason the TitleScreen and MenuBar classes need a field of type ResultsTrackerGUI is for their 
saving and loading functionality. Additionally, this refactoring would mean only one class in the ui package would have 
an association to the classes in the persistence package. 
Another way cohesion could be increased is by creating nested classes for the different listeners, and moving methods 
related to listening for and handling user interactions to these classes. Currently, many of my gui classes serve two
different key purposes: setting up and displaying a certain aspect of the gui to the user, and handling user 
interactions with that aspect. While this might make sense for the Form class and its subtypes since their purpose is 
to collect user input, it isn't as logical for other gui classes. Nested listener classes would increase cohesion in 
this respect. 
There are also several smaller refactoring changes that could be done. The EventsPanel class could refer to the 
ResultsTrackerGUI class's Athlete field rather than having its own, thus reducing coupling to the Athlete class and 
solving the issue of needing to keep these two fields in sync (as they should always refer to the same athlete). This 
would also make it easier to add support for multiple users in the future. Also, the two methods for parsing\selecting 
an EventCategory in the two different versions of the ui could be abstracted into a single method and relocated to the 
EventCategory class.

An additional refactoring that would improve my design is adding more exceptions to eliminate requires clauses and make my 
design more robust. Many of my methods have requires clauses that put restrictions on parameters that get their values 
from user inputs, which are particularly challenging to control. While my console ui does a good job checking that user 
inputs were valid, my graphical ui is not as thorough in this respect. Adding exceptions for these invalid values will
allow methods to handle these values, ensure that invalid inputs are not simply being passed in unnoticed, and make it
easier to respond to invalid inputs.

## Citations
- code adapted from [TellerApp project](https://github.students.cs.ubc.ca/CPSC210/TellerApp.git) used in ResultsTracker 
class in the ui package
- code taken or adapted from [JsonSerializationDemo project](https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)
used throughout project, but particularly in JsonReader, JsonWriter, and Writable classes in the persistence package
- code based on Java Swing [Component Examples](https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html), 
[Layout Examples](https://docs.oracle.com/javase/tutorial/uiswing/examples/layout/index.html#SpringDemo2), and
[Event Listener Examples](https://docs.oracle.com/javase/tutorial/uiswing/examples/events/index.html#MultiListener) 
(particularly the CheckBoxDemoProject, ListDemoProject, SimpleTableDemoProject, and MultiListenerProject) used throughout gui package 

## Image Sources
- [creazilla](https://creazilla.com/nodes/38602-track-and-field-clipart)
