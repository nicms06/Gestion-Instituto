package model;

import exceptions.InvalidDniException;
import interfaces.Exportable;

import java.util.HashMap;

/**
 * Represents a student from School
 * @author Nicolás Mingorance Sánchez
 * @version 1.0
 */
public class Student extends Person implements Comparable<Student>, Exportable {

    /** Student's Course */
    private Course course;

    /** Student's Grades ("Subject": "mark") */
    private HashMap<Subject, Double> grades;

    /**
     * Constructor for Student Class
     * @param dni Student's ID number
     * @param name Student's name
     * @param lastName Student's last name
     * @throws InvalidDniException If the DNI is null, does not have 9 characters,
     *      or does not follow the format (8 digits + 1 letter)
     */
    public Student(String dni, String name, String lastName) throws InvalidDniException {
        super(dni, name, lastName);
        this.grades = new HashMap<>();
        generateEmail();
    }

    /**
     * Returns the course
     * @return The course of the student
     */
    public Course getCourse() { return this.course; }

    /**
     * Sets the course
     * @param course we are setting
     */
    public void setCourse(Course course) {
        this.course = course;
    }

    /**
     * Returns the grades
     * @return The grades of the student
     */
    public HashMap<Subject, Double> getGrades() { return this.grades; }

    /**
     * Adds a grade for a specific subject.
     * @param subject The subject to grade.
     * @param mark The score obtained (must be between 0 and 10).
     * @throws IllegalArgumentException if the subject is null or grade is out of range.
     * @throws IllegalStateException if the subject already has an assigned grade.
     */
    public void addGrade(Subject subject, double mark){
        checkSubjectNotNull(subject);

        if (this.grades.containsKey(subject)) {
            throw new IllegalStateException("The student already has a grade for: " + subject.getName() + ".");
        }

        if (mark > 10 || mark < 0){
            throw new IllegalArgumentException("The mark must be between 1 and 10.");
        }

        this.grades.put(subject, mark);
    }

    /**
     * Deletes a grade for a specific subject.
     * @param subject The subject to delete.
     * @throws IllegalArgumentException if the student is not enrolled in the subject
     */
    public void deleteGrade(Subject subject){
        checkSubjectNotNull(subject);

        checkSubjectExists(subject);

        this.grades.remove(subject);

    }

    /**
     * Updates a mark for a specific subject
     * @param subject The subject to update.
     * @param mark The new mark
     * @throws IllegalArgumentException if the mark isn't between 0 and 10
     */
    public void updateGrade(Subject subject, double mark){
        checkSubjectNotNull(subject);

        checkSubjectExists(subject);

        if (mark < 0 || mark > 10) throw new IllegalArgumentException("Grade must be between 0-10");

        this.grades.put(subject, mark);

    }

    /**
     * Validates that the given subject is not null
     * @param subject The subject object to check
     * @throws IllegalArgumentException If the subject is null.
     */
    private static void checkSubjectNotNull(Subject subject){
        if (subject == null) {
            throw new IllegalArgumentException("Subject cannot be null.");
        }
    }

    /**
     * Validates that the student is actually enrolled in the given subject.
     * * @param subject The subject to check in the student's grades.
     * @throws IllegalArgumentException If the subject is not found in the map.
     */
    private void checkSubjectExists(Subject subject){
        if(!this.grades.containsKey(subject)){
            throw new IllegalArgumentException("Cannot remove: The student is not enrolled in " + subject.getName());
        }
    }

    /**
     * Generates the student's email address using the format: name.lastname@school.com.
     * All spaces are removed and characters are converted to lowercase.
     */
    @Override
    public void generateEmail(){
        this.email = (name + "." + lastName).toLowerCase().replace(" ", "") + "@school.com";
    }

    /**
     * Method that prints a student's information
     * @return A string containing the ID, name, last name, email, course, group and grades
     */
    @Override
    public String toString(){
        return super.toString() + "\nCourse: " + course + "\n" + grades;
    }

    /**
     * Compares this student with another based on their DNI.
     * @param other The student to be compared.
     * @return a negative integer, zero, or a positive integer as this student
     * is less than, equal to, or greater than the specified student.
     */
    @Override
    public int compareTo(Student other) {
        return this.dni.compareTo(other.getDni());
    }

    /**
     * Converts the student object into a formatted CSV line.
     * @return A semicolon-separated String with the student's data
     */
    @Override
    public String toCSV() {
        return "STUDENT;" + getDni() + ";" + getName() + ";" + getLastName();
    }
}