package Problema4;
 public class Test{
     
     
     
     
     public static void main (String [] args)
     {
         Complex c1=new Complex(3,15);
         Complex c2=new Complex (1,17);
         Complex suma=c1.add(c2);
         Complex dif=c1.descrease(c2);

         c1.showC();
         c2.showC();
         System.out.println("Suma celor doua numere este: ");
         suma.showC();
         System.out.println("Iar diferenta este:");
         dif.showC();
     }
}
