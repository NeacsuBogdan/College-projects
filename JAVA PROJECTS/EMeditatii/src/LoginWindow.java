import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginWindow extends JFrame{
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
    private Authentification auth;
    private GuiManager guiManager;

    public LoginWindow(Authentification auth,GuiManager guiManager) {
        frame = new JFrame("Autentificare");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.setBackground(Color.BLACK);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
    
        loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setVisible(true);
        loginPanel.setBackground(Color.BLACK);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 0;
        loginPanel.add(emailLabel, constraints);

        emailField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 0;
        loginPanel.add(emailField, constraints);
        emailField.setText("");

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 1;
        loginPanel.add(passwordLabel, constraints);

        passwordField = new JPasswordField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        loginPanel.add(passwordField, constraints);
        passwordField.setText("");

        JButton loginButton = new JButton("Login");
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(Color.BLACK);
        loginButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        loginButton.setPreferredSize(new Dimension(70,40));
        constraints.gridx = 0;
        constraints.gridy = 2;
        loginPanel.add(loginButton, constraints);

        JButton registerButton = new JButton("Register");
        registerButton.setForeground(Color.WHITE);
        registerButton.setBackground(Color.BLACK);
        registerButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        registerButton.setPreferredSize(new Dimension(70,40));
        constraints.gridx = 1;
        constraints.gridy = 2;
        loginPanel.add(registerButton, constraints);
        frame.add(loginPanel);
        loginButton.addActionListener(new ButtonActionListener(loginButton));
    registerButton.addActionListener(new ButtonActionListener(registerButton));
    

    registerPanel = new JPanel();
    registerPanel.setLayout(new GridBagLayout());
    registerPanel.setBackground(Color.BLACK);
    registerPanel.setVisible(false);
    
    JLabel registerNameLabel = new JLabel("Name:");
    registerNameLabel.setForeground(Color.WHITE);
    constraints.gridx = 0;
    constraints.gridy = 3;
    registerPanel.add(registerNameLabel, constraints);
    
    registerNameField = new JTextField(20);
    constraints.gridx = 1;
    constraints.gridy = 3;
    registerPanel.add(registerNameField, constraints);
    
    JLabel registerEmailLabel = new JLabel("Email:");
    registerEmailLabel.setForeground(Color.WHITE);
    constraints.gridx = 0;
    constraints.gridy = 4;
    registerPanel.add(registerEmailLabel, constraints);
    
    registerEmailField = new JTextField(20);
    constraints.gridx = 1;
    constraints.gridy = 4;
    registerPanel.add(registerEmailField, constraints);
    
    JLabel registerPasswordLabel = new JLabel("Password:");
    registerPasswordLabel.setForeground(Color.WHITE);
    constraints.gridx = 0;
    constraints.gridy = 5;
    registerPanel.add(registerPasswordLabel, constraints);
    
    registerPasswordField = new JPasswordField(20);
    constraints.gridx = 1;
    constraints.gridy = 5;
    registerPanel.add(registerPasswordField, constraints);
    
    registerIsTeacherCheckbox = new JCheckBox("Sunteti Profesor?");
    registerIsTeacherCheckbox.setForeground(Color.WHITE);
    registerIsTeacherCheckbox.setBackground(Color.BLACK);
    constraints.gridx = 1;
    constraints.gridy = 6;
    registerPanel.add(registerIsTeacherCheckbox, constraints);
    
    JButton registerSubmitButton = new JButton("Submit");
    registerSubmitButton.setForeground(Color.WHITE);
    registerSubmitButton.setBackground(Color.BLACK);
    registerSubmitButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
    registerSubmitButton.setPreferredSize(new Dimension(70,40));
    constraints.gridx = 0;
    constraints.gridy = 7;
    registerPanel.add(registerSubmitButton, constraints);
    
    JButton registerCancelButton = new JButton("Cancel");
    registerCancelButton.setForeground(Color.WHITE);
    registerCancelButton.setBackground(Color.BLACK);
    registerCancelButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
    registerCancelButton.setPreferredSize(new Dimension(70,40));
    constraints.gridx = 1;
    constraints.gridy = 7;
    registerPanel.add(registerCancelButton, constraints);
    
    frame.add(registerPanel);
    
  registerSubmitButton.addActionListener(new ButtonActionListener(registerSubmitButton));
  registerCancelButton.addActionListener(new ButtonActionListener(registerCancelButton));
  frame.pack();
  frame.setLocationRelativeTo(null);
  frame.setVisible(true);
  frame.setResizable(false);
  this.auth=auth;
  this.guiManager=guiManager;
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
boolean succes = auth.login(email, password,guiManager);
if ( succes == true) {
JOptionPane.showMessageDialog(null, "Login successful!");
emailField.setText("");
passwordField.setText("");
frame.dispose();
guiManager.openMain();
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
    
    if (name.matches("^[A-Z][a-z]+ [A-Z][a-z]+$")==false)
    {
         JOptionPane.showMessageDialog(null, "Va rugam introduceti un nume valid!");
         registerNameField.setText("");
registerPasswordField.setText("");
registerEmailField.setText("");
registerIsTeacherCheckbox.setSelected(false);
       return;
    }
    
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
else if (button.getText().equals("Cancel"))
{
    registerPanel.setVisible(false);
    loginPanel.setVisible(true);
    frame.pack();
}
}
}
public void close()
    {
        frame.dispose();
    }

}