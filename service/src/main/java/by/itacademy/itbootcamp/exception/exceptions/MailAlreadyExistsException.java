package by.itacademy.itbootcamp.exception.exceptions;

public class MailAlreadyExistsException extends RuntimeException {

    private String email;

    public MailAlreadyExistsException(String email) {
        this.email = email;
    }

    public MailAlreadyExistsException(String message, String email) {
        super(message);
        this.email = email;
    }

    public MailAlreadyExistsException(String message, Throwable cause, String email) {
        super(message, cause);
        this.email = email;
    }

    public MailAlreadyExistsException(Throwable cause, String email) {
        super(cause);
        this.email = email;
    }

    public MailAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String email) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
