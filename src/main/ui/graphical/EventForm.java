package ui.graphical;

import exceptions.InvalidInputException;
import model.EventCategory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.time.Duration;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EventForm extends Form {

    private static final String DISTANCE = "Distance";
    private static final String CATEGORY = "Category (SP, MD, LD, H, SC, or RW)";

    EventsPanel eventsPanel;

    public EventForm(EventsPanel eventsPanel) {
        super("Add Event", fieldSet());
        this.eventsPanel = eventsPanel;
    }

    private static Set<String> fieldSet() {
        Set<String> fields = new HashSet<>();
        fields.add(DISTANCE);
        fields.add(CATEGORY);
        return fields;
    }

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
