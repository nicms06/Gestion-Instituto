package exceptions;

public class InvalidDniException extends Exception {
    public InvalidDniException() {
        super("Invalid ID Format.");
    }
}
