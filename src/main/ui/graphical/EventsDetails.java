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
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        // TODO: get this to display / update
        // TODO: making the swing lists work with util collections
        if (selectedEvent != null) {
            JButton b1 = new JButton("HELLOOOO");
            add(b1);
            raceListModel = new DefaultListModel<>();
            for (Race race : selectedEvent.getAllRaces()) {
                raceListModel.addElement(race);
            }
            raceList = new JList<>();
            JScrollPane racesScrollPane = new JScrollPane(raceList);
            add(racesScrollPane);
        }
    }

    public void setSelectedEvent(Event selectedEvent) {
        this.selectedEvent = selectedEvent;
    }

    public void unselectEvent() {
        selectedEvent = null;
    }
}
