
import java.util.ArrayList;
import java.util.List;

public class Subject {
    private String name;
    private List<String> subtopics;

    public Subject(String name) {
        this.name = name;
        this.subtopics = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<String> getSubtopics() {
        return subtopics;
    }

    public void addSubtopic(String subtopic) {
        subtopics.add(subtopic);
    }

    public void removeSubtopic(String subtopic) {
        subtopics.remove(subtopic);
    }
}
