package Problema3;
public class ContBancar {
    private String numarCont;
    private float sumaCont;
    
    
    ContBancar(String n,float s)
    {
        numarCont=n;
        sumaCont=s;
    }
    
    public void afisare()
    {
        System.out.println("\nDETALII CONT\n\nNR CONT: "+numarCont+"\nSUMA CONT: "+sumaCont);
    }
    
    public void extrageDinCont(float s)
    {
        if(s>sumaCont)
        {
            throw new IllegalArgumentException("SUMA SELECTATA ESTE MAI MARE DECAT CEA DISPONIBILA");
        }
        else
        {
            sumaCont-=s;
        }
    }
    
    public void adaugaInCont(float s)
    {
        sumaCont+=s;
    }
    
    public String toString()
    {
        return ("\nDETALII CONT\n\nNR CONT: "+numarCont+"\nSUMA CONT: "+sumaCont);
    }
    
    public String Afiseaza()
    {
        return "NUMAR CONT ESTE: "+numarCont;
    }
    
    
}
