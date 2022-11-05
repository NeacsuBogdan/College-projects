import javax.swing.JOptionPane;
public class Ex2{
    
     public static void bubbleSort(int v[])
    {
        int n = v.length;
        for (int i = 0; i < n ; i++)
        {
            if(v[i]%2==0)
            {
            for (int j = 0; j< n; j++)
            {
              if (v[j]%2==0)
              {

                if (v[j] < v[i]) {
                    int temp = v[i];
                    v[i] = v[j];
                    v[j] = temp;
                }
                }
            }
            }
        }
    }
 
    
    public static void main(String[] args)
    {
       String elem_before="";
       String elem_after="";
       int n=Integer.parseInt(JOptionPane.showInputDialog("Introduceti dimensiunea vectorului: "));
        int[] v = new int[n];
        for(int i=0;i<n;i++)
        {
           v[i]=Integer.parseInt(JOptionPane.showInputDialog(elem_before+"\nAdaugati elemente in vector: "));
           elem_before+=v[i]+",  ";
           
        }
        bubbleSort(v);
        for(int i=0;i<n;i++)
        {
            elem_after+=v[i]+",  ";
        }
        JOptionPane.showMessageDialog(null,"Elementele vectorului initial:\n"+elem_before+"\nElementele vectorului sortat:\n"+elem_after, "DESCENDENT EVEN SORT",
            JOptionPane.INFORMATION_MESSAGE);
    }
}
