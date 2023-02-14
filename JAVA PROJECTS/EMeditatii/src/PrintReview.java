
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PrintReview{
    private JPanel mainPanel;
    private ImageIcon starfull;
    
    PrintReview(User currentUser)
    {
        starfull = new ImageIcon("C:\\Users\\bogdan\\Desktop\\INFO 2\\PROGRAMARE ORIENTATA OBIECT\\EMeditatii\\images\\star.png");
        mainPanel= new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
           
       for (Review review:currentUser.getReviews())
       {
           JPanel recenzie = new JPanel();
           recenzie.setLayout(new BoxLayout(recenzie, BoxLayout.Y_AXIS));
           recenzie.setAlignmentX(JComponent.CENTER_ALIGNMENT);
           recenzie.setBackground(Color.BLACK);
           JLabel studentName = new JLabel("Student: "+review.getStudent());
           studentName.setFont(new Font("Arial",Font.BOLD,20));
           studentName.setForeground(Color.WHITE);
           studentName.setAlignmentX(JComponent.CENTER_ALIGNMENT);
           JTextArea comment = new JTextArea(review.getComment());
           comment.setForeground(Color.WHITE);
           comment.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
           comment.setWrapStyleWord(true);
           comment.setLineWrap(true);
           comment.setEditable(false);
           comment.setPreferredSize(new Dimension(300,100));
           comment.setOpaque(false);
           comment.setFont(new Font("Arial",Font.BOLD,15));
           comment.setBorder(new LineBorder(Color.RED, 3));
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
        recenzie.add(studentName);
        recenzie.add(comment);
        recenzie.add(ratingPanel);
        recenzie.setBorder(new EmptyBorder(10,10,10,10));
        mainPanel.add(recenzie);
       }
       printToPDF();
    }
    
    
    public void printToPDF()
{
 JFileChooser chooser = new JFileChooser();
chooser.setDialogTitle("Salvează PDF-ul");
chooser.setFileFilter(new FileNameExtensionFilter("PDF Files", "pdf"));
chooser.setAcceptAllFileFilterUsed(false);


if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
  File file = chooser.getSelectedFile();
  String path = file.getAbsolutePath();
  if (!path.endsWith(".pdf")) {
    path += ".pdf";
  }
  
  Document document = new Document(PageSize.A4);
  try {
    PdfWriter.getInstance(document, new FileOutputStream(path));
    document.open();
 for (Component c : mainPanel.getComponents()) {
     if (c instanceof JPanel) {
        JPanel card = (JPanel) c;
        JFrame frame = new JFrame();
frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
frame.add(card);
frame.pack();
    BufferedImage image = new BufferedImage(card.getWidth(), card.getHeight(), BufferedImage.TYPE_INT_ARGB);
    frame.dispose();
    card.paint(image.getGraphics());
    Image pdfImage = Image.getInstance(image, null);
    document.add(pdfImage);
    Paragraph space = new Paragraph("");
document.add(space);
  }
 }
  document.close();
} catch (DocumentException | FileNotFoundException e) {
  e.printStackTrace();
} catch(IOException e){
    e.printStackTrace();
}
}
}
}
