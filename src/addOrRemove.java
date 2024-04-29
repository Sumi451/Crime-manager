import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class addOrRemove extends JFrame {
    private JButton verifyButton;
    private JTextField textField;
    private JButton[] menuButtons;

    public addOrRemove() {
        setTitle("Second Menu");

        // Create text field
        textField = new JTextField(20);

        // Create verify button
        verifyButton = new JButton("Verify");

        // Create panel for text field and verify button
        JPanel inputPanel = new JPanel();
        inputPanel.add(textField);
        inputPanel.add(verifyButton);

        // Create menu buttons
        menuButtons = new JButton[6];
        String[] buttonLabels = {"Add Crime","Add witness to crime", "Add Court/Lawyer to Casefile", "Add Sentence", "Remove Witness",
                                 "Remove Crime/CaseFile"};
        for (int i = 0; i < 6; i++) {
            menuButtons[i] = new JButton(buttonLabels[i]);
            menuButtons[i].setEnabled(false); // Initially disable menu buttons
        }

        // Create panel for menu buttons
        JPanel buttonPanel = new JPanel(new GridLayout(4, 2));
        for (int i = 0; i < 6; i++) {
            buttonPanel.add(menuButtons[i]);
        }

        // Add action listener to verify button
        verifyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inputText = textField.getText();
                if (inputText.equals("12345")) {
                    // Enable menu buttons if input is correct
                    for (JButton button : menuButtons) {
                        button.setEnabled(true);
                    }
                } else {
                    // Show message if input is incorrect
                    JOptionPane.showMessageDialog(addOrRemove.this, "Invalid input!");
                }
            }
        });

        // Add action listeners to menu buttons
        for (int i = 0; i < 6; i++) {
            final int index = i; // Capture index for use in lambda expression
            menuButtons[i].addActionListener(e -> {
                // Handle menu button click
                if (menuButtons[index].getText().equals("Add Crime")) {
                    new DynamicFieldsGenerator("Add Crime");
                } else {
                    new DynamicFieldsGenerator(menuButtons[index].getText());
                }
            });
        }

        // Add panels to frame
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        // Set frame properties
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(addOrRemove::new);
    }
}
