package Problema1;
import java.util.*;
public class Test{
 public static void main (String[] args)
    {
        Scanner read = new Scanner(System.in);
        int a,b;
        System.out.println("Introduceti inceputul si sfarsitul intervalului\n!a < b!");
        do {
           a=read.nextInt();
           b=read.nextInt();
        } while(a>=b);
        Pb1 vector = new Pb1(a,b);
        vector.setVector(a, b);
        vector.print();
    }
}
