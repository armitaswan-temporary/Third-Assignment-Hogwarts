import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hogwarts {
    private static List <User> users = new ArrayList<>();
    private static List <User> unconfirmedUsers = new ArrayList<>();
    private static List <Course> courses = new ArrayList<>();
    private static List <StudentCourseDetail> studentCourseDetails = new ArrayList<>();

    static {
        users = new ArrayList<>();
        courses = new ArrayList<>();
        studentCourseDetails = new ArrayList<>();
        try {
            //Admin
            users.add(new Assistant("Albus","Dumbledore", "Wiz", "SherbetLemon"));

            //Teachers
            Teacher lupin = new Teacher("Remus","Lupin", "Moony", "Chocolate", 1);
            Teacher mcGonagall = new Teacher("Minerva","McGonagall", "M.McGonagall", "PiertotumLocomotor", 1);
            Teacher snape = new Teacher("Severus","Snape", "PotionsMaster", "AlwaysLily", 4);
            users.add(lupin);
            users.add(mcGonagall);
            users.add(snape);

            //Students
            Student hermione = new Student("Hermione","Granger", "Leviosa", "Crookshanks", 1);
            Student harry = new Student("Harry","Potter", "TheBoyWhoLived", "ScarHead", 1);
            users.add(new Student("Luna","Lovegood", "NargleHunter", "CrumpleHorned", 3));
            users.add(hermione);
            users.add(harry);

            //Courses
            Course transfiguration = new Course("Transfiguration");
            transfiguration.setTeacherId(mcGonagall.getUserID());
            Course defense = new Course("Defense Against the dark arts");
            defense.setTeacherId(lupin.getUserID());
            Course potions = new Course("Potions");
            potions.setTeacherId(snape.getUserID());
            courses.add(transfiguration);
            courses.add(defense);
            courses.add(potions);
            courses.add(new Course("Charms"));

            //StudentCourseDetails
            StudentCourseDetail detail1 = new StudentCourseDetail(hermione.getUserID(), potions.getCourseID());
            detail1.setGrade(17);
            detail1.setTeacherGrade(1);

            StudentCourseDetail detail2 = new StudentCourseDetail(harry.getUserID(), potions.getCourseID());
            detail2.setGrade(1);
            detail2.setTeacherGrade(1);

            StudentCourseDetail detail3 = new StudentCourseDetail(hermione.getUserID(), defense.getCourseID());
            detail3.setGrade(20);
            detail3.setTeacherGrade(5);
            detail3.setTeacherComment("Awesome Teacher");

            studentCourseDetails.add(detail1);
            studentCourseDetails.add(detail2);
            studentCourseDetails.add(detail3);
        }
        catch (Exception exception) {
        }
    }

    public static List<Teacher> getAllTeachers() {
        List <Teacher> teachers = new ArrayList<>();
        for (User user : users) {
            if (user instanceof Teacher) {
                teachers.add((Teacher) user);
            }
        }
        return teachers;
    }

    public static List<Student> getAllStudents() {
        List <Student> students = new ArrayList<>();
        for (User user : users) {
            if (user instanceof Student) {
                students.add((Student) user);
            }
        }
        return students;
    }

    public static List<Course> getAllCourses() {
        return courses;
    }

    public static List<Course> getAllCoursesWithTeacher() {
        List<Course> result = new ArrayList<>();
        for (Course course : courses) {
            if (course.getHasTeacher()) {
                result.add(course);
            }
        }
        return result;
    }

    public static List<StudentCourseDetail> getAllStudentCourseDetails() {
        return studentCourseDetails;
    }

    public static void addStudentCourseDetail(StudentCourseDetail detail) {
        studentCourseDetails.add(detail);
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public static List<User> getAllUsers() {
        return Collections.unmodifiableList(users);
    }

    public static void removeUser(User user) {
        users.remove(user);
    }

    public static void addCourse(Course course) {
        courses.add(course);
    }

    public static void addUnconfirmedUser(User user) {
        unconfirmedUsers.add(user);
    }

    public static List<User> getUnconfirmedUsers() {
        return unconfirmedUsers;
    }

    public static List<User> getAllStudentsAndTeachers() {
        List <User> result = new ArrayList<>();
        for (User user : users) {
            if (user instanceof Student || user instanceof Teacher) {
                result.add(user);
            }
        }
        return result;
    }
}