package com.example.accountuser.exception;

public class BadRequestException extends ExecutionFailureException {
  public BadRequestException(String fieldName, String message) {
    super(fieldName, message);
  }
}
