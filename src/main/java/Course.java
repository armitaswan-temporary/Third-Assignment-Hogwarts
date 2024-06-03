import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Course {
    protected UUID courseID;
    protected String name;
    protected UUID teacherID;
    protected boolean hasTeacher = false;

    public Course(String name) {
        this.name = name;
        this.hasTeacher = false;
        this.courseID = UUID.randomUUID();
    }

    public static List<Course> getCoursesWithNoTeacher() {
        List<Course> courses = Hogwarts.getAllCourses();
        List<Course> result = new ArrayList<>();
        for(Course course : courses)
        {
            if(course.hasTeacher == false)
            {
                result.add(course);
            }
        }
        return result;
    }

    public List<Student> getStudents() {
        return StudentCourseDetail.getStudentsOfCourse(courseID);
    }

    public static Course getCourse(UUID courseID) {
        List<Course> courses = Hogwarts.getAllCourses();
        for(Course course : courses)
        {
            if(course.courseID == courseID)
            {
                return course;
            }
        }
        return null;
    }

    public void setTeacherId(UUID teacherID) {
        this.teacherID = teacherID;
        this.hasTeacher = true;
    }

    public UUID getCourseID() {
        return courseID;
    }

    public UUID getTeacherId() {
        return teacherID;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Course == false)
            return false;
        return  ((Course)obj).getCourseID().equals(courseID);
    }

    public String getName() {
        return name;
    }

    public boolean getHasTeacher() {
        return hasTeacher;
    }
}