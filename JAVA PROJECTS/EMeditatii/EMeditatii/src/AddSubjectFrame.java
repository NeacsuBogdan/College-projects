import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AddSubjectFrame extends JFrame {
    private JLabel subjectNameLabel;
    private JTextField subjectNameField;
    private JLabel subjectPriceLabel;
    private JTextField subjectPriceField;
    private JButton okButton;
    private JButton cancelButton;
    private User currentUser;
    private EditProfile profilePrincipal;
    private JFrame frame;
    
    public AddSubjectFrame(User currentUser,EditProfile profilePrincipal) {
        this.currentUser=currentUser;
        this.profilePrincipal=profilePrincipal;
        setTitle("Add Subject");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        subjectNameLabel = new JLabel("Nume Materie:");
        subjectNameLabel.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        mainPanel.add(subjectNameLabel, constraints);

        subjectNameField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        mainPanel.add(subjectNameField, constraints);
        
        subjectPriceLabel=new JLabel("Pret:");
        subjectPriceLabel.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 1;
        mainPanel.add(subjectPriceLabel,constraints);
        
        subjectPriceField = new JTextField(10);
        constraints.gridx = 1;
        constraints.gridy = 1;
        mainPanel.add(subjectPriceField,constraints);

        okButton = new JButton("OK");
        okButton.setForeground(Color.WHITE);
        okButton.setBackground(Color.BLACK);
        okButton.setPreferredSize(new Dimension(100,40));
        okButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.EAST;
        mainPanel.add(okButton, constraints);

        cancelButton = new JButton("Cancel");
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBackground(Color.BLACK);
        cancelButton.setPreferredSize(new Dimension(100,40));
        cancelButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.WEST;
        mainPanel.add(cancelButton, constraints);

        ButtonListener listener = new ButtonListener();
        cancelButton.addActionListener(listener);
        okButton.addActionListener(listener);
         this.add(mainPanel);
         this.setResizable(false);
         this.pack();
         setLocationRelativeTo(null);
         this.setVisible(true);
         frame=this;
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
              if (e.getSource() == okButton) {
            // handle the add button press
           
            if(subjectNameField.getText().length()<1||subjectPriceField.getText().length()<1)
            {
                JOptionPane.showMessageDialog(null, "Va rugam sa nu lasati campurile necompletate");
                return;
            }
             String subjectName = subjectNameField.getText()+" "+subjectPriceField.getText();
             for (String element : currentUser.getSubjects()) {
        if (element.equals(subjectName)) {
            JOptionPane.showMessageDialog(null, "Subjectul exista deja in lista userului");
                return;
        }
    }
             currentUser.addSubject(subjectName);
            File dir = new File("datas\\Subjects");
            if (!dir.exists()) {
                dir.mkdir();
            }
            String fileName = currentUser.getName().replaceAll(" ", "_") + "_" + currentUser.getCode() + ".txt";
            File file = new File(dir, fileName);
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
                writer.write(subjectName);
                writer.newLine();
                writer.close();
            } catch (IOException z) {
                z.printStackTrace();
            }
 JOptionPane.showMessageDialog(okButton, "Materia a fost adaugata!","ADAUGARE MATERIE",JOptionPane.INFORMATION_MESSAGE);
            profilePrincipal.refresh();
             frame.dispose();
    }
              if(e.getSource() == cancelButton)
           frame.dispose();
        }
    }
}


