package by.itacademy.itbootcamp.errors;


import by.itacademy.itbootcamp.exception.enums.EErrorType;

public class ErrorResponse {
    private EErrorType logref;
    private String message;

    public ErrorResponse() {
    }

    public ErrorResponse(EErrorType logref, String message) {
        this.logref = logref;
        this.message = message;
    }

    public EErrorType getLogref() {
        return logref;
    }

    public void setLogref(EErrorType logref) {
        this.logref = logref;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
