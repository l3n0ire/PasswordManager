import java.awt.* ;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
/**
 * Prompts user for master password
 * 
 * @author Colin Lin
 * @version 1.0 2018-12-31
 */
public class PasswordPrompt extends JFrame implements ActionListener
{
   private JLabel instructions = new JLabel("Please Enter Your Master Password");
   private JLabel attemptsLeft = new JLabel("5 attempts left.");
   private JPasswordField passwordf = new JPasswordField(12);
   private JButton ok = new JButton("OK");
   private JButton cancel = new JButton("Cancel");
   private JPanel buttons = new JPanel();
   private static String name;
   private static String password;
   private static boolean isValidPassword = false;
   private static int invalidPassCounter;
   private static String whichAction;
   public PasswordPrompt(String s){
       Container c = getContentPane();
       name=LoginScreen.getUserName();
       whichAction=s;
       invalidPassCounter=0;
       c.setLayout(new GridLayout(4,1));
       c.add(instructions);
       c.add(attemptsLeft);
       c.add(passwordf);
       buttons.setLayout(new GridLayout(1,2));
       buttons.add(cancel);
       buttons.add(ok);
       c.add(buttons);
       
       cancel.addActionListener(this);
       ok.addActionListener(this);
    }
   public static void resetCount(){
       invalidPassCounter=0;
    }
    
   public void actionPerformed( ActionEvent event) {
       JButton button = (JButton) event.getSource();
       password = passwordf.getText();
       if(button.getText().equals("OK") && invalidPassCounter<5){
           if(Processor.isValidPassword(name,password)){
               isValidPassword = true;
               if(whichAction.equals("delete")){
               ManagerScreen.deletePassword();
            }
            else if (whichAction.equals("save")){
                AddScreen.savePassword();
            }
            else if (whichAction.equals("change")){
                EditorScreen.changePassword();
                } 
               dispose();
            }
           else{
               invalidPassCounter++;
               int attempts =5-invalidPassCounter;
               instructions.setText("Incorrect Password Please Try Again");
               attemptsLeft.setText(attempts+" attempts left");
            }
        }
       else if(button.getText().equals("Cancel")){
           dispose();
        }
        if(invalidPassCounter==5){
           dispose();
           AccountLock accountLock = new AccountLock(whichAction);
           accountLock.setSize(300,100);
           accountLock.setVisible(true);
        }
    }
   
   


} // end of class PasswordPrompt