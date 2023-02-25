package ui;


import model.Event;
import model.EventCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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

        while (running) {
            displayEvents();
            displayMainMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("quit")) {
                running = false;
            } else {
                processMainMenuCommand(command);
            }
        }
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
        if (events.size() > 0) {
            System.out.println("\nEvent List: ");
            for (Event evt : events) {
                System.out.println("\t" + evt.getName());
            }
        } else {
            System.out.println("\nNo events added yet");
        }
    }

    // EFFECTS: displays main menu options to user
    private void displayMainMenu() {
        System.out.println("\nTo add a new event, enter \"new\"");
        if (events.size() > 0) {
            System.out.println("To view an existing event, enter \"select\"");
        }
        System.out.println("To quit the application, enter \"quit\"");
    }

    // MODIFIES: this
    // EFFECTS: processes user selection for main menu
    private void processMainMenuCommand(String command) {
        if (command.equals("new")) {
            viewEvent(addNewEvent());
        } else if (command.equals("select") && events.size() > 0) {
            viewEvent(selectEvent());
        } else {
            System.out.println("\nNot a valid command \nPlease try again");
        }
    }


    // MODIFIES: this
    // EFFECTS: prompts user to create a new event; if event with distance and category given by user does not yet
    //          exist, then add and return the event, otherwise return the pre-existing equivalent event
    private Event addNewEvent() {
        System.out.println("\nAdding a new event");
        System.out.println("Enter the event distance (in meters):");
        int distance = input.nextInt();
        displayEventCategories();
        EventCategory category = selectEventCategory();
        Event newEvent = new Event(distance, category);
        events.add(newEvent);
        return newEvent;
    }

    // EFFECTS: display event category options to user
    private void displayEventCategories() {
        System.out.println("Select an event category:");
        System.out.println("\tsp -> sprint");
        System.out.println("\tmd -> middle distance");
        System.out.println("\tld -> long distance");
        System.out.println("\thd -> hurdles");
        System.out.println("\tsc -> steeplechase");
        System.out.println("\trw -> race walk");
    }

    // EFFECTS: prompts user to select an event category and returns it
    private EventCategory selectEventCategory() {
        String selection = input.next();
        selection = selection.toLowerCase();
        EventCategory category;

        switch (selection) {
            case "sp":  category = EventCategory.SPRINT;
            break;
            case "md":  category = EventCategory.MID_DIST;
            break;
            case "ld":  category = EventCategory.LONG_DIST;
            break;
            case "hd":  category = EventCategory.HURDLES;
            break;
            case "sc":  category = EventCategory.STEEPLECHASE;
            break;
            case "rw":  category = EventCategory.RACE_WALK;
            break;
            default:
                System.out.println("Not a valid selection \nPlease try again");
                category = selectEventCategory();
        }

        return category;
    }


    // EFFECTS: prompts user to select an event and returns that event
    private Event selectEvent() {
        System.out.println("Select an event");
        return null; // stub
    }

    // MODIFIES: event
    // EFFECTS: displays basic stats for event and manages user interactions with event
    private void viewEvent(Event event) {
        System.out.println("View selected event");
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
