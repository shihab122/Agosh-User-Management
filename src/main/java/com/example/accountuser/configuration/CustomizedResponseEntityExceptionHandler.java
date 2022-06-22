package com.example.accountuser.configuration;

import com.example.accountuser.exception.BadRequestException;
import com.example.accountuser.exception.EntityNotFoundException;
import com.example.accountuser.exception.ExecutionFailureException;
import com.example.accountuser.util.BaseResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  public final ResponseEntity handleNotFoundExceptions(ExecutionFailureException ex, WebRequest request) {
    BaseResponse response = new BaseResponse();
    response.setStatusCode(HttpStatus.NOT_FOUND.value());
    response.setStatusMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
    response.setData(ex.getMessage());
    response.setFieldName(ex.getFieldName());
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(BadRequestException.class)
  public final ResponseEntity handleBadRequestExceptions(ExecutionFailureException ex, WebRequest request) {
    BaseResponse response = new BaseResponse();
    response.setStatusCode(HttpStatus.BAD_REQUEST.value());
    response.setStatusMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
    response.setData(ex.getMessage());
    response.setFieldName(ex.getFieldName());
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public final ResponseEntity handle(DataIntegrityViolationException e) {
    BaseResponse response = new BaseResponse();
    String detailMessage = e.getCause().getCause().getMessage();
    int firstIndex = detailMessage.indexOf("(");
    int secondIndex = detailMessage.indexOf(")", firstIndex + 1);
    int thirdIndex = detailMessage.indexOf("(", secondIndex + 1);
    response.setStatusCode(HttpStatus.BAD_REQUEST.value());
    response.setStatusMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
    response.setFieldName(detailMessage.substring(firstIndex + 1, secondIndex));
    response.setData(detailMessage.substring(thirdIndex + 1, detailMessage.length() - 1).replaceAll("\\)", ""));
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public final ResponseEntity handleExceptions(Exception ex, WebRequest request) {
    BaseResponse response = new BaseResponse();
    response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    response.setStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    response.setData(ex.getMessage());
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
