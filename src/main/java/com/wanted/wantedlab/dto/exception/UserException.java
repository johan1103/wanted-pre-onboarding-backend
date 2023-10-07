package com.wanted.wantedlab.dto.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class UserException extends RuntimeException{
  private String message;
  private UserExceptionInfo exceptionInfo;
  private HttpStatus httpStatus;
}
