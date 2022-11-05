import javax.swing.*;
public class Ex1{
    
    
    
    public static void main (String[] args)
    {
        int s=0;
        for(int i=1;i<=15;i++)
        {
            s=s+i;
        }
        JOptionPane.showMessageDialog(null,"Suma numerelor din intervalul [1,15] este: "+s);

    }
}
