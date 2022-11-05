import javax.swing.*;
public class Ex4{
    
    public static int countChars(String text,char simbol)
    {
        int count=0;
        for(int i=0;i<text.length();i++)
        {
            if (simbol==text.charAt(i))
            {
                count++;
            }
        }
        return count;
    }
    
    public static void main(String[] args)
    {
       String text=JOptionPane.showInputDialog("Scrieti o propozitie:");
       char simbol=JOptionPane.showInputDialog("Scrieti un caracter:").charAt(0);
        JOptionPane.showMessageDialog(null, "''"+text+"''\nNumarul de aparitii al lui ''"+simbol+"'' in textul dat este: "+countChars(text,simbol), "NR APARITII",
            JOptionPane.INFORMATION_MESSAGE);
    }
}

