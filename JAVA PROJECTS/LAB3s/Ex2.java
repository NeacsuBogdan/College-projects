import java.util.Arrays;
import javax.swing.*;
public class Ex2{
    public static String acronim;
    
     
    public static void printArray() 
    {
  
    JOptionPane.showMessageDialog(null,"Segmentul de cod afiseaza primele litere ale fiecarui cuvant:\n"+acronim, "GET ACRONIM",
            JOptionPane.INFORMATION_MESSAGE);
}
    
    public static void getFirstChar(String name)
    {
        
       boolean startWord= true;
       int trigger=0;
        for(int i=0;i<name.length();i++)
       {
           if(startWord)
           {
               if(trigger==0)
               {
               acronim=name.charAt(i)+"\n";
               trigger++;
               }
               else
                   acronim+=name.charAt(i)+"\n";
           }
                 if(name.charAt(i)==' ')
           {
               startWord=true;
           }
           else
               startWord=false;
       }
    }
    
    public static void main (String[] args)
    {
       String name="Richard M. Nixon";
       getFirstChar(name);
       printArray();
    }
}

// Programul afiseaza prima litera a fiecarui cuvant .