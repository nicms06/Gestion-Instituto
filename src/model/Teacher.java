package model;

import exceptions.InvalidDniException;
import exceptions.InvalidSalaryException;

/**
 * Represents a Teacher from School
 * @author Nicolás Mingorance Sánchez
 * @version 1.0
 */
public class Teacher extends Staff{

    /** Teacher's Tutored Group and Course */
    private Course tutoredGroup;

    /**
     *
     * @param dni Teacher's DNI
     * @param name Teacher's Name
     * @param lastName Teacher's Last Name
     * @param salary Teacher's Salary
     * @param subjectTaught Teacher's Subject
     * @param tutoredGroup Teacher's Group
     * @throws InvalidDniException If the DNI is null, does not have 9 characters,
     *      or does not follow the format (8 digits + 1 letter)
     * @throws InvalidSalaryException if the salary isn´t between MIN_SALARY and MAX_SALARY
     */
    public Teacher(String dni, String name, String lastName,
                   double salary, Subject subjectTaught,
                   Course tutoredGroup) throws InvalidDniException, InvalidSalaryException {
        super(dni, name, lastName, salary, subjectTaught);
        this.tutoredGroup = tutoredGroup;
    }

    /**
     * Method that prints a student's information
     * @return A string containing the ID, name, last name, email, department and salary
     */
    @Override
    public String toString(){
        return super.toString() + " | TutoredGroup: " + tutoredGroup;
    }

}