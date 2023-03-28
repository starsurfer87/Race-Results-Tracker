package ui.graphical;

import model.Athlete;
import model.Event;
import model.EventCategory;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class EventsPanel extends JPanel implements ListSelectionListener {
    private static final String ADD_EVENT = "New Event";

    private Athlete athlete;
    private JList<String> eventList;
    private DefaultListModel<String> eventListModel;
    private EventDetails eventDetails;


    public EventsPanel(ResultsTrackerGUI resultsTracker) {
        this.athlete = resultsTracker.getAthlete();
        eventDetails = new EventDetails();

        eventListModel = new DefaultListModel<>();
        populateEventsListModel();
        eventList = new JList<>(eventListModel);
        eventList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        eventList.addListSelectionListener(this);
        JScrollPane eventsScrollPane = new JScrollPane(eventList);
        add(eventsScrollPane);

        EventForm eventForm = new EventForm(this);
        add(eventForm);

        eventDetails.setVisible(false);
        add(eventDetails);
    }

    private void populateEventsListModel() {
        eventListModel.clear();
        for (String eventName : athlete.getEventNames()) {
            eventListModel.addElement(eventName);
        }
    }

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

    public void addEventToAthlete(int distance, EventCategory category) {
        athlete.addEvent(new Event(distance, category));
        populateEventsListModel();
    }
}
