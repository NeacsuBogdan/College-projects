import java.util.*;
import javax.swing.JOptionPane;
public class Ex3{

    public static int verifySort(int v[],int p)
    {
        int ok=1;
        for (int i=0;i<v.length-1;i++)
        {
            if(i<p)
            {
            if(v[i]<v[i+1])
            {
                ok=0;
            }
            }
            if(i>p)
            {
                if (v[i]==v[v.length-1])
                {
                    break;
                }
                if(v[i]>v[i+1])
                {
                    ok=0;
                }
            }
        }
        if (ok==1)
        return 1;
        else
        return 0;
    }
    
    
    
    
    
    public static void main(String[] args)
    {
        String elem="";
        int n=Integer.parseInt(JOptionPane.showInputDialog("Introduceti dimensiunea vectorului: "));
        int[] v = new int[n];
        for(int i=0;i<n;i++)
        {
           v[i]=Integer.parseInt(JOptionPane.showInputDialog(elem+"\nAdaugati elemente in vector: "));
           elem+=v[i]+",  ";
           
        }
        int p=Integer.parseInt(JOptionPane.showInputDialog(elem+"\nIntroduceti punctul de depresiune[indicele]: "));
       if(verifySort(v,p-1)==1)
       {
           JOptionPane.showMessageDialog(null,"Elementele vectorului:\n"+elem+"\nDA,vectorul este tip depresiune", "VECTOR DEPRESSION CHECK",
            JOptionPane.INFORMATION_MESSAGE);
       }
       else if(verifySort(v,p-1)==0)
       {
          JOptionPane.showMessageDialog(null,"Elementele vectorului:\n"+elem+"\nNU,vectorul nu este tip depresiune", "VECTOR DEPRESSION CHECK",
            JOptionPane.INFORMATION_MESSAGE); 
       }
    }
}
