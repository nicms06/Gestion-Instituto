package exceptions;

public class InvalidCourseException extends SchoolException {
    public InvalidCourseException()
    {
        super("Invalid course (1 - 6).");
    }
}
