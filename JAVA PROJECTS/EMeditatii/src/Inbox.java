import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;


public class Inbox extends JFrame {

  private JFrame frame;
  private JPanel messagesPanel;
  private ArrayList<Message> messages;
  private JButton okButton;
  private JButton reviewButton;
  private Message currentMessage;
  private User currentUser;
  private MainWindow mWindow;
  private JScrollPane scrollPane;
  private int index;

  public Inbox(User currentUser,MainWindow mWindow) {
    this.currentUser = currentUser;
    this.mWindow=mWindow;
    frame = new JFrame("Inbox");
    messagesPanel = new JPanel();

   messagesPanel.setLayout(new BoxLayout(messagesPanel, BoxLayout.Y_AXIS));
   messagesPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

    okButton = new JButton("OK");
    okButton.addActionListener(new ButtonListener());
    
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setSize(700,200);
    frame.setLocationRelativeTo(null);
    this.messages = currentUser.getMessages();
    index = -1;
    
    for (Message message : messages) {
      JPanel panel = new JPanel();
      panel.setLayout(new GridBagLayout());
      panel.setBackground(Color.BLACK);
      GridBagConstraints constr = new GridBagConstraints();
       
      index++;
      JTextArea textArea = new JTextArea("Expeditor: " + message.getExpeditor() + "\n" + message.getMesaj());
      textArea.setLineWrap(true);
      textArea.setWrapStyleWord(true);
      textArea.setEditable(false);
      textArea.setSize(500,70);
      textArea.setMaximumSize(new Dimension(500,200));
      textArea.setOpaque(true);
      textArea.setFont(new Font("Arial",Font.BOLD,15));
      textArea.setForeground(Color.WHITE);
      textArea.setBackground(Color.BLACK);
      textArea.setMargin(new Insets(10,10,10,10));
      JPanel textPanel = new JPanel();
      textPanel.setBackground(Color.WHITE);
      textPanel.add(textArea);
      constr.gridx = 0;
      constr.gridy = 0;
      panel.add(textPanel, constr);
      panel.putClientProperty("index", index);
        okButton = new JButton("OK");
        okButton.setForeground(Color.BLACK);
        okButton.setBackground(Color.RED);
        okButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        okButton.setPreferredSize(new Dimension(70,30));
        reviewButton=new JButton("Review");
        reviewButton.setBackground(Color.RED);
        reviewButton.setForeground(Color.BLACK);
        reviewButton.setPreferredSize(new Dimension(70,30));
        reviewButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
      okButton.addActionListener(new ButtonListener());
      reviewButton.addActionListener(new ButtonListener());
      constr.gridx = 3;
      if(message.getIdExpeditor()==-1)
      {
      panel.add(okButton, constr);
      }
      else
      {
          panel.add(reviewButton,constr);
      }
      panel.setBorder (BorderFactory.createEmptyBorder (10, 10, 10, 10));

      messagesPanel.add(panel);
    }
    frame.setVisible(true);
    frame.setResizable(false);
    scrollPane = new JScrollPane(messagesPanel);
    scrollPane.setPreferredSize(new Dimension(700,300));
    messagesPanel.setBackground(Color.BLACK);
    frame.add(scrollPane);
    if(messages.size()>0)
    {
    frame.pack();
    }
  }
  
  private void refresh() {
    messagesPanel.removeAll(); // Îndepărtează toate componentele existente din panoul de mesaje
    int index = -1;
    for (Message message : messages) {
      JPanel panel = new JPanel();
      panel.setLayout(new GridBagLayout());
      panel.setBackground(Color.BLACK);
      GridBagConstraints constr = new GridBagConstraints();
       
      index++;
      JTextArea textArea = new JTextArea("Expeditor: " + message.getExpeditor() + "\n" + message.getMesaj());
      textArea.setLineWrap(true);
      textArea.setWrapStyleWord(true);
      textArea.setEditable(false);
      textArea.setSize(500,70);
      textArea.setMaximumSize(new Dimension(500,200));
      textArea.setOpaque(true);
      textArea.setFont(new Font("Arial",Font.BOLD,15));
      textArea.setForeground(Color.WHITE);
      textArea.setBackground(Color.BLACK);
      textArea.setMargin(new Insets(10,10,10,10));
      JPanel textPanel = new JPanel();
      textPanel.setBackground(Color.WHITE);
      textPanel.add(textArea);
      constr.gridx = 0;
      constr.gridy = 0;
      panel.add(textPanel, constr);
      panel.putClientProperty("index", index);
        okButton = new JButton("OK");
        okButton.setForeground(Color.BLACK);
        okButton.setBackground(Color.RED);
        okButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        okButton.setPreferredSize(new Dimension(70,30));
        reviewButton=new JButton("Review");
        reviewButton.setBackground(Color.RED);
        reviewButton.setForeground(Color.BLACK);
        reviewButton.setPreferredSize(new Dimension(70,30));
        reviewButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
      okButton.addActionListener(new ButtonListener());
      reviewButton.addActionListener(new ButtonListener());
      constr.gridx = 3;
      if(message.getIdExpeditor()==-1)
      {
      panel.add(okButton, constr);
      }
      else
      {
          panel.add(reviewButton,constr);
      }
      panel.setBorder (BorderFactory.createEmptyBorder (10, 10, 10, 10));

      messagesPanel.add(panel);
    }
    messagesPanel.revalidate(); // Forțează recalcularea dimensiunii și a poziției componentelor din panoul de mesaje
    messagesPanel.repaint();

}
  
    private class ButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buton = (JButton)e.getSource();
        if (buton.getText().equals("OK"))
        {
        JPanel panel = (JPanel) buton.getParent();
  int index = (int) panel.getClientProperty("index");
   String fileName1 = "datas\\Messages\\" + currentUser.getName().replaceAll(" ", "_") + "_" + currentUser.getCode() +".txt";
 String fileNamecache = "buf.txt";
 String lineToDelete1 = messages.get(index).getExpeditor()+ "," + messages.get(index).getMesaj()+","+messages.get(index).getIdExpeditor();
  messages.remove(index);
  refresh();


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
    }
     if (buton.getText().equals("Review"))
     {
         JPanel panel = (JPanel) buton.getParent();
         int index = (int) panel.getClientProperty("index");
         new ReviewFrame(messages.get(index).getLesson());
         frame.dispose();
         Lesson meditatie= messages.get(index).getLesson();
  messages.remove(index);
  refresh();



                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                String lineToDelete1= meditatie.getStudent()+","+meditatie.getTeacher()+","+meditatie.getSubject()+","+dateFormat.format(meditatie.getDate())+","+timeFormat.format(meditatie.getTime())+","+meditatie.getId()+","+currentUser.getCode();
                String lineToDelete2= meditatie.getStudent()+","+meditatie.getTeacher()+","+meditatie.getSubject()+","+dateFormat.format(meditatie.getDate())+","+timeFormat.format(meditatie.getTime())+","+currentUser.getCode()+","+meditatie.getId();
                String fileName1 ="datas\\Lessons\\" + currentUser.getName().replaceAll(" ", "_") + "_" + currentUser.getCode() + ".txt";
                String fileName2 ="datas\\Lessons\\" +meditatie.getTeacher().replaceAll(" ", "_")+ "_" + meditatie.getId() + ".txt";
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
  }
     mWindow.updateInbox();
    }
    }
}
 
