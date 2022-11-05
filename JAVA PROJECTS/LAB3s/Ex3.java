import java.util.Arrays;
import javax.swing.*;
public class Ex3{
    public static String levels[][];
    
    public static void construct(int n)
    {
        levels=new String[n][n];
    }
    
    
    public static void printArray(String[][] num1) {
    String output = "";
    for (int x = 0; x < num1.length; x++) {
        output += Arrays.toString(num1[x]) + "\n";
    }
    output=output.replace(" ","  ");
    output=output.replace("["," ");
    output=output.replace("]", " ");
    output=output.replace(","," ");
    JOptionPane.showMessageDialog(null, output, "PRINT STARS",
            JOptionPane.INFORMATION_MESSAGE);
}
    
    public static void getLevels(int n)
    {

        if (n<1)
        {
            System.out.println("Valoare invalida!");
        }
        else
      {
        for (int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if (j<i+1)
                {
                      levels[i][j]="*";
                }
                else
                    levels[i][j]=" ";
            }
        }
      }
    }
    
    public static void main(String[] args)
    {
        int x=Integer.parseInt(JOptionPane.showInputDialog("Introduceti numarul de nivele: "));
        construct(x);
        getLevels(x);
        printArray(levels);
    }
}

