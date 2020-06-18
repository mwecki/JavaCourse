package pl.wsb.students.exceptions;

public class ValidationException extends Exception {
    public ValidationException(String message) {
        super(message);
    }
    public ValidationException() {
        super("Validation error...");
    }
}
