public class Request implements Comparable<Request>{
    private String participant;
    private Lesson lesson;
    private boolean isAccepted;
    private int active;
    private int id_participant;

    public Request(String participant,Lesson lesson,int id)  {
        this.participant = participant;
        this.lesson = lesson;
        this.id_participant=id;
        active = 1;
    }

    public String getParticipant() {
        return participant;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAnswer(boolean answer) {
        isAccepted = answer;
    }

    /**
     * @return the active
     */
    public int getActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(int active) {
        this.active = active;
    }
    
    public int getId()
    {
        return id_participant;
    }
    
    public int compareTo(Request other)
    {
        if (this.getLesson().getDate().compareTo(other.getLesson().getDate())==0)
                {
                    if (this.getLesson().getSubject().compareTo(other.getLesson().getSubject())==0)
                    {
                        return 0;
                    }
                }
        return 1;
    }
    @Override
    public boolean equals(Object other) {
        return this.getLesson().equals(((Request)other).getLesson());
    }
    
}
