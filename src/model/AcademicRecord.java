package model;

/**
 * Represents the Academic Record of a student, containing their general profile and remarks.
 * @author Nicolás Mingorance Sánchez
 * @version 1.0
 */
public class AcademicRecord {

    /** The student associated with this academic record */
    private Student student;

    /** General remarks or behavioral observations about the student */
    private String observations;

    /**
     * Constructor for the AcademicRecord Class.
     * @param student The Student object to link with this record
     * @param observations The initial academic or behavioral remarks
     */
    public AcademicRecord(Student student, String observations) {
        this.student = student;
        this.observations = observations;
    }

    /**
     * Returns the student associated with this record.
     * @return The student object
     */
    public Student getStudent() {
        return this.student;
    }

    /**
     * Returns the academic observations.
     * @return A string containing the remarks
     */
    public String getObservations() {
        return this.observations;
    }

    /**
     * Sets or updates the academic observations.
     * @param observations The new remarks to set
     */
    public void setObservations(String observations) {
        this.observations = observations;
    }

    /**
     * Method that prints the academic record's complete details.
     * @return A string containing the student's name, their registered grades, and observations
     */
    @Override
    public String toString() {
        return "Academic Record of: " + student.getName() + " | Grades: " + student.getGrades() + " | Observations: " + observations;
    }
}