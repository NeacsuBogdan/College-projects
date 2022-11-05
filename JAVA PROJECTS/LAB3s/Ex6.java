import javax.swing.*;
public class Ex6{

public static void main(String[] args)
{
    double nr;
    StatCalc calc = new StatCalc();
    do{
      nr=Double.parseDouble(JOptionPane.showInputDialog("Numere introduse:\n"+calc.elem+"\nIntroduceti numere\nProcesul se incheie la introducerea lui 0"));
      calc.add(nr);
    } while(nr!=0);
    JOptionPane.showMessageDialog(null,"ELEMENTE INTRODUSE: \n"+calc.elem+"\nNR ELEM: "+ calc.getCount()+"\nSUMA ELEMENTE: "+calc.getSum()+"\nMEDIA ARITMETICA: "+calc.getMean()+"\nMINIMUL: "+calc.getMin()+"\nMAXIMUL: "+calc.getMax()+"\nDEVIATIA STANDARD: "+calc.getStandardDeviation(), "GESTIONARE ELEMENTE",
            JOptionPane.INFORMATION_MESSAGE);
}

    
}
