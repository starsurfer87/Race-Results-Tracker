package ui;


import model.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Application for tracking race results
public class ResultsTracker {
    // code based on modified version of the TellerApp class of the Teller application

    private List<Event> events;
    private Scanner input;

    // EFFECTS: runs the results tracker
    public ResultsTracker() {
        runResultsTracker();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runResultsTracker() {
        boolean running = true;
        String command = null;

        init();
    }

    // MODIFIES: this
    // EFFECTS: initializes scanner and empty list of events
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");

        events = new ArrayList<>();
    }

    // EFFECTS: displays list of all events that have been added, or indicates if none have been added
    private void displayEvents() {

    }

    // EFFECTS: displays main menu options to user
    private void displayMainMenu() {

    }


    // MODIFIES: this
    // EFFECTS: prompts user to create a new event; if event with distance and category given by user does not yet
    //          exist, then add and return the event, otherwise return the pre-existing equivalent event
    private Event addNewEvent() {
        return null;
    }

    // EFFECTS: prompts user to select an event and returns that event
    private Event selectEvent() {
        return null; // stub
    }

    // MODIFIES: event
    // EFFECTS: displays basic stats for event and manages user interactions with event
    private void viewEvent(Event event) {

    }

    // EFFECTS: displays the name of given event, along with the event's personal best and goal times if they exist
    private void displayEventStats(Event event) {

    }

    // EFFECTS: displays menu of options specific to an event to user
    private void displayEventMenu() {

    }

    // MODIFIES: event
    // EFFECTS: sets event's goal time according to user input
    private void setGoalTime(Event event) {

    }

    // EFFECTS: displays the lap split time needed to achieve goal time for event
    private void displayGoalTimeLapSplit(Event event) {

    }

    // MODIFIES: event
    // EFFECTS: adds a new race to event according to user input
    private void addNewRace(Event event) {

    }

    // EFFECTS: display all races for given event
    private void displayRaces(Event event) {

    }

    // EFFECTS: display all races where a current or past personal best time was achieved
    private void displayPBs(Event event) {

    }
}
