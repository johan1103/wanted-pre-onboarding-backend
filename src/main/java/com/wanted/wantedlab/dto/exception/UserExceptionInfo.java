package com.wanted.wantedlab.dto.exception;

public enum UserExceptionInfo {
  INVALID_USERID("USER-001");
  private final String code;
  UserExceptionInfo(String code){
    this.code=code;
  }
  public String getCode(){
    return code;
  }
}
