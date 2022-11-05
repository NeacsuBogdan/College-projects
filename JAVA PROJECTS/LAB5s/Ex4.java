import javax.swing.JOptionPane;
public class Ex4{
    public static int count=0;
    public static String digits="";
    
   public static void getDifferentDigits(String text)
   {
       boolean[] used = new boolean[10];

    for (int i = 0; i < text.length(); i++) {
        int num = Character.getNumericValue(text.charAt(i));
        if(!used[num]) {
            digits+=num+",  ";
            used[num] = true;
        }
    }
}
          
    public static void countsDigits(String text)
    {
        int trigger=0;
        int n=0;
        out:
        for (int i=0;i<digits.length();i++)
        {
            count=n;
            n=0;
            for(int j=0;j<text.length();j++)
            {
                if(digits.charAt(i)==','||digits.charAt(i)==' ')
                {
                    if(i+1==digits.length())
                    {
                        break out;
                    }
                    i++;
                }
                if (digits.charAt(i)==text.charAt(j))
                {
                    n++;
                }
            }
          if(i>0)
          {
              if(count!=n)
              {
                  JOptionPane.showMessageDialog(null,text+" are cate "+count+" cifre de "+digits+"\nNU!", "NUMBER OF DIGITS CHECK",
            JOptionPane.INFORMATION_MESSAGE);
                  trigger=1;
                  break;
              }
          }
        }
        if(trigger==0)
        {
        JOptionPane.showMessageDialog(null,text+" are cate "+count+" cifre de "+digits+"\n DA!", "NUMBER OF DIGITS CHECK",
            JOptionPane.INFORMATION_MESSAGE);
        }
    }
      
    public static void main(String[] args)
    {
        String nr=JOptionPane.showInputDialog("Introduceti un numar: ");
        getDifferentDigits(nr);
        countsDigits(nr);
    }
}
