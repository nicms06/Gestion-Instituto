package exceptions;

public class InvalidCourseException extends Error {
    public InvalidCourseException()
    {
        super("Invalid course (1 - 6).");
    }
}
