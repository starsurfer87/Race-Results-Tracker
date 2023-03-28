package ui.graphical;

import model.Athlete;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/*
Represents main window of the Results Tracker application
 */
public class ResultsTrackerGUI extends JFrame {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 600;

    private Athlete athlete;
    private String fileDest;

    // EFFECTS: sets up window for Results Tracker application starting on title screen
    public ResultsTrackerGUI() {
        super("Race Results Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        fileDest = "./data/AthleteData.json";
        JsonReader reader = new JsonReader(fileDest);
        TitleScreen titleScreen = new TitleScreen(this);
        add(titleScreen);
        centreOnScreen();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS:  location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    public void showMainScreen() {
        MenuBar menu = new MenuBar(this);
        EventsPanel eventsPanel = new EventsPanel(this);


        setLayout(new BorderLayout());
        add(menu, BorderLayout.PAGE_END);
        add(eventsPanel, BorderLayout.CENTER);
    }

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public String getFileDest() {
        return fileDest;
    }
}
