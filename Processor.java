import java.io.*;
import java.util.*;
import java.awt.* ;
import java.awt.event.*;
import javax.swing.*;
/**
 * Processes all user requests and reads input from encrypted files
 * 
 * @author Colin Lin
 * @version 2018-11-11
 */
public class Processor
{
    private static Hashtable<String,User> users;
    private static String fileName ="file.txt";
    private static File file = new File(fileName);
    private static Scanner input;
    private static int invalidPassCounter = 0;
    /**
     * processes one line of input from encrypted users text file
     */
    public static void processUsers(String s){
        String name="",password="";
        boolean isName=true;
        for(int i=0;i<s.length();i++){
            if(isName && s.charAt(i)!=32)// 32 is acii for space
            {
                name = name +s.charAt(i)+"";
            }
            if(s.charAt(i)==32){
                isName=false;
            }
            else if(!isName && s.charAt(i)!=32){
                password = password +s.charAt(i)+"";
            }
        }
        name = Encrypter.decrypt(name);
        password = Encrypter.decrypt(password);
        users.put(name,new User(name,password));
        String userFileName = Encrypter.encrypt(name)+".txt";

        try{
            BufferedReader reader = new BufferedReader(new FileReader(userFileName));
            while((s=reader.readLine())!=null){
                processPasswords(s,users.get(name));
            }
            reader.close();
        }catch(Exception e){
            System.out.println("no passwords");

        }

    }

    /**
     * processes one line of input from encrypted user password file
     */
    public static void processPasswords(String s, User user){
        String website="",username="",password="";
        int pos=0; // 0=website, 1=username, 2=password
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)==32){// 32 is acii for space
                pos++;
            }
            else if(pos==0)
            {
                website = website +s.charAt(i)+"";
            }
            else if(pos==1){
                username = username +s.charAt(i)+"";
            }
            else if(pos==2)
            {
                password = password +s.charAt(i)+"";
            }
        }
        website = Encrypter.decrypt(website);
        username = Encrypter.decrypt(username);
        password = Encrypter.decrypt(password);
        user.addCredential(website,new Credential(username,password));
    }

    public static String[] printAllCredentials(String name){
        return sortAlphaOrder(users.get(name).printAllCredentials(),users.get(name).printAllCredentials().length);
    }

    public static String getWebsiteCredentials(String name,String website){
        return users.get(name).getWebsiteCredentials(website);

    }

    public static boolean isValidPassword(String name,String password){
        boolean isValid =false;
        try{
            users.get(name).getName();
            if(users.get(name).getPassword().equals(password)){
                isValid=true;
            }
            else{
                invalidPassCounter++;
            }
        }catch(Exception e){
            return false;
        } 
        return isValid;
    }

    public static boolean isValidUsername(String name){
        return !users.containsKey(name);
    }

    public static String isValidWebsite(String name, String website){
        String output="";
        try{
            output = "Website: "+website+ users.get(name).getWebsiteCredentials(website);
        }catch(Exception e){
            output = "Website Does not exist";
        }
        return output;
    }

    public static void savePassword(String name,String website, String username, String password){
        users.get(name).saveCredential(website,new Credential(username,password));
    }

    public static String register(String name, String password){
        String output = "";
        if(!isValidUsername(name)){
            output = "Username is taken. Please use another one";
        }
        else if(isValidUsername(name)){ 
            users.put(name,new User(name,password));
            String encrypted = Encrypter.encrypt(name)+" "+Encrypter.encrypt(password);
            Encrypter.saveFile(encrypted,fileName,file);
            output = "Welcome "+name;
        }
        return output;

    }

    public static String login(String name, String password){
        String output="";

        if(!isValidPassword(name,password)){
            output = "Username or Password is incorrect. Please try again.";
        }
        else if(isValidPassword(name,password)){
            output = "Welcome back "+name+"!";
        }

        return output;
    }

    public static String getUsername(String name, String website){
        return users.get(name).getUsername(website);
    }

    public static String getPassword(String name, String website){
        return users.get(name).getPassword(website);
    }

    public static void getInputFromFile()
    {
        String s ="";
        String name="",password="";
        users = new Hashtable<String,User>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while((s=reader.readLine())!=null){
                processUsers(s);
            }
        }catch(Exception e){

        }
    } 

    public static String[] sortAlphaOrder(String[] arr,int n){
        if(n==1)
            return arr;
        for ( int i = 0;  i < n - 1;  i++ ){
            if ( arr [ i ].compareToIgnoreCase( arr [ i+1 ] ) > 0 ){
                String temp = arr [ i ];
                    arr [ i ] = arr [ i+1];     // swapping
                    arr [ i+1] = temp; 
            }
            sortAlphaOrder(arr,n-1);
        }
        return arr;
    }

    public static void resetCounter()
    {
        invalidPassCounter=0;
    }

    public static void deletePassword(String name, String website){
        name = Encrypter.encrypt(name)+".txt";
        website = Encrypter.encrypt(website);
        try{
            File tempFile = new File("output.txt");
            File inputFile = new File(name);
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            PrintWriter pw = new PrintWriter(tempFile);
            String s = reader.readLine(); 
            // loop for each line of input.txt 
            while(s != null) 
            { 
                int pos=0; 
                String delete="";
                int i=0;
                while(pos<1){ 
                    if(s.charAt(i)==32){
                        pos++;
                    }
                    else{
                        delete+=s.charAt(i);
                    }
                    i++;
                }
                if(!delete.equals(website)){
                    pw.println(s); 
                }
                s = reader.readLine(); 
            } 
            pw.flush();
            pw.close();
            reader.close(); 
            inputFile.delete();
            tempFile.renameTo(inputFile);
        }catch(Exception e){
            System.out.println("oops!");
        }
    }
} // end of class Tester