package ics314.dezesseis.calendar.exceptions;

public class ScopeException extends RuntimeException {
    public ScopeException() {
        super();
    }
    public ScopeException(String message) {
        super(message);
    }
    public ScopeException(String message, Throwable cause) {
        super(message, cause);
    }
    public ScopeException(Throwable cause) {
        super(cause);
    }
}
