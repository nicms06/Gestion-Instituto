package exceptions;

public class InvalidGroupException extends Error {
    public InvalidGroupException() {
        super("Invalid group (A, B or C)");
    }
}
