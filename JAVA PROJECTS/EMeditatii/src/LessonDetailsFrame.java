
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LessonDetailsFrame extends JPanel
{
    private Lesson lesson;
    private JLabel profesorName;
    private JLabel studentName;
    private JLabel data;
    private JLabel ora;
    private JButton anulati;
    private JFrame frame;
    private User currentUser;
    private LessonsCardExample principalLessons;
    
    LessonDetailsFrame(Lesson lesson,User currentUser,LessonsCardExample principalLessons)
    {
        this.principalLessons=principalLessons;
        this.currentUser=currentUser;
        this.lesson=lesson;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
       frame = new JFrame();
       GridBagConstraints constr = new GridBagConstraints();
       constr.insets = new Insets(10,10,10,10);
       JPanel mainPanel = new JPanel();
       mainPanel.setBackground(Color.BLACK);
       mainPanel.setLayout(new GridBagLayout());
       profesorName = new JLabel("Profesor: "+lesson.getTeacher());
       profesorName.setForeground(Color.WHITE);
       studentName = new JLabel("Student: "+ lesson.getStudent());
       studentName.setForeground(Color.WHITE);
       data = new JLabel("Data: "+ dateFormat.format(lesson.getDate()));
       data.setForeground(Color.WHITE);
       ora = new JLabel("Ora: "+timeFormat.format(lesson.getTime()));
       ora.setForeground(Color.WHITE);
       anulati = new JButton("Anulati");
       
       anulati.addActionListener(new ButtonListener());
       anulati.setForeground(Color.WHITE);
       anulati.setBackground(Color.BLACK);
       anulati.setPreferredSize(new Dimension(70,30));
       anulati.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
       constr.gridx = 0;
       constr.gridy = 0;
       mainPanel.add(profesorName,constr);
       constr.gridy = 1;
       mainPanel.add(studentName,constr);
       constr.gridy = 2;
       mainPanel.add(data,constr);
       constr.gridy = 3;
       mainPanel.add(ora,constr);
       constr.gridy = 4;
       mainPanel.add(anulati,constr);
       frame.setPreferredSize(new Dimension(300,300));
       frame.add(mainPanel);
       frame.setVisible(true);
       frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       frame.setLocationRelativeTo(null);
       frame.pack();
    }
    
    public class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                String lineToDelete1= null;
                String lineToDelete2= null;
                String fileName1=null;
                String fileName2=null;
                if(currentUser.getType().equals("Profesor"))
                {
                fileName1 ="datas\\Lessons\\" + currentUser.getName().replaceAll(" ", "_") + "_" + currentUser.getCode() + ".txt";
                fileName2 ="datas\\Lessons\\" +lesson.getStudent().replaceAll(" ", "_")+ "_" + lesson.getId() + ".txt";
                lineToDelete1= lesson.getStudent()+","+lesson.getTeacher()+","+lesson.getSubject()+","+dateFormat.format(lesson.getDate())+","+timeFormat.format(lesson.getTime())+","+lesson.getId();
                lineToDelete2= lesson.getStudent()+","+lesson.getTeacher()+","+lesson.getSubject()+","+dateFormat.format(lesson.getDate())+","+timeFormat.format(lesson.getTime())+","+currentUser.getCode();
                }
                else
                {
                fileName1 ="datas\\Lessons\\" + currentUser.getName().replaceAll(" ", "_") + "_" + currentUser.getCode() + ".txt";
                fileName2 ="datas\\Lessons\\" +lesson.getTeacher().replaceAll(" ", "_")+ "_" + lesson.getId() + ".txt";
                lineToDelete1= lesson.getStudent()+","+lesson.getTeacher()+","+lesson.getSubject()+","+dateFormat.format(lesson.getDate())+","+timeFormat.format(lesson.getTime())+","+lesson.getId();
                lineToDelete2= lesson.getStudent()+","+lesson.getTeacher()+","+lesson.getSubject()+","+dateFormat.format(lesson.getDate())+","+timeFormat.format(lesson.getTime())+","+currentUser.getCode();
                }
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
    System.out.println("Nu am putut sterge fisierul original: "+originalFile);
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
    System.out.println("Nu am putut sterge fisierul original "+originalFile);
    return;
}

if (!tempFile.renameTo(originalFile)) {
    System.out.println("Nu am putut redenumi fisierul temporar");
}


File dir = new File("datas\\Messages");
if (!dir.exists()) {
    dir.mkdir();
}
String name=null;
String fileName3 = null;
if(currentUser.getType().equals("Profesor")==true)
{
fileName3 = lesson.getStudent().replaceAll(" ", "_") + "_" + lesson.getId() + ".txt";
name = lesson.getStudent();
}
else
{
    fileName3 = lesson.getTeacher().replaceAll(" ", "_")+ "_" + lesson.getId() + ".txt";
    name = lesson.getTeacher();
}
File file1 = new File(dir, fileName3);
try {
    BufferedWriter writer = new BufferedWriter(new FileWriter(file1, true));
    writer.write(name+",a anulat cererea pentru meditatia la "+lesson.getSubject()+ " din data de "+dateFormat.format(lesson.getDate())+" la ora "+timeFormat.format(lesson.getTime())+"!,-1");
    writer.newLine();
    writer.close();
} catch (IOException z) {
    z.printStackTrace();
}



currentUser.removeLesson(lesson);
principalLessons.refresh();
frame.dispose();


  }
        
        }
    }

