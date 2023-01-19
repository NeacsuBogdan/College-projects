public class Review {
    private User student;
    private Lesson lesson;
    private int rating;
    private String comment;

    public Review(User student, Lesson lesson, int rating, String comment) {
        this.student = student;
        this.lesson = lesson;
        if (rating >=1 && rating <=10)
            this.rating = rating;
        else 
            System.out.println("Valoarea introdusa pentru rating nu este valida");
        this.comment = comment;
    }

    public User getStudent() {
        return student;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }
}

