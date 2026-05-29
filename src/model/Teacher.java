package model;

import exceptions.InvalidDniException;
import exceptions.InvalidSalaryException;
import interfaces.Exportable;

/**
 * Represents a Teacher from School.
 * @author Nicolás Mingorance Sánchez
 * @version 1.0
 */
public class Teacher extends Staff implements Exportable {

    /** Teacher's Tutored Group and Course */
    private Course tutoredGroup;

    /**
     * Constructor for the Teacher Class.
     * @param dni Teacher's DNI
     * @param name Teacher's Name
     * @param lastName Teacher's Last Name
     * @param salary Teacher's Salary
     * @param subjectTaught Teacher's Subject
     * @param tutoredGroup Teacher's Group
     * @throws InvalidDniException If the DNI is null, does not have 9 characters,
     * or does not follow the format (8 digits + 1 letter)
     * @throws InvalidSalaryException if the salary isn't between MIN_SALARY and MAX_SALARY
     */
    public Teacher(String dni, String name, String lastName,
                   double salary, Subject subjectTaught,
                   Course tutoredGroup) throws InvalidDniException, InvalidSalaryException {
        super(dni, name, lastName, salary, subjectTaught);
        this.tutoredGroup = tutoredGroup;
    }

    /**
     * Method that prints a teacher's information.
     * @return A string containing the ID, name, last name, email, department, salary and tutored group
     */
    @Override
    public String toString(){
        return super.toString() + " | TutoredGroup: " + tutoredGroup;
    }

    /**
     * Converts the teacher object into a formatted CSV line.
     * @return A String with the information
     */
    @Override
    public String toCSV() {
        return "TEACHER;" + getDni() + ";" + getName() + ";" + getLastName() + ";" + getSalary();
    }
}