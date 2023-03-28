package ui.graphical;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/*
Form collecting and acting upon user input
 */
public abstract class Form extends JPanel implements ActionListener {

    protected String actionName;
    protected Map<String, JTextField> values;

    // REQUIRES: valueNames has no duplicate elements
    // EFFECTS: creates a form with a text input field for each element in valueNames (maintaining order), and a
    //          submission button corresponding to actionName
    public Form(String actionName, List<String> valueNames) {
        setLayout(new BoxLayout(this,
                BoxLayout.PAGE_AXIS));
        this.actionName = actionName;
        values = new HashMap<>();
        for (String valueName : valueNames) {
            addTextField(valueName);
        }
        addSubmitButton();
    }

    // MODIFIES: this
    // EFFECTS: adds a text input field for valueName to the form
    protected void addTextField(String valueName) {
        JTextField field = new JTextField(10);
        values.put(valueName, field);
        add(new JLabel(valueName));
        add(field);
    }

    // MODIFIES: this
    // EFFECTS: ands a submission button corresponding to actionName to the form
    private void addSubmitButton() {
        JButton submitButton = new JButton(actionName);
        submitButton.setActionCommand(actionName);
        submitButton.addActionListener(this);
        add(submitButton);
    }

    // MODIFIES: this
    // EFFECTS: collects user inputs and clears text from all text input fields
    private Map<String, String> collectInputs() {
        Map<String, String> userInputs = new HashMap<>();

        for (String valueName : values.keySet()) {
            JTextField field = values.get(valueName);
            String userInput = field.getText();
            userInputs.put(valueName, userInput);
            field.setText("");
        }

        return userInputs;
    }

    // MODIFIES: this
    // EFFECTS: collects and acts upon user inputs and clears form
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(actionName)) {
            Map<String, String> userInputs = collectInputs();
            handleUserInputs(userInputs);
        }
    }

    // EFFECTS: handles inputs collected from user
    protected abstract void handleUserInputs(Map<String, String> userInputs);
}
