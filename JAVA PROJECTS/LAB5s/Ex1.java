import java.util.Arrays;
import javax.swing.JOptionPane;
public class Ex1{
    static int MIN_DIGIT=0;
    
    public static int maxDig(int n)
    {
        if(n<0)
        {
            n*=-1;
        }
        int mai_mare=MIN_DIGIT;
        while(n != 0)
    {
        int digit = n % 10;
 
        // Gaseste cea mai mare cifra
        mai_mare = Math.max(digit, mai_mare);
        n = n / 10;
    }
        return mai_mare;
    }
    
    public static int getNr(int v[])
    {
        
        for (int i=0;i<v.length;i++)
        {
            v[i]=maxDig(v[i]);
        }
        return 0;
    }
    
    
    public static void main(String[] args)
    {
        int n,nr_final=0;
        String elemv="";
        String elemf="";
        n=Integer.parseInt(JOptionPane.showInputDialog("Introduceti dimensiunea vectorului: "));
        int[] v = new int[n];
        for(int i=0;i<n;i++)
        {
           v[i]=Integer.parseInt(JOptionPane.showInputDialog(elemv+"\nAdaugati elemente in vector: "));
           elemv+=v[i]+",  ";
           
        }
        getNr(v);
        Arrays.sort(v);
        for(int i=v.length-1;i>=0;i--)
        {
            nr_final=nr_final*10+v[i];
            elemf+=v[i]+",  ";
        }
           JOptionPane.showMessageDialog(null,elemv+"\nCifrele maxime apartinand fiecarui elem din vector sunt: \n"+elemf+"\nNumarul maxim ce poate fi format cu acestea este: \n"+nr_final, "PRINT STARS",
            JOptionPane.INFORMATION_MESSAGE);
        
    }
}
