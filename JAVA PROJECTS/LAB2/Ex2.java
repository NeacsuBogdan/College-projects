import javax.swing.*;
public class Ex2{
    public static void main (String[] args)
    {
        double p=1;
       double x1=Double.parseDouble(JOptionPane.showInputDialog("Introduceti inceputul intervalului"));
       double x2=Double.parseDouble(JOptionPane.showInputDialog("Introduceti sfarsitul intervalului"));
       
       for (double i=x1;i<=x2;i++)
       {
           if(i%2==0)
           {
               p=p*i;
           }
       }
       JOptionPane.showMessageDialog(null,"Suma numerelor din intervalul ["+(int)x1+","+(int)x2+"] este: "+p+"  ");
    }
}
