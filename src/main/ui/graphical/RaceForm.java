package ui.graphical;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.time.Duration;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RaceForm extends Form {

    private static final String DATE = "Date";
    private static final String TIME = "Time";
    private static final String PLACE = "Placement";

    EventDetails eventDetails;

    public RaceForm(EventDetails eventDetails) {
        super("Add Race", fieldSet());
        this.eventDetails = eventDetails;
    }

    private static Set<String> fieldSet() {
        Set<String> fields = new HashSet<>();
        fields.add(DATE);
        fields.add(TIME);
        fields.add(PLACE);
        return fields;
    }

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
