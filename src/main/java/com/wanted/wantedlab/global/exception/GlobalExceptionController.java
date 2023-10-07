package com.wanted.wantedlab.global.exception;

import com.wanted.wantedlab.dto.exception.JobPostException;
import com.wanted.wantedlab.dto.exception.UserException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {
  @ExceptionHandler(JobPostException.class)
  public ResponseEntity<ErrorResponse> postException(JobPostException e){
    ErrorResponse errorResponse = new ErrorResponse(e.getMessage(),e.getExceptionInfo().getCode(),e.getHttpStatus());
    return new ResponseEntity<>(errorResponse,errorResponse.getHttpStatus());
  }
  @ExceptionHandler(UserException.class)
  public ResponseEntity<ErrorResponse> userException(UserException e){
    ErrorResponse errorResponse = new ErrorResponse(e.getMessage(),e.getExceptionInfo().getCode(),e.getHttpStatus());
    return new ResponseEntity<>(errorResponse,errorResponse.getHttpStatus());
  }
}
