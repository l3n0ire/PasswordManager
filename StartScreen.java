import java.awt.* ;
import java.awt.event.*;
import javax.swing.*;
/**
 * Interface which redirects new users to the RegisterScreen 
 * and existing users to the LoginScreen
 * contains main method of Password Manager
 * 
 * @author Colin Lin
 * @version 1.0 2018-12-30
 */
public class StartScreen extends JFrame implements ActionListener
{
    private JButton newUser = new JButton("New User");
    private JButton existingUser = new JButton("Existing User");
    private JLabel instructions = new JLabel("Are you a New User or an Existing User?");
    private static boolean isNewUser = false;
    public StartScreen (){
        setTitle( "Password Manager" );
        Container c = getContentPane();
        c.setLayout(new GridLayout(3,1));
        c.add(instructions);
        c.add(newUser);
        c.add(existingUser);
        newUser.addActionListener(this);
        existingUser.addActionListener(this);
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );  

    }
    public static boolean isNewUser(){
        return isNewUser;
    }

    public void actionPerformed( ActionEvent event) {
        JButton clicked = (JButton)event.getSource();

        if(clicked.getText().equals("New User")){
            isNewUser = true;
        }
        else if(clicked.getText().equals("Existing User")){
            isNewUser = false;
        }

        dispose();
        LoginScreen loginScreen = new LoginScreen();
        loginScreen.setSize( 350, 230 );     
        loginScreen.setVisible( true );

    }

    /**
     * Main method of Password Manager
     *
     * @param argument not used
     */
    public static void main(String[] argument)
    {
        Processor.getInputFromFile();
        StartScreen startScreen  = new StartScreen() ;
        startScreen.setSize( 350, 230 );     
        startScreen.setVisible( true );

    } // end of method main(String[] argument)

} // end of class StartScreen