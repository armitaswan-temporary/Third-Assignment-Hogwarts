import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

public class Course {
    private String title;
    private UUID courseID;
    private ArrayList<Student> students;
    private Teacher teacher;
    private int capacity;
    private boolean capacityFull;

    public Course(String title, Teacher teacher, int capacity) {
        this.title = title;
        this.teacher = teacher;
        courseID = UUID.randomUUID();
        this.capacity = capacity;
        capacityFull = false;
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public List<Student> getStudents() {
        return students;
    }

    public String getTitle() {
        return title;
    }

    public UUID getCourseID() {
        return courseID;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacityFull(boolean capacityFull) {
        this.capacityFull = capacityFull;
    }

    public boolean getCapacityFull() {
        return capacityFull;
    }
}
