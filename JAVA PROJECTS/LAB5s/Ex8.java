import java.io.File;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Ex8{
    public static int matrix[][];
    public static String elem="";
    public static int trigger;
   
    public static void construct(int n,int m)
    {
        matrix=new int[n][m];
    }
    
    
    public static void fin(int n,int m,int z)
    {
        int max_final=0;
        int min_final=0;
        int max_row=0;
        int min_row=0;
        int lmin=0,cmin=0,lmax=0,cmax=0;
        for(int i=0;i<n;i++)
        {
                min_row=matrix[i][0];
                max_row=matrix[i][0];
            for(int j=0;j<m;j++)
            {
                if(matrix[i][j]<min_row)
                {
                    min_row=matrix[i][j];
                    lmin=i;
                    cmin=j;
                }
                if(matrix[i][j]>max_row)
                {
                    max_row=matrix[i][j];
                    cmin=i;
                    lmin=j;
                }
            }
            if(min_row>min_final && trigger==1)
            {
                min_final=min_row;
            }
            else if(trigger==0)
            {
                min_final=min_row;
            }
            if(max_row<max_final && trigger==1)
            {
                max_final=max_row;
            }
            else if(trigger==0)
            {
                max_final=max_row;
                trigger=1;
            }
        }
            if(z==0)
            {
            JOptionPane.showMessageDialog(null,elem+"\nCel mai mare element dintre cele mai mici elemente de pe fiecare linie este: \n"
                +min_final+" si este situat pe linia "+lmin+" , coloana "+cmin, "LOWEST(MAX) && BIGEST(MIN)",
            JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(null,elem+"\nCel mai mic element dintre cele mai mari elemente de pe fiecare linie este: \n"
                +max_final+" si este situat pe linia "+lmax+" , coloana "+cmax, "LOWEST(MAX) && BIGEST(MIN)",
            JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
            JOptionPane.showMessageDialog(null,"SETUL DE DATE NR: "+z+"\n"+elem+"\nCel mai mare element dintre cele mai mici elemente de pe fiecare linie este: \n"
                +min_final+" si este situat pe linia "+lmin+" , coloana "+cmin, "LOWEST(MAX) && BIGEST(MIN)",
            JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(null,"SETUL DE DATE NR: "+z+"\n"+elem+"\nCel mai mic element dintre cele mai mari elemente de pe fiecare linie este: \n"
                +max_final+" si este situat pe linia "+lmax+" , coloana "+cmax, "LOWEST(MAX) && BIGEST(MIN)",
            JOptionPane.INFORMATION_MESSAGE);    
            }
    }
    
    
    
    public static void main (String[] args)
    {
         String[] cases ={"MANUAL","DIN FISIER"};
        int caz= JOptionPane.showOptionDialog(
                null,
                "ALEGETI MODUL DE INTRODUCERE A DATELOR!",
                "LOWEST(MAX) && BIGEST(MIN)",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                cases,
                -1);
        if(caz==0)
        {
          int n=Integer.parseInt(JOptionPane.showInputDialog("Introduceti numarul de linii al matricei "));
          int m=Integer.parseInt(JOptionPane.showInputDialog("Introduceti numarul de coloane al matricei "));
        construct(n,m);
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                matrix[i][j]= Integer.parseInt(JOptionPane.showInputDialog(elem+"\nIntroduceti elementele matricei "));
                elem+=matrix[i][j]+"    ";
            }
            elem+="\n";
        } 
         fin(n,m,0);
        }
        else if (caz==1)
        {
        try{
            for(int z=1;z<5;z++)
            {
                elem="";
                trigger=0;
     Scanner read = new Scanner(new File("C:\\Users\\bogdan\\Desktop\\INFO 2\\PROGRAMARE ORIENTATA OBIECT\\DATE\\LAB_5_EXTRA\\Ex8\\date"+z+".txt"));
        int n = read.nextInt();
        int m = read.nextInt();
        construct(n,m);
        for (int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                matrix[i][j]=read.nextInt();
                elem+=matrix[i][j]+"   ";
            }
            elem+="\n";
        }
        fin(n,m,z);
        }
        }
        catch (Exception c)
                {
            JOptionPane.showMessageDialog(null,"NU S-A PUTUT GASI FISIERUL", "LOWEST(MAX) && BIGEST(MIN)",
            JOptionPane.INFORMATION_MESSAGE);
                }
        }
    }
}


