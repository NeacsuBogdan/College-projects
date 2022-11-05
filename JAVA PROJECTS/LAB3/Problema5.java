
import javax.swing.JOptionPane;

public class Problema5{
    public static int cmmdc(int u,int v)
    {
        if(v!=0)
        {
        return cmmdc(v,u%v);
        }
        return u;
    }
    

    
    
    public static void main(String[] args)
    {
        InOut doo = new InOut();
        int u=doo.add(1,"u");
        int v=doo.add(1,"v");
        doo.print("CMMDC din "+u+" si "+v+" este: ", cmmdc(u,v), "CMMDC");
        
    }
}
