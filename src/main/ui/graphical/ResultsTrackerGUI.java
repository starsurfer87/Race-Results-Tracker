package ui.graphical;

import model.Athlete;
import model.log.Event;
import model.log.EventLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/*
Main window of the Results Tracker application
 */
public class ResultsTrackerGUI extends JFrame {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;

    private Athlete athlete;
    private String fileDest;

    // EFFECTS: sets up window for Results Tracker application starting on title screen
    public ResultsTrackerGUI() {
        super("Race Results Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        fileDest = "./data/AthleteData.json";
        TitleScreen titleScreen = new TitleScreen(this);
        add(titleScreen);
        centreOnScreen();
        addWindowListener(new ApplicationWindowListener());
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS:  location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    // MODIFIES: this
    // EFFECTS: sets up main screen of the Results Tracker application
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

    private class ApplicationWindowListener implements WindowListener {
        @Override
        public void windowOpened(WindowEvent e) {

        }

        @Override
        public void windowClosing(WindowEvent e) {
            printEventLog();
        }

        @Override
        public void windowClosed(WindowEvent e) {

        }

        @Override
        public void windowIconified(WindowEvent e) {

        }

        @Override
        public void windowDeiconified(WindowEvent e) {

        }

        @Override
        public void windowActivated(WindowEvent e) {

        }

        @Override
        public void windowDeactivated(WindowEvent e) {

        }

        private void printEventLog() {
            for (Event loggedEvent : EventLog.getInstance()) {
                System.out.println(loggedEvent.toString());
            }
        }
    }
}
