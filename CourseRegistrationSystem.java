import java.util.ArrayList;
import java.util.Scanner;

class Course {
    private final String code;
    private final String title;
    private final String description;
    private final int capacity;
    private final String schedule;
    private int enrolled;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolled = 0;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    public int getEnrolled() {
        return enrolled;
    }

    public void incrementEnrolled() {
        enrolled++;
    }

    public void decrementEnrolled() {
        enrolled--;
    }

    public boolean isFull() {
        return enrolled >= capacity;
    }
}

class Student {
    private final int id;
    private final String name;
    private final ArrayList<Course> registeredCourses;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerForCourse(Course course) {
        registeredCourses.add(course);
        course.incrementEnrolled();
    }

    public void dropCourse(Course course) {
        registeredCourses.remove(course);
        course.decrementEnrolled();
    }
}

public class CourseRegistrationSystem {
    private final ArrayList<Course> courses;
    private final ArrayList<Student> students;
    private final Scanner scanner;

    public CourseRegistrationSystem() {
        courses = new ArrayList<>();
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void addCourse() {
        System.out.println("Enter course code:");
        String code = scanner.nextLine();
        System.out.println("Enter course title:");
        String title = scanner.nextLine();
        System.out.println("Enter course description:");
        String description = scanner.nextLine();
        System.out.println("Enter course capacity:");
        int capacity = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter course schedule:");
        String schedule = scanner.nextLine();

        Course newCourse = new Course(code, title, description, capacity, schedule);
        courses.add(newCourse);
        System.out.println("Course added successfully!");
    }

    public void displayCourses() {
        System.out.println("Available Courses:");
        for (Course course : courses) {
            System.out.println("Course Code: " + course.getCode());
            System.out.println("Title: " + course.getTitle());
            System.out.println("Description: " + course.getDescription());
            System.out.println("Capacity: " + course.getCapacity());
            System.out.println("Enrolled: " + course.getEnrolled());
            System.out.println("Schedule: " + course.getSchedule());
            System.out.println("-----------------------");
        }
    }

    public Course findCourseByCode(String code) {
        for (Course course : courses) {
            if (course.getCode().equalsIgnoreCase(code)) {
                return course;
            }
        }
        return null;
    }

    public Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public void registerStudent() {
        System.out.println("Enter student ID:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter student name:");
        String name = scanner.nextLine();

        Student newStudent = new Student(id, name);
        students.add(newStudent);

        System.out.println("Student registered successfully!");
    }

    public void displayStudentCourses() {
        System.out.println("Enter student ID:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Student student = findStudentById(id);
        if (student != null) {
            ArrayList<Course> registeredCourses = student.getRegisteredCourses();
            System.out.println("Registered Courses for " + student.getName() + ":");
            for (Course course : registeredCourses) {
                System.out.println("Course Code: " + course.getCode());
                System.out.println("Title: " + course.getTitle());
                System.out.println("Description: " + course.getDescription());
                System.out.println("Schedule: " + course.getSchedule());
                System.out.println("-----------------------");
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    public void registerStudentForCourse() {
        displayCourses();
        System.out.println("Enter course code to register:");
        String code = scanner.nextLine();

        Course course = findCourseByCode(code);
        if (course != null) {
            if (!course.isFull()) {
                System.out.println("Enter student ID to register:");
                int id = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                Student student = findStudentById(id);
                if (student != null) {
                    student.registerForCourse(course);
                    System.out.println("Student " + student.getName() + " registered for course " + course.getTitle() + ".");
                } else {
                    System.out.println("Student not found.");
                }
            } else {
                System.out.println("Course " + course.getTitle() + " is full. Cannot register.");
            }
        } else {
            System.out.println("Course not found.");
        }
    }

    public void dropStudentFromCourse() {
        System.out.println("Enter student ID:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Student student = findStudentById(id);
        if (student != null) {
            displayStudentCourses();
            System.out.println("Enter course code to drop:");
            String code = scanner.nextLine();

            Course course = findCourseByCode(code);
            if (course != null) {
                if (student.getRegisteredCourses().contains(course)) {
                    student.dropCourse(course);
                    System.out.println("Student " + student.getName() + " dropped from course " + course.getTitle() + ".");
                } else {
                    System.out.println("Student is not registered for this course.");
                }
            } else {
                System.out.println("Course not found.");
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    public void startSystem() {
        int choice;
        do {
            System.out.println("\nCourse Registration System");
            System.out.println("1. Add Course");
            System.out.println("2. Display Available Courses");
            System.out.println("3. Register Student");
            System.out.println("4. Display Student's Courses");
            System.out.println("5. Register Student for Course");
            System.out.println("6. Drop Student from Course");
            System.out.println("0. Exit");
            System.out.println("Enter your choice:");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addCourse();
                case 2 -> displayCourses();
                case 3 -> registerStudent();
                case 4 -> displayStudentCourses();
                case 5 -> registerStudentForCourse();
                case 6 -> dropStudentFromCourse();
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    public static void main(String[] args) {
        CourseRegistrationSystem system = new CourseRegistrationSystem();
        system.startSystem();
    }
}
