import java.io.File;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Ex9{
    public static int matrix[][];
    public static String elem_before="";
    public static String elem_after="";
    
    public static void construct(int l,int c)
    {
        matrix=new int[l][c];
    }
    
    public static void print (int l,int c)
    {
        System.out.println();
        for (int i=0;i<l;i++)
        {
            for(int j=0;j<c;j++)
            {
                elem_after+=matrix[i][j]+"   ";
            }
            elem_after+="\n";
        }
    }
    
    public static void switchRow(int l,int c)
    {
        int aux;
        
        for (int i=0;i<l;i++)
        {
            for(int j=0;j<c-1;j++)
            {
                aux=matrix[i][j];
                matrix[i][j]=matrix[i][j+1];
                matrix[i][j+1]=aux;
                j++;
            }
        }
    }
    
    public static void main (String[] args)
    {
        String[] cases ={"MANUAL","DIN FISIER"};
        int caz= JOptionPane.showOptionDialog(
                null,
                "ALEGETI MODUL DE INTRODUCERE A DATELOR!",
                "SWITCH COLUMNS",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                cases,
                -1);
        if(caz==0)
        {
          int l=Integer.parseInt(JOptionPane.showInputDialog("Introduceti numarul de linii al matricei "));
          int c=Integer.parseInt(JOptionPane.showInputDialog("Introduceti numarul de coloane al matricei "));
        construct(l,c);
        for(int i=0;i<l;i++)
        {
            for(int j=0;j<c;j++)
            {
                matrix[i][j]= Integer.parseInt(JOptionPane.showInputDialog(elem_before+"\nIntroduceti elementele matricei "));
                elem_before+=matrix[i][j]+"    ";
            }
            elem_before+="\n";
        }
        switchRow(l,c);
        print(l,c);
        JOptionPane.showMessageDialog(null,"MATRICEA INAINTE DE INTERSCHIMBARE:\n"+elem_before+"\nMATRICEA DUPA INTERSCHIMBARE:\n"+elem_after, "SWITCH COLUMNS",
            JOptionPane.INFORMATION_MESSAGE); 
        }
        if(caz==1)
        {
            for(int z=1;z<5;z++)
            {
           try{
         elem_before="";
         elem_after="";
     Scanner read = new Scanner(new File("C:\\Users\\bogdan\\Desktop\\INFO 2\\PROGRAMARE ORIENTATA OBIECT\\DATE\\LAB_5_EXTRA\\Ex9\\date"+z+".txt"));
        int n = read.nextInt();
        int m = read.nextInt();
        construct(n,m);
        for (int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                matrix[i][j]=read.nextInt();
                elem_before+=matrix[i][j]+"   ";
            }
            elem_before+="\n";
        }
        switchRow(n,m);
        print(n,m);
            JOptionPane.showMessageDialog(null,"SETUL DE DATE NR: :"+z+"\nMATRICEA INAINTE DE INTERSCHIMBARE:\n"+elem_before+"\nMATRICEA DUPA INTERSCHIMBARE:\n"+elem_after, "SWITCH COLUMNS",
            JOptionPane.INFORMATION_MESSAGE); 
          }
         catch(Exception c)
         {
             JOptionPane.showMessageDialog(null,"NU S-A PUTUT GASI FISIERUL", "SWITCH COLUMNS",
            JOptionPane.INFORMATION_MESSAGE);
         }
            }
        }
    }
}
