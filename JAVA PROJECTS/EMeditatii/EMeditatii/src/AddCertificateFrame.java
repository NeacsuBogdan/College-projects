import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

public class AddCertificateFrame {
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel institutionLabel;
    private JTextField institutionField;
    private JLabel dateLabel;
    private JSpinner dateSpinner;
    private JLabel timeLabel;
    private JSpinner timeSpinner;
    private JButton addButton;
    private JButton cancelButton;
    private User currentUser;
    private JFrame frame;
    private EditProfile setingsPrincipal;

    public AddCertificateFrame(User currentUser,EditProfile setingsPrincipal) {
        this.currentUser = currentUser;
        this.setingsPrincipal=setingsPrincipal;
        frame = new JFrame();
        frame.setTitle("Add Certificate");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        nameLabel = new JLabel("Name:");
        nameLabel.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        mainPanel.add(nameLabel, constraints);

        nameField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        mainPanel.add(nameField, constraints);

    dateLabel = new JLabel("Date:");
    dateLabel.setForeground(Color.WHITE);
constraints.gridx = 0;
constraints.gridy = 1;
constraints.gridwidth = 1;
mainPanel.add(dateLabel, constraints);

// Create a Calendar object set to the current date and time
Calendar calendar = Calendar.getInstance();
calendar.setTime(new Date());

// Create a SpinnerDateModel using the Calendar object
SpinnerDateModel dateModel = new SpinnerDateModel(calendar.getTime(), null, null, Calendar.DAY_OF_MONTH);

// Create a JSpinner using the SpinnerDateModel
dateSpinner = new JSpinner(dateModel);

// Set the editor for the JSpinner to be a JSpinner.DateEditor
dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy"));
dateSpinner.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
constraints.gridx = 1;
constraints.gridy = 1;
constraints.gridwidth = 2;
mainPanel.add(dateSpinner, constraints);

    addButton = new JButton("Add");
    addButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
    addButton.setForeground(Color.WHITE);
    addButton.setBackground(Color.BLACK);
    addButton.setPreferredSize(new Dimension(50,30));
    constraints.gridx = 1;
    constraints.gridy = 2;
    constraints.gridwidth = 1;
    constraints.anchor = GridBagConstraints.EAST;
    mainPanel.add(addButton, constraints);

    cancelButton = new JButton("Cancel");
    cancelButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
    cancelButton.setForeground(Color.WHITE);
    cancelButton.setBackground(Color.BLACK);
    cancelButton.setPreferredSize(new Dimension(50,30));
    constraints.gridx = 2;
    constraints.gridy = 2;
    constraints.gridwidth = 1;
    constraints.anchor = GridBagConstraints.WEST;
    mainPanel.add(cancelButton, constraints);

    addButton.addActionListener(new ButtonListener());
    cancelButton.addActionListener(new ButtonListener());
    frame.add(mainPanel);
    frame.setResizable(false);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
}

private class ButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            // handle the add button press
            String name = nameField.getText();
            Date date = (Date) dateSpinner.getValue();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Certificate certificate = new Certificate(name, date);
            if (currentUser.getCertificates().contains(certificate)==true)
            {
                JOptionPane.showMessageDialog(null, "Certificarea exista deja in sistem");
                return;
            }
            else
            {
                // Add the certificate to the current user's list of certificates
            currentUser.addCertificate(certificate);
            File dir = new File("datas\\Certificari");
if (!dir.exists()) {
    dir.mkdir();
}
String fileName = currentUser.getName().replaceAll(" ", "_") + "_" + currentUser.getCode() + ".txt";
File file = new File(dir, fileName);
try {
    BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
    writer.write(certificate.getName() + "," + dateFormat.format(certificate.getDate()));
    writer.newLine();
    writer.close();
} catch (IOException z) {
    z.printStackTrace();
}
 JOptionPane.showMessageDialog(addButton, "Certificarea a fost adaugata!","ADAUGARE CERTIFICARE",JOptionPane.INFORMATION_MESSAGE);
            
            setingsPrincipal.refresh();
            frame.dispose();
            }
         
          if (e.getSource() == cancelButton) {
            // handle the cancel button press
            frame.dispose();
        }
    }
    }
}
}
