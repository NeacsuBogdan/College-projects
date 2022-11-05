import javax.swing.*;

public class Ex4{
    private static final double MINF =-459.67;
    private static final double MINC =-273.15;
    
    public static double transformaInGradeCelsius(double tempFahrenheit)
    {
      return (tempFahrenheit-32)*5/9;
      
    }
    
    public static double transformaInGradeFahrenheit(double tempCelsius)
    {
        return tempCelsius*9/5+32;
    }
    
    
    public static void main(String[] args)
    {  
        
        double f=Double.parseDouble(JOptionPane.showInputDialog("Introduceti temperatura in grade Fahrenheit mai mare decat: -459.67"));
        double c=Double.parseDouble(JOptionPane.showInputDialog("Introduceti temperatura in grade Celsius mai mare decat:-273.15 "));
           if (f > MINF)
           {
               JOptionPane.showMessageDialog(null,"Temperatura echivalenta pentru "+f+" grade Fahrenheit in grade celsius este : "+transformaInGradeCelsius(f));
           }
           else
               JOptionPane.showMessageDialog(null,"Temperatura introdusa in Fahrenheit nu este acceptata");
           if(c> MINC)
           {
               JOptionPane.showMessageDialog(null,"Temperatura echivalenta pentru "+c+" grade Fahrenheit in grade fahrenheit este : "+transformaInGradeFahrenheit(c));
           }
           else
              JOptionPane.showMessageDialog(null,"Temperatura introdusa in Celsius nu este acceptata"); 
        
    }
}
