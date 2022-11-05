public class Problema6{
    
    
    public static int ac(int m,int n)
    {
        if(m==0)
        {
         return n+1;
        }
        if(n==0)
        {
            return ac(m-1,1);
        }
        if(m>0 && n>0)
        {
            return ac(m-1,ac(m,n-1));
        }
        return 0;
    }
    
    
    public static void main(String[] args)
    {
        InOut doo = new InOut();
        int m=doo.add(0, "m");
        int n=doo.add(0, "n");
        doo.print("Valoarea functiei Ackermann pentru "+m+" si "+n+" este:  ", ac(m,n), "FUNCTIA ACKERMANN");
    }
}
