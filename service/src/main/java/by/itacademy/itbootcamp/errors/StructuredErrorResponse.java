package by.itacademy.itbootcamp.errors;


import by.itacademy.itbootcamp.exception.enums.EErrorType;

import java.util.Map;

public class StructuredErrorResponse {

    private EErrorType logref;
    private Map<String, String> errors;

    public StructuredErrorResponse() {
    }

    public StructuredErrorResponse(EErrorType logref, Map<String, String> errors) {
        this.logref = logref;
        this.errors = errors;
    }

    public EErrorType getLogref() {
        return logref;
    }

    public void setLogref(EErrorType logref) {
        this.logref = logref;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
