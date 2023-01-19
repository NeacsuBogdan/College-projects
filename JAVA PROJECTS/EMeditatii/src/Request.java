public class Request {
    private User student;
    private Lesson lesson;
    private boolean isAccepted;

    public Request(User student, Lesson lesson) {
        this.student = student;
        this.lesson = lesson;
        this.isAccepted = false;
    }

    public User getStudent() {
        return student;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }
}
