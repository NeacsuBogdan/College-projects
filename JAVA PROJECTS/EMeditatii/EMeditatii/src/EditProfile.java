import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class EditProfile extends JPanel {
    private User currentUser;
    private JLabel profilePictureLabel;
    private JButton uploadButton;
    private JButton addSubjectButton;
    private JButton addCertificationButton;
    private JLabel emailLabel;
    private JLabel nameLabel;
    private JPasswordField passwordField;
    private JLabel certificationsLabel;
    private JLabel certificari;
    private JLabel subjectsLabel;
    private JLabel subjects;
    private JButton saveButton;
    private JButton cancelButton;
    private ImageIcon starfull;
    private JFrame frame;
    private JPanel mainPanel;
    private EditProfile thisPanel;
    private String profilePicturetoSave;

    public EditProfile(User currentUser) {
        thisPanel=this;
        this.currentUser = currentUser;
        frame = new JFrame();
        frame.setTitle("Edit Profile");
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
         
        mainPanel = new JPanel();
    mainPanel.setBackground(Color.BLACK);
    mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        starfull = new ImageIcon("C:\\Users\\bogdan\\Desktop\\INFO 2\\PROGRAMARE ORIENTATA OBIECT\\EMeditatii\\images\\star.png");
        profilePictureLabel = new JLabel();
        profilePictureLabel.setIcon(new ImageIcon(currentUser.getProfilePicture()));
        profilePictureLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.weightx = 0.5;
        constraints.anchor = GridBagConstraints.CENTER;
        profilePictureLabel.setPreferredSize(new Dimension(150, 150));
        mainPanel.add(profilePictureLabel, constraints);

       ;
        
      constraints.gridwidth = 1;
      constraints.weightx=0;
      constraints.gridx = 2;
      constraints.gridy = 0;
      nameLabel = new JLabel("Nume: " + currentUser.getName());
      nameLabel.setForeground(Color.WHITE);
      mainPanel.add(nameLabel, constraints);

      constraints.gridy = 1;
      emailLabel = new JLabel("Email: " + currentUser.getEmail());
      emailLabel.setForeground(Color.WHITE);
      mainPanel.add(emailLabel, constraints);
      
      if(currentUser.getType().equals("Profesor"))
      {
        constraints.gridx = 0;
        constraints.gridy = 1;
        mainPanel.add(getRatingPanel(),constraints);
        
       constraints.gridx = 2;
       constraints.gridy = 2;
       subjectsLabel=new JLabel("Subiecte");
       subjectsLabel.setForeground(Color.WHITE);
       mainPanel.add(subjectsLabel,constraints);
       
       constraints.gridy = 3;
       subjects=new JLabel("<html>"+listeazaSubiecte()+"</html>");
       subjects.setForeground(Color.WHITE);
      JScrollPane subiecteScroll = new JScrollPane();
      JPanel backgroundPanel = new JPanel();
      backgroundPanel.setBackground(Color.BLACK);
      backgroundPanel.add(subjects);
      subiecteScroll.setPreferredSize(new Dimension(300,100));
      subiecteScroll.setViewportView(backgroundPanel);
      mainPanel.add(subiecteScroll,constraints);
      
      constraints.gridy = 4;
      certificationsLabel = new JLabel("Certificari:");
      certificationsLabel.setForeground(Color.WHITE);
      mainPanel.add(certificationsLabel, constraints);
       
      JScrollPane certificariScroll = new JScrollPane();
      certificariScroll.setPreferredSize(new Dimension(300,100));
      certificari=new JLabel("<html>"+listeazaCertificari()+"</html>");
      certificari.setForeground(Color.WHITE);
      backgroundPanel = new JPanel();
      backgroundPanel.setBackground(Color.BLACK);
      backgroundPanel.add(certificari);
      certificariScroll.setViewportView(backgroundPanel);
      constraints.gridy = 5;
      mainPanel.add(certificariScroll,constraints);
      
      JPanel addButtons = new JPanel();
      addButtons.setBackground(Color.BLACK);
      
      addSubjectButton = new JButton("Add Subiecte");
      addSubjectButton.setForeground(Color.WHITE);
      addSubjectButton.setBackground(Color.BLACK);
      addSubjectButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
      addSubjectButton.setPreferredSize(new Dimension(100,40));
      addCertificationButton = new JButton("Add Certificari");
      addCertificationButton.setForeground(Color.WHITE);
      addCertificationButton.setBackground(Color.BLACK);
      addCertificationButton.setPreferredSize(new Dimension(100,40));
      addCertificationButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
      
      addButtons.add(addSubjectButton);
      addButtons.add(addCertificationButton);
       
    addSubjectButton.addActionListener(new ButtonListener());
    addCertificationButton.addActionListener(new ButtonListener());
     constraints.gridy =6;
     mainPanel.add(addButtons,constraints);
      }
      
      JPanel setButtons = new JPanel();
      setButtons.setBackground(Color.BLACK);
       uploadButton = new JButton("Upload Picture");
       uploadButton.setForeground(Color.WHITE);
       uploadButton.setBackground(Color.BLACK);
       uploadButton.setPreferredSize(new Dimension(100,40));
       uploadButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
       setButtons.add(uploadButton);

       cancelButton = new JButton("Anulează");
       cancelButton.setForeground(Color.WHITE);
      cancelButton.setBackground(Color.BLACK);
      cancelButton.setPreferredSize(new Dimension(100,40));
      cancelButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
       setButtons.add(cancelButton);

       constraints.gridy = 7;
       mainPanel.add(setButtons,constraints);
    uploadButton.addActionListener(new ButtonListener());
    cancelButton.addActionListener(new ButtonListener());
     frame.setResizable(false);
     frame.add(mainPanel);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
}
    
    
    public void refresh()
    {
         mainPanel.removeAll();
    mainPanel.setBackground(Color.BLACK);
    mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        starfull = new ImageIcon("C:\\Users\\bogdan\\Desktop\\INFO 2\\PROGRAMARE ORIENTATA OBIECT\\EMeditatii\\images\\star.png");
        profilePictureLabel = new JLabel();
        profilePictureLabel.setIcon(new ImageIcon(currentUser.getProfilePicture()));
        profilePictureLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.weightx = 0.5;
        constraints.anchor = GridBagConstraints.CENTER;
        profilePictureLabel.setPreferredSize(new Dimension(150, 150));
        mainPanel.add(profilePictureLabel, constraints);

       ;
        
      constraints.gridwidth = 1;
      constraints.weightx=0;
      constraints.gridx = 2;
      constraints.gridy = 0;
      nameLabel = new JLabel("Nume: " + currentUser.getName());
      nameLabel.setForeground(Color.WHITE);
      mainPanel.add(nameLabel, constraints);

      constraints.gridy = 1;
      emailLabel = new JLabel("Email: " + currentUser.getEmail());
      emailLabel.setForeground(Color.WHITE);
      mainPanel.add(emailLabel, constraints);
      
      if(currentUser.getType().equals("Profesor"))
      {
        constraints.gridx = 0;
        constraints.gridy = 1;
        mainPanel.add(getRatingPanel(),constraints);
        
       constraints.gridx = 2;
       constraints.gridy = 2;
       subjectsLabel=new JLabel("Subiecte");
       subjectsLabel.setForeground(Color.WHITE);
       mainPanel.add(subjectsLabel,constraints);
       
       constraints.gridy = 3;
       subjects=new JLabel("<html>"+listeazaSubiecte()+"</html>");
       subjects.setForeground(Color.WHITE);
      JScrollPane subiecteScroll = new JScrollPane();
      JPanel backgroundPanel = new JPanel();
      backgroundPanel.setBackground(Color.BLACK);
      backgroundPanel.add(subjects);
      subiecteScroll.setPreferredSize(new Dimension(300,100));
      subiecteScroll.setViewportView(backgroundPanel);
      mainPanel.add(subiecteScroll,constraints);
      
      constraints.gridy = 4;
      certificationsLabel = new JLabel("Certificari:");
      certificationsLabel.setForeground(Color.WHITE);
      mainPanel.add(certificationsLabel, constraints);
       
      JScrollPane certificariScroll = new JScrollPane();
      certificariScroll.setPreferredSize(new Dimension(300,100));
      certificari=new JLabel("<html>"+listeazaCertificari()+"</html>");
      certificari.setForeground(Color.WHITE);
      backgroundPanel = new JPanel();
      backgroundPanel.setBackground(Color.BLACK);
      backgroundPanel.add(certificari);
      certificariScroll.setViewportView(backgroundPanel);
      constraints.gridy = 5;
      mainPanel.add(certificariScroll,constraints);
      
      JPanel addButtons = new JPanel();
      addButtons.setBackground(Color.BLACK);
      
      addSubjectButton = new JButton("Add Subiecte");
      addSubjectButton.setForeground(Color.WHITE);
      addSubjectButton.setBackground(Color.BLACK);
      addSubjectButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
      addSubjectButton.setPreferredSize(new Dimension(100,40));
      addCertificationButton = new JButton("Add Certificari");
      addCertificationButton.setForeground(Color.WHITE);
      addCertificationButton.setBackground(Color.BLACK);
      addCertificationButton.setPreferredSize(new Dimension(100,40));
      addCertificationButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
      
      addButtons.add(addSubjectButton);
      addButtons.add(addCertificationButton);
       
    addSubjectButton.addActionListener(new ButtonListener());
    addCertificationButton.addActionListener(new ButtonListener());
     constraints.gridy =6;
     mainPanel.add(addButtons,constraints);
      }
      
      JPanel setButtons = new JPanel();
      setButtons.setBackground(Color.BLACK);
       uploadButton = new JButton("Upload Picture");
       uploadButton.setForeground(Color.WHITE);
       uploadButton.setBackground(Color.BLACK);
       uploadButton.setPreferredSize(new Dimension(100,40));
       uploadButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
       setButtons.add(uploadButton);

       cancelButton = new JButton("Anulează");
       cancelButton.setForeground(Color.WHITE);
      cancelButton.setBackground(Color.BLACK);
      cancelButton.setPreferredSize(new Dimension(100,40));
      cancelButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
       setButtons.add(cancelButton);

       constraints.gridy = 7;
       mainPanel.add(setButtons,constraints);
    uploadButton.addActionListener(new ButtonListener());
    cancelButton.addActionListener(new ButtonListener());
    mainPanel.revalidate();
    mainPanel.repaint();
    }
private class ButtonListener implements ActionListener {
public void actionPerformed(ActionEvent e) {

    if (e.getSource() == uploadButton) {
       JOptionPane.showMessageDialog(uploadButton, "Poza trebuie sa aiba rezolutia 150x150 altfel nu va fi afisata bine!","ATENTIE!",JOptionPane.INFORMATION_MESSAGE);
         
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Image files", "jpg", "png", "gif", "jpeg");
        fileChooser.addChoosableFileFilter(imageFilter);
        int result = fileChooser.showOpenDialog((JButton) e.getSource());
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            profilePictureLabel.setIcon(new ImageIcon(selectedFile.getAbsolutePath()));
            profilePicturetoSave = selectedFile.getAbsolutePath();
            currentUser.setProfilePicture(profilePicturetoSave);
        }
    }
    else if(e.getSource() == addSubjectButton){
        // handle the add subject button press
        // put your code here
        new AddSubjectFrame(currentUser,thisPanel);
    }
    else if(e.getSource() == addCertificationButton){
        // handle the add certification button press
        // put your code here
        new AddCertificateFrame(currentUser,thisPanel);
    }
    else if(e.getSource() == cancelButton){
        // handle the cancel button press
        // put your code here
        frame.dispose();
    }
}
}

private String listeazaCertificari()
{
    StringBuilder sb = new StringBuilder();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    for(Certificate certificare:currentUser.getCertificates())
    {
        sb.append(certificare.getName());
        sb.append(" din ");
        sb.append(dateFormat.format(certificare.getDate()));
        sb.append("<br>");
    }
            
       return sb.toString();
}
private String listeazaSubiecte()
{ 
    StringBuilder sb = new StringBuilder();
    for(String subiect:currentUser.getSubjects())
    {
        String line[]=subiect.split(" ");
        sb.append("Materie: ");
        sb.append(line[0]);
        sb.append(" pret: ");
        sb.append(line[1]);
        sb.append(" RON");
        sb.append("<br>");
    }
    return sb.toString();
}

private JPanel getRatingPanel()
{
    JPanel ratingPanel = new JPanel();
ratingPanel.setPreferredSize(new Dimension(200,50));
ratingPanel.setBackground(Color.BLACK);
        JLabel star1 = new JLabel();
        star1.setIcon(starfull);
        JLabel star2 = new JLabel();
        star2.setIcon(starfull);
        JLabel star3 = new JLabel();
        star3.setIcon(starfull);
        JLabel star4 = new JLabel();
        star4.setIcon(starfull);
        JLabel star5 = new JLabel();
        star5.setIcon(starfull);
        int rating = getGeneralRating();
       
        switch(rating)
        {
            case 1:
                ratingPanel.add(star1);
                break;
            case 2:
                ratingPanel.add(star1);
                ratingPanel.add(star2);
                break;
            case 3:
                ratingPanel.add(star1);
                ratingPanel.add(star2);
                ratingPanel.add(star3);
                break;
            case 4:
                ratingPanel.add(star1);
                ratingPanel.add(star2);
                ratingPanel.add(star3);
                ratingPanel.add(star4);
                break;
            case 5:
                ratingPanel.add(star1);
                ratingPanel.add(star2);
                ratingPanel.add(star3);
                ratingPanel.add(star4);
                ratingPanel.add(star5);
        }
        
        return ratingPanel;
}

private int getGeneralRating()
    {
        int r=0;
        for (Review review:currentUser.getReviews())
        {
            r+=review.getRating();
        }
        try{
        return r/currentUser.getReviews().size();
        }catch(ArithmeticException ex)
        {
            return 5;
        }
    }
}