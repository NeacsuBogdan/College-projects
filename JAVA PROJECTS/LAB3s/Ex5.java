public class Ex5{
    static final int INALTIME=9;
    static final int LATIME=9;
    
    static void printN()
{
    int count = 0;
    for (int i = 0; i < INALTIME; i++)
    {
        System.out.printf("*");
        for (int j = 0; j <= INALTIME; j++)
        {
            if (j == INALTIME)
                System.out.printf("*");
            else if (j == count)
                System.out.printf("*");
            else
                System.out.printf(" ");
        }
        count++;
        System.out.printf("\n");
    }
}
    
    static void printB()
{
    int mijloc = (INALTIME / 2);
    for (int i = 0; i < INALTIME; i++)
    {
        System.out.printf("*");
        for (int j = 0; j < LATIME; j++)
        {
            if ((i == 0 || i == INALTIME - 1 || i == mijloc)
                && j < (LATIME - 2))
                System.out.printf("*");
            else if (j == (LATIME - 2)
                    && !(i == 0 || i == INALTIME - 1 || i == mijloc))
                System.out.printf("*");
            else
                System.out.printf(" ");
        }
        System.out.printf("\n");
    }
}
    
    
    public static void main(String[] args)
    {
        printN();
        printB();
    }
}
