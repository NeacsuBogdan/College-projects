import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class ReviewFrame extends JFrame {
    private Lesson lesson;
    private int rating;
    private String date;
    private String time;
    private JTextArea reviewField;
    private JRadioButton star1;
    private JRadioButton star2;
    private JRadioButton star3;
    private JRadioButton star4;
    private JRadioButton star5;
    private ImageIcon starblank;
    private ImageIcon starfull;
    private ButtonGroup ratingGroup;
    private JButton submitButton;
    private JFrame frame;
    private JLabel nrCuvinte;
    
    public ReviewFrame(Lesson lesson) {
    this.lesson = lesson;
    
    starblank = new ImageIcon("C:\\Users\\bogdan\\Desktop\\INFO 2\\PROGRAMARE ORIENTATA OBIECT\\EMeditatii\\images\\blankstar.png");
    starfull = new ImageIcon("C:\\Users\\bogdan\\Desktop\\INFO 2\\PROGRAMARE ORIENTATA OBIECT\\EMeditatii\\images\\star.png");
    JLabel profesorLabel = new JLabel("Profesor: " + lesson.getTeacher());
    profesorLabel.setForeground(Color.WHITE);
    JLabel materiaLabel = new JLabel("Materia: " + lesson.getSubject());
    materiaLabel.setForeground(Color.WHITE);
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    JLabel dataOraLabel = new JLabel("Data " + dateFormat.format(lesson.getDate())+" ora "+timeFormat.format(lesson.getTime()));
    dataOraLabel.setForeground(Color.WHITE);

    reviewField = new JTextArea(5, 40);
    reviewField.setPreferredSize(new Dimension(reviewField.getWidth(),130));
    reviewField.setWrapStyleWord(true);
    reviewField.setLineWrap(true);
    reviewField.addKeyListener(new MyKeyListener());
    JLabel ratingLabel = new JLabel("Rating: ");
    ratingLabel.setForeground(Color.WHITE);
    star1 = new JRadioButton(starblank);
    star1.setBackground(Color.BLACK);
    star2 = new JRadioButton(starblank);
    star2.setBackground(Color.BLACK);
    star3 = new JRadioButton(starblank);
    star3.setBackground(Color.BLACK);
    star4 = new JRadioButton(starblank);
    star4.setBackground(Color.BLACK);
    star5 = new JRadioButton(starblank);
    star5.setBackground(Color.BLACK);
    ratingGroup = new ButtonGroup();
    ratingGroup.add(star1);
    ratingGroup.add(star2);
    ratingGroup.add(star3);
    ratingGroup.add(star4);
    ratingGroup.add(star5);
    
    submitButton = new JButton("Trimite");
    submitButton.setForeground(Color.WHITE);
    submitButton.setBackground(Color.BLACK);
    submitButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
    submitButton.setPreferredSize(new Dimension(70,30));

    JPanel reviewPanel = new JPanel();
    reviewPanel.setLayout(new BoxLayout(reviewPanel,BoxLayout.Y_AXIS));
    JLabel commentLabel = new JLabel("Comentariu: ");
    commentLabel.setForeground(Color.WHITE);
    commentLabel.setAlignmentX(0.5f);
    reviewPanel.setBackground(Color.BLACK);
    nrCuvinte = new JLabel("Caractere disponibile: "+reviewField.getText().length()+"/300");
    nrCuvinte.setForeground(Color.WHITE);
    nrCuvinte.setAlignmentX(0.5f);
    reviewPanel.add(commentLabel);
    reviewPanel.add(nrCuvinte);
    reviewPanel.add(reviewField);

    JPanel ratingPanel = new JPanel();
    ratingPanel.setBackground(Color.BLACK);
    ratingPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
    ratingPanel.add(ratingLabel);
    ratingPanel.add(star1);
    ratingPanel.add(star2);
    ratingPanel.add(star3);
    ratingPanel.add(star4);
    ratingPanel.add(star5);

    JPanel mainPanel = new JPanel(new GridBagLayout());
    mainPanel.setBackground(Color.BLACK);
    GridBagConstraints c = new GridBagConstraints();

    c.insets = new Insets(15, 15, 15, 15);
    c.fill = GridBagConstraints.HORIZONTAL;
    
    c.gridx = 0;
    c.gridy = 0;
    
    mainPanel.add(profesorLabel,c);
    c.gridy = 1;
    mainPanel.add(materiaLabel,c);
    c.gridy = 2;
    mainPanel.add(dataOraLabel,c);
    c.gridy = 3;
    mainPanel.add(ratingPanel,c);
    c.gridy = 4;
    mainPanel.add(reviewPanel,c);
    c.gridx = 0;
    c.gridy = 5;
    mainPanel.add(submitButton,c);
        frame = new JFrame();
        frame.add(mainPanel);
        star1.addActionListener(new ButtonListener());
        star2.addActionListener(new ButtonListener());
        star3.addActionListener(new ButtonListener());
        star4.addActionListener(new ButtonListener());
        star5.addActionListener(new ButtonListener());
        submitButton.addActionListener(new ButtonListener());
        frame.setTitle("Review Teacher");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.pack();
    }
    
    public class ButtonListener implements ActionListener{
        
        public void actionPerformed(ActionEvent e)
        {
           if(e.getSource()==star1)
           {
               rating = 1;
               star1.setIcon(starfull);
               star2.setIcon(starblank);
               star3.setIcon(starblank);
               star4.setIcon(starblank);
               star5.setIcon(starblank);
               star1.revalidate();
               star2.revalidate();
               star3.revalidate();
               star4.revalidate();
               star5.revalidate();
           }
           if(e.getSource()==star2)
           {
               rating = 2;
               star1.setIcon(starfull);
               star2.setIcon(starfull);
               star3.setIcon(starblank);
               star4.setIcon(starblank);
               star5.setIcon(starblank);
               star1.revalidate();
               star2.revalidate();
               star3.revalidate();
               star4.revalidate();
               star5.revalidate();
           }
           if(e.getSource()==star3)
           {
               rating = 3;
               star1.setIcon(starfull);
               star2.setIcon(starfull);
               star3.setIcon(starfull);
               star4.setIcon(starblank);
               star5.setIcon(starblank);
               star1.revalidate();
               star2.revalidate();
               star3.revalidate();
               star4.revalidate();
               star5.revalidate();
           }
           if(e.getSource()==star4)
           {
               rating = 4;
               star1.setIcon(starfull);
               star2.setIcon(starfull);
               star3.setIcon(starfull);
               star4.setIcon(starfull);
               star5.setIcon(starblank);
               star1.revalidate();
               star2.revalidate();
               star3.revalidate();
               star4.revalidate();
               star5.revalidate();
           }
           if(e.getSource()==star5)
           {
               rating = 5;
               star1.setIcon(starfull);
               star2.setIcon(starfull);
               star3.setIcon(starfull);
               star4.setIcon(starfull);
               star5.setIcon(starfull);
               star1.revalidate();
               star2.revalidate();
               star3.revalidate();
               star4.revalidate();
               star5.revalidate();
           }
           if(e.getSource()==submitButton)
           {
               if(reviewField.getText().length()<1||rating == 0 )
               {
                   JOptionPane.showMessageDialog(submitButton, "Va rugam completati toate campurile!","EROARE REVIEW",JOptionPane.ERROR_MESSAGE);
                   return;
               }
               String comment = reviewField.getText();
               String lineToWrite = lesson.getStudent()+"©"+comment+"©"+rating;
               File dir = new File("datas\\Review");
if (!dir.exists()) {
    dir.mkdir();
}
String fileName = lesson.getTeacher().replaceAll(" ", "_") + "_" + lesson.getId() + ".txt";
File file1 = new File(dir, fileName);
try {
    BufferedWriter writer = new BufferedWriter(new FileWriter(file1, true));
    writer.write(lineToWrite);
    writer.newLine();
    writer.close();
} catch (IOException z) {
    z.printStackTrace();
}
JOptionPane.showMessageDialog(submitButton, "Review trimis cu succes!");
frame.dispose();
           }
        }
    }
    
    public class MyKeyListener implements KeyListener{
        
       public void keyReleased(KeyEvent e) {
         
       }

          public void keyPressed(KeyEvent e) {
                int numarCaractere = reviewField.getText().length();
           if (numarCaractere >= 300) {
  if (e.getKeyCode() == KeyEvent.VK_ENTER) {
    e.consume();
  } }
  
  }
    public void keyTyped(KeyEvent e) {
                     int numarCaractere = reviewField.getText().length();
  if (numarCaractere >= 300) {
    if (e.getKeyCode() != KeyEvent.VK_ENTER) {
      e.consume();
      JOptionPane.showMessageDialog(null, "Numarul de caractere a depasit 300!", "Atentie!", JOptionPane.WARNING_MESSAGE);
      e.consume();
    }
  }
  nrCuvinte.setText("Caractere disponibile: " + reviewField.getText().length() + "/300");
  nrCuvinte.revalidate();
  nrCuvinte.repaint();
}
}
}


