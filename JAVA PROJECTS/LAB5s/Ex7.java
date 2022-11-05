import java.io.File;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Ex7{
    public static int m[][];
    public static String elem="";
    
    public static void construct(int n)
    {
        m=new int[n][n];
    }
    
    public static void calculZone(int n,int trigger)
    {
        int zn=0,ze=0,zw=0,zs=0;
        for (int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if (i<j && i+j<n-1)
                {
                   zn+=m[i][j]; 
                }
                if (i<j && i+j>n-1)
                {
                    ze+=m[i][j];
                }
                if (i>j && i+j<n-1)
                {
                    zw+=m[i][j];
                }
                if (i>j && i+j>n-1)
                {
                    zs+=m[i][j];
                }
            }
        }
        if(trigger==0)
        {
        JOptionPane.showMessageDialog(null,elem+"\nSUMA ZONA NORDICA: "+zn+"\nSUMA ZONA ESTICA: "+ze+"\nSUMA ZONA SUDICA: "+zs+"\nSUMA ZONA WESTICA: "+zw, "ZONES SUM IN MATRIX",
            JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
          JOptionPane.showMessageDialog(null,"SETUL DE DATE NR: "+trigger+"\n"+elem+"\nSUMA ZONA NORDICA: "+zn+"\nSUMA ZONA ESTICA: "+ze+"\nSUMA ZONA SUDICA: "+zs+"\nSUMA ZONA WESTICA: "+zw, "ZONES SUM IN MATRIX",
            JOptionPane.INFORMATION_MESSAGE);  
        }
    }
    
    public static void main (String[] args)
    {
         String[] cases ={"MANUAL","DIN FISIER"};
        int caz= JOptionPane.showOptionDialog(
                null,
                "ALEGETI MODUL DE INTRODUCERE A DATELOR!",
                "ZONES SUM IN MATRIX",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                cases,
                -1);
        if(caz==0)
        {
        int n=Integer.parseInt(JOptionPane.showInputDialog("Introduceti ordinul matricei "));
        construct(n);
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                m[i][j]= Integer.parseInt(JOptionPane.showInputDialog(elem+"\nIntroduceti elementele matricei "));
                elem+=m[i][j]+"    ";
            }
            elem+="\n";
        }
        calculZone(n,0);
        }
        else if (caz==1)
        {
        try{
            for(int z=1;z<4;z++)
            {
                elem="";
     Scanner read = new Scanner(new File("C:\\Users\\bogdan\\Desktop\\INFO 2\\PROGRAMARE ORIENTATA OBIECT\\DATE\\LAB_5_EXTRA\\Ex6\\date"+z+".txt"));
        int n = read.nextInt();
        construct(n);
        for (int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                m[i][j]=read.nextInt();
                elem+=m[i][j]+"   ";
            }
            elem+="\n";
        }
        calculZone(n,z);
        }
        }
        catch (Exception c)
                {
            JOptionPane.showMessageDialog(null,"NU S-A PUTUT GASI FISIERUL", "ZONES SUM IN MATRIX",
            JOptionPane.INFORMATION_MESSAGE);
                }
    }
    }
}
