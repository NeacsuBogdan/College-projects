
import com.sun.net.httpserver.Request;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.security.auth.Subject;

public class User {
    private String name;
    private String email;
    private String password;
    private String type; // "teacher" sau "student"
    private String profilePicture;
    private List<Subject> subjects; // materiile predate/preferate
    private List<Certificate> certificates; // certificatele profesorilor
    private List<Lesson> lessons; // meditațiile programate
    private List<Review> reviews; // recenziile primite
    private List<Request> requests; // cererile primite/trimise
    private static Set<String> existingCodes = new HashSet<>();
    
    // constructor pentru a crea un nou utilizator
    public User(String name, String email, String password, String type) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
        if (type.equals("Profesor"))
        {
        this.reviews = new ArrayList<Review>();
        this.subjects = new ArrayList<Subject>();
        this.certificates = new ArrayList<Certificate>();
        }
        this.lessons = new ArrayList<Lesson>();
        this.requests = new ArrayList<Request>();
        
    }
    
    // metodele getter și setter pentru toți membrii de clasă
    
    // metoda setter pentru profilePicture
    public void uploadProfilePicture(String filePath) throws IOException {
        // folder-ul prestabilit unde se vor salva pozele
        String uploadDir = "images/profiles";
        // crearea folderului daca nu exista
        File uploadFolder = new File(uploadDir);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }
        // obtinerea numelui fisierului
        File sourceFile = new File(filePath);
        String fileName = sourceFile.getName();
        //generarea codului unic
        String uniqueId = UUID.randomUUID().toString();
        //verificarea daca codul exista deja
        while(existingCodes.contains(uniqueId)){
            uniqueId = UUID.randomUUID().toString();
        }
        existingCodes.add(uniqueId);
        //crearea numelui noului fisier
        String destinationFileName = this.name+"_"+uniqueId+"_"+fileName;
        //crearea cale catre noul fisier
        String destinationPath = uploadDir + File.separator + destinationFileName;
        //copierea fisierului in folderul prestabilit
        Files.copy(sourceFile.toPath(), (new File(destinationPath)).toPath(), StandardCopyOption.REPLACE_EXISTING);
        //setarea pozei de profil
        this.profilePicture = destinationPath;
    }
    // metoda getter pentru profilePicture
    public String getProfilePicture() {
        return profilePicture;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public List<Subject> getSubjects() {
        return subjects;
    }
    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
    public List<Certificate> getCertificates() {
        return certificates;
    }
    public void setCertificates(List<Certificate> certificates) {
        this.certificates = certificates;
    }
    public List<Lesson> getLessons() {
        return lessons;
    }
    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
    public List<Review> getReviews() {
        return reviews;
    }
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
    public List<Request> getRequests() {
        return requests;
    }
    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }
     // metodele suplimentare
    public void addSubject(Subject subject) {
        this.subjects.add(subject);
    }
    public void removeSubject(Subject subject) {
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
    public void addRequest(Request request) {
        this.requests.add(request);
    }
    public void removeRequest(Request request) {
        this.requests.remove(request);
    }
}
