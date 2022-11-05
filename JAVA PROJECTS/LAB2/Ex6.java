import java.util.Arrays;
import javax.swing.*;

public class Ex6{
    public static String stars="";
    
    
    public static void printArray(String num1) {
    
    JOptionPane.showMessageDialog(null, num1, "PRINT STARS",
            JOptionPane.INFORMATION_MESSAGE);
}
    
    
    public static void afiseazaPatrat(int n)
    {
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {  
                    stars+="*";
            }
             stars+="\n";
        }
    }
    public static void afiseazaPatrat(int n,char c)
    {
       for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {  
                    stars+=c+" ";
            }
            stars+="\n";
        } 
    }
    
    public static void main(String[] args){
        
        int n=Integer.parseInt(JOptionPane.showInputDialog("Introduceti dimensiunea laturii patratului "));
        char c=JOptionPane.showInputDialog("Scrieti un caracter:").charAt(0);
        afiseazaPatrat(n,c);
        printArray(stars);
    }
    
}
