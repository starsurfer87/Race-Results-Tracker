# Personal Project: Race Results Tracker

## Project Proposal

This application will keep track of an athlete's race results and allow them to view their progress over time with 
respect to their goals. The key idea is that athletes and their coaches will be able to use the app to analyze the 
athlete's results over time in two main ways:
- determine how they have progressed in a particular **event** by seeing their current personal best with 
respect to all past personal bests and their goal time
- determine how they have done at a particular **competition** over time by 
being able to view and compare all **races** at that **competition** in current and past years

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
- **competition:** a track meet with a series of **races** for one or more **events**, usually occurring annually or on 
another repeating basis (e.g. Canadian Track & Field Championships, Harry Jerome Track Classic)
- **event:** a certain track discipline (e.g. 100m, 1500m, 3000m)
- **race:** a particular occurrence of participating in an **event** (e.g. a particular 100m that the athlete ran)



## User Stories
- As a user, I want to be able to add a race to an event, recording statistics including date, result time, and placement
- As a user, I want to be able to delete or edit a race
- As a user, I want to be able to specify a race as being part of a competition
- As a user, I want to be able to remove a race from a particular competition
- As a user, I want to be able to set a goal time for an event
- As a user, I want to be able to view all past performances at a particular competition
- As a user, I want to be able to view current and all past personal bests for a specific event
