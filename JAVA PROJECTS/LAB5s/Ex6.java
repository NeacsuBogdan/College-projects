import java.io.File;
import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;
public class Ex6{
    public static int m[][];
    public static int v[];
    public static int MIN;
    
    public static void construct(int n)
    {
        m = new int[n][n];
        v =new int[(n-1)*n];
    }
    
    public static int regMin(int x,int i,int j)
    {
        if(x==0)
        {
            return 100+i*10+j;
        }
        return x*100+i*10+j;
    }
    
    public static void getZone()
    {
        int coordonate=MIN%100;
        for (int i=1;i<v.length;i++)
        {
            if (v[i]==MIN)
            {
                if(v[i-1]%100/10==v[i]%100/10)
                {
                    MIN=((MIN/100)+(v[i-1]/100))*100+coordonate;
                }
                else
                {
                    MIN=(MIN/100+v[i+1]/100)*100+coordonate;
                }
            }
        }
    }
    
    public static int getMinim(int aux)
    {
        int count=1;
        while(aux!=0)
        {
           count*=10;
           aux/=10;
            
        }
        return count;
    }
    
    public static void check()
    {
        for (int i=1;i<v.length;i++)
        {
            if (v[i-1]%100/10==v[i]%100/10)
            {
                if (v[i]<MIN)
                {
                    MIN=v[i];
                }
            }
        }
    }
    
    public static void calculSuma(int n)
    {
        int x=0;
        for(int i=0;i<n-1;i++)
        {
            for(int j=0;j<n;j++)
            {
                   
                v[x]=regMin(m[i][j]+m[i+1][j],i,j);
                x++;

            }
        }
    }
    
    
    
    
    
    public static void main (String[] args)
    {
        String[] cases ={"MANUAL","DIN FISIER"};
        int caz= JOptionPane.showOptionDialog(
                null,
                "ALEGETI MODUL DE INTRODUCERE A DATELOR!",
                "FIND LOWEST IN MATRIX",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                cases,
                -1);
        if(caz==0)
        {
        String elem="";
        int n=Integer.parseInt(JOptionPane.showInputDialog("Introduceti ordinul matricei "));
        construct(n);
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                m[i][j]= Integer.parseInt(JOptionPane.showInputDialog(elem+"\nIntroduceti elementele matricei "));
                elem+=m[i][j]+"  ";
            }
            elem+="\n";
        }
        calculSuma(n);
        System.out.println();
        MIN=regMin(m[0][0]+m[1][0],0,0);
         check();
      getZone();
      JOptionPane.showMessageDialog(null,elem+"\nSuma minima este "+MIN%getMinim(MIN)/100+". Regiunea incepe pe linia "+MIN%100/10+" coloana a "+(MIN%10-1), "FIND LOWEST IN MATRIX",
            JOptionPane.INFORMATION_MESSAGE);
        }
        else if (caz==1)
        {
            try{
                for(int z=1;z<4;z++)
                {
        String elem="";
         Scanner read = new Scanner(new File("C:\\Users\\bogdan\\Desktop\\INFO 2\\PROGRAMARE ORIENTATA OBIECT\\DATE\\LAB_5_EXTRA\\Ex6\\date"+z+".txt"));
        int n = read.nextInt();
        construct(n);
        for (int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                m[i][j]=read.nextInt();
                elem+=m[i][j]+"  ";
            }
            elem+="\n";
        }
         calculSuma(n);
         System.out.println();
        MIN=regMin(m[0][0]+m[1][0],0,0);
        check();
        getZone();
      JOptionPane.showMessageDialog(null,"SETUL DE DATE NR: "+z+"\n"+elem+"\nSuma minima este "+MIN%getMinim(MIN)/100+". Regiunea incepe pe linia "+MIN%100/10+" coloana a "+(MIN%10-1), "FIND LOWEST IN MATRIX",
            JOptionPane.INFORMATION_MESSAGE);
        }
            }
        catch(Exception c)
                {
                 JOptionPane.showMessageDialog(null,"NU S-A PUTUT GASI FISIERUL", "FIND LOWEST IN MATRIX",
            JOptionPane.INFORMATION_MESSAGE);
                }    
        }
}
}