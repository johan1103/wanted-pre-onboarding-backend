package com.wanted.wantedlab.global.exception;

import com.wanted.wantedlab.dto.exception.JobPostException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {
  @ExceptionHandler(JobPostException.class)
  public ResponseEntity<JobPostException> postException(JobPostException e){
    return new ResponseEntity<>(e,e.getHttpStatus());
  }
}
