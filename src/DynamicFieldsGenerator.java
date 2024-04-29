import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class DynamicFieldsGenerator extends JFrame {
    public DynamicFieldsGenerator(String buttonLabel) {
        setTitle("Dynamic Fields Generator");

        JPanel panel = new JPanel(new GridLayout(0, 2));

        if (buttonLabel.equals("Add Crime")) {
            JTextField descriptionField = new JTextField();
            JTextField locationField = new JTextField();
            JTextField officerIdField = new JTextField();
            JTextField witnessNameField = new JTextField();
            JTextField criminalNameField = new JTextField();
            JTextField criminaladdressField= new JTextField();
            JTextField witnessaddressField=new JTextField();
            JTextField witnessageField=new JTextField();
            JTextField criminalageField=new JTextField();
            // Create and add text fields for adding crime data
            panel.add(new JLabel("Description:"));
            panel.add(descriptionField);
            panel.add(new JLabel("Location:"));
            panel.add(locationField);
            panel.add(new JLabel("Officer ID:"));
            panel.add(officerIdField);
            panel.add(new JLabel("Witness Name:"));
            panel.add(witnessNameField);
            panel.add(new JLabel("Criminal Name"));
            panel.add(criminalNameField);
            panel.add(new JLabel("Criminal Address"));
            panel.add(criminaladdressField);
            panel.add(new JLabel("Witness Address"));
            panel.add(witnessaddressField);
            panel.add(new JLabel("Witness Age:"));
            panel.add(witnessageField);
            panel.add(new JLabel("Criminal Age"));
            panel.add(criminalageField);
            JButton submitButton = new JButton("Submit");
            submitButton.addActionListener(e -> {
                String description = descriptionField.getText();
                String location = locationField.getText();//crime location
                int officerId =Integer.parseInt (officerIdField.getText());
                String witnessName = witnessNameField.getText();
                String criminalName = criminalNameField.getText();
                String criminal_address=criminaladdressField.getText();
                String witness_address=witnessaddressField.getText();
            
                int witness_age=Integer.parseInt(witnessageField.getText());
                int criminal_age=Integer.parseInt(criminalageField.getText());

                AcessDatabase.addCrime(description,location,officerId,witnessName,criminalName,criminal_address,witness_address,witness_age,criminal_age);
                // Handle submit button click
                // You can perform actions like validating input and saving data here
                // For demonstration, let's just close the window
                setVisible(false);
                dispose(); // Dispose the window
            });
            // Add submit button to panel
         panel.add(submitButton);
        } else if (buttonLabel.equals("Add witness to crime")) {
             JTextField witnessidField=new JTextField();
             JTextField crimeidField=new JTextField();
            // Create and add text fields for removing data
            panel.add(new JLabel("Witness Id"));
            panel.add(witnessidField);
            panel.add(new JLabel("Crime ID"));
            panel.add(crimeidField);
          
            JButton submitButton=new JButton("Submit");
            submitButton.addActionListener(e -> {
                int witness_id=Integer.parseInt(witnessidField.getText());
                int crime_id=Integer.parseInt(crimeidField.getText());
                try {
                    AcessDatabase.addWitnessToCrime(witness_id,crime_id);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                // Handle submit button click
                // You can perform actions like validating input and saving data here
                // For demonstration, let's just close the window
                setVisible(false);
                dispose(); // Dispose the window
            });
             // Add submit button to panel
         panel.add(submitButton);
        }
        else if (buttonLabel.equals("Add Sentence")) {
            // Create and add text fields for removing data
            panel.add(new JLabel("Description"));
            panel.add(new JTextField());
            panel.add(new JLabel("Criminal ID"));
            panel.add(new JTextField());
            panel.add(new JLabel("Judge ID"));
            panel.add(new JTextField());
            panel.add(new JLabel("Court ID"));
            panel.add(new JTextField());
            JButton submitButton=new JButton("Submit");
            submitButton.addActionListener(e -> {
                // Handle submit button click
                // You can perform actions like validating input and saving data here
                // For demonstration, let's just close the window
                setVisible(false);
                dispose(); // Dispose the window
            });
             // Add submit button to panel
         panel.add(submitButton);
        }
        else if (buttonLabel.equals("Remove Witness")) {
            // Create and add text fields for removing data
            panel.add(new JLabel("Witness ID"));
            panel.add(new JTextField());
           
            JButton submitButton=new JButton("Submit");
            submitButton.addActionListener(e -> {
                // Handle submit button click
                // You can perform actions like validating input and saving data here
                // For demonstration, let's just close the window
                setVisible(false);
                dispose(); // Dispose the window
            });
             // Add submit button to panel
         panel.add(submitButton);
        }
        else if (buttonLabel.equals("Remove Crime/CaseFile")) {
            // Create and add text fields for removing data
            panel.add(new JLabel("Criminal Id"));
            panel.add(new JTextField());
            panel.add(new JLabel("Crime ID"));
            panel.add(new JTextField());
           
            JButton submitButton=new JButton("Submit");
            submitButton.addActionListener(e -> {
                // Handle submit button click
                // You can perform actions like validating input and saving data here
                // For demonstration, let's just close the window
                setVisible(false);
                dispose(); // Dispose the window
            });
             // Add submit button to panel
         panel.add(submitButton);
        }
        else if (buttonLabel.equals("Add witness to crime")) {
            // Create and add text fields for removing data
            panel.add(new JLabel("Crime Id"));
            panel.add(new JTextField());
            panel.add(new JLabel("Witness Id"));
            panel.add(new JTextField());
           
            JButton submitButton=new JButton("Submit");
            submitButton.addActionListener(e -> {
                // Handle submit button click
                // You can perform actions like validating input and saving data here
                // For demonstration, let's just close the window
                setVisible(false);
                dispose(); // Dispose the window
            });
             // Add submit button to panel
         panel.add(submitButton);
        }
         // Create submit button
       
 
        
 

        // Add panel to frame
        add(panel);

        // Set frame properties
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose on close, as it's a popup window
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }
}
