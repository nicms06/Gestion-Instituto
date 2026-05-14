package model;

import exceptions.InvalidCourseException;
import exceptions.InvalidDniException;
import exceptions.InvalidGroupException;

import java.util.HashMap;

/**
 * Represents a student from School
 * @author Nicolás Mingorance Sánchez
 * @version 1.0
 */
public class Student extends Person implements Comparable<Student>{

    /** Student's Course (from 1º to 6º)*/
    private int course;

    /** Student's Group (A, B or C)*/
    private char group;

    /** Student's Grades ("Subject": "mark") */
    private HashMap<Subject, Double> grades;

    public Student(String dni, String name, String lastName, int course, char group) throws InvalidDniException {
        super(dni, name, lastName);
        setCourse(course);
        setGroup(group);
        this.grades = new HashMap<>();
        generateEmail();
    }

    /**
     * Returns the course
     * @return The course of the student
     */
    public int getCourse() { return this.course; }

    /**
     * Returns the group
     * @return The group of the student
     */
    public char getGroup() { return this.group; }

    /**
     * Returns the grades
     * @return The grades of the student
     */
    public HashMap<Subject, Double> getGrades() { return this.grades; }

    /**
     * Sets the course after validating the value
     * @param course The course int to set (must be between 1 and 6)
     * @throws InvalidCourseException if the course isn't between 1 and 6
     */
    public void setCourse(int course) throws InvalidCourseException {
        if (course > 6 || course < 1){
            throw new InvalidCourseException();
        }

        this.course = course;
    }

    /**
     * Sets the group after validating the value
     * @param group The group char to set (Must be A, B or C)
     * @throws InvalidGroupException if the group isn't A, B or C
     */
    public void setGroup(char group) throws InvalidGroupException {
        char[] groups = {'A', 'B', 'C'};
        for (char letter : groups){
            if (Character.toUpperCase(group) == letter){
                this.group = letter;
                return;
            }
        }

        throw new InvalidGroupException();
    }

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
        return super.toString() + "\nCourse: " + course + "º" + group + "\n" + grades;
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
}