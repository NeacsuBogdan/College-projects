package Problema1;
public class Pb1
{
    private int v[];
   
    Pb1(int a,int b)
    {
        this.v = new int[getSize(a,b)];
    }
    
   public int getSize(int a,int b)
   {
       int size=0;
       for (int i=a;i<=b;i++)
       {
           if (i%3==0)
           {
               size++;
           }
       }
       return size;
   }
   
   public void setVector(int a,int b)
   {
       int z=0;
       for (int i=b;i>=a;i--)
       {
           if (i%3==0)
           {
               v[z]=i;
               z++;
           }
       }
   }
   
   public void print()
   {
       System.out.print("v=[");
       for (int i=0;i<v.length;i++)
       {
           System.out.print(this.v[i]+",");
       }
       System.out.print("]");
   }
    

}
