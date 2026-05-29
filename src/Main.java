import service.SchoolService;
import model.Course;
import model.Student;
import model.Subject;
import model.Teacher;
import exceptions.InvalidDniException;
import exceptions.InvalidSalaryException;
import exceptions.InvalidCourseException;
import exceptions.InvalidGroupException;
import exceptions.InvalidSubjectException;

import java.util.Scanner;

/**
 * Main entry point for the School Management System application.
 * Handles the user interface, menu loop, input validation, and triggers data persistence.
 * @author Nicolás Mingorance Sánchez
 * @version 1.1
 */
public class Main {

    /** Service layer handler for business logic and data persistence */
    private static SchoolService systemService = new SchoolService();

    /** Scanner instance to read user input from the standard console */
    private static Scanner sc = new Scanner(System.in);

    /**
     * Main method that drives the application lifecycle.
     * Automatically loads data at startup and ensures a safe menu loop execution.
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        systemService.loadDataAutomatically();

        int option = 0;
        do {
            try {
                printMenu();
                option = Integer.parseInt(sc.nextLine());
                processOption(option);
            } catch (NumberFormatException e) {
                System.out.println("\n[Error] Invalid input. Please enter a valid numerical option.");
            } catch (Exception e) {
                System.out.println("\n[Error] An unexpected error occurred: " + e.getMessage());
            }
        } while (option != 7); // Ahora la opción de salida es la 7
    }

    /**
     * Prints the interactive user menu options to the standard console output.
     */
    private static void printMenu() {
        System.out.println("\n========== IES NERVIÓN - MANAGEMENT SYSTEM ==========");
        System.out.println("1. Register New Student");
        System.out.println("2. Register New Teacher");
        System.out.println("3. List All Enrolled Students");
        System.out.println("4. Assign/Update Student Grade");
        System.out.println("5. Execute Advanced Reports (Stream API)");
        System.out.println("6. Unregister Member by DNI");
        System.out.println("7. Save & Exit Application");
        System.out.print("Select an option: ");
    }

    /**
     * Processes the business logic associated with the chosen menu option.
     * @param option The numerical choice selected by the user
     * @throws Exception If any validation or operational error occurs during execution
     */
    private static void processOption(int option) throws Exception {
        switch (option) {
            case 1:
                System.out.println("\n--- Register Student ---");
                try {
                    System.out.print("Enter DNI (8 digits + 1 letter): "); String dni = sc.nextLine();
                    System.out.print("Enter Name: "); String name = sc.nextLine();
                    System.out.print("Enter Last Name: "); String lastName = sc.nextLine();

                    System.out.print("Enter Course Number (1-6): ");
                    int studentCourseNum = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter Group Letter (A, B or C): ");
                    char studentGroupLetter = sc.nextLine().toUpperCase().charAt(0);

                    Course studentCourse = new Course(studentCourseNum, studentGroupLetter);

                    Student newStudent = new Student(dni, name, lastName);

                    newStudent.setCourse(studentCourse);

                    systemService.registerStudent(newStudent);
                    System.out.println("[Success] Student registered successfully. Email: " + newStudent.getEmail());
                } catch (InvalidDniException e) {
                    System.out.println("[Error] Registration failed: The DNI format is invalid.");
                }
                break;

            case 2:
                System.out.println("\n--- Register Teacher ---");
                try {
                    System.out.print("Enter DNI (8 digits + 1 letter): "); String teacherDni = sc.nextLine();
                    System.out.print("Enter Name: "); String teacherName = sc.nextLine();
                    System.out.print("Enter Last Name: "); String teacherLastName = sc.nextLine();
                    System.out.print("Enter Base Salary (1200 - 2200): "); double salary = Double.parseDouble(sc.nextLine());

                    System.out.print("Enter Subject (Math, Science, History, English, Biology, Physics): ");
                    String subjectName = sc.nextLine();
                    Subject teacherSubject = new Subject(subjectName);

                    System.out.print("Enter Tutored Course Number (1-6): ");
                    int courseNum = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter Tutored Group Letter (A, B or C): ");
                    char groupLetter = sc.nextLine().toUpperCase().charAt(0);
                    Course tutoredCourse = new Course(courseNum, groupLetter);

                    Teacher newTeacher = new Teacher(teacherDni, teacherName, teacherLastName, salary, teacherSubject, tutoredCourse);
                    systemService.registerTeacher(newTeacher);
                    System.out.println("[Success] Teacher registered successfully. Email: " + newTeacher.getEmail());

                } catch (InvalidDniException e) {
                    System.out.println("[Error] DNI format is incorrect.");
                } catch (InvalidSalaryException e) {
                    System.out.println("[Error] Salary out of bounds (Must be between 1200€ and 2200€).");
                } catch (InvalidSubjectException e) {
                    System.out.println("[Error] Invalid subject name. Choose from the allowed list.");
                } catch (InvalidCourseException | InvalidGroupException e) {
                    System.out.println("[Error] Course configuration failed: Course must be 1-6 and Group A-C.");
                }
                break;

            case 3:
                System.out.println("\n--- LIST OF ALL ENROLLED STUDENTS ---");
                if (systemService.getAllStudents().isEmpty()) {
                    System.out.println("No students currently registered in the system.");
                } else {
                    for (Student student : systemService.getAllStudents()) {
                        System.out.println(student);
                    }
                }
                break;

            case 4:
                System.out.println("\n--- Assign/Update Student Grade ---");
                System.out.print("Enter Student DNI: ");
                String targetStudentDni = sc.nextLine();
                Student targetStudent = systemService.findStudent(targetStudentDni);

                if (targetStudent == null) {
                    System.out.println("[Error] Student not found in the system.");
                } else {
                    try {
                        System.out.print("Enter Subject Name (Math, Science, etc.): ");
                        String gradeSubjectName = sc.nextLine();
                        Subject gradeSubject = new Subject(gradeSubjectName);

                        System.out.print("Enter Mark (0.0 - 10.0): ");
                        double mark = Double.parseDouble(sc.nextLine());

                        // Si ya tiene nota la actualiza, si no, la añade de forma segura
                        if (targetStudent.getGrades().containsKey(gradeSubject)) {
                            targetStudent.updateGrade(gradeSubject, mark);
                            System.out.println("[Success] Grade updated successfully.");
                        } else {
                            targetStudent.addGrade(gradeSubject, mark);
                            System.out.println("[Success] Grade added successfully.");
                        }
                    } catch (InvalidSubjectException e) {
                        System.out.println("[Error] That subject does not exist in the school catalog.");
                    } catch (IllegalArgumentException | IllegalStateException e) {
                        System.out.println("[Error] Operation failed: " + e.getMessage());
                    }
                }
                break;

            case 5:
                System.out.println("\n--- ADVANCED STATISTICS & REPORTS ---");
                System.out.println("-> Total students matriculated: " + systemService.countTotalStudents());
                System.out.println("-> Corporate teacher email directories: " + systemService.getAllTeacherEmails());

                systemService.getTeacherWithMaxSalary().ifPresent(topTeacher ->
                        System.out.println("-> Highest earning teacher: " + topTeacher.getName() + " " + topTeacher.getLastName() + " (" + topTeacher.getSalary() + "€)")
                );
                break;

            case 6:
                System.out.println("\n--- Unregister Member ---");
                System.out.print("Enter DNI to remove: "); String targetDni = sc.nextLine();

                systemService.removeStudent(targetDni);
                systemService.removeTeacher(targetDni);
                System.out.println("[Success] Unregistration processed for the specified DNI.");
                break;

            case 7:
                System.out.println("\n[System] Saving current state to records...");
                systemService.saveDataAutomatically();
                System.out.println("Closing the application...");
                break;

            default:
                System.out.println("\n[Warning] Invalid option selection. Please try again.");
        }
    }
}