import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;


public class ReviewCardExample extends JPanel {
    private ArrayList<Review> reviewsSort;
    private JPanel cardContainer;
    private JScrollPane scrollPane;
    private ActionListener buttonListener;
    private ImageIcon starfull;

    public ReviewCardExample(ArrayList<Review> reviews) {
        reviewsSort = reviews;
        
        this.setLayout(new GridLayout(1, 1));
        starfull = new ImageIcon("C:\\Users\\bogdan\\Desktop\\INFO 2\\PROGRAMARE ORIENTATA OBIECT\\EMeditatii\\images\\star.png");
        cardContainer = new JPanel();
        cardContainer.setLayout(new GridBagLayout());
        cardContainer.setBackground(Color.BLACK);
       GridBagConstraints constrb = new GridBagConstraints();
       constrb.insets = new Insets(10,5,10,5);
        buttonListener = new ButtonListener();
    int elem_rand=0;
    int rand=0;
        for (Review review : reviewsSort) {
            JPanel card = new JPanel();
            card.setBackground(Color.BLACK);
            card.setMaximumSize(new Dimension(200,120));
            card.setBorder(BorderFactory.createLineBorder(Color.WHITE,2));
            BoxLayout boxLayout = new BoxLayout(card, BoxLayout.Y_AXIS);
            card.setLayout(boxLayout);
        JLabel studentNameLabel = new JLabel(review.getStudent());
        studentNameLabel.setForeground(Color.WHITE);
        studentNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        studentNameLabel.setBorder(BorderFactory.createEmptyBorder(10,5,10,5));
        card.add(studentNameLabel);

        JLabel ratingLabel = new JLabel("Rating: ");
        ratingLabel.setForeground(Color.WHITE);
        ratingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        ratingLabel.setBorder(BorderFactory.createEmptyBorder(10,5,10,5));
        card.add(ratingLabel);
        
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
        
        card.putClientProperty("review", review);
        
        card.add(ratingPanel);
        JButton detailsButton = new JButton("Detalii");
        detailsButton.addActionListener(buttonListener);
        detailsButton.setForeground(Color.BLACK);
        detailsButton.setBackground(Color.RED);
        detailsButton.setMaximumSize(new Dimension(200,40));
        detailsButton.setBorder(BorderFactory.createEmptyBorder(10,5,10,5));
        detailsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(detailsButton);

        if(elem_rand==3)
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
    scrollPane.setPreferredSize(new Dimension(800, 600));
    scrollPane.setBorder(BorderFactory.createLineBorder(Color.WHITE,2));
    this.add(scrollPane);
}

private class ButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        JPanel card = (JPanel) button.getParent();

        // handle the details button press
        Review review = (Review)card.getClientProperty("review");
        new ReviewDetailsFrame(review);
    }
}

}

