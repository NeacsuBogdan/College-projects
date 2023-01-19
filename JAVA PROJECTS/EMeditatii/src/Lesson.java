import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.security.auth.Subject;

public class Lesson {
    private User teacher;
    private User student;
    private Subject subject;
    private String location;
    private Date date;
    private boolean isCancelled;
    private boolean isCompleted;
 private Date startTime;
    private Date endTime;

    // constructor pentru a crea o noua meditatie
    public Lesson(User teacher, User student, Subject subject, String location, Date date,Date startTime, Date endTime) {
        this.teacher = teacher;
        this.student = student;
        this.subject = subject;
        this.location = location;
        this.date = date;
        this.isCancelled = false;
        this.isCompleted = false;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // metodele getter și setter pentru toți membrii de clasă
    public User getTeacher() {
        return teacher;
    }
    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }
    public User getStudent() {
        return student;
    }
    public void setStudent(User student) {
        this.student = student;
    }
    public Subject getSubject() {
        return subject;
    }
    public void setSubject(Subject subject) {
        this.subject = subject;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public boolean isCancelled() {
        return isCancelled;
    }
    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }
    public boolean isCompleted() {
        return isCompleted;
    }
    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
  // metode suplimentare
    public void cancelLesson() {
        this.isCancelled = true;
    }
    public void completeLesson() {
        this.isCompleted = true;
    }
       public Date getStartTime() {
        return startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public Date endStartTime(){
        return endTime;
    }
    
   public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    public int getDuration() {
        long duration = this.endTime.getTime() - this.startTime.getTime();
        return (int) TimeUnit.MILLISECONDS.toMinutes(duration);
    }
}

