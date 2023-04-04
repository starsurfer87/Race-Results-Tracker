package ui.console;


import model.Athlete;
import model.TrackEvent;
import model.TrackEventCategory;
import model.Race;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

// Application for tracking race results
public class ResultsTracker {
    // code based on modified version of the TellerApp class of the Teller project
    // https://github.students.cs.ubc.ca/CPSC210/TellerApp.git

    private Athlete athlete;
    private Scanner input;
    private String fileDest;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the results tracker
    public ResultsTracker() {
        runResultsTracker();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runResultsTracker() {
        boolean running = true;
        String command;

        init();

        while (running) {
            displayEvents();
            displayMainMenu();
            command = input.next();
            command = command.toLowerCase().trim();

            if (command.equals("quit")) {
                running = !quit();
            } else {
                processMainMenuCommand(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: saves athlete's data to file
    private void save() throws FileNotFoundException {
        try {
            jsonWriter.open();
            jsonWriter.write(athlete);
            System.out.println("\nYour work has been saved");
        } finally {
            jsonWriter.close();
        }
    }

    // MODIFIES: this
    // EFFECTS: gives user option to save before quitting and returns true if program should quit, false otherwise
    private boolean quit() {
        displayQuitOptions();
        String command = input.next();
        command = command.toLowerCase().trim();

        switch (command) {
            case "s":
                try {
                    save();
                    return true;
                } catch (FileNotFoundException e) {
                    System.out.println("\nThere was an problem saving your data");
                    System.out.println("Returning to main menu");
                    return false;
                }
            case "q":
                return true;
            case "c":
                System.out.println("\nReturning to main menu");
                return false;
            default:
                System.out.println("\nNot a valid option \nReturning to main menu");
                return false;
        }
    }

    // EFFECTS: displays quit options to user
    private void displayQuitOptions() {
        System.out.println("\nQuit options:");
        System.out.println("\ts -> save and quit");
        System.out.println("\tq -> quit");
        System.out.println("\tc -> cancel");
    }

    // MODIFIES: this
    // EFFECTS: initializes scanner, json reader and writer, and athlete
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        fileDest = "./data/AthleteData.json";
        jsonReader = new JsonReader(fileDest);
        jsonWriter = new JsonWriter(fileDest);
        selectLoadOrNew();
    }

    // MODIFIES: this
    // EFFECTS: loads data from file or initializes new athlete from scratch according to user input
    private void selectLoadOrNew() {
        System.out.println("Would you like to load your previous data?");
        System.out.println("Enter \"yes\" or \"no\"");
        String command = input.next();
        command = command.toLowerCase().trim();

        if (command.equals("yes")) {
            try {
                athlete = jsonReader.read();
            } catch (IOException e) {
                System.out.println("\nThere was an issue accessing your file");
                System.out.println("Likely you have not saved any data yet");
                athlete = createNewAthlete();
            }
        } else if (command.equals("no")) {
            athlete = createNewAthlete();
        } else {
            System.out.println("\nNot a valid command \nPlease try again");
            selectLoadOrNew();
        }
    }

    // EFFECTS: creates and returns a new athlete with name specified by user input
    private Athlete createNewAthlete() {
        System.out.println("\nWhat is your name?");
        String athleteName = input.next();
        return new Athlete(athleteName);
    }

    // EFFECTS: displays list of all events that have been added, or indicates if none have been added
    private void displayEvents() {
        if (athlete.numEvents() > 0) {
            System.out.println("\n" + athlete.getName() + "'s Event List: ");
            for (String eventName : athlete.getEventNames()) {
                System.out.println("\t" + eventName);
            }
        } else {
            System.out.println("\nNo events added yet");
        }
    }

    // EFFECTS: displays main menu options to user
    private void displayMainMenu() {
        System.out.println("\nTo add a new event, enter \"new\"");
        if (athlete.numEvents() > 0) {
            System.out.println("To view an existing event, enter \"select\"");
        }
        System.out.println("To save your data, enter \"save\"");
        System.out.println("To quit the application, enter \"quit\"");
    }

    // MODIFIES: this
    // EFFECTS: processes user selection for main menu
    private void processMainMenuCommand(String command) {
        if (command.equals("new")) {
            viewEvent(addNewEvent());
        } else if (command.equals("select") && athlete.numEvents() > 0) {
            viewEvent(selectEvent());
        } else if (command.equals("save")) {
            try {
                save();
            } catch (FileNotFoundException e) {
                System.out.println("\nThere was an problem saving your data");
            }
        } else {
            System.out.println("\nNot a valid command \nPlease try again");
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user to create a new event; if event same name does not yet
    //          exist, then add and return the event, otherwise return the pre-existing equivalent event
    private TrackEvent addNewEvent() {
        System.out.println("\nAdding a new event");
        int distance = inputPosInteger("Enter the event distance (in meters):");
        displayEventCategories();
        TrackEventCategory category = selectEventCategory();
        TrackEvent newTrackEvent = new TrackEvent(distance, category);
        boolean newEventAdded = athlete.addEvent(newTrackEvent);

        if (!newEventAdded) {
            System.out.println("\nAn event with this distance and category already exists:");
        }

        return athlete.getEvent(newTrackEvent.getName());
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

    // EFFECTS: prompts user to select an event category and returns that category
    private TrackEventCategory selectEventCategory() {
        String selection = input.next();
        selection = selection.toLowerCase().trim();
        TrackEventCategory category;

        switch (selection) {
            case "sp": category = TrackEventCategory.SPRINT;
                break;
            case "md": category = TrackEventCategory.MID_DIST;
                break;
            case "ld": category = TrackEventCategory.LONG_DIST;
                break;
            case "hd": category = TrackEventCategory.HURDLES;
                break;
            case "sc": category = TrackEventCategory.STEEPLECHASE;
                break;
            case "rw": category = TrackEventCategory.RACE_WALK;
                break;
            default:
                System.out.println("Not a valid selection \nPlease try again");
                category = selectEventCategory();
        }

        return category;
    }


    // EFFECTS: prompts user to select an event and returns that event
    private TrackEvent selectEvent() {
        System.out.println("\nEnter the name of the event you would like to select:");
        String eventName = input.next();
        eventName = eventName.trim();

        TrackEvent selectedTrackEvent = athlete.getEvent(eventName);

        if (selectedTrackEvent != null) {
            return selectedTrackEvent;
        } else {
            System.out.println("Not a valid selection \nPlease try again");
            return selectEvent();
        }
    }

    // MODIFIES: event
    // EFFECTS: displays basic stats for event and manages user interactions with event
    private void viewEvent(TrackEvent trackEvent) {
        boolean viewingEvent = true;
        String command;


        while (viewingEvent) {
            displayEventStats(trackEvent);
            displayEventMenu();
            command = input.next();
            command = command.toLowerCase().trim();

            if (command.equals("exit")) {
                viewingEvent = false;
            } else {
                processEventMenuCommand(trackEvent, command);
            }
        }
    }

    // EFFECTS: displays the name of given event, along with the event's personal best and goal times if they exist
    private void displayEventStats(TrackEvent trackEvent) {
        System.out.println("\n" + trackEvent.getName());
        if (trackEvent.getPB() != null) {
            System.out.println("Current Personal Best: " + trackEvent.getPB());
        }
        if (trackEvent.getGoalTime() != null) {
            System.out.println("Goal Time: " + trackEvent.getGoalTime());
        }
    }

    // EFFECTS: displays menu of options specific to an event to user
    private void displayEventMenu() {
        System.out.println("\nSelect one of the following:");
        System.out.println("\tsgt  -> set a goal time");
        System.out.println("\tgls  -> get goal time lap split");
        System.out.println("\tar   -> add a new race");
        System.out.println("\tvr   -> view all races");
        System.out.println("\tvpb  -> view personal bests");
        System.out.println("\texit -> back to main menu");
    }

    // MODIFIES: event
    // EFFECTS: processes user selection for event menu
    private void processEventMenuCommand(TrackEvent trackEvent, String command) {

        switch (command) {
            case "sgt":
                setGoalTime(trackEvent);
                break;
            case "gls":
                displayGoalTimeLapSplit(trackEvent);
                break;
            case "ar":
                addNewRace(trackEvent);
                break;
            case "vr":
                displayRaces(trackEvent);
                break;
            case "vpb":
                displayPBs(trackEvent);
                break;
            default:
                System.out.println("Not a valid selection \nPlease try again");
        }
    }

    // MODIFIES: event
    // EFFECTS: sets event's goal time according to user input
    private void setGoalTime(TrackEvent trackEvent) {
        System.out.println("\nEnter your " + trackEvent.getName()
                + " goal time using ISO-8601 seconds based representation:");
        String goalTimeString = input.next();

        try {
            Duration goalTime = Duration.parse(goalTimeString);
            if (isPositiveTime("Goal time", goalTime)) {
                trackEvent.setGoalTime(goalTime);
                System.out.println("Your goal time has been set");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Not a valid time");
        }
    }

    // EFFECTS: displays the lap split time needed to achieve goal time for event
    private void displayGoalTimeLapSplit(TrackEvent trackEvent) {
        if (trackEvent.getDistance() < TrackEvent.LAP_DIST) {
            System.out.println("Event is less than a lap long - cannot calculate lap splits");
        } else if (trackEvent.getGoalTime() == null) {
            System.out.println("No goal time set - cannot calculate lap splits");
        } else {
            System.out.println("\nLap split time needed to achieve goal time: " + trackEvent.goalTimeLapSplit());
        }
    }

    // MODIFIES: event
    // EFFECTS: adds a new race to event according to user input
    private void addNewRace(TrackEvent trackEvent) {
        System.out.println("\nAdding a new " + trackEvent.getName() + " race");

        LocalDate raceDate = inputRaceDate(trackEvent);
        Duration resultTime = inputRaceTime();
        int placement = inputPosInteger("Enter your placement:");

        trackEvent.addRace(raceDate, resultTime, placement);
    }

    // EFFECTS: prompts user to input a date for a race and returns it
    private LocalDate inputRaceDate(TrackEvent raceTrackEvent) {
        System.out.println("Enter the date of the race in the form yyyy-mm-dd:");
        String dateString = input.next();
        LocalDate date;

        try {
            date = LocalDate.parse(dateString);
            if (raceTrackEvent.numRaces() > 0) {
                LocalDate prevRaceDate = raceTrackEvent.getRace(0).getDate();
                if (date.isBefore(prevRaceDate)) {
                    System.out.println("Date cannot be before the date of your previous " + raceTrackEvent.getName()
                            + "race (" + prevRaceDate + ")");
                    date = inputRaceDate(raceTrackEvent);
                }
            }
        } catch (DateTimeParseException e) {
            System.out.println("Not a valid date");
            date = inputRaceDate(raceTrackEvent);
        }

        return date;
    }

    // EFFECTS: prompts user to input a result time for a race and returns it
    private Duration inputRaceTime() {
        System.out.println("Enter your result time using ISO-8601 seconds based representation:");
        String timeString = input.next();
        Duration time;

        try {
            time = Duration.parse(timeString);
            if (!isPositiveTime("Time", time)) {
                time = inputRaceTime();
            }
        } catch (DateTimeParseException e) {
            System.out.println("Not a valid time");
            time = inputRaceTime();
        }

        return time;
    }

    // EFFECTS: display race stats for a specified list of races
    private void displayListOfRaces(TrackEvent trackEvent, List<Race> raceList, String listType, boolean identifyPBs) {
        System.out.println("\n" + trackEvent.getName() + " " + listType);
        System.out.println("Date\t\tTime\tPlace");
        for (Race race : raceList) {
            System.out.print(race.getDate() + "\t" + race.getTime() + "\t" + race.getPlacement());
            if (race.isPB() && identifyPBs) {
                System.out.print("\t\tPB");
            }
            System.out.print("\n");
        }
    }

    // EFFECTS: display all races for given event
    private void displayRaces(TrackEvent trackEvent) {
        displayListOfRaces(trackEvent, trackEvent.getAllRaces(), "Races", true);
    }

    // EFFECTS: display all races where a current or past personal best time was achieved
    private void displayPBs(TrackEvent trackEvent) {
        displayListOfRaces(trackEvent, trackEvent.getAllPBs(), "Personal Bests", false);
    }

    // EFFECTS: prompts user to input a positive non-zero integer and returns it
    private int inputPosInteger(String userPrompt) {
        System.out.println(userPrompt);
        int inputInt = input.nextInt();
        if (inputInt > 0) {
            return inputInt;
        } else {
            System.out.println("You must input an positive non-zero integer value \nPlease try again");
            return inputPosInteger(userPrompt);
        }
    }

    // EFFECTS: returns true if time is a positive and non-zero Duration, otherwise returns false and indicates the
    //          issue to the user
    private boolean isPositiveTime(String name, Duration time) {
        if (time.isNegative()) {
            System.out.println(name + " cannot be negative");
            return false;
        } else if (time.isZero()) {
            System.out.println(name + " cannot be zero");
            return false;
        } else {
            return true;
        }
    }
}
