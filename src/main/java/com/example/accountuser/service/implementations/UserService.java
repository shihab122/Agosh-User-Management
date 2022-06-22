package com.example.accountuser.service.implementations;

import com.example.accountuser.exception.BadRequestException;
import com.example.accountuser.exception.EntityNotFoundException;
import com.example.accountuser.exception.ExecutionFailureException;
import com.example.accountuser.model.User;
import com.example.accountuser.repository.UserRepository;
import com.example.accountuser.service.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.accountuser.util.Utility.createPageableObject;
import static com.example.accountuser.util.Validation.validateUserObject;

@Service
public class UserService implements IUserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public Page<User> findAll(Integer pageNo, Integer pageSize, String sortBy) {
    return userRepository.findAll(createPageableObject(pageNo, pageSize, sortBy));
  }

  @Override
  public User findById(Long userId) throws ExecutionFailureException {
    Optional<User> optionalUnit = userRepository.findById(userId);
    if (optionalUnit.isEmpty())
      throw new EntityNotFoundException("id", "User details not found in the database");
    return optionalUnit.get();
  }

  @Override
  public User create(User user) throws ExecutionFailureException {
    validateUserObject(user);
    return userRepository.save(user);
  }

  @Override
  public User update(Long userId, User user) throws ExecutionFailureException {
    validateUserObject(user);
    findById(userId);
    return userRepository.save(user);
  }

  @Override
  public void delete(Long userId) throws ExecutionFailureException {
    findById(userId);
    userRepository.deleteById(userId);
  }
}
