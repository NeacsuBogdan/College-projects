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
import java.util.Collections;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class CardExample extends JPanel {
    private ArrayList<User> usersSort;
    private JPanel cardContainer;
    private JScrollPane scrollPane;
    private ActionListener buttonListener;
    private GuiManager guiManager;
    private RequestCardExample requestPrincipal;
    private int index;
    
    public CardExample(HashMap<String, User> users, GuiManager guiManager,RequestCardExample requestPrincipal) {
        this.requestPrincipal=requestPrincipal;
        this.guiManager = guiManager;
        usersSort = new ArrayList<User>(users.values());
        Collections.sort(usersSort);
        
    this.setLayout(new GridLayout(1, 1));

    cardContainer = new JPanel();
    cardContainer.setLayout(new GridBagLayout());
    cardContainer.setBackground(Color.BLACK);
    GridBagConstraints constrb = new GridBagConstraints();
    constrb.insets = new Insets(10,5,10,5);
     int elem_rand=0;
     int rand=0;
    buttonListener = new ButtonListener();

    for (User user : usersSort) {
        if (user.getType().equals("Profesor")) {
JPanel card = new JPanel();
card.setBackground(Color.BLACK);
card.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
card.setPreferredSize(new Dimension(160, 250));
card.setLayout(new GridBagLayout());
GridBagConstraints constr = new GridBagConstraints();
constr.insets = new Insets(5,0,5,0);

constr.gridx = 0;
constr.gridy = 0;
JLabel nameLabel = new JLabel("<html><center>"+user.getName().replaceAll(" ","<br>")+"</center></html>");
nameLabel.setForeground(Color.WHITE);
nameLabel.setMaximumSize(new Dimension((int)nameLabel.getMinimumSize().width,(int)nameLabel.getMinimumSize().width+10));
card.add(nameLabel,constr);

constr.gridy = 1;
JLabel profileImage = new JLabel();
ImageIcon image = new ImageIcon(user.getProfilePicture());
profileImage.setIcon(image);
profileImage.setAlignmentX(Component.CENTER_ALIGNMENT);
profileImage.setPreferredSize(new Dimension(150, 150));
card.add(profileImage,constr);

constr.gridy = 2;
JButton detailsButton = new JButton("Detalii");
detailsButton.setForeground(Color.BLACK);
detailsButton.setBackground(Color.RED);
detailsButton.setPreferredSize(new Dimension(150,30));
detailsButton.setBorder(BorderFactory.createEmptyBorder(5,0,5,0));
detailsButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
detailsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
card.putClientProperty("user", user);
card.add(detailsButton,constr);
detailsButton.addActionListener(buttonListener);

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
this.add(scrollPane);
}
    
private class ButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        JPanel card = (JPanel) button.getParent();
        User user = (User)card.getClientProperty("user");
        new ProfesorDetailsPanel(user,guiManager,requestPrincipal);
        
    }
}
}

