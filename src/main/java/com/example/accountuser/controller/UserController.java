package com.example.accountuser.controller;

import com.example.accountuser.exception.ExecutionFailureException;
import com.example.accountuser.model.User;
import com.example.accountuser.service.IUserService;
import com.example.accountuser.util.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
  private final IUserService iUserService;

  private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

  public UserController(IUserService iUserService) {
    this.iUserService = iUserService;
  }

  @GetMapping()
  public BaseResponse<Page<User>> getAllUsers(@RequestParam(defaultValue = "0") Integer pageNo,
                                                @RequestParam(defaultValue = "10") Integer pageSize,
                                                @RequestParam(defaultValue = "id") String sortBy) {
    LOGGER.info("@@@@Entered getAllUsers");
    Page<User> response = iUserService.findAll(pageNo, pageSize, sortBy);
    LOGGER.info("@@@@Exited getAllUsers");
    return new BaseResponse<>(HttpStatus.OK, response);
  }

  @GetMapping("/{id}")
  public BaseResponse<User> getUserById(@PathVariable("id") Long userId) throws ExecutionFailureException {
    LOGGER.info("@@@@Entered getUserById :::: " + userId);
    User response;
    try {
      response = iUserService.findById(userId);

    } catch (ExecutionFailureException e) {
      LOGGER.error("Exception occurred while processing request :::: ", e);
      throw e;
    }
    LOGGER.info("@@@@Exited getUserById");
    return new BaseResponse<>(HttpStatus.OK, response);
  }

  @PostMapping("/create")
  public BaseResponse<User> createUser(@RequestBody User user) throws ExecutionFailureException {
    LOGGER.info("@@@@Entered createUser");
    User response;
    try {
      response = iUserService.create(user);
    }
    catch (ExecutionFailureException e){
      LOGGER.error("Exception occurred while processing request :::: ", e);
      throw e;
    }
    LOGGER.info("@@@@Exited createUser");
    return new BaseResponse<>(HttpStatus.CREATED, response);
  }

  @PutMapping("/update/{id}")
  public BaseResponse<User> updateUser(@PathVariable("id") Long userId, @RequestBody User user) throws ExecutionFailureException {
    LOGGER.info("@@@@Entered updateUser");
    User response;
    try {
      response = iUserService.update(userId, user);
    }
    catch (ExecutionFailureException e){
      LOGGER.error("Exception occurred while processing request :::: ", e);
      throw e;
    }
    LOGGER.info("@@@@Exited updateUser");
    return new BaseResponse<>(HttpStatus.ACCEPTED, response);
  }

  @DeleteMapping("/{id}")
  public BaseResponse<User> deleteUser(@PathVariable("id") Long userId) throws ExecutionFailureException{
    LOGGER.info("@@@@Entered deleteUser");
    try {
      iUserService.delete(userId);
    }
    catch (ExecutionFailureException e){
      LOGGER.error("Exception occurred while processing request :::: ", e);
      throw e;
    }
    LOGGER.info("@@@@Exited deleteUser");
    return new BaseResponse<>(HttpStatus.ACCEPTED);
  }
}
