package exceptions;

public class InvalidSubjectException extends Exception {
    public InvalidSubjectException() {
        super("Invalid subject name.");
    }
}
