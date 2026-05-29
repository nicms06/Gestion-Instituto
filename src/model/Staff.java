package model;

import exceptions.InvalidDniException;
import exceptions.InvalidSalaryException;

/**
 * Represents Staff from School
 * @author Nicolás Mingorance Sánchez
 * @version 1.0
 */
public class Staff extends Person{

    /** Maximum Salary */
    public static final double MIN_SALARY = 1200;

    /** Minimum Salary */
    public static final double MAX_SALARY = 2200;

    /** Staff's Salary */
    private double salary;

    /** Staff's Subject*/
    private Subject subjectTaught;

    /** Staff's Department*/
    private String department;

    /**
     * Constructor for the Staff Class
     * @param dni Staff's ID number
     * @param name Staff's Name
     * @param lastName Staff's Last name
     * @param salary Staff's salary
     * @param subjectTaught Staff's subject
     * @throws InvalidDniException If the DNI is null, does not have 9 characters,
     *      or does not follow the format (8 digits + 1 letter)
     * @throws InvalidSalaryException if the salary isn´t between MIN_SALARY and MAX_SALARY
     */
    public Staff(String dni, String name, String lastName,
                 double salary, Subject subjectTaught) throws InvalidDniException, InvalidSalaryException {
        super(dni, name, lastName);
        this.subjectTaught = subjectTaught;
        generateEmail();
        setDepartment();
        setSalary(salary);
    }

    /**
     * Returns the Department
     * @return The Department of the Staff
     */
    public String getDepartment(){ return this.department; }

    /**
     * Returns the Salary
     * @return The Salary of the Staff
     */
    public double getSalary(){ return this.salary; }

    /**
     * Sets the salary after validating the value
     * @param salary The salary double to set (must be between MIN_SALARY and MAX_SALARY)
     * @throws InvalidSalaryException if the salary isn´t between MIN_SALARY and MAX_SALARY
     */
    public void setSalary(double salary) throws InvalidSalaryException {
        if (salary > MAX_SALARY || salary < MIN_SALARY){
            throw new InvalidSalaryException();
        }

        this.salary = salary;
    }

    /**
     * Sets the department's name depending on the subject taught
     */
    public void setDepartment(){
        if (this.subjectTaught.getName().equalsIgnoreCase("Math")){

            this.department = "Mathematics";

        } else if (this.subjectTaught.getName().equalsIgnoreCase("Science")
                || this.subjectTaught.getName().equalsIgnoreCase("Biology")
                || this.subjectTaught.getName().equalsIgnoreCase("Physics")){

            this.department = "Sciences";

        } else if (this.subjectTaught.getName().equalsIgnoreCase("History")) {

            this.department = "Humanities";

        } else if (this.subjectTaught.getName().equalsIgnoreCase("English")){

            this.department = "Languages";
        }
    }

    /**
     * Generates the student's email address using the format: name.lastname@schoolteacher.com.
     * All spaces are removed and characters are converted to lowercase.
     */
    @Override
    public void generateEmail() {
        this.email = (name + "." + lastName).toLowerCase().replace(" ", "") + "@schoolteacher.com";
    }

    /**
     * Method that prints a student's information
     * @return A string containing the ID, name, last name, email, department and salary
     */
    @Override
    public String toString(){
        return super.toString() + "\nDepartment: " + department + " | Subject Taught: " + subjectTaught.getName() + " | Salary: " + salary;    }
    }