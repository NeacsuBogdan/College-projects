package Problema2;
import java.util.*;
public class MultimeIntregi
{
    private int a[];
    private int count=0;
    
    MultimeIntregi()
    {
        a = new int [10]; 
    }
    MultimeIntregi(int x[])
    {
        a = new int [getMax(x)];
        count=1;
        for (int i=0;i<x.length;i++)
        {
            a[x[i]]=1;
        }
    }
    MultimeIntregi(int n)
    {
        a=new int[n];
        count=1;
        for (int i=0;i<n;i++)
        {
            a[i]= new Random().nextInt(10);
        }
    }
    
    public int[] getArray()
    {
        return this.a;
    }
    
    public int getMax(int x[])
    {
       int max=0;
       for (int i=0;i<x.length;i++)
       {
           if(x[i]>max)
           {
               max=x[i];
           }
       }
       return max+1;
    }
    
    public MultimeIntregi reunesteMultimiIntregi(MultimeIntregi m)
    {
        int x = Math.max(this.a.length, m.a.length);
        MultimeIntregi Maux= new MultimeIntregi();
        
        for(int i=0;i<x;i++)
        {
            if(i<this.a.length && this.a[i]==1)
            {
            Maux.a[i]=1;
            Maux.count=1;
            }
            if (i<m.a.length && m.a[i]==1)
            {
                Maux.a[i]=1;
                Maux.count=1;
            }
        }
        
        return Maux;
    }
    
    public MultimeIntregi intersecteazaMultimiIntregi(MultimeIntregi m)
    {
        int y = Math.min(this.a.length, m.a.length);
        MultimeIntregi Maux= new MultimeIntregi();
        for(int i=0;i<y;i++)
        {
            if(this.a[i]==1 && m.a[i]==1 )
            {
            Maux.a[i]=1;
            Maux.count=1;
            }
        }
        return Maux;
    }
    
    public void insereazaElement(int k)
    {
        this.a[k]=1;
    }
    
    public void stergeElement(int j)
    {
        this.a[j]=0;
        check();
    }
    
    public void check()
    {
        count=0;
        for(int i=0;i<this.a.length;i++)
        {
            if (this.a[i]==1)
            {
                count=1;
            }
        }
    }
    
    
    public void print()
    {
        if(this.count!=0)
        for (int i=0;i<a.length;i++)
        {
            System.out.print(a[i]+" ");
        }
        else
                System.out.print("---");
        System.out.println();
    }
}
