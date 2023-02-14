
import java.util.Date;

public class Certificate implements Comparable {
    private String name;
    private Date date;

    public Certificate(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }
    
   public int compareTo(Object other)
   {
       return this.getName().compareTo(((Certificate)other).getName());
   }
   
  public boolean equals(Object other)
  {
      return this.name.equals(((Certificate)other).name);
  }
}
