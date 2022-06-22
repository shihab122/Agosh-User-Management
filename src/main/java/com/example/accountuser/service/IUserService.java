package com.example.accountuser.service;

import com.example.accountuser.exception.EntityNotFoundException;
import com.example.accountuser.exception.ExecutionFailureException;
import com.example.accountuser.model.User;
import org.springframework.data.domain.Page;

public interface IUserService {
  Page<User> findAll(Integer pageNo, Integer pageSize, String sortBy);

  User findById(Long userId) throws ExecutionFailureException;

  User create(User user) throws ExecutionFailureException;

  User update(Long userId, User user) throws ExecutionFailureException;

  void delete(Long userId) throws ExecutionFailureException;
}
