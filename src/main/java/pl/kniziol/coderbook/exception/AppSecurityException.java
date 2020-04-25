package pl.kniziol.coderbook.exception;

public class AppSecurityException extends RuntimeException {

    public AppSecurityException(String message) {
        super(message);
    }
}
