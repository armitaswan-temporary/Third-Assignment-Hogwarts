import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Teacher extends User {
    public Teacher (String firstName, String lastName, String username, String password, int houseId) throws Exception {
        super (firstName, lastName, username, password);
        this.houseID = houseId;
        this.roleID = 2;
    }

    public static Teacher getTeacher(UUID teacherID) {
        List <Teacher> teachers = Hogwarts.getAllTeachers();
        for (Teacher teacher : teachers) {
            if (teacher.userID == teacherID) {
                return teacher;
            }
        }
        return null;
    }

    public void takeCourse(Course course) {
        course.setTeacherId(userID);
    }

    public List<Student> getUnscoredStudents() {
        return StudentCourseDetail.getStudentsOfTeacher(userID, true);
    }

    public List<Course> getCourses() {
        List <Course> courses = Hogwarts.getAllCourses();
        List <Course> result = new ArrayList<>();
        for (Course course : courses) {
            if (course.getTeacherId() == userID) {
                result.add(course);
            }
        }
        return result;
    }


    public double getAverageScore() {
        return StudentCourseDetail.getTeacherAverageScore(userID);
    }

    public List<String> getComments() {
        return StudentCourseDetail.getTeacherComments(userID);
    }
}