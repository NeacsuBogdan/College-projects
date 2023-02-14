import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Lesson implements Comparable<Lesson> {
    private String teacher;
    private String student;
    private String subject;
    private Date date;
    private Date time;
    private int id_participant;
    private int id_curent;

    // constructor pentru a crea o noua meditatie
    public Lesson(String teacher,String student, String subject, Date date,Date time,int id,int id2) {
        this.teacher = teacher;
        this.student = student;
        this.subject = subject;
        this.date = date;
        this.time=time;
        this.id_participant=id;
        this.id_curent=id2;
    }

    // metodele getter și setter pentru toți membrii de clasă
    public String getTeacher() {
        return teacher;
    }
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
    public String getStudent() {
        return student;
    }
    public void setStudent(String student) {
        this.student = student;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public int getId()
    {
        return id_participant;
    }
    public int getIdCurent()
    {
        return id_curent;
    }
    public void setID(int id)
    {
        id_participant=id;
    }

    /**
     * @return the time
     */
    public Date getTime() {
        return time;
    }
    
    public int compareTo(Lesson other)
    {
       if (this.date.equals(other.getDate()))
       {
           return this.time.compareTo(other.getTime());
       }
       return this.date.compareTo(other.getDate());
    }
    public boolean equals (Lesson other)
    {
        
        if (this.date.equals(other.getDate())&&this.time.equals(other.getTime()))
        {
            return this.subject.equals(other.getSubject());
        }
        return false;
    }
}

