import java.awt.* ;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
/**
 * Interface which allows users to add,edit, and delete their passwords
 * 
 * @author Colin Lin
 * @version 1.0 2018-12-03
 */
public class ManagerScreen extends JFrame implements ActionListener {
    private JLabel title     = new JLabel("Password Manager");
    private static JLabel status = new JLabel("");
    private static JLabel credentials = new JLabel("Please Select a Website");
    private JButton add   = new JButton("Add a New Password");
    private JButton viewEdit = new JButton("View/Edit Password");
    private JButton logout = new JButton("Log Out");
    private JButton delete = new JButton("Delete");
    private static String name;
    private JScrollPane listScroller;
    private Container c = getContentPane();
    private static String selectedWebsite;
    private DefaultListModel listmodel = new DefaultListModel();
    private static JList list = new JList();;
    private static String[] websites;

    MouseListener mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {

                    selectedWebsite = (String) list.getSelectedValue();
                    credentials.setText("Selected Website: "+selectedWebsite);
                }

                revalidate();
                repaint();
            }
        };
    public ManagerScreen(){
        setTitle( "Password Manager" );
        Processor.getInputFromFile();
        selectedWebsite="";
        name=LoginScreen.getUserName();

        c.setLayout(new FlowLayout());
        JPanel LP = new JPanel();
        JPanel RP = new JPanel();
        LP.setLayout(new GridLayout(3,1));
        RP.setLayout(new GridLayout(4,1));

        LP.add(credentials);
        //list of websites
        websites = Processor.printAllCredentials(name);
        DefaultListModel<String> listModel = new DefaultListModel<String>();
        for(int i=0;i<websites.length;i++){
            listModel.addElement(websites[i]);
        }
        list.setModel(listModel);
        list.addMouseListener(mouseListener);
        LP.add(list);

        LP.add(status);

        RP.add(add);
        RP.add(viewEdit);
        RP.add(delete);
        RP.add(logout);

        c.add(LP);
        c.add(RP);

        //actionlisteners
        add.addActionListener(this);
        viewEdit.addActionListener(this);
        delete.addActionListener(this);
        logout.addActionListener(this);

        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE ); 
    }
    public static void refreshList(){
        Processor.getInputFromFile();
        websites = Processor.printAllCredentials(name);
        DefaultListModel<String> listModel = new DefaultListModel<String>();
        for(int i=0;i<websites.length;i++){
            listModel.addElement(websites[i]);
        }
        list.setModel(listModel);

    }
    public static void deletePassword(){
         Processor.deletePassword(name,selectedWebsite);
         refreshList();
         status.setText("Password Deleted");
         credentials.setText("Please select a website");
    }
    public static void resetStatus(){
        status.setText("");
    }

    public void actionPerformed( ActionEvent event) {
        JButton button = (JButton) event.getSource();

        if(button.getText().equals("Add a New Password")){
            dispose();
            AddScreen addScreen = new AddScreen();
            addScreen.setSize(280,360);
            addScreen.setVisible(true);
            addScreen.resetStatus();
        }
        else if(button.getText().equals("View/Edit Password") && !selectedWebsite.equals("")){
            dispose();
            EditorScreen editorScreen = new EditorScreen();
            editorScreen.setSize( 280, 360 );     
            editorScreen.setVisible( true );
            editorScreen.resetStatus();
        }
        else if (button.getText().equals("Delete") && !selectedWebsite.equals("")){
            PasswordPrompt passwordPrompt = new PasswordPrompt("delete");
            passwordPrompt.setSize(300,200);
            passwordPrompt.setVisible(true);
        }
        revalidate();
        repaint();

        if(button.getText().equals("Log Out")){
            dispose();
            StartScreen startScreen  = new StartScreen() ;
            startScreen.setSize( 350, 230 );     
            startScreen.setVisible( true );
        }
    }

    public static String getWebsite(){
        return selectedWebsite;
    }
    
    public static void resetWebsite(){
        selectedWebsite="";
        credentials.setText("Please Select a Website"+selectedWebsite);
    }

} // end of class ManagerScreen