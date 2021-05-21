package com.selflearning.englishcourses.controller.advice;

import com.selflearning.englishcourses.service.error.FieldValidationError;
import com.selflearning.englishcourses.service.error.FieldValidationErrorDetails;
import com.selflearning.englishcourses.service.error.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestControllerAdvice
public class RestValidationHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<FieldValidationErrorDetails> handleValidationError(
            MethodArgumentNotValidException exception, HttpServletRequest request) {
        FieldValidationErrorDetails errorDetails = new FieldValidationErrorDetails();
        errorDetails.setTimestamp(new Date().getTime());
        errorDetails.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDetails.setTitle("Field Validation Error");
        errorDetails.setDetail("Input Field Validation Failed");
        errorDetails.setDeveloperMessage(exception.getClass().getName());
        errorDetails.setPath(request.getRequestURI());

        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        for (FieldError error : fieldErrors) {
            FieldValidationError fieldError = processFieldError(error);
            List<FieldValidationError> validationErrors = errorDetails.getErrors().get(error.getField());
            if (validationErrors == null) {
                validationErrors = new ArrayList<FieldValidationError>();
            }
            validationErrors.add(fieldError);
            errorDetails.getErrors().put(error.getField(), validationErrors);
        }
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    // method to process field errors
    private FieldValidationError processFieldError(final FieldError error) {
        FieldValidationError fieldValidationError = new FieldValidationError();
        if (error != null) {
            Locale locale = LocaleContextHolder.getLocale();
            String message = messageSource.getMessage(error.getDefaultMessage(), null, locale);
            fieldValidationError.setField(error.getField());
            fieldValidationError.setMessageType(MessageType.ERROR);
            fieldValidationError.setMessage(message);
        }
        return fieldValidationError;
    }
}
