package com.wanted.wantedlab.global.exception;

import com.wanted.wantedlab.dto.exception.JobPostExceptionInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class ErrorResponse {
  private String message;
  private String exceptionCode;
  private HttpStatus httpStatus;
}
