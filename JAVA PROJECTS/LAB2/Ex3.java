import java.util.*;
import javax.swing.*;
public class Ex3{
    public static void main (String[] args)
    {
        //Scanner read= new Scanner(System.in);
        String luni[]={"Ianuarie","Februarie","Martie","Aprilie","Mai","Iunie","Iulie","August","Septembire","Octombrie","Noiembrie","Decembrie"};
        int x=Integer.parseInt(JOptionPane.showInputDialog("Introduceti un numar de la 1 la 12"));
        /*for(int i=1;i<=12;i++)
        {
           System.out.print(luni[i-1]+" ");
        }
        */
         JOptionPane.showMessageDialog(null,"Numarul "+x+" reprezinta luna: "+luni[x-1]);
    }
    }