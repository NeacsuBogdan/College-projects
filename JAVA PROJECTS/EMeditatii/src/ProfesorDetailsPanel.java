import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

class ProfesorDetailsPanel extends JFrame {
    private User professor;
    private Lesson selectedLesson;
    private JFrame profesorDetailsFrame;
    private JButton requestButton;
    private JButton okButton;
    private GuiManager guiManager;
    private Request request;
    private ImageIcon starfull;
    private RequestCardExample requestPrincipal;
    
    public ProfesorDetailsPanel(User professor,GuiManager guiManager,RequestCardExample requestPrincipal) {
        this.requestPrincipal=requestPrincipal;
        this.professor = professor;
        this.guiManager = guiManager;
    profesorDetailsFrame = new JFrame("Detalii profesor");
    profesorDetailsFrame.setSize(400, 400);
    profesorDetailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    profesorDetailsFrame.setResizable(false);
    
     starfull = new ImageIcon("C:\\Users\\bogdan\\Desktop\\INFO 2\\PROGRAMARE ORIENTATA OBIECT\\EMeditatii\\images\\star.png");
        
 
    JPanel professorDetailsPanel = new JPanel();
    professorDetailsPanel.setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    professorDetailsPanel.setBackground(Color.BLACK);
    //professorDetailsPanel.setBackground(Color.BLACK);
    constraints.insets = new Insets(10, 10, 10, 10);
        
    ImageIcon profilePicture = new ImageIcon(professor.getProfilePicture());
    //redimensioneaza poza
    profilePicture.setImage(profilePicture.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
     JLabel profilePictureLabel = new JLabel(profilePicture);
     profilePictureLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

//pozitionare poza in layout
constraints.gridx = 0;
constraints.gridy = 0;
constraints.gridheight = 4;
professorDetailsPanel.add(profilePictureLabel, constraints);
       

    // Display professor's name
constraints.gridx = 1;
constraints.gridy = 0;
constraints.gridheight = 1;
JLabel nameLabel = new JLabel("Nume: " + professor.getName());
nameLabel.setForeground(Color.WHITE);
professorDetailsPanel.add(nameLabel, constraints);

// Display professor's subjects
constraints.gridy = 1;
constraints.gridheight = 1;
JLabel subjectsLabel = new JLabel("Subiecte predate: ");
subjectsLabel.setForeground(Color.WHITE);
professorDetailsPanel.add(subjectsLabel, constraints);

constraints.gridy = 2;
JLabel subjects = new JLabel("<html>"+listeazaSubiecte()+"<html>");
subjects.setForeground(Color.WHITE);
JScrollPane subiecteScroll = new JScrollPane();
subiecteScroll.setVisible(true);
subiecteScroll.setPreferredSize(new Dimension(300,100));
subiecteScroll.setViewportView(subjects);
JPanel backgroundPanel = new JPanel();
backgroundPanel.setBackground(Color.BLACK);
backgroundPanel.add(subjects);
subiecteScroll.setViewportView(backgroundPanel);
subiecteScroll.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
professorDetailsPanel.add(subiecteScroll,constraints);


// Display professor's certifications
constraints.gridy =3;
constraints.gridheight = 1;
JLabel certificationsLabel = new JLabel("Calificari: ");
certificationsLabel.setForeground(Color.WHITE);
professorDetailsPanel.add(certificationsLabel, constraints);

constraints.gridy = 4;
JScrollPane certificariScroll = new JScrollPane();
certificariScroll.setPreferredSize(new Dimension(300,100));
JLabel certificari=new JLabel("<html>"+listeazaCertificari()+"</html>");
certificari.setForeground(Color.WHITE);
backgroundPanel = new JPanel();
backgroundPanel.setBackground(Color.BLACK);
backgroundPanel.add(certificari);

certificariScroll.setViewportView(backgroundPanel);
certificariScroll.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
professorDetailsPanel.add(certificariScroll,constraints);


// Display professor's reviews
constraints.gridy = 5;
constraints.gridheight = 1;
JLabel reviewsLabel = new JLabel("Reviewuri: ");
reviewsLabel.setForeground(Color.WHITE);
professorDetailsPanel.add(reviewsLabel, constraints);

constraints.gridy = 6;
JScrollPane reviews = new JScrollPane();
reviews.setPreferredSize(new Dimension(500,200));
reviews.setViewportView(createPanelReviews());
reviews.setBackground(Color.BLACK);
reviews.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
professorDetailsPanel.add(reviews,constraints);
 
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


        constraints.gridx = 0;
        constraints.gridy = 3;
        professorDetailsPanel.add(ratingPanel,constraints);
    requestButton = new JButton("Request");
    requestButton.setPreferredSize(new Dimension(70,30));
    requestButton.setForeground(Color.WHITE);
    requestButton.setBackground(Color.BLACK);
    requestButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
    requestButton.addActionListener(new ButtonListener());
    constraints.gridx = 0;
    constraints.gridy = 4;
    professorDetailsPanel.add(requestButton, constraints);

    okButton = new JButton("Ok");
    okButton.setForeground(Color.WHITE);
    okButton.setBackground(Color.BLACK);
    okButton.setPreferredSize(new Dimension(70,30));
    okButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
    okButton.addActionListener(new ButtonListener());
    constraints.gridx = 0;
    constraints.gridy = 5;
    professorDetailsPanel.add(okButton, constraints);

    profesorDetailsFrame.add(professorDetailsPanel);
    profesorDetailsFrame.setVisible(true);
     profesorDetailsFrame.pack();
     profesorDetailsFrame.setLocationRelativeTo(null);
    }
    
    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == requestButton)
            {
            new AddRequestFrame(professor,guiManager.getCurrentUser(),requestPrincipal);
            // Show a message or dialog box to confirm that the request was sent
            }
            else if (e.getSource() == okButton)
            {
                profesorDetailsFrame.dispose();
            }
        }
    }
    
    private int getGeneralRating()
    {
        int r=0;
        for (Review review:professor.getReviews())
        {
            r+=review.getRating();
        }
        try{
        return r/professor.getReviews().size();
        }catch(ArithmeticException ex)
        {
            return 5;
        }
    }
    private String listeazaSubiecte()
{ 
    StringBuilder sb = new StringBuilder();
    for(String subiect:professor.getSubjects())
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
    
    private String listeazaCertificari()
{
    StringBuilder sb = new StringBuilder();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
    
    for(Certificate certificare:professor.getCertificates())
    {
        sb.append(certificare.getName());
        sb.append(" din ");
        sb.append(dateFormat.format(certificare.getDate()));
        sb.append("<br>");
    }
            
       return sb.toString();
}
   
    private JPanel createPanelReviews()
    {
        JPanel mainPanel= new JPanel();
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
           
       for (Review review:professor.getReviews())
       {
           JPanel recenzie = new JPanel();
           recenzie.setLayout(new BoxLayout(recenzie, BoxLayout.Y_AXIS));
           recenzie.setAlignmentX(JComponent.CENTER_ALIGNMENT);
           JLabel studentName = new JLabel("Student: "+review.getStudent());
           studentName.setFont(new Font("Arial",Font.BOLD,20));
           studentName.setForeground(Color.WHITE);
           studentName.setAlignmentX(JComponent.CENTER_ALIGNMENT);
           JTextArea comment = new JTextArea(review.getComment());
           comment.setForeground(Color.WHITE);
           comment.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 10));
           comment.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
           comment.setWrapStyleWord(true);
           comment.setLineWrap(true);
           comment.setEditable(false);
           comment.setPreferredSize(new Dimension(300,100));
           comment.setOpaque(false);
           comment.setFont(new Font("Arial",Font.BOLD,15));
           
JPanel wrapper = new JPanel();
wrapper.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
wrapper.add(comment);
wrapper.setBackground(Color.BLACK);
           JPanel ratingPanel = new JPanel();
        ratingPanel.setPreferredSize(new Dimension(200,50));
        ratingPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
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
        int rating = review.getRating();
        
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
        recenzie.add(studentName);
        recenzie.add(wrapper);
        recenzie.add(ratingPanel);
        recenzie.setBorder(new EmptyBorder(10,10,10,10));
        recenzie.setBackground(Color.BLACK);
        mainPanel.add(recenzie);
       }
return mainPanel;
    }
    
}
