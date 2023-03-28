package ui.graphical;

import model.Event;
import model.Race;

import javax.swing.*;
import java.awt.*;

public class EventsDetails extends JPanel {

    private Event selectedEvent;
    private JList<Race> raceList;
    private DefaultListModel<Race> raceListModel;

    public EventsDetails() {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        selectedEvent = null;
        JButton b1 = new JButton("HELLOOOO");
        add(b1);
        raceListModel = new DefaultListModel<>();
        raceList = new JList<>(raceListModel);
        JScrollPane racesScrollPane = new JScrollPane(raceList);
        add(racesScrollPane);
    }

    public void setSelectedEvent(Event selectedEvent) {
        raceListModel.clear();
        this.selectedEvent = selectedEvent;
        raceListModel.addAll(selectedEvent.getAllRaces());
    }



    public void unselectEvent() {
        selectedEvent = null;
        raceListModel.clear();
    }
}
