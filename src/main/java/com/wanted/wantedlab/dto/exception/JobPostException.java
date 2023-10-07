package com.wanted.wantedlab.dto.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class JobPostException extends RuntimeException{
  private String message;
  private JobPostExceptionInfo exceptionInfo;
  private HttpStatus httpStatus;
}
