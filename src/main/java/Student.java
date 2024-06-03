import java.util.List;
import java.util.UUID;

public class Student extends User {
    public Student(String firstName, String lastName, String username, String password, int houseID) throws Exception {
        super(firstName, lastName, username, password);
        this.houseID = houseID;
        this.roleID = 3;
    }

    public static Student getStudent(UUID studentID) {
        List<Student> students = Hogwarts.getAllStudents();
        for (Student student : students) {
            if (student.userID == studentID) {
                return student;
            }
        }
        return null;
    }

    public void enrollInCourse(Course course) {
        StudentCourseDetail detail = new StudentCourseDetail(userID, course.getCourseID());
        Hogwarts.addStudentCourseDetail(detail);
    }
    public List<Course> getCourses() {
        return StudentCourseDetail.getCoursesOfStudent(userID);
    }
    public List<Teacher> getTeachers() {
        return StudentCourseDetail.getTeachersOfStudent(userID);
    }

    public void sortIntoHouse(int houseID) {
        this.houseID = houseID;
    }
}