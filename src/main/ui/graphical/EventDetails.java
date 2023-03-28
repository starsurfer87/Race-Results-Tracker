package ui.graphical;

import model.Event;
import model.Race;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.Duration;
import java.time.LocalDate;

public class EventDetails extends JPanel {

    private Event selectedEvent;
    DefaultTableModel raceTableModel;
    JTable raceTable;

    public EventDetails() {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        selectedEvent = null;
        makeRacesTable();
        add(new RaceForm(this));
    }

    public void setSelectedEvent(Event selectedEvent) {
        this.selectedEvent = selectedEvent;
        populateRacesTable();
    }

    public void unselectEvent() {
        selectedEvent = null;
        raceTableModel.setRowCount(0);
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

    public void addRaceToEvent(LocalDate date, Duration time, int place) {
        selectedEvent.addRace(date, time, place);
        populateRacesTable();
    }
}
