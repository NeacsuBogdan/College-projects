public class Message{
    private String expeditor;
    private String mesaj;
    private int id_expeditor;
    private Lesson lesson;
   
    Message (String expeditor,String mesaj,int id)
    {
        this.expeditor=expeditor;
        this.mesaj=mesaj;
        this.id_expeditor=id;
    }
    
     Message (String mesaj,Lesson lesson)
    {
        this.lesson=lesson;
        this.mesaj=mesaj;
        expeditor=lesson.getTeacher();
    }
    /**
     * @return the expeditor
     */
    public String getExpeditor() {
        return expeditor;
    }

    /**
     * @return the mesaj
     */
    public String getMesaj() {
        return mesaj;
    }
    
    public int getIdExpeditor()
    {
        return id_expeditor;
    }
    
    public Lesson getLesson()
    {
        return lesson;
    }
    
}
