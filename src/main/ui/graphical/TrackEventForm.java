package ui.graphical;

import model.TrackEventCategory;
import ui.exceptions.InvalidInputException;

import javax.swing.*;
import java.util.*;

/*
Form for creating and adding a new event
 */
public class TrackEventForm extends Form {

    private static final String DISTANCE = "Distance";
    private static final String CATEGORY = "Category (SP, MD, LD, H, SC, or RW)";

    TrackEventsPanel trackEventsPanel;

    // EFFECTS: creates a form for adding a new event
    public TrackEventForm(TrackEventsPanel trackEventsPanel) {
        super("Add Event", getInputValues());
        this.trackEventsPanel = trackEventsPanel;
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
            TrackEventCategory category = parseCategory(userInputs.get(CATEGORY));
            trackEventsPanel.addTrackEventToAthlete(distance, category);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Input Not Valid",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // EFFECTS: parses EventCategory from categoryString
    private TrackEventCategory parseCategory(String categoryString) throws InvalidInputException {
        TrackEventCategory category;

        switch (categoryString) {
            case "SP": category = TrackEventCategory.SPRINT;
                break;
            case "MD": category = TrackEventCategory.MID_DIST;
                break;
            case "LD": category = TrackEventCategory.LONG_DIST;
                break;
            case "H": category = TrackEventCategory.HURDLES;
                break;
            case "SC": category = TrackEventCategory.STEEPLECHASE;
                break;
            case "RW": category = TrackEventCategory.RACE_WALK;
                break;
            default:
                throw new InvalidInputException();
        }

        return category;
    }
}
