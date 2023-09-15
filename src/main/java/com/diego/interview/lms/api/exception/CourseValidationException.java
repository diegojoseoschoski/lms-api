package com.diego.interview.lms.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class CourseValidationException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public CourseValidationException(String message) {
        super(message);
    }
}
