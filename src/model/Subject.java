package model;

import exceptions.InvalidSubjectException;

/**
 * Represents a Subject from School
 * @author Nicolás Mingorance Sánchez
 * @version 1.0
 */
public class Subject{
    /** Subject's name */
    private String name;

    /**
     * Constructor for the Subject class
     * @param name Subject's name
     * @throws InvalidSubjectException If the name isn't Math, Science, History, English, Biology or Physics
     */
    public Subject(String name) throws InvalidSubjectException{
        setName(name);
    }

    /**
     * Returns the name
     * @return The name of the Subject
     */
    public String getName(){ return this.name; }

    /**
     * Sets the subject's name after validating it
     * @param subjectName The name string to set (Must be Math, Science, History, English, Biology or Physics)
     * @throws InvalidSubjectException If the name isn't Math, Science, History, English, Biology or Physics
     */
    public void setName(String subjectName) throws InvalidSubjectException {
        String[] subjects = {"Math", "Science", "History", "English", "Biology", "Physics"};

        boolean isValidSubject = false;

        for (String name : subjects){
            if (name.equalsIgnoreCase(subjectName)){
                this.name = name;
                return;
            }
        }

        throw new InvalidSubjectException();

    }

}
