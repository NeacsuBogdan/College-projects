import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

public class User implements Comparable<User> {
    private String name;
    private String email;
    private String password;
    private String type; // "Profesor" sau "Student"
    private String profilePicture;
    private ArrayList<String> subjects; // materiile predate/preferate
    private ArrayList<Certificate> certificates; // certificatele profesorilor
    private ArrayList<Lesson> lessons; // meditațiile programate
    private ArrayList<Review> reviews; // recenziile primite
    private ArrayList<Request> requests; // cererile primite/trimise
    private ArrayList<Message> messages;//mesajele primite de utilizator
    private int uniq_code;
    private static int counter;
    
    // constructor pentru a crea un nou utilizator
    public User(String name, String email, String password, String type,int counter) {
        uniq_code=counter;
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
        if (type.equals("Profesor")==true)
        {
        this.reviews = new ArrayList<Review>();
        this.subjects = new ArrayList<String>();
        this.certificates = new ArrayList<Certificate>();
        setCertificates();
        setSubjects();
        setReviews();
        }
        this.lessons = new ArrayList<Lesson>();
        this.requests = new ArrayList<Request>();
        this.messages = new ArrayList<Message>();
        loadProfilePicture();
        setRequests();
        loadMessages();
        setLessons();
    }
    
    // metodele getter și setter pentru toți membrii de clasă
    
    // metoda setter pentru profilePicture
    public void setProfilePicture(String filePath) {
        // folder-ul prestabilit unde se vor salva pozele
        String uploadDir = "images/profiles";
        String destinationPath="";
        // crearea folderului daca nu exista
        try{
        File uploadFolder = new File(uploadDir);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }
        // obtinerea numelui fisierului
        File sourceFile = new File(filePath);
        String fileName = sourceFile.getName();
        //crearea numelui noului fisier
        String destinationFileName = this.name.replaceAll(" ", "_")+"_"+uniq_code;
        //crearea cale catre noul fisier
        destinationPath = uploadDir + File.separator + destinationFileName;
        //copierea fisierului in folderul prestabilit
        Files.copy(sourceFile.toPath(), (new File(destinationPath)).toPath(), StandardCopyOption.REPLACE_EXISTING);
        //setarea pozei de profil
        }catch (IOException e)
        {};
        this.profilePicture = destinationPath;
    }
    
    public void loadProfilePicture()
    {
        File photo = new File("images/profiles/"+this.name.replaceAll(" ", "_")+"_"+uniq_code);
        if(photo.exists())
        {
            this.profilePicture=photo.getPath();
        }
        else
            this.profilePicture = "images/profiles\\default.jpg";
    }
    
    // metoda getter pentru profilePicture
    public String getProfilePicture() {
        return profilePicture;
    }
    
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getType() {
        return type;
    }
    public ArrayList<String> getSubjects() {
        return subjects;
    }
    public void setSubjects() {
        String fileName="datas\\Subjects\\"+this.name.replaceAll(" ", "_")+"_"+this.uniq_code+".txt";
         File file = new File(fileName);
    if (file.exists()) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                this.addSubject(line);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
    public ArrayList<Certificate> getCertificates() {
        return certificates;
    }
    public void setCertificates() {
        
        String fileName="datas\\Certificari\\"+this.name.replaceAll(" ", "_")+"_"+this.uniq_code+".txt";
        File file = new File(fileName);
        if (file.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(fileName));
                String line;
                while ((line = br.readLine()) != null) {
                    String[] certificateData = line.split(",");
                    String name = certificateData[0];
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    Date date = dateFormat.parse(certificateData[1]);
                    Certificate certificate = new Certificate(name,date);
                    this.addCertificate(certificate);
                }
                br.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
    public ArrayList<Lesson> getLessons() {
        return lessons;
    }
    public void setLessons() {
         String fileName="datas\\Lessons\\"+this.name.replaceAll(" ", "_")+"_"+this.uniq_code+".txt";
        File file = new File(fileName);
        if (file.exists()) {
            try {
                BufferedReader br1 = new BufferedReader(new FileReader(fileName));
                String line;
                while ((line = br1.readLine()) != null) {
                    String[] lessonData = line.split(",");
                    SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm");
                    Date date1 = dateFormat1.parse(lessonData[3]);
                    String date2=lessonData[3];
                    Date time1 = dateFormat2.parse(lessonData[4]);
                    String time2=lessonData[4];
                    Lesson meditatie;
                    String student = lessonData[0];
                    String profesor = lessonData[1];
                    String subiect = lessonData[2];
                    int id_teacher = Integer.parseInt(lessonData[5]);
                    
                        meditatie = new Lesson (profesor,student,subiect,date1,time1,id_teacher,this.uniq_code);
                        Calendar currentTime = Calendar.getInstance();
                        
                        Calendar lessonTime = Calendar.getInstance();
                        lessonTime.setTime(meditatie.getDate());
                        lessonTime.set(Calendar.HOUR_OF_DAY, meditatie.getTime().getHours());
                        lessonTime.set(Calendar.MINUTE, meditatie.getTime().getMinutes());
                        
                        if (lessonTime.before(currentTime)) {
                        if(type.equals("Student")){
                         Message mesaj = new Message("Meditatia din data " +date2+ " ora "+time2+" s-a incheiat va rog sa lasati un review",meditatie);
                        messages.add(mesaj);
                        }
                        } else {
                        lessons.add(meditatie);
                            }
                        
                }
                br1.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
    }
    }
    public ArrayList<Review> getReviews() {
        return reviews;
    }
    public void setReviews() {
        String fileName="datas\\Review\\"+this.name.replaceAll(" ", "_")+"_"+this.uniq_code+".txt";
        File file = new File(fileName);
        if (file.exists()) {
            try {
                BufferedReader br1 = new BufferedReader(new FileReader(fileName));
                String line;
                while((line = br1.readLine())!=null)
                {
                    String[] reviewData=line.split("©");
                    String student = reviewData[0];
                    String comment = reviewData[1];
                    int rating = Integer.parseInt(reviewData[2]);
                    Review review = new Review(student,comment,rating);
                    reviews.add(review);
                }
                
              br1.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public ArrayList<Request> getRequests() {
        return requests;
    }
    public void setRequests() {
        String fileName="datas\\Requests\\"+this.name.replaceAll(" ", "_")+"_"+this.uniq_code+".txt";
        File file = new File(fileName);
        if (file.exists()) {
            try {
                BufferedReader br1 = new BufferedReader(new FileReader(fileName));
                String line;
                while ((line = br1.readLine()) != null) {
                    String[] requestData = line.split(",");
                    String subiect = requestData[0];;
                    SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm");
                    Date date = dateFormat1.parse(requestData[1]);
                    Date time = dateFormat2.parse(requestData[2]);
                    int id_participant =Integer.parseInt(requestData[4]);
                    Lesson meditatie;
                    if(this.type.equals("Profesor"))
                    {
                        String expeditor = requestData[3];
                        meditatie = new Lesson (this.name,expeditor,subiect,date,time,id_participant,this.uniq_code);
                    }
                    else
                    {
                        String destinatar = requestData[3];
                        meditatie = new Lesson (destinatar,this.name,subiect,date,time,id_participant,this.uniq_code);
                    }
                    
                    Request cerere;
                    if(this.type.equals("Profesor"))
                    {
                    cerere = new Request(meditatie.getStudent(),meditatie,id_participant);
                    }
                    else
                    {
                     cerere = new Request(meditatie.getTeacher(),meditatie,id_participant);
                    } 
                    this.addRequests(cerere);
                }
                br1.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
    public int getCode()
    {
        return uniq_code;
    }
    
     // metodele suplimentare
    public void addSubject(String subject) {
        this.subjects.add(subject);
    }
    public void removeSubject(String subject) {
        this.subjects.remove(subject);
    }
    public void addCertificate(Certificate certificate) {
        this.certificates.add(certificate);
    }
    public void removeCertificate(Certificate certificate) {
        this.certificates.remove(certificate);
    }
    public void addLesson(Lesson lesson) {
        this.lessons.add(lesson);
    }
    public void removeLesson(Lesson lesson) {
        this.lessons.remove(lesson);
    }
    public void addReview(Review review) {
        this.reviews.add(review);
    }
    public void removeReview(Review review) {
        this.reviews.remove(review);
    }
    public void addRequests(Request request) {
        this.requests.add(request);
    }
    public void removeRequest(Request request) {
        this.requests.remove(request);
    }
    
        public int compareTo(User other) {
        return this.getName().compareTo(other.getName());
    }
        
         public void sendNotification(String message) {
        JOptionPane.showMessageDialog(null,"Notificare: " + message);
    }

    /**
     * @return the messages
     */
    public ArrayList<Message> getMessages() {
        return messages;
    }
    public void setMessages()
    {
        
    }
    public void addMessages(Message mesaj)
    {
        this.messages.add(mesaj);
    }
    public void loadMessages()
    {
        String fileName="datas\\Messages\\"+this.name.replaceAll(" ", "_")+"_"+this.uniq_code+".txt";
        
        File file = new File(fileName);
    ArrayList<Message> messages = new ArrayList<Message>();
    if (file.exists()) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                String[] messageData = line.split(",");
                String sender = messageData[0];
                String content = messageData[1];
                int id = Integer.parseInt(messageData[2]);
                Message message = new Message(sender,content,id);
                messages.add(message);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    this.messages = messages;
    }

}
