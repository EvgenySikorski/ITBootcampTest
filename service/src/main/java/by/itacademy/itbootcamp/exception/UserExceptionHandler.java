package by.itacademy.itbootcamp.exception;


import by.itacademy.itbootcamp.errors.ErrorResponse;
import by.itacademy.itbootcamp.errors.StructuredErrorResponse;
import by.itacademy.itbootcamp.exception.enums.EErrorType;
import by.itacademy.itbootcamp.exception.exceptions.IncorrectDataException;
import by.itacademy.itbootcamp.exception.exceptions.MailAlreadyExistsException;
import by.itacademy.itbootcamp.exception.exceptions.NotValidBodyException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(MailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleMailError(MailAlreadyExistsException exception){
        ErrorResponse response = new ErrorResponse(EErrorType.ERROR, "Пользователь с такой почтой уже существует: ");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotValidBodyException.class)
    public ResponseEntity<?> handlerNotValidBodyError(NotValidBodyException ex) {
        Map<String, String> errors = ex.getErrors();
        StructuredErrorResponse response = new StructuredErrorResponse(EErrorType.STRUCTURED_ERROR, errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StructuredErrorResponse> handleInvalidArgument(ConstraintViolationException violationException){
        StructuredErrorResponse response = new StructuredErrorResponse(EErrorType.STRUCTURED_ERROR, new HashMap<>());
        violationException.getConstraintViolations().stream().forEach(violation -> {
            response.getErrors().put(violation.getPropertyPath().toString(), violation.getMessage());
        });

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncorrectDataException.class)
    public ResponseEntity<ErrorResponse> handleIncorrectDataError(IncorrectDataException exception){
        ErrorResponse response = new ErrorResponse(EErrorType.ERROR, "Введены некорректные данные");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({
            IOException.class,
            Error.class
    })
    public ResponseEntity<ErrorResponse> handleInnerError(){
        ErrorResponse response = new ErrorResponse(EErrorType.ERROR, "Внутренняя ошибка сервера. " +
                "Сервер не смог корректно обработать запрос. Пожалуйста обратитесь к администратору");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
