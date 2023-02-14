
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame {
    private JFrame frame;
    private JPanel mainPanel;
    private JButton professorsButton;
    private JButton scheduledLessonsButton;
    private JButton requestsButton;
    private JButton reviewsButton;
    private JButton logoutButton;
    private JButton editButton;
    private JButton inboxButton;
    private JButton printReview;
    private ActionListener buttonListener;
    private GuiManager guiManager;
    private CardExample cardExample;
    private RequestCardExample requestCards;
    private LessonsCardExample lessonCardExample;
    private ReviewCardExample reviewCardExample;
    private HashMap<String, User> auth;
    private MainWindow mainwindowprincipal;
    
    
    MainWindow(GuiManager guiManager, HashMap<String, User> users) {
        mainwindowprincipal=this;
        this.auth = users;
        this.guiManager = guiManager;
        frame = new JFrame("EMeditatii");
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setVisible(true);
        mainPanel.setBackground(Color.BLACK);

        
        // Create LessonsCardExample and add to mainPanel
        lessonCardExample = new LessonsCardExample(guiManager.getCurrentUser(),guiManager.getCurrentUser().getLessons());
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 0.3;
        constraints.weighty = 1.0;
        mainPanel.add(lessonCardExample,constraints);
        lessonCardExample.setVisible(false);
        
       //Create requestExample and add to mainPanel 
       requestCards = new RequestCardExample(guiManager.getCurrentUser(),auth,lessonCardExample);
       constraints.gridx = 0;
       constraints.gridy = 0;
       constraints.gridwidth = 2;
       constraints.fill = GridBagConstraints.BOTH;
       constraints.weightx = 0.3;
       constraints.weighty = 1.0;
       mainPanel.add(requestCards, constraints);
       requestCards.setVisible(false);
        
        
         // Create cardExample and add to mainPanel
        cardExample = new CardExample(users,guiManager,requestCards);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 0.3;
        constraints.weighty = 1.0;
        mainPanel.add(cardExample, constraints);
        
         if(guiManager.getCurrentUser().getType().equals("Profesor"))
        {
            cardExample.setVisible(false);
        }
        else
        {
            cardExample.setVisible(true);
        }
        
        
        // Create ReviewCardExample and add to mainPanel
        if(guiManager.getCurrentUser().getType().equals("Profesor"))
        {
        reviewCardExample = new ReviewCardExample(guiManager.getCurrentUser().getReviews());
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 0.3;
        constraints.weighty = 1.0;
        mainPanel.add( reviewCardExample,constraints);
        }
        
       // Create buttons and add to mainPanel
       buttonListener = new ButtonListener();
       if(this.guiManager.getCurrentUser().getType().equals("Profesor")) {
            // Set position for reviews button
            reviewsButton = new JButton("Reviews");
            reviewsButton.setForeground(Color.BLACK);
            reviewsButton.setBackground(Color.RED);
            reviewsButton.setPreferredSize(new Dimension(70,30));
            reviewsButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            constraints.gridx = 0;
            constraints.gridy = 1;
            mainPanel.add(reviewsButton, constraints);
            reviewsButton.addActionListener(buttonListener);
        }
        else {
            // Set position for professors button
            professorsButton = new JButton("Profesori");
            professorsButton.setForeground(Color.BLACK);
            professorsButton.setBackground(Color.RED);
            professorsButton.setPreferredSize(new Dimension(70,30));
            professorsButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            constraints.gridx = 0;
            constraints.gridy = 1;
            mainPanel.add(professorsButton, constraints);
            professorsButton.addActionListener(buttonListener);
        }
       
       requestsButton = new JButton("Cereri");
       requestsButton.setForeground(Color.BLACK);
       requestsButton.setBackground(Color.RED);
       requestsButton.setPreferredSize(new Dimension(70,30));
       requestsButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
       constraints.gridx = 0;
       constraints.gridy = 3;
       mainPanel.add(requestsButton, constraints);

        scheduledLessonsButton = new JButton("Lectii programate");
        scheduledLessonsButton.setForeground(Color.BLACK);
        scheduledLessonsButton.setBackground(Color.RED);
        scheduledLessonsButton.setPreferredSize(new Dimension(70,30));
        scheduledLessonsButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        constraints.gridx = 0;
        constraints.gridy = 2;
        mainPanel.add(scheduledLessonsButton, constraints);

        // Create logout and edit buttons and add to mainPanel
        JPanel topRightPanel = new JPanel();
        topRightPanel.setBackground(Color.BLACK);
        editButton = new JButton("Setari");
        editButton.setForeground(Color.WHITE);
        editButton.setBackground(Color.BLACK);
        editButton.setPreferredSize(new Dimension(70,30));
        editButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        topRightPanel.add(editButton);
        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.NORTHEAST;
        
        inboxButton = new JButton("Inbox("+guiManager.getCurrentUser().getMessages().size()+")");
        inboxButton.setForeground(Color.WHITE);
        inboxButton.setBackground(Color.BLACK);
        inboxButton.setPreferredSize(new Dimension(70,30));
        inboxButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        topRightPanel.add(inboxButton, constraints);
        
        if(guiManager.getCurrentUser().getType().equals("Profesor"))
        {
            printReview=new JButton("Print\nReview");
            printReview.setForeground(Color.WHITE);
            printReview.setBackground(Color.BLACK);
            printReview.setPreferredSize(new Dimension(100,30));
            printReview.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            constraints.gridx = 4;
            topRightPanel.add(printReview,constraints);
            printReview.addActionListener(buttonListener);
        }
        
        
        
        logoutButton = new JButton("Logout");
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBackground(Color.BLACK);
        logoutButton.setPreferredSize(new Dimension(70,30));
        logoutButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        topRightPanel.add(logoutButton);
        constraints.gridx = 2;
        constraints.gridy = 3;
        constraints.anchor = GridBagConstraints.NORTHEAST;

        mainPanel.add(topRightPanel, constraints);
        
        
        
        scheduledLessonsButton.addActionListener(buttonListener);
        logoutButton.addActionListener(buttonListener);
        editButton.addActionListener(buttonListener);
        requestsButton.addActionListener(buttonListener);
        inboxButton.addActionListener(buttonListener);
           frame.add(mainPanel);
           frame.pack();
           frame.setLocationRelativeTo(null);
           frame.setVisible(true);
}

   public void setVisibility (boolean b) {
       frame.setVisible(b);
    }

    
    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == professorsButton) {
                // Afișați panoul cu profesori
                lessonCardExample.setVisible(false);
                requestCards.setVisible(false);
                cardExample.setVisible(true);
                frame.pack();
                
            } else if (e.getSource() == scheduledLessonsButton) {
                 // Afișați panoul cu lectii
                 if (guiManager.getCurrentUser().getType().equals("Profesor"))
                 {
                 reviewCardExample.setVisible(false);
                 }
                 cardExample.setVisible(false);
                 lessonCardExample.setVisible(true);
                 requestCards.setVisible(false);
                 frame.pack();
            }
            else if(e.getSource() == logoutButton)
            {
                frame.dispose();
                guiManager.openAuth();
            }
            else if (e.getSource()== editButton)
            {
            EditProfile editProfile = new EditProfile(guiManager.getCurrentUser());
            editProfile.setVisible(true);
            }
            else if (e.getSource()==reviewsButton)
            {
                reviewCardExample.setVisible(true);
                lessonCardExample.setVisible(false);
                requestCards.setVisible(false);
                frame.pack();
            }
            else if (e.getSource()==requestsButton)
            {
                if (guiManager.getCurrentUser().getType().equals("Profesor"))
                 {
                 reviewCardExample.setVisible(false);
                 }
                 cardExample.setVisible(false);
                 lessonCardExample.setVisible(false);
                 requestCards.setVisible(true);
                 frame.pack();
            }
            else if (e.getSource()==inboxButton)
            {
                
                new Inbox(guiManager.getCurrentUser(),mainwindowprincipal);
            }
            else if (e.getSource()==printReview)
            {
             new PrintReview(guiManager.getCurrentUser()); 
            }
                
}
    }

    public void updateInbox() {
    inboxButton.setText("Inbox (" +guiManager.getCurrentUser().getMessages().size() + ")");
    inboxButton.revalidate();
    inboxButton.repaint();
    frame.pack();
    
}   
}