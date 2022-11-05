import java.io.*;
class Automat{
private String st_init;
private String st_finale[];
private ListaTranzitii lt= new ListaTranzitii();

public String getStInit()
{
    return st_init;
}

public String[] getStFinale()
{
    return st_finale;
}

Automat (String nume_fis) throws Exception{
BufferedReader buf= new BufferedReader(new FileReader(nume_fis));

this.st_init=buf.readLine();
String st_finale_str=buf.readLine();
this.st_finale=st_finale_str.split(" ");

while(true){
String linie=buf.readLine();
if(linie==null)
break;
else{
String tbl[]=linie.split(" ");
if (tbl.length<4)
{
Tranzitie tr = new Tranzitie(tbl[0],tbl[1].charAt(0),tbl[2]);
this.lt.adaugaTranzitie(tr);
}
else
{
 Tranzitie tr = new Tranzitie(tbl[0],tbl[1].charAt(0),tbl[2],tbl[3]);
 this.lt.adaugaTranzitie(tr);
}
}}}


boolean analizaCuvant(String sir_intrare){
    
    String st_end=st_init;
    String show;
    int i;
    for (i=0;i<lt.lista.size();i++)
    {
        Tranzitie trs=lt.lista.get(i);
        
    }
    System.out.println(st_end);
    for(i=0;i<sir_intrare.length();i++)
    {
        Tranzitie tr = lt.gasesteTranzitie(st_end, sir_intrare.charAt(i));
        if(tr.spuneSimbol()==sir_intrare.charAt(i))
        {
            System.out.println(tr.spuneOutput());
            st_end=tr.spuneStSfarsit();
            System.out.println(st_end);
        }
        else
        {
            return false;
        }
    }
            i=0;
          while(i<st_finale.length){
           if(st_end.equals(st_finale[i]))
        {
            return true;
        }
        else
        {
            i++;
        }
        }
return false;
}
}