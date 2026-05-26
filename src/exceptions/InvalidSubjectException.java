package exceptions;

public class InvalidSubjectException extends SchoolException {
    public InvalidSubjectException() {
        super("Invalid subject name.");
    }
}
