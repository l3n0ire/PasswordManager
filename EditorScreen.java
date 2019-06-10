import java.awt.* ;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
/**
 * Interface that allows users to edit their previously saved passwords
 * 
 * @author Colin Lin
 * @version 1.0 2018-12-03
 */
public  class EditorScreen extends JFrame implements ActionListener
{
    private JLabel title     = new JLabel("Password Manager");
    private JLabel editL = new JLabel("Edit Website Credentials?");
    private JLabel websiteL = new JLabel("Website URL:");
    private JLabel usernameL = new JLabel("Username:");
    private JLabel passwordL = new JLabel("Password:");
    private static JLabel status = new JLabel("");
    private static JTextField website = new JTextField(7);
    private static JTextField username = new JTextField(7);
    private static JTextField password = new JTextField(7);
    private static JTextArea credentials = new JTextArea();
    private JButton back = new JButton("Back to Manager");
    private JButton edit = new JButton("Save Changes");
    private static String currentWebsite;
    private Container c = getContentPane();
    private static String name;
    public EditorScreen(){
        setTitle("PasswordManager");
        c.setLayout(new GridLayout(12,1));
        currentWebsite = ManagerScreen.getWebsite();
        c.add(title);
        name=LoginScreen.getUserName();
        c.add(editL);
        website.setText(currentWebsite);
        c.add(websiteL);
        c.add(website);
        username.setText(Processor.getUsername(name,currentWebsite));
        c.add(usernameL);
        c.add(username);
        password.setText(Processor.getPassword(name,currentWebsite));
        c.add(passwordL);
        c.add(password);
        c.add(edit);
        c.add(back);
        c.add(status);
        back.addActionListener(this);
        edit.addActionListener(this);
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
    }

    public static void changePassword(){
        Processor.deletePassword(name,currentWebsite);
        Processor.savePassword(name,website.getText(),username.getText(),password.getText());
        currentWebsite= website.getText();
        status.setText("Changes Saved");
    }
    
    public static void resetStatus(){
        status.setText("");
    }

    public void actionPerformed( ActionEvent event) {
        JButton button = (JButton)event.getSource();
        if(button.getText().equals("Back to Manager")){
            dispose();
            ManagerScreen managerScreen = new ManagerScreen();
            managerScreen.setSize( 500, 300 );     
            managerScreen.setVisible( true );
            managerScreen.resetWebsite();
            managerScreen.resetStatus();
        }
        else if(button.getText().equals("Save Changes")){
            PasswordPrompt passwordPrompt = new PasswordPrompt("change");
            passwordPrompt.setSize(300,200);
            passwordPrompt.setVisible(true);

        }
        revalidate();
        repaint();
    }
}

 // end of class EditPassword