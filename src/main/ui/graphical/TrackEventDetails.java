package ui.graphical;

import model.TrackEvent;
import model.Race;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

/*
Panel with details and interactions related to a specific event
 */
public class TrackEventDetails extends JPanel implements ItemListener {
    private static final String FILTER_FOR_PBS = "Filter for PBs";

    private TrackEvent selectedTrackEvent;
    private DefaultTableModel raceTableModel;
    private JCheckBox filterButton;
    private boolean racesFiltered;

    // EFFECTS: creates event details panel with an empty table of races and a form for adding new races
    public TrackEventDetails() {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        selectedTrackEvent = null;
        makeRacesTable();
        add(new RaceForm(this));
        racesFiltered = false;
    }

    // MODIFIES: this
    // EFFECTS: sets the selected event and populates the table with races of that event
    public void setSelectedEvent(TrackEvent selectedTrackEvent) {
        this.selectedTrackEvent = selectedTrackEvent;
        populateRacesTable();
    }

    // MODIFIES: this
    // EFFECTS: unselects event and clears race table
    public void unselectEvent() {
        selectedTrackEvent = null;
        raceTableModel.setRowCount(0);
    }

    // MODIFIES: this
    // EFFECTS: creates an empty race table
    private void makeRacesTable() {
        Object[] columnNames = {"Place", "Date", "Time", "PB"};
        raceTableModel = new DefaultTableModel(columnNames, 0);
        JTable raceTable = new JTable(raceTableModel);
        raceTable.setDefaultEditor(Object.class, null);

        filterButton = new JCheckBox(FILTER_FOR_PBS);
        filterButton.addItemListener(this);
        add(filterButton);

        JScrollPane scrollPane = new JScrollPane(raceTable);
        add(scrollPane);
    }

    // MODIFIES: this
    // EFFECTS: populates race table with races of selected event
    private void populateRacesTable() {
        raceTableModel.setRowCount(0);
        List<Race> racesToDisplay;

        if (racesFiltered) {
            racesToDisplay = selectedTrackEvent.getAllPBs();
        } else {
            racesToDisplay = selectedTrackEvent.getAllRaces();
        }

        for (Race race : racesToDisplay) {
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
        selectedTrackEvent.addRace(date, time, place);
        populateRacesTable();
    }

    // MODIFIES: this
    // EFFECTS: updates whether race list is filtered for personal bests
    @Override
    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();

        if (source == filterButton) {
            if (e.getStateChange() == ItemEvent.DESELECTED) {
                racesFiltered = false;
                populateRacesTable();
            } else if (e.getStateChange() == ItemEvent.SELECTED) {
                racesFiltered = true;
                populateRacesTable();
            }
        }
    }
}
