package ui.graphical;

import model.Athlete;

import javax.swing.*;
import java.awt.*;

/*
Represents main window of the Results Tracker application
 */
public class ResultsTrackerGUI extends JFrame {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;
    public static final int CTR_X = WIDTH / 2;

    private Athlete athlete;
    private TitleScreen ts;

    // EFFECTS: sets up window for Results Tracker application starting on title screen
    public ResultsTrackerGUI() {
        super("Race Results Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        ts = new TitleScreen(this);
        add(ts);
        centreOnScreen();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS:  location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
    }
}
