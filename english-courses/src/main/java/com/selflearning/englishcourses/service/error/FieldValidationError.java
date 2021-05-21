package com.selflearning.englishcourses.service.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FieldValidationError {

    private String field;
    private String message;
    private MessageType messageType;

}
