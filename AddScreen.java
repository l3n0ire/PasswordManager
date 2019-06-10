import java.awt.* ;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
/**
 * Interface which allows users to add a new password
 * 
 * @author Colin Lin
 * @version 1.0 2018-12-30
 */
public class AddScreen extends JFrame implements ActionListener
{
    private JLabel title = new JLabel("Add a New Password");
    private JLabel websiteL = new JLabel("Website URL:");
    private JLabel usernameL = new JLabel("Username:");
    private JLabel passwordL = new JLabel("Password:");
    private static JLabel status = new JLabel("");
    private JButton add   = new JButton("Save Password");
    private JButton back   = new JButton("Back to Manager");
    private static JTextField website = new JTextField(7);
    private static JTextField username = new JTextField(7);
    private static JTextField password = new JTextField(7);
    private static String name;
    private Container c = getContentPane();

    public AddScreen(){
        setTitle("Password Manager");
        c.setLayout(new GridLayout(10,1));
        name=LoginScreen.getUserName();
        c.add(title);
        c.add(websiteL);
        c.add(website);
        c.add(usernameL);
        c.add(username);
        c.add(passwordL);
        c.add(password);
        c.add(add);
        c.add(back);
        c.add(status);

        add.addActionListener(this);
        back.addActionListener(this);
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE ); 

    }

    
    public static void resetTextFields(){
        website.setText("");
        username.setText("");
        password.setText("");
    }
    
    public static void savePassword(){
        Processor.savePassword(name,website.getText(),username.getText(),password.getText());
        status.setText("Password Saved");
        resetTextFields();
    }
    
    public static void resetStatus(){
        status.setText("");
    }

    public void actionPerformed( ActionEvent event) {
        JButton button = (JButton) event.getSource();
        if(button.getText().equals("Save Password")){
            PasswordPrompt passwordPrompt = new PasswordPrompt("save");
            passwordPrompt.setSize(300,200);
            passwordPrompt.setVisible(true);

        }
        else if(button.getText().equals("Back to Manager")){
            dispose();
            ManagerScreen managerScreen = new ManagerScreen();
            managerScreen.setSize( 500, 300 );     
            managerScreen.setVisible( true );
            managerScreen.resetWebsite();
            managerScreen.resetStatus();
        }

        revalidate();
        repaint();
    }

} // end of class AddScreen