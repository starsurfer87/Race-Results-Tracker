package ui.graphical;

import javax.swing.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.*;

/*
Form for creating and adding a new race to an event
 */
public class RaceForm extends Form {

    private static final String DATE = "Date";
    private static final String TIME = "Time";
    private static final String PLACE = "Placement";

    EventDetails eventDetails;

    // EFFECTS: creates a form for adding a new race
    public RaceForm(EventDetails eventDetails) {
        super("Add Race", getInputValues());
        this.eventDetails = eventDetails;
    }

    // EFFECTS: returns list of names of values needed in order to create a race
    private static List<String> getInputValues() {
        List<String> inputValues = new ArrayList<>();
        inputValues.add(DATE);
        inputValues.add(TIME);
        inputValues.add(PLACE);
        return inputValues;
    }

    // REQUIRES: key values of userInputs match values returned by getInputValues()
    // MODIFIES: this
    // EFFECTS: if user inputs can be parsed to needed values, adds new race using these values, otherwise displays
    //          error message pop-up
    protected void handleUserInputs(Map<String, String> userInputs) {
        try {
            LocalDate date = LocalDate.parse(userInputs.get(DATE));
            Duration time = Duration.parse(userInputs.get(TIME));
            int place = Integer.parseInt(userInputs.get(PLACE));
            eventDetails.addRaceToEvent(date, time, place);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Input Not Valid",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
