package model;

import exceptions.InvalidDniException;
import interfaces.IEmailGenerator;

/**
 * Represents a person from School
 * @author Nicolás Mingorance Sánchez
 * @version 1.0
 */
public abstract class Person implements IEmailGenerator {
    /** Person's ID number */
    protected String dni;

    /** Person's Name */
    protected String name;

    /** Person's Last Name */
    protected String lastName;

    /** Person's Email */
    protected String email;

    /**
     * Constructor for the Person Class
     * @param dni Person's ID number
     * @param name Person's Name
     * @param lastName Person's Last Name
     * @throws InvalidDniException If the DNI is null, does not have 9 characters,
     * or does not follow the format (8 digits + 1 letter)
     */
    public Person(String dni, String name, String lastName) throws InvalidDniException{
        setDni(dni);
        this.name = name;
        this.lastName = lastName;
    }

    /**
     * Returns the document number (DNI)
     * @return The DNI of the person
     */
    public String getDni(){ return this.dni; }

    /**
     * Returns the Person's name
     * @return The name of the person
     */
    public String getName(){ return this.name; }

    /**
     * Returns the Person's Last Name
     * @return The Last Name of the Person
     */
    public String getLastName() { return this.lastName; }

    /**
     * Returns the Person's Email
     * @return The Email of the Person
     */
    public String getEmail() { return this.email; }

    /**
     * Sets the person's DNI after validating its format
     * @param dni The dni string to set (must be 8 digits followed by a letter)
     * @throws InvalidDniException If the DNI is null, its length is not 9 or the format is incorrect
     */
    public void setDni(String dni) throws InvalidDniException {

        if (dni == null || dni.length() != 9){
            throw new InvalidDniException();
        }

        for (int dniIndex = 0; dniIndex < 8; dniIndex++){
            if (!Character.isDigit(dni.charAt(dniIndex))){
                throw new InvalidDniException();
            }
        }

        if (!Character.isLetter(dni.charAt(8))){
            throw new InvalidDniException();
        }

        this.dni = dni;

    }

    /**
     * Method that generates the email of the person
     */
    @Override
    public abstract void generateEmail();

    /**
     * Method that prints a person's information
     * @return A string containing the ID, name, last name and email
     */
    @Override
    public String toString(){
        return "DNI: " + dni + " | Name: " + name + " | Last Name: " + lastName + " | Email: " + email;
    }
}