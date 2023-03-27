package ui.graphical;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventsPanel extends JPanel implements ActionListener {
    private ResultsTrackerGUI resultsTracker;

    public EventsPanel(ResultsTrackerGUI resultsTracker) {
        this.resultsTracker = resultsTracker;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
