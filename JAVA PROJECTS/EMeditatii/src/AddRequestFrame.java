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

public class AddRequestFrame extends JFrame {
    private JLabel destinatarLabel;
    private JLabel destinatarField;
    private JLabel lessonLabel;
    private JTextField lessonField;
    private JLabel dateLabel;
    private JSpinner dateSpinner;
    private JSpinner timeSpinner;
    private JButton sendButton;
    private JButton cancelButton;
    private User destinatar;
    private User currentUser;
    private JFrame frame;
    private RequestCardExample requestPrincipal;
    
    public AddRequestFrame(User destinatar,User currentUser,RequestCardExample requestPrincipal) {
        frame=this;
        this.requestPrincipal=requestPrincipal;
        this.destinatar=destinatar;
        this.currentUser=currentUser;
        this.setBackground(Color.BLACK);
        setTitle("Add Request");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());

       JPanel mainPanel = new JPanel();
    mainPanel.setBackground(Color.BLACK);
    mainPanel.setLayout(new GridBagLayout());

    GridBagConstraints constraints = new GridBagConstraints();
    constraints.insets = new Insets(10, 10, 10, 10);;

        destinatarLabel = new JLabel("Destinatar:");
        destinatarLabel.setForeground(Color.WHITE);
        destinatarLabel.setBackground(Color.BLACK);
    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.gridwidth = 1;
    constraints.anchor = GridBagConstraints.WEST;
    mainPanel.add(destinatarLabel, constraints);

    destinatarField = new JLabel(destinatar.getName());
    destinatarField.setForeground(Color.WHITE);
    constraints.gridx = 1;
    constraints.gridy = 0;
    constraints.gridwidth = 2;
    mainPanel.add(destinatarField, constraints);

    lessonLabel = new JLabel("Materie:");
    lessonLabel.setForeground(Color.WHITE);
    constraints.gridx = 0;
    constraints.gridy = 1;
    constraints.gridwidth = 1;
    mainPanel.add(lessonLabel, constraints);

    lessonField = new JTextField(20);
    constraints.gridx = 1;
    constraints.gridy = 1;
    constraints.gridwidth = 2;
    mainPanel.add(lessonField, constraints);
    
    dateLabel = new JLabel("Date:");
    dateLabel.setForeground(Color.WHITE);
    constraints.gridx = 0;
    constraints.gridy = 2;
    constraints.gridwidth = 1;
    mainPanel.add(dateLabel, constraints);

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date());

    SpinnerDateModel dateModel = new SpinnerDateModel(calendar.getTime(), null, null, Calendar.DAY_OF_MONTH);

    dateSpinner = new JSpinner(dateModel);
    dateSpinner.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
    dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy"));
    constraints.gridx = 1;
    constraints.gridy = 2;
    constraints.gridwidth = 2;
    mainPanel.add(dateSpinner, constraints);
    
    JLabel timeLabel = new JLabel("Time:");
    timeLabel.setForeground(Color.WHITE);
constraints.gridx = 0;
constraints.gridy = 3;
constraints.gridwidth = 1;
mainPanel.add(timeLabel, constraints);

timeSpinner = new JSpinner(new SpinnerDateModel());
JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
timeSpinner.setEditor(timeEditor);
timeSpinner.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
constraints.gridx = 1;
constraints.gridy = 3;
constraints.gridwidth = 2;
mainPanel.add(timeSpinner, constraints);

    sendButton = new JButton("Send");
    sendButton.setForeground(Color.WHITE);
    sendButton.setBackground(Color.BLACK);
    sendButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
    sendButton.setPreferredSize(new Dimension(70,30));
    constraints.gridx = 1;
    constraints.gridy = 4;
    constraints.gridwidth = 1;
    constraints.anchor = GridBagConstraints.EAST;
    mainPanel.add(sendButton, constraints);

    cancelButton = new JButton("Cancel");
    cancelButton.setForeground(Color.WHITE);
    cancelButton.setBackground(Color.BLACK);
    cancelButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
    cancelButton.setPreferredSize(new Dimension(70,30));
    constraints.gridx = 2;
    constraints.gridy = 4;
    constraints.gridwidth = 1;
    constraints.anchor = GridBagConstraints.WEST;
    mainPanel.add(cancelButton, constraints);
    this.add(mainPanel);
    this.setVisible(true);
    this.pack();
    this.setLocationRelativeTo(null);
    this.setResizable(false);
        
        sendButton.addActionListener(new ButtonListener());
        cancelButton.addActionListener(new ButtonListener());
    }
      private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
             if (e.getSource() == sendButton) {
            String lesson = lessonField.getText();
            if(lesson.length()<1)
            {
               JOptionPane.showMessageDialog(null, "Completati materia!", "Atentie!", JOptionPane.WARNING_MESSAGE);
               return;
            }
Calendar now = Calendar.getInstance();
Date selectedDate = (Date) dateSpinner.getValue();
Calendar selectedCalendar = Calendar.getInstance();
selectedCalendar.setTime(selectedDate);

Date selectedTime = (Date) timeSpinner.getValue();
Calendar selectedTimeCalendar = Calendar.getInstance();
selectedTimeCalendar.setTime(selectedTime);
selectedCalendar.set(Calendar.HOUR_OF_DAY, selectedTimeCalendar.get(Calendar.HOUR_OF_DAY));
selectedCalendar.set(Calendar.MINUTE, selectedTimeCalendar.get(Calendar.MINUTE));

if (selectedCalendar.before(now)) {
    JOptionPane.showMessageDialog(null, "Selectati o data si o ora valida!", "Atentie!", JOptionPane.WARNING_MESSAGE);
    return;
}
SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
String date = dateFormat.format((Date)dateSpinner.getValue());
String time = timeFormat.format((Date)timeSpinner.getValue());
Lesson programare = new Lesson(destinatar.getName(),currentUser.getName(),lesson,(Date)dateSpinner.getValue(),(Date)timeSpinner.getValue(),destinatar.getCode(),currentUser.getCode());

      Request cerere = new Request (currentUser.getName(),programare,destinatar.getCode());
      if (currentUser.getRequests().contains(cerere))
      {
          JOptionPane.showMessageDialog(null, "Cererea a fost deja facuta");
      }
      else
      {
          currentUser.addRequests(cerere);
          File dir = new File("datas\\Requests");
if (!dir.exists()) {
    dir.mkdir();
}
String fileName1 = currentUser.getName().replaceAll(" ", "_") + "_" + currentUser.getCode() + ".txt";
String fileName2 = destinatar.getName().replaceAll(" ", "_") + "_" + destinatar.getCode() + ".txt";
File file1 = new File(dir, fileName1);
try {
    BufferedWriter writer = new BufferedWriter(new FileWriter(file1, true));
    writer.write(cerere.getLesson().getSubject() + "," + date + "," + time + "," + destinatar.getName() +"," + destinatar.getCode());
    writer.newLine();
    writer.close();
} catch (IOException z) {
    z.printStackTrace();
}
File file2 = new File(dir,fileName2);
try {
    BufferedWriter writer = new BufferedWriter(new FileWriter(file2, true));
    writer.write(cerere.getLesson().getSubject() + "," + date + "," + time + "," + currentUser.getName() + "," + currentUser.getCode());
    writer.newLine();
    writer.close();
} catch (IOException z) {
    z.printStackTrace();
}
      }
      frame.dispose();
    //Actualizeaza lista requesturi
    requestPrincipal.reloadRequests();
    JOptionPane.showMessageDialog(sendButton, "Cererea a fost trimisa!","CERERE TRIMISA",JOptionPane.INFORMATION_MESSAGE);
} else if (e.getSource() == cancelButton) {
    frame.dispose();
}
        }
    }
}