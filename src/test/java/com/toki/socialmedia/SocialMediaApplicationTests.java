package com.toki.socialmedia;

import com.toki.socialmedia.controller.AuthController;
import com.toki.socialmedia.dto.LoginDTO;
import com.toki.socialmedia.model.User;
import com.toki.socialmedia.repository.UserRepository;
import com.toki.socialmedia.response.AuthResponse;
import com.toki.socialmedia.security.JwtProvider;
import com.toki.socialmedia.service.UserDetailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class AuthControllerTests {

	@InjectMocks
	private AuthController authController;

	@Mock
	private UserRepository userRepository;

	@Mock
	private PasswordEncoder passwordEncoder;

	@Mock
	private JwtProvider jwtProvider;

	@Mock
	private UserDetailService userDetailService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	//injection mock into InjectMocks

	@Test
	@DisplayName("Test Sign In Success")
	void testSignUp_Success() throws Exception {
		User user = new User();
		user.setEmail("test@example.com");
		user.setPassword("password");
		user.setFirstname("John");
		user.setLastname("Doe");

		User savedUser = new User();
		savedUser.setEmail("test@example.com");
		savedUser.setPassword("encodedPassword");

		//find result of step to step
		when(userRepository.findByEmail(anyString())).thenReturn(null);
		when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
		when(userRepository.save(any(User.class))).thenReturn(savedUser);
		when(jwtProvider.generateToken(any(Authentication.class))).thenReturn("jwtToken");

		//find result of function you want to test
		AuthResponse response = authController.signUp(user);

		//check result is matching ?
		assertNotNull(response);
		assertEquals("jwtToken", response.getToken());
		assertEquals("Sign in successful", response.getMessage());
	}

	@Test
	@DisplayName("Test Sign In User Exists")
	void testSignUp_UserExists() throws Exception {
		User user = new User();
		user.setEmail("test@example.com");

		when(userRepository.findByEmail(anyString())).thenReturn(new User());

		Exception exception = assertThrows(Exception.class, () -> authController.signUp(user));

		assertEquals("User is exits", exception.getMessage());
	}

	@Test
	@DisplayName("Test Sign In User Not Found")
	void testSignIn_Success() throws Exception {
		LoginDTO loginDto = new LoginDTO();
		loginDto.setEmail("test@example.com");
		loginDto.setPassword("password");

		User user = new User();
		user.setEmail("test@example.com");
		user.setPassword("encodedPassword");

		UserDetails userDetails = org.springframework.security.core.userdetails.User
				.withUsername("test@example.com")
				.password("encodedPassword")
				.authorities("USER")
				.build();

		when(userRepository.findByEmail(anyString())).thenReturn(user);
		when(userDetailService.loadUserByUsername(anyString())).thenReturn(userDetails);
		when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
		when(jwtProvider.generateToken(any(Authentication.class))).thenReturn("jwtToken");

		AuthResponse response = authController.signIn(loginDto);

		assertNotNull(response);
		assertEquals("jwtToken", response.getToken());
		assertEquals("Sign in successful", response.getMessage());
	}

	@Test
	@DisplayName("Test Sign In Password Incorrect")
	void testSignIn_UserNotFound() {
		LoginDTO loginDto = new LoginDTO();
		loginDto.setEmail("nonexistent@example.com");

		when(userRepository.findByEmail(anyString())).thenReturn(null);

		Exception exception = assertThrows(Exception.class, () -> authController.signIn(loginDto));
		assertEquals("User not found", exception.getMessage());
	}

	@Test
	@DisplayName("Test Sign In Bad Credentials By Wrong password")
	void testSignIn_BadCredentials() throws Exception {
		LoginDTO loginDto = new LoginDTO();
		loginDto.setEmail("test@example.com");
		loginDto.setPassword("wrongPassword");

		User user = new User();
		user.setEmail("test@example.com");
		user.setPassword("encodedPassword");

		UserDetails userDetails = org.springframework.security.core.userdetails.User
				.withUsername("test@example.com")
				.password("encodedPassword")
				.authorities("USER")
				.build();

		when(userRepository.findByEmail(anyString())).thenReturn(user);
		when(userDetailService.loadUserByUsername(anyString())).thenReturn(userDetails);
		when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);

		Exception exception = assertThrows(BadCredentialsException.class, () -> authController.signIn(loginDto));
		assertEquals("Password is incorrect", exception.getMessage());
	}
}
