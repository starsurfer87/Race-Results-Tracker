package ui.graphical;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class Form extends JPanel implements ActionListener {

    protected String actionName;
    protected Map<String, JTextField> fields;

    public Form(String actionName, Set<String> fieldNames) {
        setLayout(new BoxLayout(this,
                BoxLayout.PAGE_AXIS));
        this.actionName = actionName;
        fields = new HashMap<>();
        for (String fieldName : fieldNames) {
            addTextInput(fieldName);
        }
        addSubmitButton();
    }

    protected void addTextInput(String fieldName) {
        JTextField field = new JTextField(10);
        fields.put(fieldName, field);
        add(new JLabel(fieldName));
        add(field);
    }

    private void addSubmitButton() {
        JButton submitButton = new JButton(actionName);
        submitButton.setActionCommand(actionName);
        submitButton.addActionListener(this);
        add(submitButton);
    }

    private Map<String, String> collectInputs() {
        Map<String, String> userInputs = new HashMap<>();

        for (String fieldName : fields.keySet()) {
            JTextField field = fields.get(fieldName);
            String userInput = field.getText();
            userInputs.put(fieldName, userInput);
            field.setText("");
        }

        return userInputs;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(actionName)) {
            Map<String, String> userInputs = collectInputs();
            handleUserInputs(userInputs);
        }
    }

    protected abstract void handleUserInputs(Map<String, String> userInputs);
}
