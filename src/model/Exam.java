package model;

import java.time.LocalDate;

/**
 * Represents an Exam from School, storing its subject and scheduling date.
 * @author Nicolás Mingorance Sánchez
 * @version 1.0
 */
public class Exam {

    /** The name of the subject this exam belongs to */
    private String subjectName;

    /** The scheduled date of the exam */
    private LocalDate examDate;

    /**
     * Constructor for the Exam Class.
     * Initializes the exam date using the LocalDate factory method taught in class.
     * @param subjectName The name of the subject
     * @param year The year of the exam date
     * @param month The month of the exam date
     * @param day The day of the month of the exam date
     */
    public Exam(String subjectName, int year, int month, int day) {
        this.subjectName = subjectName;
        this.examDate = LocalDate.of(year, month, day);
    }

    /**
     * Returns the name of the subject.
     * @return A string containing the subject name
     */
    public String getSubjectName() {
        return this.subjectName;
    }

    /**
     * Returns the scheduled exam date.
     * @return The LocalDate object of the exam
     */
    public LocalDate getExamDate() {
        return this.examDate;
    }

    /**
     * Sets or updates the subject name of the exam.
     * @param subjectName The new subject name to set
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    /**
     * Sets or updates the exam date.
     * @param examDate The new LocalDate to set
     */
    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }

    /**
     * Method that prints the exam's information.
     * @return A formatted string containing the subject and the date
     */
    @Override
    public String toString() {
        return "EXAM | Subject: " + subjectName + " | Date: " + examDate.toString();
    }
}