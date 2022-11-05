import javax.swing.*;
public class Ex1{
    private static int MAX=10000,MIN=1;
    public static int countDivizori(int n)
    {
        int div=2,nr_div=1,putere;
         while(n!=1)
         {
             putere=0;
             while(n%div==0)
             {
                 n=n/div;
                 putere++;
             }
             if(putere>0)
             {
                 nr_div=nr_div*(putere+1);
             }
             div++;
         }
        return nr_div;
    }
    
    public static int maxim(int start)
    {
        if (start==MAX)
        {
            return MAX;
        }
        if (countDivizori(start)>countDivizori(MAX))
        {
            MAX=start;
        }
        else
        {
         maxim(start+1);
        }
        return MAX;
    }
    
    
    
    public static void main (String[] args)
    {
        MIN=Integer.parseInt(JOptionPane.showInputDialog("Introduceti inceputul intervalului"));
        MAX=Integer.parseInt(JOptionPane.showInputDialog("Introduceti sfarsitul intervalului"));
         JOptionPane.showMessageDialog(null,"Numarul cu cei mai multi divizori cuprins in intervalul ["+MIN+","+MAX+"] este: "+maxim(MIN));
    }
}
