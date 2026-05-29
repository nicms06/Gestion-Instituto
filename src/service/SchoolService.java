package service;

import model.Course;
import model.Student;
import model.Subject;
import model.Teacher;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class that manages school operations, persistence, and statistics using Streams.
 * @author Nicolás Mingorance Sánchez
 * @version 1.0
 */
public class SchoolService {
    /** Map containing all students indexed by their DNI */
    private HashMap<String, Student> students;

    /** Map containing all teachers indexed by their DNI */
    private HashMap<String, Teacher> teachers;

    /** Path to the data file as specified in the guidelines */
    private final String filename = "resources/data.csv";

    /**
     * Default constructor for SchoolService.
     * Initializes the data structures.
     */
    public SchoolService() {
        this.students = new HashMap<>();
        this.teachers = new HashMap<>();
    }

    /**
     * Automatically loads students and teachers data from the CSV file.
     * Follows the structure taught in Unit 6 using BufferedReader and FileReader.
     */
    public void loadDataAutomatically() {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(filename));
            String line;

            while ((line = in.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length < 4) continue;

                String type = data[0];
                String dni = data[1];
                String name = data[2];
                String lastName = data[3];

                if (type.equals("STUDENT")) {
                    students.put(dni, new Student(dni, name, lastName));
                } else if (type.equals("TEACHER")) {
                    double salary = Double.parseDouble(data[4]);
                    Subject subject = new Subject("Math");
                    teachers.put(dni, new Teacher(dni, name, lastName, salary, subject, new Course(1, 'A')));
                }
            }
            System.out.println("[System] File loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("[System] Data file not found. A new one will be created upon exit.");
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error loading data: " + e.getMessage());
        } finally {
            try {
                if (in != null) in.close();
            } catch (IOException e) {
                System.out.println("Error closing the input stream.");
            }
        }
    }

    /**
     * Automatically saves all registered students and teachers into the CSV file.
     * Follows the structure taught in Unit 6 using BufferedWriter, FileWriter, and newLine().
     */
    public void saveDataAutomatically() {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter(filename));

            for (Student s : students.values()) {
                out.write(s.toCSV());
                out.newLine();
            }

            for (Teacher t : teachers.values()) {
                out.write(t.toCSV());
                out.newLine();
            }

            out.flush();
            System.out.println("[System] Data automatically saved to recursos/datos.csv.");
        } catch (IOException e) {
            System.out.println("Error writing to the file: " + e.getMessage());
        } finally {
            try {
                if (out != null) out.close();
            } catch (IOException e) {
                System.out.println("Error closing the output stream.");
            }
        }
    }

    /**
     * Filters the student collection by their last name using Stream API.
     * @param lastName The last name to search for
     * @return A List of students matching the last name
     */
    public List<Student> filterStudentsByLastName(String lastName) {
        return students.values().stream()
                .filter(s -> s.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }

    /**
     * Extracts a list of all teacher emails using Stream API.
     * @return A List of String containing all teacher email addresses
     */
    public List<String> getAllTeacherEmails() {
        return teachers.values().stream()
                .map(Teacher::getEmail)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a list of teachers sorted by their salary in descending order using Stream API.
     * @return A sorted List of teachers
     */
    public List<Teacher> getTeachersSortedBySalary() {
        return teachers.values().stream()
                .sorted((t1, t2) -> Double.compare(t2.getSalary(), t1.getSalary()))
                .collect(Collectors.toList());
    }

    /**
     * Counts the total number of students registered in the system using Stream API.
     * @return The total number of students as a long value
     */
    public long countTotalStudents() {
        return students.values().stream().count();
    }

    /**
     * Finds the teacher with the highest salary using Stream API.
     * @return An Optional containing the teacher with the maximum salary, or empty if none
     */
    public Optional<Teacher> getTeacherWithMaxSalary() {
        return teachers.values().stream()
                .max(Comparator.comparingDouble(Teacher::getSalary));
    }

    /**
     * Registers a new student into the service map.
     * @param s The Student object to add
     */
    public void registerStudent(Student s) {
        students.put(s.getDni(), s);
    }

    /**
     * Registers a new teacher into the service map.
     * @param t The Teacher object to add
     */
    public void registerTeacher(Teacher t) {
        teachers.put(t.getDni(), t);
    }

    /**
     * Searches for a student by their DNI.
     * @param dni The DNI string to look for
     * @return The Student object if found, null otherwise
     */
    public Student findStudent(String dni) {
        return students.get(dni);
    }

    /**
     * Removes a student from the system using their DNI.
     * @param dni The DNI of the student to remove
     */
    public void removeStudent(String dni) {
        students.remove(dni);
    }

    /**
     * Removes a teacher from the system using their DNI.
     * @param dni The DNI of the teacher to remove
     */
    public void removeTeacher(String dni) {
        teachers.remove(dni);
    }

    /**
     * Returns an unmodifiable view or direct collection of all registered students.
     * @return A Collection of all students
     */
    public Collection<Student> getAllStudents() {
        return students.values();
    }
}