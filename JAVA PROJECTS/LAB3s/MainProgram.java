import java.io.IOException;
import javax.swing.*;
import java.awt.*;
public class MainProgram{
    
    
    
    public static void main(String[] args)
    {
        Runtime rt = Runtime.getRuntime();
        int caz=0;
        
       while(caz!=JOptionPane.CLOSED_OPTION)
       {
      String[] exes = { "1","2","3","4","5","6"};
      JComboBox comboBox = new JComboBox(exes); comboBox.setSelectedIndex(0);
     caz = JOptionPane.showConfirmDialog(null, comboBox, "ALEGETI EXERCITIUL LABORATOR_3 EXTRA",
      JOptionPane.DEFAULT_OPTION);
         if(caz==JOptionPane.OK_OPTION)
         {
           caz = Integer.parseInt((String)comboBox.getSelectedItem());
         }
           switch(caz)
           {
            case 1:
        try {
        rt.exec(new String[]{"java","-cp","C:\\Users\\bogdan\\Desktop\\INFO 2\\PROGRAMARE ORIENTATA OBIECT\\Laborator3-Extra\\build\\classes","Ex1"});

        } catch (IOException e) 
        {
        e.printStackTrace();
        }
     break;
            case 2:
              try {
        rt.exec(new String[]{"java","-cp","C:\\Users\\bogdan\\Desktop\\INFO 2\\PROGRAMARE ORIENTATA OBIECT\\Laborator3-Extra\\build\\classes","Ex2"});

        } catch (IOException e) 
        {
        e.printStackTrace();
        }
     break;  
            case 3:
                try {
        rt.exec(new String[]{"java","-cp","C:\\Users\\bogdan\\Desktop\\INFO 2\\PROGRAMARE ORIENTATA OBIECT\\Laborator3-Extra\\build\\classes","Ex3"});

        } catch (IOException e) 
        {
        e.printStackTrace();
        }
     break;
            case 4:
                    try {
        rt.exec(new String[]{"java","-cp","C:\\Users\\bogdan\\Desktop\\INFO 2\\PROGRAMARE ORIENTATA OBIECT\\Laborator3-Extra\\build\\classes","Ex4"});

        } catch (IOException e) 
        {
        e.printStackTrace();
        }
     break;
            case 5:
                try {
        rt.exec(new String[]{"java","-cp","C:\\Users\\bogdan\\Desktop\\INFO 2\\PROGRAMARE ORIENTATA OBIECT\\Laborator3-Extra\\build\\classes","Ex5b"});

        } catch (IOException e) 
        {
        e.printStackTrace();
        }
     break;
            case 6:
                try {
        rt.exec(new String[]{"java","-cp","C:\\Users\\bogdan\\Desktop\\INFO 2\\PROGRAMARE ORIENTATA OBIECT\\Laborator3-Extra\\build\\classes","Ex6"});

        } catch (IOException e) 
        {
        e.printStackTrace();
        }
     break;
           }
       }
    }
}
