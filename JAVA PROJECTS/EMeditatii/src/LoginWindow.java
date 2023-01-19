import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginWindow {
    private JFrame frame;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JTextField registerNameField;
    private JTextField registerEmailField;
    private JPasswordField registerPasswordField;
    private JCheckBox registerIsTeacherCheckbox;
    private JPanel registerPanel;
    private JPanel loginPanel;
    private boolean isRegisterPanelVisible = false;
    private Authentification auth = new Authentification();

    public LoginWindow() {
        frame = new JFrame("Autentificare");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
    
        loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setVisible(true);

        JLabel emailLabel = new JLabel("Email:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        loginPanel.add(emailLabel, constraints);

        emailField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 0;
        loginPanel.add(emailField, constraints);

        JLabel passwordLabel = new JLabel("Password:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        loginPanel.add(passwordLabel, constraints);

        passwordField = new JPasswordField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        loginPanel.add(passwordField, constraints);

        JButton loginButton = new JButton("Login");
        constraints.gridx = 0;
        constraints.gridy = 2;
        loginPanel.add(loginButton, constraints);

        JButton registerButton = new JButton("Register");
        constraints.gridx = 1;
        constraints.gridy = 2;
        loginPanel.add(registerButton, constraints);
        frame.add(loginPanel, constraints);
        loginButton.addActionListener(new ButtonActionListener(loginButton));
    registerButton.addActionListener(new ButtonActionListener(registerButton));
    

    registerPanel = new JPanel();
    registerPanel.setLayout(new GridBagLayout());
    registerPanel.setVisible(false);
    
    JLabel registerNameLabel = new JLabel("Name:");
    constraints.gridx = 0;
    constraints.gridy = 3;
    registerPanel.add(registerNameLabel, constraints);
    
    registerNameField = new JTextField(20);
    constraints.gridx = 1;
    constraints.gridy = 3;
    registerPanel.add(registerNameField, constraints);
    
    JLabel registerEmailLabel = new JLabel("Email:");
    constraints.gridx = 0;
    constraints.gridy = 4;
    registerPanel.add(registerEmailLabel, constraints);
    
    registerEmailField = new JTextField(20);
    constraints.gridx = 1;
    constraints.gridy = 4;
    registerPanel.add(registerEmailField, constraints);
    
    JLabel registerPasswordLabel = new JLabel("Password:");
    constraints.gridx = 0;
    constraints.gridy = 5;
    registerPanel.add(registerPasswordLabel, constraints);
    
    registerPasswordField = new JPasswordField(20);
    constraints.gridx = 1;
    constraints.gridy = 5;
    registerPanel.add(registerPasswordField, constraints);
    
    registerIsTeacherCheckbox = new JCheckBox("Sunteti Profesor?");
    constraints.gridx = 1;
    constraints.gridy = 6;
    registerPanel.add(registerIsTeacherCheckbox, constraints);
    
    JButton registerSubmitButton = new JButton("Submit");
    constraints.gridx = 0;
    constraints.gridy = 7;
    registerPanel.add(registerSubmitButton, constraints);
    
    JButton registerCancelButton = new JButton("Cancel");
    constraints.gridx = 1;
    constraints.gridy = 7;
    registerPanel.add(registerCancelButton, constraints);
    
    frame.add(registerPanel, constraints);
    
  registerSubmitButton.addActionListener(new ButtonActionListener(registerSubmitButton));
  registerCancelButton.addActionListener(new ButtonActionListener(registerCancelButton));
  frame.pack();
  frame.setLocationRelativeTo(null);
  frame.setVisible(true);
  }
    
private class ButtonActionListener implements ActionListener
{
    private JButton button;
    ButtonActionListener(JButton button)
    {
        this.button=button;
    }

        @Override
     public void actionPerformed(ActionEvent e) {
String email = emailField.getText();
String password = new String(passwordField.getPassword());
if (button.getText().equals("Login")) {
boolean succes = auth.login(email, password);
if ( succes == true) {
JOptionPane.showMessageDialog(null, "Login successful!");
emailField.setText("");
passwordField.setText("");
//open new window for logged in user
} else {
JOptionPane.showMessageDialog(null, "Invalid email or pasword!");
emailField.setText("");
passwordField.setText("");
}
} else if (button.getText().equals("Register")) {   
   loginPanel.setVisible(false);
   registerPanel.setVisible(true);
   frame.pack();
   }
else if (button.getText().equals("Submit"))
{
    String name = registerNameField.getText();
    String reg_password = new String(registerPasswordField.getPassword());
    String reg_email = registerEmailField.getText();
    String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(reg_email);
    boolean userType;
    if(registerIsTeacherCheckbox.isSelected()==true)
    {
        userType = true;
    }
    else
    {
        userType = false;
    }
    if (name.length()<1||reg_password.length()<1||reg_email.length()<1)
    {
        JOptionPane.showMessageDialog(null, "Va rog completati toate campurile!");
registerNameField.setText("");
registerPasswordField.setText("");
registerEmailField.setText("");
registerIsTeacherCheckbox.setSelected(false);
       return;
    }
    else if (!matcher.matches()) {
                JOptionPane.showMessageDialog(frame, "Invalid email format!");
                return;
            }
    else{
boolean success = auth.register(name,reg_email,reg_password,userType);
if (success) {
JOptionPane.showMessageDialog(null, "Register successful!");
registerPanel.setVisible(false);
loginPanel.setVisible(true);
frame.pack();
registerNameField.setText("");
registerPasswordField.setText("");
registerEmailField.setText("");
registerIsTeacherCheckbox.setSelected(false);
} else {
JOptionPane.showMessageDialog(null, "Email already registered");
registerNameField.setText("");
registerPasswordField.setText("");
registerEmailField.setText("");
registerIsTeacherCheckbox.setSelected(false);
}
}
}
else if (button.getText().equals("Cancel"))
{
    registerPanel.setVisible(false);
    loginPanel.setVisible(true);
    frame.pack();
}
}
}
}