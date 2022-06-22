package com.example.accountuser.exception;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Data
public class ExecutionFailureException extends Exception {

  private static final long serialVersionUID = 2909566653236171691L;

  private static final Logger logger = LoggerFactory.getLogger(ExecutionFailureException.class);

  private final List<String> messages;

  private final String fieldName;

  public ExecutionFailureException(String fieldName, String message) {
    super(message);
    this.messages = new ArrayList<>();
    this.messages.add(message);
    this.fieldName = fieldName;
    logMessage();
  }

  public ExecutionFailureException(String fieldName, List<String> messages) {
    super(buildMessage("\n", fieldName, messages));
    this.messages = messages;
    this.fieldName = fieldName;
    logMessage();
  }

  public ExecutionFailureException(Throwable cause) {
    super(cause);
    this.messages = new ArrayList<>();
    messages.add("No specifics given");
    this.fieldName = "";
    logMessage();
  }

  public ExecutionFailureException(String fieldName, List<String> messages, Throwable cause) {
    super(buildMessage("\n", fieldName, messages), cause);
    this.messages = messages;
    this.fieldName = fieldName;
    logMessage();
  }

  private void logMessage() {
    logger.error(buildMessage(";", fieldName, messages), this);
  }

  private static String buildMessage(String delimiter, String fieldName, List<String> messages) {

    StringBuilder builder = new StringBuilder();
    builder.append("Field Name: ");
    builder.append(fieldName);
    builder.append(" Message: ");
    int msgCount = messages.size();
    for (String s : messages) {
      builder.append(s);
      if (--msgCount > 0) {
        builder.append(delimiter);
      }
    }
    return builder.toString();

  }
}
