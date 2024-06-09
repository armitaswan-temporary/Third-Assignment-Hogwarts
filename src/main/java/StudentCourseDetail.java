import java.util.*;

public class StudentCourseDetail {
    private UUID studentCourseDetailID;
    private UUID courseID;
    private UUID studentID;
    private double grade;
    private double teacherScore = 0;
    private boolean hasBeenGraded = false;
    private String studentComment = "";

    public StudentCourseDetail(UUID studentID, UUID courseID) {
        this.studentCourseDetailID = UUID.randomUUID();
        this.studentID = studentID;
        this.courseID = courseID;
    }

    public static List<Student> getStudentsOfCourse(UUID courseID) {
        List <Student> result = new ArrayList<>();
        List <StudentCourseDetail> details = Hogwarts.getAllStudentCourseDetails();
        for (StudentCourseDetail detail : details) {
            if (detail.courseID == courseID) {
                Student student = Student.getStudent(detail.studentID);
                if (result.contains(student) == false) {
                    result.add(student);
                }
            }
        }
        return result;
    }
    public static List<Student> getStudentsOfTeacher(UUID teacherID, boolean onlyUnscoredOnes) {
        List <Student> result = new ArrayList<>();
        List <StudentCourseDetail> details = Hogwarts.getAllStudentCourseDetails();
        for (StudentCourseDetail detail : details) {
            if (onlyUnscoredOnes && detail.hasBeenGraded == false) {
                continue;
            }
            Course course = Course.getCourse(detail.courseID);
            if (course.teacherID == teacherID) {
                Student student = Student.getStudent(detail.studentID);
                if (result.contains(student) == false) {
                    result.add(student);
                }
            }
        }
        return result;
    }
    public static List<Teacher> getTeachersOfStudent(UUID studentID) {
        List <Teacher> result = new ArrayList<>();
        List <StudentCourseDetail> details = Hogwarts.getAllStudentCourseDetails();
        for (StudentCourseDetail detail : details) {
            if (detail.studentID == studentID) {
                Course course = Course.getCourse(detail.courseID);
                Teacher teacher = Teacher.getTeacher(course.teacherID);
                if (result.contains(teacher) == false) {
                    result.add(teacher);
                }
            }
        }
        return result;
    }

    public static List<Course> getCoursesOfStudent(UUID studentID) {
        List <Course> result = new ArrayList<>();
        List <StudentCourseDetail> details = Hogwarts.getAllStudentCourseDetails();
        for (StudentCourseDetail detail : details) {
            if (detail.studentID == studentID) {
                Course course = Course.getCourse(detail.courseID);
                if (result.contains(course) == false) {
                    result.add(course);
                }
            }
        }
        return result;
    }

    public static StudentCourseDetail getStudentCourseDetail(UUID studentID, UUID courseID) {
        List <StudentCourseDetail> details = Hogwarts.getAllStudentCourseDetails();
        for (StudentCourseDetail detail : details) {
            if (detail.studentID == studentID && detail.courseID == courseID) {
                return detail;
            }
        }
        return null;
    }

    public static double getTeacherAverageScore(UUID teacherID) {
        double sum = 0;
        int count = 0;
        List <StudentCourseDetail> details = Hogwarts.getAllStudentCourseDetails();
        for (StudentCourseDetail detail : details) {
            Course course = Course.getCourse(detail.courseID);
            if (course.teacherID == teacherID && detail.teacherScore != 0) {
                sum += detail.teacherScore;
                count++;
            }
        }
        if (count == 0) return 0;
        return sum/count;
    }

    public static List<String> getTeacherComments(UUID teacherID) {
        List <String> result = new ArrayList<>();
        List <StudentCourseDetail> details = Hogwarts.getAllStudentCourseDetails();
        for (StudentCourseDetail detail : details) {
            Course course = Course.getCourse(detail.courseID);
            if (course.teacherID == teacherID && detail.studentComment.equals("") == false) {
                result.add(detail.studentComment);
            }
        }
        return result;
    }

    public void setGrade(double grade) {
        if (grade < 0)
            grade = 0;
        if (grade > 20)
            grade = 20;
        this.grade = grade;
        this.hasBeenGraded = true;
    }

    public void setTeacherGrade(double teacherScore) {
        this.teacherScore = teacherScore;
    }

    public void setTeacherComment(String comment) {
        this.studentComment = comment;
    }

    public String getGradeString() {
        if (hasBeenGraded == false)
            return "(Not yet graded)";
        return "" + grade;
    }

    public String getCourseName() {
        return Course.getCourse(courseID).getName();
    }

    public String getTeacherName() {
        return Teacher.getTeacher(Course.getCourse(courseID).teacherID).getFullName();
    }
}