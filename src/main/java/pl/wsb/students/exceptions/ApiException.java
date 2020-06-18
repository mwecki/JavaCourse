package pl.wsb.students.exceptions;

public class ApiException extends Exception {
    public ApiException(String message) {
        super(message);
    }
    public ApiException() {
        super("Api unexpected error...");
    }
}
