package ui.graphical;

import exceptions.InvalidInputException;
import model.EventCategory;

import javax.swing.*;
import java.util.*;

/*
Form for creating and adding a new event
 */
public class EventForm extends Form {

    private static final String DISTANCE = "Distance";
    private static final String CATEGORY = "Category (SP, MD, LD, H, SC, or RW)";

    EventsPanel eventsPanel;

    // EFFECTS: creates a form for adding a new event
    public EventForm(EventsPanel eventsPanel) {
        super("Add Event", getInputValues());
        this.eventsPanel = eventsPanel;
    }

    // EFFECTS: returns list of names of values needed in order to create an event
    private static List<String> getInputValues() {
        List<String> inputValues = new ArrayList<>();
        inputValues.add(DISTANCE);
        inputValues.add(CATEGORY);
        return inputValues;
    }

    // REQUIRES: key values of userInputs match values returned by getInputValues()
    // MODIFIES: this
    // EFFECTS: if user inputs can be parsed to needed values, adds new event using these values, otherwise displays
    //          error message pop-up
    protected void handleUserInputs(Map<String, String> userInputs) {
        try {
            int distance = Integer.parseInt(userInputs.get(DISTANCE));
            EventCategory category = parseCategory(userInputs.get(CATEGORY));
            eventsPanel.addEventToAthlete(distance, category);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Input Not Valid",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // EFFECTS: parses EventCategory from categoryString
    private EventCategory parseCategory(String categoryString) throws InvalidInputException {
        EventCategory category;

        switch (categoryString) {
            case "SP": category = EventCategory.SPRINT;
                break;
            case "MD": category = EventCategory.MID_DIST;
                break;
            case "LD": category = EventCategory.LONG_DIST;
                break;
            case "H": category = EventCategory.HURDLES;
                break;
            case "SC": category = EventCategory.STEEPLECHASE;
                break;
            case "RW": category = EventCategory.RACE_WALK;
                break;
            default:
                throw new InvalidInputException();
        }

        return category;
    }
}
