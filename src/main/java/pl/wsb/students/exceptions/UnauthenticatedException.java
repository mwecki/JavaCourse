package pl.wsb.students.exceptions;

public class UnauthenticatedException extends Exception {
    public UnauthenticatedException(String message) {
        super(message);
    }
    public UnauthenticatedException() {
        super("Unauthenticated access...");
    }
}
