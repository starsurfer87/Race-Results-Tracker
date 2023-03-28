package ui.graphical;

import model.Athlete;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/*
Represents the title screen of the Results Tracker application
 */
public class TitleScreen extends JPanel implements ActionListener {

    private static final String NEW_ATHLETE = "New Athlete";
    private static final String LOAD_ATHLETE = "Load Athlete From File";

    private ResultsTrackerGUI resultsTracker;
    private JsonReader jsonReader;

    // EFFECTS: creates title screen with main logo, buttons for selecting athlete, a ResultsTrackerGUI that it is
    //          part of, and a JsonReader to read data from file
    public TitleScreen(ResultsTrackerGUI resultsTracker) {
        this.resultsTracker = resultsTracker;
        jsonReader = new JsonReader(resultsTracker.getFileDest());

        setBackground(Color.WHITE);
        addVisuals();
    }

    // MODIFIES: this
    // EFFECTS: adds main logo and buttons to title screen
    private void addVisuals() {
        ImageIcon trackIcon = new ImageIcon("data/images/track_logo.png");
        JLabel trackLogo = new JLabel(trackIcon);
        trackLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton buttonNew = new JButton(NEW_ATHLETE);
        buttonNew.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton buttonLoad = new JButton(LOAD_ATHLETE);
        buttonLoad.setAlignmentX(Component.CENTER_ALIGNMENT);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(trackLogo);
        add(Box.createRigidArea(new Dimension(0, 30)));
        add(buttonNew);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(buttonLoad);

        buttonNew.addActionListener(this);
        buttonLoad.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: sets athlete to either a new athlete or an athlete loaded from file
    @Override
    public void actionPerformed(ActionEvent evt) {
        try {
            if (evt.getActionCommand().equals(NEW_ATHLETE)) {
                resultsTracker.setAthlete(new Athlete("Skye"));
            } else if (evt.getActionCommand().equals(LOAD_ATHLETE)) {
                resultsTracker.setAthlete(jsonReader.read());
            }
            this.setVisible(false);
            resultsTracker.showMainScreen();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error Reading File",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}

