package Problema2;
public class Produs
{
    private String name;
    private double p_unit;
    
    Produs(String den,double x)
    {
        name=den;
        p_unit=x;
    }
   public void afiseazaProdus()
    {
        System.out.println("Denumire produs: "+name+"\nPret unitar produs: "+p_unit+" RON");
    }
   
   public double getPret()
   {
       return p_unit;
   }
   
   public void setPret(double val)
   {
       p_unit=val;
   }
    
   public void aplicaReducerea(double procent)
   {
       p_unit-=p_unit*procent;
   }
    
}
