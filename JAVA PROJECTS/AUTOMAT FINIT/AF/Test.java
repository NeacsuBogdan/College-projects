
import javax.swing.JOptionPane;

class Test
{
  public static void main (String[] args) throws Exception {
  Automat M = new Automat("C:\\Users\\bogdan\\Desktop\\INFO 2\\LIMBAJE FORMALE SI COMPILATOARE\\Proiect 1\\automat.txt");
  System.out.println(M.analizaCuvant("aba"));
  //System.out.println("aabb".matches("a*b*"));
  }
}
