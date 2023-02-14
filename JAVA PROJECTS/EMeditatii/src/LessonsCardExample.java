import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class LessonsCardExample extends JPanel {
private ArrayList<Lesson> lessons;
private JPanel cardContainer;
private JScrollPane scrollPane;
private ActionListener buttonListener;
private User currentUser;
private LessonsCardExample principalLessons;

public LessonsCardExample(User currentUser,ArrayList<Lesson> lessons) {
    
    this.lessons=lessons;
    Collections.sort(lessons);
    this.currentUser=currentUser;
    this.setBackground(Color.BLACK);
    principalLessons=this;
    cardContainer = new JPanel();
    cardContainer.setLayout(new GridBagLayout());
    cardContainer.setBackground(Color.BLACK);
    GridBagConstraints constrb = new GridBagConstraints();
    constrb.insets = new Insets(10,5,10,5);
    buttonListener = new ButtonListener();
    int elem_rand=0;
    int rand=0;
    for (Lesson lesson : lessons) {
        
        JPanel card = new JPanel();
        card.setBackground(Color.BLACK);
        card.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        card.setMaximumSize(new Dimension(150, 100));
        card.setLayout(new GridBagLayout());
        GridBagConstraints constr = new GridBagConstraints();
        constr.insets = new Insets(5,0,5,0);

        JLabel subjectLabel = new JLabel(lesson.getSubject());
        subjectLabel.setForeground(Color.WHITE);
        
        constr.gridx = 0;
        constr.gridy = 0;
        card.add(subjectLabel,constr);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = dateFormat.format(lesson.getDate());
        JLabel dateLabel = new JLabel(date);
        dateLabel.setForeground(Color.WHITE);
        constr.gridy = 1;
        card.add(dateLabel,constr);
        card.putClientProperty("lesson", lesson);
        
        JButton detailsButton = new JButton("Detalii");
        detailsButton.addActionListener(buttonListener);
        detailsButton.setForeground(Color.BLACK);
        detailsButton.setBackground(Color.RED);
        detailsButton.setPreferredSize(new Dimension(150,30));
        detailsButton.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
        
        constr.gridy =2;
        card.add(detailsButton,constr);
        
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

    scrollPane = new JScrollPane(cardContainer);
    scrollPane.setPreferredSize(new Dimension(800,600));
    scrollPane.setBackground(Color.BLACK);
    scrollPane.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
    this.add(scrollPane);
}
public void refresh() {
    cardContainer.removeAll();
     GridBagConstraints constrb = new GridBagConstraints();
    constrb.insets = new Insets(10,5,10,5);
     int elem_rand=0;
     int rand=0;
     for (Lesson lesson : lessons) {
        
        JPanel card = new JPanel();
        card.setBackground(Color.BLACK);
        card.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        card.setMaximumSize(new Dimension(150, 100));
        card.setLayout(new GridBagLayout());
        GridBagConstraints constr = new GridBagConstraints();
        constr.insets = new Insets(5,0,5,0);

        JLabel subjectLabel = new JLabel(lesson.getSubject());
        subjectLabel.setForeground(Color.WHITE);
        
        constr.gridx = 0;
        constr.gridy = 0;
        card.add(subjectLabel,constr);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = dateFormat.format(lesson.getDate());
        JLabel dateLabel = new JLabel(date);
        dateLabel.setForeground(Color.WHITE);
        constr.gridy = 1;
        card.add(dateLabel,constr);
        card.putClientProperty("lesson", lesson);
        
        JButton detailsButton = new JButton("Detalii");
        detailsButton.addActionListener(buttonListener);
        detailsButton.setForeground(Color.BLACK);
        detailsButton.setBackground(Color.RED);
        detailsButton.setPreferredSize(new Dimension(150,30));
        detailsButton.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
        
        constr.gridy =2;
        card.add(detailsButton,constr);
        
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
    cardContainer.revalidate();
    cardContainer.repaint();
}

private class ButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        JPanel card = (JPanel) button.getParent();
        Lesson lesson = (Lesson)card.getClientProperty("lesson");
        // handle the details button press
        new LessonDetailsFrame(lesson,currentUser,principalLessons);
    }
}
}

