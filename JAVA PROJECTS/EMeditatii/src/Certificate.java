
import java.util.Date;

public class Certificate {
    private String name;
    private String institution;
    private Date date;

    public Certificate(String name, String institution, Date date) {
        this.name = name;
        this.institution = institution;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getInstitution() {
        return institution;
    }

    public Date getDate() {
        return date;
    }
}
