package Problema5;

public class Test
{
 
    
    public static void main (String[] args)
    {
        Rational ar1=new Rational(2,5);
        Rational ar2=new Rational(-3,9);
        ar1.print("zecimal");
        ar2.print("zecimal");
        System.out.println("SUMA");
        ar1.add(ar2).print("zecimal");
        System.out.println("DIFERENTA");
        ar1.descrease(ar2).print("zecimal");
        System.out.println("INMULTIRE");
        ar1.multiply(ar2).print("zecimal");
        System.out.println("IMPARTIRE");
        ar1.divide(ar2).print("zecimal");
    }
}
