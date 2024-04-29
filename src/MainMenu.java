import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.table.*;

public class MainMenu extends JFrame implements ActionListener {
    JButton caseFileButton, courtButton, crimeButton, personButton, prisonButton, sentenceButton,addorremove;

    public MainMenu() {
        setTitle("Crime Record Management Menu");

        // Create buttons
        caseFileButton = new JButton("View Case Files");
        courtButton = new JButton("View Courts");
        crimeButton = new JButton("View Crimes");
        personButton = new JButton("View Persons");
        prisonButton = new JButton("View Prisons");
        sentenceButton = new JButton("View Sentences");
        addorremove = new JButton("ADD/REMOVE RECORDS(ADMIN)");

        // Add action listeners to buttons
        caseFileButton.addActionListener(this);
        courtButton.addActionListener(this);
        crimeButton.addActionListener(this);
        personButton.addActionListener(this);
        prisonButton.addActionListener(this);
        sentenceButton.addActionListener(this);
        addorremove.addActionListener(this);

        // Create panel to hold buttons
        JPanel buttonPanel = new JPanel(new GridLayout(7, 1));
        buttonPanel.add(caseFileButton);
        buttonPanel.add(courtButton);
        buttonPanel.add(crimeButton);
        buttonPanel.add(personButton);
        buttonPanel.add(prisonButton);
        buttonPanel.add(sentenceButton);
        buttonPanel.add(addorremove);

        // Add panel to frame
        add(buttonPanel);

        // Set frame properties
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }
    private void fetchdatafromtable(String tableName) {
        // Call fetchTableData method from AcessDatabase class
        Vector<Vector<Object>> data = AcessDatabase.fetchTableData(tableName);

        // Create a JFrame to display the data
        JFrame frame = new JFrame("Data from " + tableName);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create a JTable with the fetched data
        DefaultTableModel model = new DefaultTableModel(data, AcessDatabase.getColumnNames(tableName));
        JTable table = new JTable(model);

        // Add the table to a JScrollPane and add it to the frame
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);

        // Set frame properties
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(this);
        frame.setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == caseFileButton) {
            // Handle view case files button click
            fetchdatafromtable("casefile");
            System.out.println("Viewing Case Files...");
        } else if (e.getSource() == courtButton) {
            // Handle view courts button click
            fetchdatafromtable("court");
            System.out.println("Viewing Courts...");
        } else if (e.getSource() == crimeButton) {
            // Handle view crimes button click
            fetchdatafromtable("crime");
            System.out.println("Viewing Crimes...");
        } else if (e.getSource() == personButton) {
            // Handle view persons button click
            fetchdatafromtable("person");
            System.out.println("Viewing Persons...");
        } else if (e.getSource() == prisonButton) {
            // Handle view prisons button click
            fetchdatafromtable("prison");
            System.out.println("Viewing Prisons...");
        } else if (e.getSource() == sentenceButton) {
            // Handle view sentences button click
            fetchdatafromtable("sentence");
            System.out.println("Viewing Sentences...");
        }
        else if (e.getSource()==addorremove){
           //add or remove recods opens
            System.out.println("Opening add or remove");
            addOrRemove addOrRemove= new addOrRemove();
            addOrRemove.setVisible(true);
        }

       
    }


}

