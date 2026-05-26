package model;

import exceptions.InvalidCourseException;
import exceptions.InvalidGroupException;

public class Course {
    /** Student's Course (from 1º to 6º)*/
    private int course;

    /** Student's Group (A, B or C)*/
    private char group;

    /**
     * Constructor for Course Class
     * @param course Course's number (1-6)
     * @param group Course's letter (A-C)
     */
    public Course(int course, char group) throws InvalidCourseException, InvalidGroupException{
        setCourse(course);
        setGroup(group);
    }

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
     * Method that prints the course
     * @return A String containing the course and the group
     */
    @Override
    public String toString(){
        return course + "º-" + group;
    }
}
