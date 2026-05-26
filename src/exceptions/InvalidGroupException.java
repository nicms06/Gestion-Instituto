package exceptions;

public class InvalidGroupException extends SchoolException {
    public InvalidGroupException() {
        super("Invalid group (A, B or C)");
    }
}
