package Problema3;
public class Test{
    
    public static void main(String[] args) throws Exception
    {
        int n;
        Pb3 ap1= new Pb3();
        do{
            System.out.println(ap1.chooseMain());
            n = ap1.rd.nextInt();
            ap1.run(n);
        }while(n!=3);
    }
}

