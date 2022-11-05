import javax.swing.*;
public class Ex7{
    public static final double EPS = Math.pow(10, -15);
    
    public static double factorial(double y)
    {
        double factorial=1.0;
     for(double i=2;i<=y;i++)
     {
         factorial*=i;
     }
     return factorial;
    }
    
   
    public static double series(int x)
    {
        double serie = 1;
        int n = 1;
        double tp=1;
  
        while(Math.abs(tp)>EPS){
           tp= Math.pow(x,n)/factorial(n);
           serie+=tp;
           n++;
        }
        return serie;
    }
    
    
    
    public static void main(String[] args)
    {
        double x = Double.parseDouble(JOptionPane.showInputDialog("Calculati e la puterea : "));
        JOptionPane.showMessageDialog(null,"e la puterea "+(int)x+" este: "+series((int)x)+"\n"+"Valoare prin functia implicita : "+Math.exp(x));
    }
}
