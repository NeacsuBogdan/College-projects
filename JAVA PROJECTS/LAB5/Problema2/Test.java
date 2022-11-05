package Problema2;
public class Test
{
    public static void main(String[] args)
    {
        int x[]={1,3,5,6,7};
        MultimeIntregi M1= new MultimeIntregi();
        MultimeIntregi M2= new MultimeIntregi(x);
        MultimeIntregi M3= new MultimeIntregi(4);
        MultimeIntregi M4= new MultimeIntregi(M3.getArray());
        //M2.reunesteMultimiIntregi(M4).print();
        M2.print();
        M2.stergeElement(1);
        M2.stergeElement(3);
        M2.stergeElement(5);
        M2.stergeElement(6);
        M2.stergeElement(7);
        M2.print();
        
        
    }
}
