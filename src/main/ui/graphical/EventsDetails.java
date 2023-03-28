package ui.graphical;

import model.Event;
import model.Race;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EventsDetails extends JPanel {

    private Event selectedEvent;
    DefaultTableModel raceTableModel;
    JTable raceTable;

    public EventsDetails() {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        selectedEvent = null;
        makeRacesTable();
    }

    public void setSelectedEvent(Event selectedEvent) {
        this.selectedEvent = selectedEvent;
        populateRacesTable();
    }

    private void makeRacesTable() {
        Object[] columnNames = {"Place", "Date", "Time", "PB"};
        raceTableModel = new DefaultTableModel(columnNames, 0);
        raceTable = new JTable(raceTableModel);
        raceTable.setDefaultEditor(Object.class, null);
        JScrollPane scrollPane = new JScrollPane(raceTable);
        add(scrollPane);
    }

    private void populateRacesTable() {
        raceTableModel.setRowCount(0);
        for (Race race : selectedEvent.getAllRaces()) {
            Object[] row = {race.getPlacement(), race.getDate(), race.getTime(), race.isPB()};
            raceTableModel.addRow(row);
        }
    }

    public void unselectEvent() {
        selectedEvent = null;
        raceTableModel.setRowCount(0);
    }
}
