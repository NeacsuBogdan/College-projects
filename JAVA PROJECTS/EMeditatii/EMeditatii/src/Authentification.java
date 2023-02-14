
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;

public class Authentification {
    private HashMap<String, User> registeredUsers;
    private int counter;
      public Authentification(){
          registeredUsers = new HashMap<String,User>();
        try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() > 0) {
                String[] data = line.split(",");
                String name = data[0];
                String email = data[1];
                String password = data[2];
                String userType = data[3];
                counter=Integer.parseInt(data[4]);
                User user = new User(name, email, password, userType,counter);
                registeredUsers.put(email, user);
                }
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public boolean register(String name, String email, String password, boolean isTeacher) {
        String userType = isTeacher ? "Profesor" : "Student";
        
        password = codificarePw(password);
        if(registeredUsers.size()==0)
        {
            counter=0;
        }
        else
        {
            counter=counter+1;
        }
        User newUser = new User(name, email, password, userType,counter);
        if (registeredUsers.containsKey(email)) {
            System.out.println("User already registered!");
            return false;
        }
        registeredUsers.put(email, newUser);
        try (FileWriter fileWriter = new FileWriter("users.txt", true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(name + "," + email + "," + password + "," + userType + "," + counter);
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    
    public boolean login(String email, String password,GuiManager guiManager) {
        User user = registeredUsers.get(email);
        if (user != null && user.getPassword().equals(codificarePw(password))) {
            guiManager.setCurrentUser(user);
            return true;
        } else {
            return false;
        }
    }
    
    public HashMap<String,User> getRegisteredUsers()
    {
        return registeredUsers;
    }
    
    
    private String codificarePw(String password)
    {
        //Codificare parola folosind codificat folosind SHA-256 si Base64)
         try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] enteredPasswordHash = messageDigest.digest(password.getBytes());
            password = Base64.getEncoder().encodeToString(enteredPasswordHash);
            
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
         return password;
    }
}