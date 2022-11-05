public class Problema7{
    
    public static int P(int a,int b)
    {
        if(b==0)
        {
            return 1;
        }
        return a*P(a,b-1);
    }

    public static void main(String[] args)
    {
        
         InOut doo = new InOut();
         int a=doo.add(0,"a");
         int b=doo.add(0, "b");
         doo.print(a+" la puterea "+b+" este:  ", P(a,b), "RIDICAREA LA PUTEREA");
    }
}
