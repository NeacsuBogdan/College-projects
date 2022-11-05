import javax.swing.JOptionPane;

public class InOut{
    //Add section
    public int add(int x,String n)
    {
       return Integer.parseInt(JOptionPane.showInputDialog("Introduceti variabila "+n));
    }
    public float add(float x,String n)
    {
        return Float.parseFloat(JOptionPane.showInputDialog("Introduceti un numar"+n));
    }
    public double add(double x,String n)
    {
        return Double.parseDouble(JOptionPane.showInputDialog("Introduceti un numar"+n));
    }
    public String add(String x,String n)
    {
        return JOptionPane.showInputDialog("Introduceti un numar"+n);
    }
    //Print section;
    public void print(String text,int x,String title)
    {
        JOptionPane.showMessageDialog(null,text+x+"  ", title,
            JOptionPane.INFORMATION_MESSAGE);
    }
    public void print(String text,float x,String title)
    {
        JOptionPane.showMessageDialog(null,text+x+"  ", title,
            JOptionPane.INFORMATION_MESSAGE);
    }
    public void print(String text,double x,String title)
    {
        JOptionPane.showMessageDialog(null,text+x+"  ", title,
            JOptionPane.INFORMATION_MESSAGE);
    }
    public void print(String text,String x,String title)
    {
        JOptionPane.showMessageDialog(null,text+x+"  ", title,
            JOptionPane.INFORMATION_MESSAGE);
    }
}
