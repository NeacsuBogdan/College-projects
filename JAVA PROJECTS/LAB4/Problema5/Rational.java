package Problema5;
public class Rational
{
    private int numarator;
    private int numitor;
    
    
    Rational()
    {
        
    }
    
    Rational(int a,int b)
    {
        numarator=a/cmmdc(a,b);
        numitor=b/cmmdc(a,b); 
    }
    
    public int cmmdc(int a,int b)
    {
        if(a<0)
        {
            a*=-1;
        }
        if(b<0)
        {
            b*=-1;
        }
        if(b!=0)
        {
        return cmmdc(b,a%b);
        }
        return a;
    }
    
    public int cmmmc(int a,int b)
    {
        if(a<0)
        {
            a*=-1;
        }
        if(b<0)
        {
            b*=-1;
        }
       return (a*b)/cmmdc(a,b);
    }
    
    public void end(Rational x)
    {
        if (x.numitor<0&&x.numarator<0)
        {
            x.numitor*=-1;
            x.numarator*=-1;
        }
        if(x.numitor<0&&x.numarator>=0)
        {
            x.numitor*=-1;
            x.numarator*=-1;
        }
        x.numarator/=cmmdc(x.numarator,x.numitor);
        x.numitor/=cmmdc(x.numarator,x.numitor);
    }
    
    public void print(String optiune)
    {
        if (optiune.equalsIgnoreCase("fractie"))
        {
        System.out.println(numarator+"/"+numitor);
        }
        else if (optiune.equalsIgnoreCase("zecimal"))
        {
            System.out.println((double)numarator/numitor);
        }
        else
            System.out.println("Dati o optiune valida de afisare");
    }
    
    public Rational add(Rational a)
    {
        Rational rez=new Rational();
       if(numitor!=a.numitor)
       {
           int x =cmmmc(numitor,a.numitor);
               rez.numarator=numarator*cmmdc(x,a.numitor)+a.numarator*cmmdc(x,numitor);
               rez.numitor=x;
       }
       else
           rez.numarator=numarator+a.numarator;
        
        end(rez);
        return rez;
    }
   
    public Rational descrease(Rational a)
    {
        Rational rez=new Rational();
       if(numitor!=a.numitor)
       {
           int x =cmmdc(cmmmc(numitor,a.numitor),numitor);
           if(a.numitor>numitor)
           {
               rez.numarator=(numarator*x)-a.numarator;
               rez.numitor=numitor*x;
           }
           else if (numitor>a.numitor)
           {
               rez.numarator=numarator-(a.numarator*x);
               rez.numitor=a.numitor*x;
           }
       }
       else{
           rez.numarator=numarator-a.numarator;
           }        

        end(rez);
        return rez; 
    }
    
    public Rational multiply(Rational a)
    {
        Rational rez=new Rational();
        rez.numarator=numarator*a.numarator;
        rez.numitor=numitor*a.numitor;
        end(rez);
        return rez;
    }
    
    public Rational divide(Rational a)
    {
        Rational rez=new Rational();
        rez.numarator=numarator*a.numitor;
        rez.numitor=numitor*a.numarator;
        end(rez);
        return rez;
    }
    
    
}
