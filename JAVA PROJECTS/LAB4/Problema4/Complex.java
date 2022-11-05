package Problema4;
public class Complex
{
    public double parteaReala;
    public double parteaImaginara;
    
    Complex()
    {
    }

    Complex(double a,double b)
    {
        parteaReala=a;
        parteaImaginara=b;
    }
        public void showC()
    {
      System.out.println(parteaReala+" +i"+parteaImaginara);
    }
    
    public Complex add(Complex b)
    {
        return new Complex(parteaReala+=b.parteaReala,parteaImaginara+=b.parteaImaginara);
    }
    
    public Complex descrease(Complex b)
    {
        return new Complex(parteaReala-=b.parteaReala,parteaImaginara-=b.parteaImaginara);
    }
    
    
}
