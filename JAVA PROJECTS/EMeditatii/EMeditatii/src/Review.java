public class Review {
    private String student;
    private int rating;
    private String comment;

    public Review(String student,String comment,int rating) {
        this.student=student;
        this.rating=rating;
        this.comment=comment;
    }

    public String getStudent() {
        return student;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }
}

