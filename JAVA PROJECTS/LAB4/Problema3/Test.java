package Problema3;
import java.util.*;
public class Test{
    
    public static void main(String[] args)
    {
        Scanner read = new Scanner(System.in);
        float n=0;
        do while(n<=0)
        {
            n=read.nextFloat();
        }while(n<=0);
        ContBancar cb = new ContBancar("40",n);
        cb.afisare();
        cb.adaugaInCont(30f);
        try{
        cb.extrageDinCont(60f);
        System.out.println(cb);
        } catch (IllegalArgumentException e)
        {
            e.getMessage();
        }
    }
}
