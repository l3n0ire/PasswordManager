
/**
 * User's login credentials for an online account
 * 
 * @author Colin Lin
 * @version 1.0 2018-11-24
 */
public class Credential
{
   private String username;
   private String password;
   
   public Credential(String username, String password){
       this.username = username;
       this.password = password;
    }
    
   public String printCredentials(){
       return "\nUsername: "+this.username+"\nPassword: "+this.password;
    }
    
   public String encrypt(){
       return Encrypter.encrypt(this.username)+" "+Encrypter.encrypt(this.password);
    }
    
   public String getUsername(){
       return this.username;
    }
   public String getPassword(){
       return this.password;
    }

} // end of class Password