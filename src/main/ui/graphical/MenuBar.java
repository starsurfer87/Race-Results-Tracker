package ui.graphical;

import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
Represents menu bar of Results Tracker application
 */
public class MenuBar extends JPanel implements ActionListener {
    private ResultsTrackerGUI resultsTracker;
    private JsonWriter jsonWriter;

    // EFFECTS: creates a menu bar with a save button, a ResultsTrackerGUI that it is
    //         part of, and a JsonWriter to write data to file
    public MenuBar(ResultsTrackerGUI resultsTracker) {
        this.resultsTracker = resultsTracker;
        jsonWriter = new JsonWriter(resultsTracker.getFileDest());
        JButton saveButton = new JButton("Save");
        add(saveButton);
        saveButton.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: saves athlete to file
    @Override
    public void actionPerformed(ActionEvent evt) {
        try {
            jsonWriter.open();
            jsonWriter.write(resultsTracker.getAthlete());
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "System Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            jsonWriter.close(); //TODO: fix null pointer exception
        }
    }
}
