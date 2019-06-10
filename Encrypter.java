import java.io.*;
import java.util.*;
/**
 * Handles all the encryption and IO of the program
 * 
 * @author Colin Lin
 * @version 2018-11-11
 */
public class Encrypter
{

    public static String encrypt(String password){
        String encrypted= "";
        for(int i=0;i<password.length();i++){
            encrypted= encrypted + (char)(password.charAt(i)+5);
        }
        return encrypted;
    }

    public static void saveFile(String encrypted, String fileName,File file){

        try {
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file,true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(encrypted);
            pw.close();
            fw.close();
        }

        catch (IOException e) {
            System.out.println("error");        
        }
        System.out.println("Password Saved");
    }

    public static String decrypt(String encrypted){
        String decrypted= "";
        for(int i=0;i<encrypted.length();i++){
            decrypted= decrypted + (char)(encrypted.charAt(i)-5);
        }
        return decrypted;
    }

  


}

