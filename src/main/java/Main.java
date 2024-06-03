import java.util.*;

public class Main {
    private static User currentUser;
    public static void main(String[] args) {
        while(runMenu());
    }
    //Menu
    public static boolean runMenu() {
        System.out.println();
        System.out.println(" ---Hogwarts School of Witchcraft and Wizardry--- ");
        System.out.println("|                                                |");
        System.out.println("|                    welcome                     | ");
        System.out.println("|                                                |");
        System.out.println("|         Enter the number of the command        | ");
        System.out.println("|                 and press enter                |");
        System.out.println("|                                                |");
        System.out.println("|                 1) Sign up                     |");
        System.out.println("|                 2) Login                       |");
        System.out.println("|                 3) Exit                        |");
        System.out.println("|                                                |");
        System.out.println(" ------------------------------------------------ ");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        String command = scanner.next();
        switch (command) {
            case "1" -> {
                showSignupPage();
                return true;
            }
            case "2" -> {
                showLoginPage();
                return true;
            }
            case "3" -> {
                return false;
            }
        }
        return true;
    }
    private static void showSignupPage() {
        System.out.println();
        System.out.println(" ---Hogwarts School of Witchcraft and Wizardry--- ");
        System.out.println("|                                                |");
        System.out.println("|                    Sign Up                     | ");
        System.out.println("|                                                |");
        System.out.println("|          Enter your username and password      | ");
        System.out.println("|          Enter your firstname and lastname     | ");
        System.out.println("|               Enter your house name            | ");
        System.out.println("|        Enter your role (teacher/student)       | ");
        System.out.println("|   press enter after entering each one of them  |");
        System.out.println("|          enter EXIT as username to exit        |");
        System.out.println("|                                                |");
        System.out.println(" ------------------------------------------------ ");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Username: ");
        String username = scanner.next();
        if (Objects.equals(username, "EXIT")) {
            return;
        }
        System.out.println("Enter Password: ");
        String password = scanner.next();

        System.out.println("Enter First Name: ");
        String firstName = scanner.next();

        System.out.println("Enter Last Name: ");
        String lastName = scanner.next();

        System.out.println("Enter Your House Number:  ");
        System.out.println("1)Gryffindor\n2)Hufflepuff\n3)Ravenclaw\n4)Slytherin");
        int houseId = scanner.nextInt();
        if (houseId < 1)
            houseId = 1;
        if (houseId > 4)
            houseId = 4;

        System.out.println("Enter Your Role Id:  ");
        System.out.println("1)Teacher\n2)Student");
        int roleId = scanner.nextInt();
        if (roleId < 1)
            roleId = 1;
        if (roleId > 2)
            roleId = 2;

        try {
            User newUser;
            if (roleId == 1) {
                newUser = new Teacher(firstName, lastName, username,password, houseId );
            }
            else {
                newUser = new Student(firstName, lastName, username,password, houseId );
            }

            Hogwarts.addUnconfirmedUser(newUser);
            System.out.println("User added");
        }
        catch(Exception ignored) {
        }
    }
    private static void showLoginPage() {
        System.out.println();
        System.out.println(" ---Hogwarts School of Witchcraft and Wizardry--- ");
        System.out.println("|                                                |");
        System.out.println("|                    welcome                     | ");
        System.out.println("|                                                |");
        System.out.println("|        Enter the your username and password    | ");
        System.out.println("|   press enter after entering each one of them  |");
        System.out.println("|          enter EXIT as username to exit        |");
        System.out.println("|                                                |");
        System.out.println(" ------------------------------------------------ ");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Username: ");
        String username = scanner.next();
        if (Objects.equals(username, "EXIT")) {
            return;
        }
        System.out.println("Enter Password: ");
        String password = scanner.next();

        currentUser = UserManagement.login(username, password);
        if (currentUser == null) {
            System.out.println();
            System.out.println("WRONG CREDENTIALS or UNCONFIRMED USER");
            System.out.println("Please Enter Again");
            showLoginPage();
            return;
        }

        printWelcome();
        if (currentUser instanceof Student) {
            showUserMenu();
            return;
        }

        if (currentUser instanceof Teacher) {
            showTeacherMenu();
            return;
        }

        if (currentUser instanceof Assistant) {
            showAssistantMenu();
            return;
        }
    }
    private static void printWelcome() {
        System.out.println();
        System.out.println();
        System.out.println("Welcome " + currentUser.getFullName());
        System.out.println("(" + currentUser.getRoleName() + ")");
    }

    //Assistant Menu
    private static void showAssistantMenu() {
        System.out.println();
        System.out.println("-------- Enter the command number --------");
        System.out.println("|                                        |");
        System.out.println("|             1) Confirm users           |");
        System.out.println("|           2) Add new assistant         |");
        System.out.println("|    3) View courses and their students  |");
        System.out.println("|    4) View(Remove) student or teacher  |");
        System.out.println("|          5) Create new course          |");
        System.out.println("|               6) Logout                |");
        System.out.println("|                                        |");
        System.out.println("------------------------------------------");

        Scanner scanner = new Scanner(System.in);
        int command = scanner.nextInt();
        switch (command) {
            case 1:
                showConfirmUser();
                break;
            case 2:
                showAddNewAssistant();
                break;
            case 3:
                showCourses();
                break;
            case 4:
                showStudentsAndTeachers();
                break;
            case 5:
                showAddNewCourse();
                break;
            default:
                currentUser = null;
                return;
        }
        showAssistantMenu();
    }
    private static void showAddNewCourse() {
        System.out.println();
        System.out.println(" ---Hogwarts School of Witchcraft and Wizardry--- ");
        System.out.println("|                                                |");
        System.out.println("|                     New Course                 | ");
        System.out.println("|                                                |");
        System.out.println("|               Enter the  Course name           | ");
        System.out.println("|              Enter EXIT as name to exit        |");
        System.out.println("|                                                |");
        System.out.println(" ------------------------------------------------ ");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Name: ");
        String name = scanner.nextLine();
        if (Objects.equals(name, "EXIT")) {
            return;
        }

        Course course = new Course(name);
        Hogwarts.addCourse(course);
        System.out.println("Course added");
    }
    private static void showStudentsAndTeachers() {
        List <User> users = Hogwarts.getAllStudentsAndTeachers();

        if (users.isEmpty()) {
            System.out.println("No Teacher or Student");
        }
        System.out.println();
        int i = 1;
        for (User user : users) {
            if (user instanceof Student) {
                System.out.println(i + ") " + user.getFullName() + " (" + user.getRoleName()+")");
            }
            else {
                System.out.println(i + ") " + user.getFullName() + " (" + user.getRoleName()+")");
            }
            i++;
        }

        System.out.println("Enter number: ");
        Scanner scanner = new Scanner(System.in);
        int command = scanner.nextInt();
        if (command > users.size() || command < 1) {
            return;
        }

        viewProfile(users.get(command - 1));
    }
    private static void viewProfile (User user) {
        System.out.println();
        System.out.println("------------- About User -------------");
        System.out.println("  Username: " + user.getUsername());
        System.out.println("  Full Name: " + user.getFullName());
        System.out.println("  Role: " + user.getRoleName());
        if (user instanceof Teacher)
            System.out.println("  Score by Students: " + ((Teacher)user).getAverageScore());
        System.out.println("  House: " + user.getHouseName());

        System.out.println("Enter Command:\n1)Remove User \n2)Get Back");
        int command = (new Scanner(System.in)).nextInt();
        if (command == 1) {
            ((Assistant)currentUser).removeUser(user);
            System.out.println("User was deleted");
        }
    }
    private static void showCourses() {
        System.out.println();
        System.out.println("-------- Enter the course number --------");
        List <Course> courses = Hogwarts.getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("No Course");
        }
        int i = 1;
        for (Course course : courses) {
            System.out.println(i + ") " + course.getName());
            i++;
        }

        System.out.println("Enter Course Number: ");
        Scanner scanner = new Scanner(System.in);
        int command = scanner.nextInt();
        if (command > courses.size() || command < 1) {
            return;
        }
        Course course = courses.get(command - 1);
        showStudentsOfCourse(course, false);
    }
    private static void showStudentsOfCourse(Course course, boolean isTeacher) {
        List <Student> studentsOfCourse = course.getStudents();
        if (studentsOfCourse.isEmpty()) {
            System.out.println("No Student");
        }
        int i = 1;

        //Sorting Students
        Collections.sort(studentsOfCourse, new Comparator<Student>() {
            public int compare(Student s1, Student s2) {
                return s2.getFullName().compareTo(s1.getFullName());
            }
        });

        for (Student student : studentsOfCourse) {
            System.out.println(i + ") " + student.getFullName());
            i++;
        }
        if (isTeacher) {
            System.out.println("Enter student number to score: ");
            Scanner scanner = new Scanner(System.in);
            int command = scanner.nextInt();
            if (command < 1)
                command = 1;
            if (command > studentsOfCourse.size())
                command = studentsOfCourse.size();
            System.out.println("Enter grade: ");
            double grade = scanner.nextDouble();

            Student student = studentsOfCourse.get(command - 1);

            StudentCourseDetail detail = StudentCourseDetail.getStudentCourseDetail(student.getUserID(), course.getCourseID());
            detail.setGrade(grade);

            System.out.println("Grade set");
            return;
        }
        else {
            System.out.println("Enter anything to get back");
            Scanner scanner = new Scanner(System.in);
            scanner.next();
        }

    }

    private static void showAddNewAssistant() {
        System.out.println();
        System.out.println(" ---Hogwarts School of Witchcraft and Wizardry--- ");
        System.out.println("|                                                |");
        System.out.println("|                    Sign Up                     | ");
        System.out.println("|                                                |");
        System.out.println("|          Enter the  username and password      | ");
        System.out.println("|          Enter the  firstname and lastname     | ");
        System.out.println("|   press enter after entering each one of them  |");
        System.out.println("|          enter EXIT as username to exit        |");
        System.out.println("|                                                |");
        System.out.println(" ------------------------------------------------ ");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Username: ");
        String username = scanner.next();
        if (Objects.equals(username, "EXIT")) {
            return;
        }
        System.out.println("Enter Password: ");
        String password = scanner.next();

        System.out.println("Enter First Name: ");
        String firstName = scanner.next();

        System.out.println("Enter Last Name: ");
        String lastName = scanner.next();

        try {
            Assistant newUser = new Assistant(firstName, lastName, username, password);
            ((Assistant)currentUser).addNewAssistant(newUser);

            System.out.println("admin was added successfully");
        }
        catch(Exception e) {
            System.out.println("something went wrong!");
        }

    }

    private static void showConfirmUser() {
        System.out.println();
        System.out.println("-------- Enter the user number --------");
        List <User> unconfirmedUsers = Hogwarts.getUnconfirmedUsers();
        if (unconfirmedUsers.isEmpty()) {
            System.out.println("No Unconfirmed User");
        }
        int i = 1;
        for (User user : unconfirmedUsers) {
            System.out.println(i + ") " + user.getFullName() + " (" + user.getRoleName()+")");
        }

        Scanner scanner = new Scanner(System.in);
        int command = scanner.nextInt();
        if (command > unconfirmedUsers.size() || command < 1) {
            return;
        }

        User newUser = unconfirmedUsers.get(command - 1);
        Hogwarts.addUser(newUser);
        unconfirmedUsers.remove(command - 1);

        System.out.println("User confirmed");
    }

    //Teacher Menu

    private static void showTeacherMenu() {
        System.out.println();
        double averageScore = ((Teacher)currentUser).getAverageScore();
        System.out.println("Teacher's Average Score: " + (averageScore == 0?"no score submitted":averageScore));
        System.out.println();
        System.out.println("-------- Enter the command number --------");
        System.out.println("|                                        |");
        System.out.println("|            1) Take Course              |");
        System.out.println("|  2) View Current Courses and Students  |");
        System.out.println("|     3) View your score and comments    |");
        System.out.println("|                4) Logout               |");
        System.out.println("|                                        |");
        System.out.println("------------------------------------------");

        Scanner scanner = new Scanner(System.in);
        int command = scanner.nextInt();
        switch (command) {
            case 1:
                showCoursesToTake();
                break;
            case 2:
                showTeacherCurrentCourses();
                break;
            case 3:
                showCommentsAndScores();
                break;
            default:
                currentUser = null;
                return;
        }
        showTeacherMenu();
    }

    private static void showCommentsAndScores() {
        double averageScore = ((Teacher)currentUser).getAverageScore();
        System.out.println("Teacher's Average Score: " + (averageScore == 0?"no score submitted":averageScore));

        List <String> comments = ((Teacher)currentUser).getComments();
        if (comments.isEmpty()) {
            System.out.println("No Comment");
        }

        int i = 1;
        for (String comment : comments) {
            System.out.println(i + ") " + "\""+comment + "\"");
        }

        System.out.println("Enter anything to get back");
        Scanner scanner = new Scanner(System.in);
        scanner.next();
    }

    private static void showTeacherCurrentCourses() {
        System.out.println();
        System.out.println("-------- Enter the course number --------");
        List <Course> courses = ((Teacher)currentUser).getCourses();
        if (courses.isEmpty()) {
            System.out.println("No Course");
        }
        int i = 1;
        for (Course course : courses) {
            System.out.println(i + ") " + course.getName());
            i++;
        }

        System.out.println("Enter Course Number: ");
        Scanner scanner = new Scanner(System.in);
        int command = scanner.nextInt();
        if (command > courses.size() || command < 1) {
            return;
        }
        Course course = courses.get(command - 1);
        showStudentsOfCourse(course, true);
    }

    private static void showCoursesToTake() {
        System.out.println();
        System.out.println("-------- Enter the course number --------");
        List <Course> courses = Course.getCoursesWithNoTeacher();
        if (courses.isEmpty()) {
            System.out.println("No Course");
        }
        int i = 1;
        for (Course course : courses) {
            System.out.println(i + ") " + course.getName());
            i++;
        }

        System.out.println("Enter Course Number: ");
        Scanner scanner = new Scanner(System.in);
        int command = scanner.nextInt();
        if (command > courses.size() || command < 1) {
            return;
        }
        Course course = courses.get(command - 1);
        ((Teacher)currentUser).takeCourse(course);

        System.out.println("You have taken this course");
    }
    //User Menu
    private static void showUserMenu() {
        System.out.println();
        System.out.println("-------- Enter the command number --------");
        System.out.println("|                                        |");
        System.out.println("|          1) Enroll in course           |");
        System.out.println("|    2) View Current Courses And Grades  |");
        System.out.println("|         3) View Current Teachers       |");
        System.out.println("|               4) Logout                |");
        System.out.println("|                                        |");
        System.out.println("------------------------------------------");

        Scanner scanner = new Scanner(System.in);
        int command = scanner.nextInt();
        switch (command) {
            case 1:
                showCoursesToEnroll();
                break;
            case 2:
                showStudentCurrentCourses();
                break;
            case 3:
                showStudentAllTeachers();
                break;
            default:
                currentUser = null;
                return;
        }
        showUserMenu();
    }
    private static void showCoursesToEnroll() {
        List<Course> studentCourses = ((Student) currentUser).getCourses();
        List<Course> allCoursesWithTeacher = Hogwarts.getAllCoursesWithTeacher();
        List<Course> notEnrolledCourses = new ArrayList<>(allCoursesWithTeacher);
        notEnrolledCourses.removeAll(studentCourses);
        if (notEnrolledCourses.isEmpty()) {
            System.out.println("No course");
        }
        int i = 1;
        for (Course course : notEnrolledCourses) {
            System.out.println(i + ") " + course.getName());
            i++;
        }

        System.out.println("Enter Course Number: ");
        Scanner scanner = new Scanner(System.in);
        int command = scanner.nextInt();
        if (command > notEnrolledCourses.size() || command < 1) {
            return;
        }
        Course course = notEnrolledCourses.get(command - 1);
        ((Student)currentUser).enrollInCourse(course);
    }
    private static void showStudentAllTeachers() {
        List <Teacher> teachersOfStudent = StudentCourseDetail.getTeachersOfStudent(currentUser.getUserID());
        if (teachersOfStudent.isEmpty()) {
            System.out.println("No teacher");
        }
        System.out.println("---- Current Teachers ----");
        int i = 1;
        for (Teacher teacher : teachersOfStudent) {
            System.out.println(i + ") " + teacher.getFullName());
            i++;
        }
        System.out.println();
        System.out.println("Enter anything to get back");
        Scanner scanner = new Scanner(System.in);
        scanner.next();
    }

    private static void showStudentCurrentCourses() {
        System.out.println();
        System.out.println("-------- Enter the course number --------");
        List <Course> courses = ((Student) currentUser).getCourses();
        if (courses.isEmpty()) {
            System.out.println("No Course");
        }
        int i = 1;
        for (Course course : courses) {
            System.out.println(i + ") " + course.getName());
            i++;
        }

        System.out.println("Enter Course Number: ");
        Scanner scanner = new Scanner(System.in);
        int command = scanner.nextInt();
        if (command > courses.size() || command < 1) {
            return;
        }
        Course course = courses.get(command - 1);
        StudentCourseDetail detail =  StudentCourseDetail.getStudentCourseDetail(((Student) currentUser).getUserID(), course.getCourseID());
        showStudentCourseDetail(detail);
    }
    private static void showStudentCourseDetail(StudentCourseDetail detail) {
        System.out.println("-------- Student Course Detail --------");
        System.out.println("  Grade: " + detail.getGradeString());
        System.out.println("  Course Name: " + detail.getCourseName());
        System.out.println("  Teacher Name: " + detail.getTeacherName());

        Scanner scanner = new Scanner(System.in);
        System.out.println("Score Teacher: (1-5)");
        double teacherScore = scanner.nextDouble();
        if (teacherScore < 1)
            teacherScore = 1;
        if (teacherScore > 5)
            teacherScore = 5;

        detail.setTeacherGrade(teacherScore);
        System.out.println("Enter Comment for teacher: ");
        scanner = new Scanner(System.in);
        String comment = scanner.nextLine();
        detail.setTeacherComment(comment);
    }
}