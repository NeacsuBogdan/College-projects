import javax.swing.*;

public class Ex5{
    
    public static int suma(int a,int b){
        return a+b;
    }
    
    public static int diferenta(int a,int b){
        return a-b;
    }
    
    public static double produs(int a,int b){
        return a*b;
    }
    
    public static int minim(int a,int b){
        if (a<b)
        {
            return a;
        }
         return b;
    }
    
    public static int maxim(int a,int b){
        if (a>b){
            return a;
        }
         return b;
    }
   
    public static void main(String[] args){
        String[] options={"SUMA","DIFERENTA","PRODUSUL","MINIMUL","MAXIMUL"};
        int a=Integer.parseInt(JOptionPane.showInputDialog("Introduceti primul numar"));
        int b=Integer.parseInt(JOptionPane.showInputDialog("Introduceti al doilea numar"));
        int x=0;
        while(x!=JOptionPane.CLOSED_OPTION)
        {
        x = JOptionPane.showOptionDialog(null, "Alegeti tipul de operatie pentru "+a+" si "+b,
                "Operatii cu doua numere",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        switch(x)
        {
            case 0:
                JOptionPane.showMessageDialog(null,"Suma numerelor "+a+" si "+b+" este: "+suma(a,b));
                break;
            case 1:
                JOptionPane.showMessageDialog(null,"Diferenta numerelor "+a+" si "+b+" este: "+diferenta(a,b));
                break;
            case 2:
                JOptionPane.showMessageDialog(null,"Produsul numerelor "+a+" si "+b+" este: "+produs(a,b));
                break;
            case 3:
                JOptionPane.showMessageDialog(null,"Minimul dintre numerele "+a+" si "+b+" este: "+minim(a,b));
                break;
            case 4:
                JOptionPane.showMessageDialog(null,"Maximul dintre numerele "+a+" si "+b+" este: "+maxim(a,b));
                break;
                
        }
        }
    }
}
