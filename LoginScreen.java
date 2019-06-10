import java.awt.* ;
import java.awt.event.*;
import javax.swing.*;
/**
 * Interface which allows existing users to login and new users to register
 * 
 * 
 * @author Colin Lin
 * @version 1.0 2018-12-03
 */
public class LoginScreen extends JFrame implements ActionListener{
    private JLabel title     = new JLabel("Password Manager");
    private JLabel usernameL = new JLabel("Username:");
    private JLabel passwordL = new JLabel("Password:");
    private JLabel welcome = new JLabel("");
    private JButton login   = new JButton("Login");
    private JButton register   = new JButton("Register");
    private JTextField username = new JTextField(16);
    private JPasswordField password = new JPasswordField(16);
    private Container c = getContentPane();
    private JButton back = new JButton("Back");
    private static String name ="";

    public LoginScreen(){                   //constructor
        setTitle( "Password Manager" );
                                        
        c.setLayout( new BoxLayout(c,BoxLayout.PAGE_AXIS) );
        c.add( title); 
        title.setAlignmentX(CENTER_ALIGNMENT);
        c.add(usernameL);
        usernameL.setAlignmentX(CENTER_ALIGNMENT);
        c.add(username);
        username.setAlignmentX(CENTER_ALIGNMENT);
        c.add(passwordL);
        passwordL.setAlignmentX(CENTER_ALIGNMENT);
        c.add(password);
        password.setAlignmentX(CENTER_ALIGNMENT);
        
        loginOrRegister();
        
        c.add(welcome);
        welcome.setAlignmentX(CENTER_ALIGNMENT);
        c.add(back);
        back.setAlignmentX(CENTER_ALIGNMENT);
        back.addActionListener(this);
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );  

    }
    
    public  void loginOrRegister(){
        if(StartScreen.isNewUser()){
            c.add(register);
            register.setAlignmentX(CENTER_ALIGNMENT);
            register.addActionListener( this );
        }
        else{
            c.add(login);
            login.setAlignmentX(CENTER_ALIGNMENT);
            login.addActionListener( this );
        }
    }
    public void actionPerformed( ActionEvent event) {
        JButton clicked = (JButton)event.getSource();
        String output="";
        name = username.getText();
        String pass = password.getText();
        if(clicked.getText().equals("Back")){
            dispose();
            StartScreen startScreen  = new StartScreen() ;
            startScreen.setSize( 350, 230 );     
            startScreen.setVisible( true ); 
        }
        else if(clicked.getText().equals("Login")){
            output = Processor.login(name,pass);
        }
        else if(clicked.getText().equals("Register")){
            output = Processor.register(name, pass);
        }
        welcome.setText(output);
        repaint();
        if(output.indexOf("Welcome")>=0){
            dispose();
            ManagerScreen managerScreen = new ManagerScreen();
            managerScreen.setSize( 500, 300 );     
            managerScreen.setVisible( true );
            managerScreen.resetStatus();
        }
    }
    
     public static String getUserName(){
        return name;
    }

    public static void main ( String[] args ){
        Processor.getInputFromFile();
        LoginScreen loginScreen  = new LoginScreen() ;
        loginScreen.setSize( 350, 230 );     
        loginScreen.setVisible( true ); 

        
    }   // end of main
}     // end of class    
