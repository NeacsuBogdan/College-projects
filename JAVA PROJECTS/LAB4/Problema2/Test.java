package Problema2;
import java.util.*;
public class Test
{
public static Produs prod[];
    
public static void constructProdList(int n)
    {
        prod = new Produs[n];
    }
public static void afisareProdus()
{
    System.out.println("\nLISTA CU PRODUSE:\n");
    for (int i=0;i<prod.length;i++)
    {
        prod[i].afiseazaProdus();
    }
}

public static void afisareProdus(int i)
{
    prod[i].afiseazaProdus();
}

public static void aplicaReducere(double red)
{
    for (int i=0;i<prod.length;i++)
    {
        prod[i].aplicaReducerea(red);
    }
}

public static void aplicaReducere(int i,double red)
{
    prod[i].aplicaReducerea(red);
}

public static void afisarePret()
{
    System.out.println("ID PRODUS:            PRET PRODUS:");
    for(int i=0;i<prod.length;i++)
    {
        System.out.println(i+"            "+prod[i].getPret());
    }
}
public static void afisarePret(int i)
{
    System.out.println("ID PRODUS: "+i);
    System.out.println("PRET PRODUS: "+prod[i].getPret());
}

public static void adaugaProd(int n)
{
     Scanner read = new Scanner(System.in);
     for (int i=0;i<n;i++)
    {
        System.out.println("Denumirea produs: ");
        String nume=read.nextLine();
        System.out.println("Pretul unitar al produsului");
        double pret=read.nextDouble();
        prod[i]=new Produs(nume,pret);
        read.nextLine();
    }
}

            
public static void main(String[] args)
{
    Scanner read = new Scanner(System.in);
    System.out.println("Numarul de produse pe care doriti sa le introduceti");
    int n = read.nextInt();
    constructProdList(n);
    adaugaProd(n);
    afisareProdus();
    aplicaReducere(0.1);
    afisareProdus();
    aplicaReducere(1,0.5);
    afisareProdus(1);
    
}
}
