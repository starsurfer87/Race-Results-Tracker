package ui.graphical;

import model.Event;
import model.Race;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.Duration;
import java.time.LocalDate;

/*
Panel with details and interactions related to a specific event
 */
public class EventDetails extends JPanel {

    private Event selectedEvent;
    DefaultTableModel raceTableModel;
    JTable raceTable;

    // EFFECTS: creates event details panel with an empty table of races and a form for adding new races
    public EventDetails() {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        selectedEvent = null;
        makeRacesTable();
        add(new RaceForm(this));
    }

    // MODIFIES: this
    // EFFECTS: sets the selected event and populates the table with races of that event
    public void setSelectedEvent(Event selectedEvent) {
        this.selectedEvent = selectedEvent;
        populateRacesTable();
    }

    // MODIFIES: this
    // EFFECTS: unselects event and clears race table
    public void unselectEvent() {
        selectedEvent = null;
        raceTableModel.setRowCount(0);
    }

    // MODIFIES: this
    // EFFECTS: creates an empty race table
    private void makeRacesTable() {
        Object[] columnNames = {"Place", "Date", "Time", "PB"};
        raceTableModel = new DefaultTableModel(columnNames, 0);
        raceTable = new JTable(raceTableModel);
        raceTable.setDefaultEditor(Object.class, null);
        JScrollPane scrollPane = new JScrollPane(raceTable);
        add(scrollPane);
    }

    // MODIFIES: this
    // EFFECTS: populates race table with races of selected event
    private void populateRacesTable() {
        raceTableModel.setRowCount(0);
        for (Race race : selectedEvent.getAllRaces()) {
            Object[] row = {race.getPlacement(), race.getDate(), race.getTime(), race.isPB()};
            raceTableModel.addRow(row);
        }
    }

    // REQUIRES: date is more recent than or equal to the date of the most recent race in races, time is a positive
    //           non-zero
    //           Duration, placement >= 1
    // MODIFIES: this
    // EFFECTS: adds race to event and updates contents of race table
    public void addRaceToEvent(LocalDate date, Duration time, int place) {
        selectedEvent.addRace(date, time, place);
        populateRacesTable();
    }
}
