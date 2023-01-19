
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Authentification {
    private Map<String, User> registeredUsers = new HashMap<>();

      public Authentification(){
        try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() > 0) {
                String[] data = line.split(",");
                String name = data[0];
                String email = data[1];
                String password = data[2];
                String userType = data[3];
                User user = new User(name, email, password, userType);
                registeredUsers.put(email, user);
                }
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public boolean register(String name, String email, String password, boolean isTeacher) {
        String userType = isTeacher ? "Profesor" : "Student";
        User newUser = new User(name, email, password, userType);
        if (registeredUsers.containsKey(email)) {
            System.out.println("User already registered!");
            return false;
        }
        registeredUsers.put(email, newUser);
        try (FileWriter fileWriter = new FileWriter("users.txt", true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(name + "," + email + "," + password + "," + userType);
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    
    public boolean login(String email, String password) {
        User user = registeredUsers.get(email);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }
}