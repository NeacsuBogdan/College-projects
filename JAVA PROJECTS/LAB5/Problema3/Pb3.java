package Problema3;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.*;
public class Pb3
{
    private int a[];
    Scanner rd= new Scanner(System.in);
    private int triggera=0;
    private int triggerb=0;
    
    Pb3(){
    a=new int [10];
    }
    
    public int checkAvailableA()
    {
        for (int i=0;i<5;i++)
        {
            if (this.a[i]==0)
            {
                return i;
            }
        }
        return -1;
    }
    
    public int checkAvailableB()
    {
        for (int i=5;i<10;i++)
        {
            if (this.a[i]==0)
            {
                return i;
            }
        }
        return -2;
    }
    
    public void reserveNonSmokers()throws Exception 
    {
       
        int a=0;
        int x=checkAvailableA();
        out:
        if(x!=-1)
        {
            this.a[x]=1;
            reserved(x);
        }
        else
        {
            triggera=1;
             if (triggera==1 && triggerb==1)
            {
                clear();
                System.out.println("Ambele categorii sunt pline\nUrmatorul zbor este in 3 ore.");
                pressEnterToContinue();
                break out;
            }
           do{
               cantReserve();
               a=rd.nextInt();
               }while(a!=1 && a!=2);
           choose(a,x);
        } 
}
    
    public void reserveSmokers()throws Exception 
    {
        int a=0;
        int x=checkAvailableB();
        out:
        if(x!=-2)
        {
            this.a[x]=1;
            reserved(x);
        }
         else
        {
             triggerb=1;
             if (triggera==1 && triggerb==1)
            {
                clear();
                System.out.println("Ambele categorii sunt pline\nUrmatorul zbor este in 3 ore.");
                pressEnterToContinue();
                break out;
            }
           do{
               cantReserve();
               a=rd.nextInt();
               }while(a!=1 && a!=2);
           choose(a,x);
        }
    }
    
    public void reserved(int x)throws Exception 
    {
        clear();
        if(x<6)
        System.out.println("ZBORUL A FOST REZERVAT\nLOC: "+(x+1)+"\nSECTIUNEA: NEFUMATORI\n\n");
        else
        System.out.println("ZBORUL A FOST REZERVAT\nLOC: "+(x+1)+"\nSECTIUNEA: FUMATORI\n\n");
        
        
       pressEnterToContinue();
    }
    
    public void cantReserve()throws Exception 
    { 
        clear();
           System.out.println("DORITI SA FACETI REZERVARE LA CEALALTA SECTIUNE?\n1.DA\n2.NU");
    }
    
    public String chooseMain()throws Exception 
    {
        clear();
        return "ALEGETI 1 PENTRU SECTIUNEA DE NEFUMATORI\nALEGETI 2 PENTRU SECTIUNEA DE FUMATORI\nALEGETI 3 PENTRU A INCHEIA PROGRAMUL";
    }
    
    public void choose(int a,int x)throws Exception 
    {
        if (a==1)
        {
            if(x==-1)
            {
                reserveSmokers();
            }
            else
                reserveNonSmokers();
        }
        if(a==2)
        {
            clear();
            System.out.println("Urmatorul zbor este in 3 ore.");
            pressEnterToContinue();
        }
    }
    
    public void run (int x)throws Exception 
    {
        clear();
        if (x==1)
        {
            reserveNonSmokers();
        }
        if(x==2)
        {
            reserveSmokers();
        }
    }

    public void clear() throws Exception {
    Robot rob = new Robot();
        rob.keyPress(KeyEvent.VK_CONTROL); // press "CTRL"
        rob.keyPress(KeyEvent.VK_L); // press "L"
        rob.keyRelease(KeyEvent.VK_L); // unpress "L"
        rob.keyRelease(KeyEvent.VK_CONTROL); // unpress "CTRL"
        Thread.sleep(100); // add delay in milisecond, if not there will automatically stop after clear
}
    
    private void pressEnterToContinue()
 { 
        System.out.println("Press Enter key to continue...");
        try
        {
            System.in.read();
        }  
        catch(Exception e)
        {}  
 }
}
