package ui.graphical;

import model.Athlete;
import model.Event;
import model.Race;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventsPanel extends JPanel implements ListSelectionListener {
    private static final String ADD_EVENT = "New Event";

    private Athlete athlete;
    private JList<String> eventList;
    private DefaultListModel<String> eventListModel;
    private EventsDetails eventsDetails;


    public EventsPanel(ResultsTrackerGUI resultsTracker) {
        this.athlete = resultsTracker.getAthlete();
        eventsDetails = new EventsDetails();

        eventListModel = new DefaultListModel<>();
        for (String eventName : athlete.getEventNames()) {
            eventListModel.addElement(eventName);
        }

        eventList = new JList<>(eventListModel);
        eventList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        eventList.addListSelectionListener(this);
        JScrollPane eventsScrollPane = new JScrollPane(eventList);
        add(eventsScrollPane);

        JButton newEventButton = new JButton(ADD_EVENT);
        add(newEventButton);
        eventsDetails.setVisible(false);
        add(eventsDetails);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int index = eventList.getSelectedIndex();

            if (index == -1) {
                eventsDetails.unselectEvent();
                eventsDetails.setVisible(false);
            } else {
                String selectedEventName = eventListModel.get(index);
                eventsDetails.setSelectedEvent(athlete.getEvent(selectedEventName));
                eventsDetails.setVisible(true);
            }
        }
    }
}
