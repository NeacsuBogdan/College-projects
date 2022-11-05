import java.io.File;
import java.util.Scanner;
import javax.swing.JOptionPane;


public class Ex5{
    public static int a[];
    public static int b[];
    public static int trigger=1;
    
    public static void constructVector(int n)
    {
        a=new int[n];
        b=new int[n];
    }
    
    public static double calcul(double x, double y)
    {
        return x/y;
    }
    
    public static double directProp()
    {
        for (int i=1;i<a.length;i++)
        {
            if (calcul(a[i-1],b[i-1])!=calcul(a[i],b[i]))
            {
                trigger=0;
            }
        }
        if (trigger==0)
        {
            trigger=1;
            return 0;
        }
        return 1;
    }
    
    public static int inversProp()
    {
        int j=a.length-2;
        for (int i=1;i<a.length;i++)
        {
               if(a[i-1]*b[j+1]!=a[i]*b[j])
                {
                    trigger=0;
                }
               j--;
        }
         if (trigger==0)
        {
            return 0;
        }
        trigger=1;
        return 1;
    }
    
    public static void main(String[] args)
    {
         String[] cases ={"MANUAL","DIN FISIER"};
        int caz= JOptionPane.showOptionDialog(
                null,
                "ALEGETI MODUL DE INTRODUCERE A DATELOR!",
                "ARRAY RAPORT CHECK",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                cases,
                -1);
        if(caz==0)
        {
        String elemA="";
        String elemB="";
        int n=Integer.parseInt(JOptionPane.showInputDialog("Introduceti dimensiunea vectorilor: "));
        constructVector(n);
        for(int i=0;i<n;i++)
        {
           a[i]=Integer.parseInt(JOptionPane.showInputDialog(elemA+"\nAdaugati elemente in primul vector: "));
           elemA+=a[i]+",  ";
        }
        for(int i=0;i<n;i++)
        {
           b[i]=Integer.parseInt(JOptionPane.showInputDialog(elemB+"\nAdaugati elemente in al doilea vector: "));
           elemB+=b[i]+",  ";
        }
        if(directProp()!=0)
        {
            JOptionPane.showMessageDialog(null,"v1={"+elemA+"}\nv2={"+elemB+"}\nVectorii sunt direct proportionali", "ARRAY RAPORT CHECK",
            JOptionPane.INFORMATION_MESSAGE);
        }
        else if (inversProp()!=0)
        {
            JOptionPane.showMessageDialog(null,"v1={"+elemA+"}\nv2={"+elemB+"}\nVectorii sunt invers proportionali", "ARRAY RAPORT CHECK",
            JOptionPane.INFORMATION_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null,elemA+"\n"+elemB+"\nSirurile nu sunt nici direct, nici invers proportionale", "ARRAY RAPORT CHECK",
            JOptionPane.INFORMATION_MESSAGE);
        }
        else if (caz==1)
        {
           for(int z=1;z<4;z++)
           {
               try{
           String elemA="";
           String elemB="";
         Scanner read = new Scanner(new File("C:\\Users\\bogdan\\Desktop\\INFO 2\\PROGRAMARE ORIENTATA OBIECT\\DATE\\LAB_5_EXTRA\\Ex5\\date"+z+".txt"));
        int n = read.nextInt();
        constructVector(n);
        for(int i=0;i<n;i++)
        {
                a[i]= read.nextInt();
                elemA+=a[i]+",  ";

        }
        for(int i=0;i<3;i++)
        {

                b[i]= read.nextInt();
                elemB+=b[i]+",  ";
        }
        if(directProp()!=0)
        {
            JOptionPane.showMessageDialog(null,"SETUL DE DATE NR: "+z+"\nv1={"+elemA+"}\nv2={"+elemB+"}\nVectorii sunt direct proportionali", "ARRAY RAPORT CHECK",
            JOptionPane.INFORMATION_MESSAGE);
        }
        else if (inversProp()!=0)
        {
            JOptionPane.showMessageDialog(null,"SETUL DE DATE NR: "+z+"\nv1={"+elemA+"}\nv2={"+elemB+"}\nVectorii sunt invers proportionali", "ARRAY RAPORT CHECK",
            JOptionPane.INFORMATION_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null,"SETUL DE DATE NR: "+z+"\nv1={"+elemA+"}\nv2={"+elemB+"}\nSirurile nu sunt nici direct, nici invers proportionale", "ARRAY RAPORT CHECK",
            JOptionPane.INFORMATION_MESSAGE);
        
               }
               catch (Exception a)
               {
                   JOptionPane.showMessageDialog(null,"NU S-A PUTUT GASI FISIERUL", "ARRAY RAPORT CHECK",
            JOptionPane.INFORMATION_MESSAGE);
               }
        }
        }
        
    }
}
