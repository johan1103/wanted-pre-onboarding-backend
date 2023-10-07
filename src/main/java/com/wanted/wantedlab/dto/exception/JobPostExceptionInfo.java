package com.wanted.wantedlab.dto.exception;

public enum JobPostExceptionInfo {
  INVALID_COMPANY("JOBPOST-001"),INVALID_JOBPOSTID("JOBPOST-002");

  private final String code;
  JobPostExceptionInfo(String code){
    this.code=code;
  }
  public String getCode(){
    return code;
  }
}
