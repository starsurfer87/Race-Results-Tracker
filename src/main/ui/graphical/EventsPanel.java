package ui.graphical;

import model.Athlete;
import model.Event;
import model.EventCategory;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/*
Panel with information and interactions related to events
 */
public class EventsPanel extends JPanel implements ListSelectionListener {
    private static final String ADD_EVENT = "New Event";

    private Athlete athlete;
    private JList<String> eventList;
    private DefaultListModel<String> eventListModel;
    private EventDetails eventDetails;

    // EFFECTS: creates a panel with a list of the athlete's events, a form for adding new events, and an event details
    //          panel which is initially hidden
    public EventsPanel(ResultsTrackerGUI resultsTracker) {
        this.athlete = resultsTracker.getAthlete();

        eventListModel = new DefaultListModel<>();
        populateEventsListModel();
        eventList = new JList<>(eventListModel);
        eventList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        eventList.addListSelectionListener(this);
        JScrollPane eventsScrollPane = new JScrollPane(eventList);
        add(eventsScrollPane);

        EventForm eventForm = new EventForm(this);
        add(eventForm);

        eventDetails = new EventDetails();
        eventDetails.setVisible(false);
        add(eventDetails);
    }

    // MODIFIES: this
    // EFFECTS: updates contents of the list of events
    private void populateEventsListModel() {
        eventListModel.clear();
        for (String eventName : athlete.getEventNames()) {
            eventListModel.addElement(eventName);
        }
    }

    // MODIFIES: this
    // EFFECTS: displays or hides event details depending on whether an event is selected, updating displayed
    //          information according to the selected event
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int index = eventList.getSelectedIndex();

            if (index == -1) {
                eventDetails.unselectEvent();
                eventDetails.setVisible(false);
            } else {
                String selectedEventName = eventListModel.get(index);
                eventDetails.setSelectedEvent(athlete.getEvent(selectedEventName));
                eventDetails.setVisible(true);
            }
        }
    }

    // REQUIRES: distance > 0
    // MODIFIES: this
    // EFFECTS: adds an event to the athlete
    public void addEventToAthlete(int distance, EventCategory category) {
        athlete.addEvent(new Event(distance, category));
        populateEventsListModel();
    }
}
