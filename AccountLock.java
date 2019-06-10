import java.awt.* ;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
/**
 * Locks password manager account for 60 seconds
 * 
 * @author Colin Lin
 * @version 1.0 2018-12-30
 */
public class AccountLock extends JFrame
{
    private JLabel message1 = new JLabel("Your Account has been locked!");
    private JLabel message2 = new JLabel("Please try again in 15 seconds");
    private Container c = getContentPane();
    private java.util.Timer timer = new java.util.Timer();
    private static int time=15;
    private static String whichAction;

    public AccountLock(String s){
        whichAction = s;
        c.setLayout( new BoxLayout(c,BoxLayout.PAGE_AXIS) );
        c.add(message1);
        message1.setAlignmentX(CENTER_ALIGNMENT);
        c.add(message2);
        message2.setAlignmentX(CENTER_ALIGNMENT);
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        timer.scheduleAtFixedRate(new TimerTask() {
        @Override
        public void run() {
            if(time<=0){
            timer.cancel();
            timer.purge();
            Processor.resetCounter();
            dispose();
            PasswordPrompt passwordPrompt = new PasswordPrompt(whichAction);
            passwordPrompt.setSize(300,200);
            passwordPrompt.setVisible(true);
            time=15;
            passwordPrompt.resetCount();
        }
        else{
            time--;
        message2.setText("Please try again in "+time+" seconds");
    }
        }
    }, 1000, 1000);
      
    }
    
   

    

} // end of class AccountLock