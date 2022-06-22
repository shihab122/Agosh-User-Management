package com.example.accountuser.exception;

public class EntityNotFoundException extends ExecutionFailureException {
  public EntityNotFoundException(String fieldName, String message) {
    super(fieldName, message);
  }
}
