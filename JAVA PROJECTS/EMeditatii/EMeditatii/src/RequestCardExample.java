import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class RequestCardExample extends JPanel {
    private ArrayList<Request> requests;
    private JPanel cardContainer;
    private JScrollPane scrollPane;
    private ActionListener buttonListener;
    private int index;
    private User currentUser;
    private JFrame frame;
    private RequestCardExample principal;
    private LessonsCardExample principal_lessons;
    private HashMap<String, User> auth;
    
    
    public RequestCardExample(User currentUser,HashMap<String, User> auth,LessonsCardExample lessons) {
        this.principal_lessons=lessons;
        this.currentUser = currentUser;
        this.auth = auth;
        this.requests = currentUser.getRequests();
        principal = this;

        cardContainer = new JPanel();
       cardContainer.setLayout(new GridBagLayout());
       cardContainer.setBackground(Color.BLACK);
       GridBagConstraints constrb = new GridBagConstraints();
       constrb.insets = new Insets(10,5,10,5);
        buttonListener = new ButtonListener();
    int elem_rand=0;
    int rand=0;
        for (Request request : requests) {
            if(request.getActive()==1){
            JPanel card = new JPanel();
            card.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            index = requests.indexOf(request);
            card.setBackground(Color.BLACK);
            card.setPreferredSize(new Dimension(150, 100));
           card.setLayout(new GridBagLayout());
           GridBagConstraints constr = new GridBagConstraints();
            constr.insets = new Insets(5,0,5,0);
            constr.gridx = 0;
            constr.gridy = 0;
            if(currentUser.getType().equals("Profesor"))
            {
            JLabel participantLabel = new JLabel(request.getParticipant());
            participantLabel.setForeground(Color.WHITE);
            participantLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            card.add(participantLabel,constr);
            }
            else
            {
                JLabel participantLabel = new JLabel(request.getLesson().getTeacher());
            participantLabel.setForeground(Color.WHITE);
            participantLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            card.add(participantLabel,constr);
            }
            constr.gridy = 1;
            
            JLabel lessonLabel = new JLabel(request.getLesson().getSubject());
            lessonLabel.setForeground(Color.WHITE);
            lessonLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            card.add(lessonLabel,constr);

            JButton detailsButton = new JButton("Detalii");
            detailsButton.addActionListener(buttonListener);
            detailsButton.setForeground(Color.BLACK);
        detailsButton.setBackground(Color.RED);
        detailsButton.setPreferredSize(new Dimension(140,30));
        detailsButton.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
            
            constr.gridy =2;
            card.add(detailsButton,constr);
            card.putClientProperty("index", index);
            if(elem_rand==5)
        {
            rand++;
            elem_rand=0;
        }
        constrb.gridx = rand;
        constrb.gridx = elem_rand;
        cardContainer.add(card,constrb);
        elem_rand++;
        }
            
        }

        scrollPane = new JScrollPane(cardContainer);
        scrollPane.setPreferredSize(new Dimension(800,600));
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
    principal.add(scrollPane);
    principal.setBackground(Color.BLACK);
    principal.setVisible(true);
    
    }

     public void reloadRequests() {
        cardContainer.removeAll();
        GridBagConstraints constrb = new GridBagConstraints();
       constrb.insets = new Insets(10,5,10,5);
         int elem_rand=0;
         int rand=0;
        for (Request request : requests) {
            if(request.getActive()==1){
            JPanel card = new JPanel();
            card.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            index = requests.indexOf(request);
            card.setBackground(Color.BLACK);
            card.setPreferredSize(new Dimension(150, 100));
           card.setLayout(new GridBagLayout());
           GridBagConstraints constr = new GridBagConstraints();
            constr.insets = new Insets(5,0,5,0);
            constr.gridx = 0;
            constr.gridy = 0;
            if(currentUser.getType().equals("Profesor"))
            {
            JLabel participantLabel = new JLabel(request.getParticipant());
            participantLabel.setForeground(Color.WHITE);
            participantLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            card.add(participantLabel,constr);
            }
            else
            {
                JLabel participantLabel = new JLabel(request.getLesson().getTeacher());
            participantLabel.setForeground(Color.WHITE);
            participantLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            card.add(participantLabel,constr);
            }
            constr.gridy = 1;
            
            JLabel lessonLabel = new JLabel(request.getLesson().getSubject());
            lessonLabel.setForeground(Color.WHITE);
            lessonLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            card.add(lessonLabel,constr);

            JButton detailsButton = new JButton("Detalii");
            detailsButton.addActionListener(buttonListener);
            detailsButton.setForeground(Color.BLACK);
        detailsButton.setBackground(Color.RED);
        detailsButton.setPreferredSize(new Dimension(140,30));
        detailsButton.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
            
            constr.gridy =2;
            card.add(detailsButton,constr);
            card.putClientProperty("index", index);
            if(elem_rand==5)
        {
            rand++;
            elem_rand=0;
        }
        constrb.gridx = rand;
        constrb.gridx = elem_rand;
        cardContainer.add(card,constrb);
        elem_rand++;

        }
        }
        cardContainer.revalidate();
        cardContainer.repaint();
    }
     private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            JPanel card = (JPanel) button.getParent();
            Request request = requests.get((int)card.getClientProperty("index"));
            RequestDetailsFrame detailsFrame = new RequestDetailsFrame(request,principal,auth,currentUser,principal_lessons);
            detailsFrame.showFrame();
        }
    }
}
     

