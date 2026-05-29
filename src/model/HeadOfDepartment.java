package model;

import exceptions.InvalidDniException;
import exceptions.InvalidSalaryException;

/**
 * Represents a Head of Department from School, extending Staff functionalities.
 * @author Nicolás Mingorance Sánchez
 * @version 1.0
 */
public class HeadOfDepartment extends Staff {

    /** The department this person manages */
    private String managedDepartment;

    /** Bonus salary for being the head of the department */
    private double responsibilityBonus;

    /**
     * Constructor for the HeadOfDepartment Class.
     * Automatically assigns the managed department based on the subject taught.
     * @param dni Staff's ID number
     * @param name Staff's Name
     * @param lastName Staff's Last name
     * @param salary Staff's base salary
     * @param subjectTaught Staff's subject
     * @param responsibilityBonus Extra bonus for the position
     * @throws InvalidDniException If the DNI format is incorrect
     * @throws InvalidSalaryException If the base salary is out of bounds
     */
    public HeadOfDepartment(String dni, String name, String lastName,
                            double salary, Subject subjectTaught,
                            double responsibilityBonus) throws InvalidDniException, InvalidSalaryException {

        super(dni, name, lastName, salary, subjectTaught);
        this.responsibilityBonus = responsibilityBonus;
        this.managedDepartment = getDepartment();
    }

    /**
     * Returns the department managed.
     * @return A String containing the department name
     */
    public String getManagedDepartment() {
        return this.managedDepartment;
    }

    /**
     * Returns the responsibility bonus.
     * @return A double representing the bonus amount
     */
    public double getResponsibilityBonus() {
        return this.responsibilityBonus;
    }

    /**
     * Sets or updates the responsibility bonus after validating its value.
     * @param responsibilityBonus The new bonus amount to set
     * @throws IllegalArgumentException If the responsibility bonus is negative
     */
    public void setResponsibilityBonus(double responsibilityBonus) {
        if (responsibilityBonus < 0) {
            throw new IllegalArgumentException("The responsibility bonus cannot be negative.");
        }
        this.responsibilityBonus = responsibilityBonus;
    }

    /**
     * Method that prints the Head of Department's information.
     * @return A formatted string containing all staff details along with managed department and bonus
     */
    @Override
    public String toString() {
        return super.toString() + " | [HEAD OF DEPARTMENT] Manages: " + managedDepartment + " | Bonus: +" + responsibilityBonus + "€";
    }
}