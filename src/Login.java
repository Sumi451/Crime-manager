//import required classes and packages  
import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;  
import java.lang.Exception;  

//create CreateLoginForm class to create login form  
//class extends JFrame to create a window where our component add  
//class implements ActionListener to perform an action on button click 
public class Login extends JFrame implements ActionListener {
    JButton b1;  
    JPanel newPanel;  
    JLabel userLabel, passLabel;  
    JTextField  textField1, textField2; 

     Login()  
    {     
          
        //create label for username   
        userLabel = new JLabel();  
        userLabel.setText("Username");      //set label value for textField1  
          
        //create text field to get username from the user  
        textField1 = new JTextField(15);    //set length of the text  
  
        //create label for password  
        passLabel = new JLabel();  
        passLabel.setText("Password");      //set label value for textField2  
          
        //create text field to get password from the user  
        textField2 = new JPasswordField(15);    //set length for the password  
          
        //create submit button  
        b1 = new JButton("SUBMIT"); //set label to button  
          
        //create panel to put form elements  
        newPanel = new JPanel(new GridLayout(3, 1));  
        newPanel.add(userLabel);    //set username label to panel  
        newPanel.add(textField1);   //set text field to panel  
        newPanel.add(passLabel);    //set password label to panel  
        newPanel.add(textField2);   //set text field to panel  
        newPanel.add(b1);           //set button to panel  
          
        //set border to panel   
        add(newPanel, BorderLayout.CENTER);  
          
        //perform action on button click   
        b1.addActionListener(this);     //add action listener to button  
        setTitle("LOGIN FORM");         //set title to the login form  
    } 
    //define abstract method actionPerformed() which will be called on button click   
    public void actionPerformed(ActionEvent ae)     //pass action listener as a parameter  
    {  
        String userValue = textField1.getText();        //get user entered username from the textField1  
        String passValue = textField2.getText();  
        
         //check whether the credentials are authentic or not  
       boolean loggedIn= AcessDatabase.checkLoginCredentials(userValue, passValue) ;
       if(loggedIn==true)
       {
        //open new page
        System.out.println("Opening new page");
        MainMenu menu= new MainMenu();
        menu.setVisible(true);
        dispose();
        
       }
       else
       {
        System.out.println("Error occured check ur credentials");
       }
       
    }  

    
}
class LoginFormDemo  
{  
    //main() method start  
    public static void main(String arg[])  
    {  
        try  
        {  
            //create instance of the CreateLoginForm  
            Login form = new Login();  
            form.setSize(300,100);  //set size of the frame  
            form.setVisible(true);  //make form visible to the user  
        }  
        catch(Exception e)  
        {     
            //handle exception   
            JOptionPane.showMessageDialog(null, e.getMessage());  
        }  
    }  
}  