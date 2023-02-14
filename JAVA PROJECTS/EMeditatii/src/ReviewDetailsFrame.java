
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class ReviewDetailsFrame extends JPanel{
    private JLabel student;
    private JTextArea mesaj;
    private JLabel commLabel;
    private JFrame frame;
    ReviewDetailsFrame(Review review)
    {
        frame = new JFrame();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(Color.BLACK);
        GridBagConstraints constr = new GridBagConstraints();
        constr.insets = new Insets(10,10,10,10);
        student = new JLabel("Student: "+review.getStudent());
        student.setFont(new Font("Arial",Font.BOLD,20));
        student.setForeground(Color.red);
        commLabel = new JLabel("Comment:");
        commLabel.setForeground(Color.WHITE);
        commLabel.setFont(new Font("Arial",Font.BOLD,20));
        mesaj = new JTextArea(review.getComment());
        mesaj.setSize(300,100);
        mesaj.setFont(new Font("Arial",Font.BOLD,15));
        mesaj.setOpaque(false);
        mesaj.setMaximumSize(new Dimension(500,frame.getHeight()));
        mesaj.setWrapStyleWord(true);
        mesaj.setLineWrap(true);
        mesaj.setEditable(false);
        mesaj.setForeground(Color.WHITE);
        mesaj.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.WHITE,2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        constr.gridx = 0;
        constr.gridy = 0;
        mainPanel.add(student,constr);
        constr.gridy = 1;
        mainPanel.add(commLabel,constr);
        constr.gridy = 2;
        mainPanel.add(mesaj,constr);
        mainPanel.setBorder(new EmptyBorder(50,50,50,50));
        frame.setMaximumSize(new Dimension(400,800));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400,400);
        frame.add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    }
}
