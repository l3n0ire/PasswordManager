import java.io.*;
import java.util.*;
/**
 * Password manager User
 * 
 * @author Colin Lin
 * @version 1.0 2018-11-28
 */
public class User
{
    private String name;
    private String masterPassword;
    private Hashtable<String,Credential> credentials;

    public User(String name, String masterPassword){
        this.name = name;
        this.masterPassword = masterPassword;
        this.credentials = new Hashtable<String,Credential>();
    }

    public void addCredential(String website, Credential credential){
        this.credentials.put(website,credential);
    }

    public void saveCredential(String website, Credential credential){
        addCredential(website,credential);
        String userFileName = Encrypter.encrypt(this.name)+".txt";
        String encrypted = Encrypter.encrypt(website)+" "+credential.encrypt();

        File file = new File(userFileName);
        Encrypter.saveFile(encrypted,userFileName,file);

    }
    public String[] printAllCredentials(){
        Set<String> keys = credentials.keySet();
        String[] output = new String[this.credentials.size()];
        int i=0;
        for(String key: keys){
            output[i] = key;
            i++;
        }
        return output;
        
    }

    public String getWebsiteCredentials(String website){
        String line="____________________________";
        return line+ "\n\nWebsite: " +website+this.credentials.get(website).printCredentials()+"\n";
    }
    public String getUsername(String website){
        return this.credentials.get(website).getUsername();
    }
    public String getPassword(String website){
        return this.credentials.get(website).getPassword();
    }
    public String getPassword(){
        return this.masterPassword;
    }

    public String getName(){
        return this.name;
    }


} // end of class User