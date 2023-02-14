import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.BorderFactory;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

    public class RequestDetailsFrame extends JFrame {
    private Request request;
    private JLabel studentLabel;
    private JLabel profesorLabel;
    private JLabel lessonLabel;
    private JLabel dateLabel;
    private JLabel timeLabel;
    private JButton acceptButton;
    private JButton rejectButton;
    private JPanel buttonPanel;
    private RequestCardExample principal;
    private LessonsCardExample principal_lessons;
    private HashMap<String, User> auth;
    private User currentUser;
    
    
    public RequestDetailsFrame(Request request,RequestCardExample principal,HashMap<String, User> auth,User currentUser,LessonsCardExample lessons) {
        this.currentUser=currentUser;
        this.auth=auth;
        this.request = request;
        this.principal=principal;
        this.principal_lessons=lessons;
SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        
        this.setSize(new Dimension(400, 200));
        this.setTitle("Detalii cerere");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        GridBagConstraints constraints = new GridBagConstraints();
        this.setLayout(new GridBagLayout());
       JPanel mainPanel = new JPanel();
       mainPanel.setPreferredSize(new Dimension(400,250));
       mainPanel.setBackground(Color.BLACK);
       mainPanel.setLayout(new GridBagLayout());
constraints.gridx = 0;
constraints.gridy = 0;
constraints.insets = new Insets(10, 10, 10, 10);
if(currentUser.getType().equals("Profesor"))
{
studentLabel = new JLabel("Student: " + request.getParticipant());
studentLabel.setForeground(Color.WHITE);
mainPanel.add(studentLabel, constraints);
}
else
{
profesorLabel = new JLabel("Profesor: " + request.getLesson().getTeacher());
profesorLabel.setForeground(Color.WHITE);
mainPanel.add(profesorLabel, constraints);
}

constraints.gridy = 1;
lessonLabel = new JLabel("Materia: " + request.getLesson().getSubject());
lessonLabel.setForeground(Color.WHITE);
mainPanel.add(lessonLabel, constraints);

constraints.gridy = 2;
dateLabel = new JLabel("Data: "+ dateFormat.format(request.getLesson().getDate()));
dateLabel.setForeground(Color.WHITE);
mainPanel.add(dateLabel,constraints);

constraints.gridy = 3;
timeLabel = new JLabel("Ora: "+ timeFormat.format(request.getLesson().getTime()));
timeLabel.setForeground(Color.WHITE);
mainPanel.add(timeLabel,constraints);

constraints.gridy = 4;
buttonPanel = new JPanel();
buttonPanel.setBackground(Color.BLACK);
buttonPanel.setLayout(new FlowLayout());

acceptButton = new JButton("Accepta");
acceptButton.setForeground(Color.WHITE);
acceptButton.setBackground(Color.BLACK);
acceptButton.setPreferredSize(new Dimension(100,40));
acceptButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
acceptButton.addActionListener(new RequestButtonListener());
buttonPanel.add(acceptButton);

rejectButton = new JButton("Respinge");
rejectButton.setForeground(Color.WHITE);
      rejectButton.setBackground(Color.BLACK);
      rejectButton.setPreferredSize(new Dimension(100,40));
      rejectButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
rejectButton.addActionListener(new RequestButtonListener());
buttonPanel.add(rejectButton);

mainPanel.add(buttonPanel, constraints);

if(currentUser.getType().equals("Student"))
{
buttonPanel.setVisible(false);
}
this.add(mainPanel);
this.setResizable(false);
this.setLocationRelativeTo(null);
this.pack();

    }

    public void showFrame() {
        this.setVisible(true);
    }

    private class RequestButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            if (button == acceptButton) {
                request.setAnswer(true);
                request.setActive(0);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                String lineToDelete1= request.getLesson().getSubject()+ "," + dateFormat.format(request.getLesson().getDate()) + "," + timeFormat.format(request.getLesson().getTime()) + "," + request.getParticipant() + "," + request.getId();
                String lineToDelete2= request.getLesson().getSubject()+ "," + dateFormat.format(request.getLesson().getDate()) + "," + timeFormat.format(request.getLesson().getTime()) + "," + currentUser.getName() + "," + currentUser.getCode();
                String fileName1 ="datas\\Requests\\" + currentUser.getName().replaceAll(" ", "_") + "_" + currentUser.getCode() + ".txt";
                String fileName2 ="datas\\Requests\\" +request.getParticipant().replaceAll(" ", "_") + "_" + request.getId() + ".txt";
                String fileNamecache= "buf.txt";
                try (BufferedReader reader = new BufferedReader(new FileReader(fileName1));
             BufferedWriter writer = new BufferedWriter(new FileWriter(fileNamecache))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (!currentLine.equals(lineToDelete1)) {
                    writer.write(currentLine);
                    writer.newLine();
                }
            }
        } catch (IOException z) {
            z.printStackTrace();
        }
                File originalFile = new File(fileName1);
                File tempFile = new File(fileNamecache);

if (!originalFile.delete()) {
    System.out.println("Nu am putut sterge fisierul original");
    return;
}

if (!tempFile.renameTo(originalFile)) {
    System.out.println("Nu am putut redenumi fisierul temporar");
}
                try (BufferedReader reader = new BufferedReader(new FileReader(fileName2));
             BufferedWriter writer = new BufferedWriter(new FileWriter(fileNamecache))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (!currentLine.equals(lineToDelete2)) {
                    writer.write(currentLine);
                    writer.newLine();
                }
            }
        } catch (IOException z) {
            z.printStackTrace();
        }
                originalFile = new File(fileName2);
                tempFile = new File(fileNamecache);

if (!originalFile.delete()) {
    System.out.println("Nu am putut sterge fisierul original");
    return;
}

if (!tempFile.renameTo(originalFile)) {
    System.out.println("Nu am putut redenumi fisierul temporar");
}

File dir = new File("datas\\Messages");
if (!dir.exists()) {
    dir.mkdir();
}
String fileName3 = request.getParticipant().replaceAll(" ", "_") + "_" + request.getId() + ".txt";
File file1 = new File(dir, fileName3);
try {
    BufferedWriter writer = new BufferedWriter(new FileWriter(file1, true));
    writer.write("Sistem"+ ","+ "Profesorul "+request.getLesson().getTeacher()+" v-a acceptat cererea pentru meditatia la " + request.getLesson().getSubject() + " din data de "+dateFormat.format(request.getLesson().getDate())+" la ora "+timeFormat.format(request.getLesson().getTime())+"!,-1");
    writer.newLine();
    writer.close();
} catch (IOException z) {
    z.printStackTrace();
}
addLessons();
principal.reloadRequests();
principal_lessons.refresh();
            } else if (button == rejectButton) {
                request.setAnswer(false);
                request.setActive(0);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                String lineToDelete1= request.getLesson().getSubject().replaceAll(" ", "_")+ "," + dateFormat.format(request.getLesson().getDate()) + "," + timeFormat.format(request.getLesson().getTime()) + "," + request.getParticipant() + "," + request.getId();
                String lineToDelete2= request.getLesson().getSubject().replaceAll(" ", "_")+ "," + dateFormat.format(request.getLesson().getDate()) + "," + timeFormat.format(request.getLesson().getTime()) + "," + currentUser.getName() + "," + currentUser.getCode();
                String fileName1 ="datas\\Requests\\" + currentUser.getName() + "_" + currentUser.getCode() + ".txt";
                String fileName2 ="datas\\Requests\\" +request.getParticipant() + "_" + request.getId() + ".txt";
                String fileNamecache= "buf.txt";
                try (BufferedReader reader = new BufferedReader(new FileReader(fileName1));
             BufferedWriter writer = new BufferedWriter(new FileWriter(fileNamecache))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (!currentLine.equals(lineToDelete1)) {
                    writer.write(currentLine);
                    writer.newLine();
                }
            }
        } catch (IOException z) {
            z.printStackTrace();
        }
                File originalFile = new File(fileName1);
                File tempFile = new File(fileNamecache);

if (!originalFile.delete()) {
    System.out.println("Nu am putut sterge fisierul original");
    return;
}

if (!tempFile.renameTo(originalFile)) {
    System.out.println("Nu am putut redenumi fisierul temporar");
}
                try (BufferedReader reader = new BufferedReader(new FileReader(fileName2));
             BufferedWriter writer = new BufferedWriter(new FileWriter(fileNamecache))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (!currentLine.equals(lineToDelete2)) {
                    writer.write(currentLine);
                    writer.newLine();
                }
            }
        } catch (IOException z) {
            z.printStackTrace();
        }
                originalFile = new File(fileName2);
                tempFile = new File(fileNamecache);

if (!originalFile.delete()) {
    System.out.println("Nu am putut sterge fisierul original");
    return;
}

if (!tempFile.renameTo(originalFile)) {
    System.out.println("Nu am putut redenumi fisierul temporar");
}
File dir = new File("datas\\Messages");
if (!dir.exists()) {
    dir.mkdir();
}
String fileName3 = request.getParticipant().replaceAll(" ", "_") + "_" + request.getId() + ".txt";
File file1 = new File(dir, fileName3);
try {
    BufferedWriter writer = new BufferedWriter(new FileWriter(file1, true));
    writer.write("Sistem"+ ","+ "Profesorul "+request.getLesson().getTeacher()+" v-a refuzat cererea pentru meditatia la " + request.getLesson().getSubject() + " din data de "+dateFormat.format(request.getLesson().getDate())+" la ora "+timeFormat.format(request.getLesson().getTime())+"!,-1");
    writer.newLine();
    writer.close();
} catch (IOException z) {
    z.printStackTrace();
}
                principal.reloadRequests();
            }
            RequestDetailsFrame.this.dispose();
        }
    }
    
    
    public void addLessons()
    {
        File dir = new File("datas\\Lessons");
if (!dir.exists()) {
    dir.mkdir();
}
String fileName1 = request.getParticipant().replaceAll(" ", "_") + "_" + request.getId() + ".txt";
String fileName2 = currentUser.getName().replaceAll(" ", "_") + "_" + currentUser.getCode() + ".txt";
SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
File file1 = new File(dir, fileName1);
File file2 = new File(dir,fileName2);
try {
    BufferedWriter writer = new BufferedWriter(new FileWriter(file1, true));
    writer.write(request.getLesson().getStudent()+ "," +request.getLesson().getTeacher() + "," + request.getLesson().getSubject()+ "," + dateFormat.format(request.getLesson().getDate()) + "," + timeFormat.format(request.getLesson().getTime()) + "," + currentUser.getCode()+","+request.getId());
    writer.newLine();
    writer.close();
} catch (IOException z) {
    z.printStackTrace();
}

try {
    BufferedWriter writer = new BufferedWriter(new FileWriter(file2, true));
    writer.write(request.getLesson().getStudent()+ "," +request.getLesson().getTeacher() + "," + request.getLesson().getSubject()+ "," + dateFormat.format(request.getLesson().getDate()) + "," + timeFormat.format(request.getLesson().getTime()) + "," + request.getId()+","+currentUser.getCode());
    writer.newLine();
    writer.close();
} catch (IOException z) {
    z.printStackTrace();
}
if(currentUser.getType().equals("Profesor"))
{
request.getLesson().setID(request.getId());
}
currentUser.addLesson(request.getLesson());
    }
    
}
