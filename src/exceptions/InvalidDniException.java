package exceptions;

public class InvalidDniException extends SchoolException {
    public InvalidDniException() {
        super("Invalid ID Format.");
    }
}
