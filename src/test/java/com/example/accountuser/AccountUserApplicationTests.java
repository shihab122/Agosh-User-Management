package com.example.accountuser;

import com.example.accountuser.exception.ExecutionFailureException;
import com.example.accountuser.model.User;
import com.example.accountuser.repository.UserRepository;
import com.example.accountuser.service.implementations.UserService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountUserApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@DisplayName("Test - User Create")
	@Test
	@Order(1)
	@Rollback(value = false)
	void createUser() throws ExecutionFailureException {
		User user = new User("Jhon", "Doe", "jhon@gmail.come", LocalDate.of(1994, 1, 1));
		User createdUser = userService.create(user);
		Assertions.assertNotNull(createdUser, "User created successfully");
		Assertions.assertEquals(user.getEmail(), createdUser.getEmail());
	}

	@DisplayName("Test - User List")
	@Test
	@Order(2)
	void getUserList() {
		List<User> users = userRepository.findAll();
		Assertions.assertTrue(users.size() > 0);
	}

	@DisplayName("Test - Get User By Id")
	@Test
	@Order(3)
	void getUserById() {
		User user = userRepository.findById(1L).get();
		Assertions.assertEquals(user.getId(), 1L);
	}

	@DisplayName("Test - User Update")
	@Test
	@Order(4)
	@Rollback(value = false)
	void updateUser() throws ExecutionFailureException {
		User user = userRepository.findById(1L).get();
		user.setEmail("jhondoe@gmail.com");
		User updatedUser = userService.update(user.getId(), user);
		Assertions.assertNotNull(user, "User updated successfully");
		Assertions.assertEquals(user.getEmail(), updatedUser.getEmail());
	}

	@DisplayName("Test - User Delete")
	@Test
	@Order(4)
	@Rollback(value = false)
	void deleteUser() throws ExecutionFailureException {
		User user = null;
		userService.delete(1L);
		Optional<User> optionalUser = userRepository.findById(1L);
		if (optionalUser.isPresent()) {
			user = optionalUser.get();
		}
		Assertions.assertNull(user);
	}
}
