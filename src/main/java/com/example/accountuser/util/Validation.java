package com.example.accountuser.util;

import com.example.accountuser.exception.BadRequestException;
import com.example.accountuser.exception.ExecutionFailureException;
import com.example.accountuser.model.User;
import org.springframework.util.ObjectUtils;

public class Validation {
  private static final String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
          "[a-zA-Z0-9_+&*-]+)*@" +
          "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
          "A-Z]{2,7}$";

  public static void validateUserObject(User user) throws ExecutionFailureException {

    if (ObjectUtils.isEmpty(user)) {
      throw new BadRequestException( "name", "User is empty");
    }
    if (ObjectUtils.isEmpty(user.getFirstName()) || user.getFirstName().length() < 3 || user.getFirstName().length() > 100)
      throw new BadRequestException("name", "User first name must be more than 2 & less than 100 characters");

    if (ObjectUtils.isEmpty(user.getLastName()) || user.getLastName().length() < 3 || user.getLastName().length() > 100)
      throw new BadRequestException("name", "User last name must be more than 2 & less than 100 characters");

    if (ObjectUtils.isEmpty(user.getEmail()))
      throw new BadRequestException("email", "Email is required for creating an user account");

    if (!ObjectUtils.isEmpty(user.getEmail()))
      validateEmail(user.getEmail());

  }

  public static void validateEmail(String email) throws ExecutionFailureException {

    if (ObjectUtils.isEmpty(email))
      throw new BadRequestException("email", "Email is required field");

    if (email.length() < 3 || email.length() > 50)
      throw new BadRequestException("email", "Email must be more than 2 & less than 50 characters long");

    if (!email.matches(emailRegex) && !email.matches(emailRegex))
      throw new BadRequestException("email", "Email is not valid");
  }
}
