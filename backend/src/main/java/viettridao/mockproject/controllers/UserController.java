package viettridao.mockproject.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import viettridao.mockproject.configurations.security.jwt.JWTAuthResponse;
import viettridao.mockproject.dtos.users.UserLoginDTO;
import viettridao.mockproject.dtos.users.UserRegisterDTO;
import viettridao.mockproject.dtos.users.UserUpdateDTO;
import viettridao.mockproject.responses.LoginResponse;
import viettridao.mockproject.responses.ResponseObject;
import viettridao.mockproject.responses.UserResponse;
import viettridao.mockproject.services.IUserService;

import java.util.List;

/**
 * UserController
 * Version: 1.0
 * Date: 5/21/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/21/2024 diepit9x Create
 */
@RestController
@RequestMapping("${api.prefix}/users")
@RequiredArgsConstructor
public class UserController {
	private final IUserService userService;

	/**
	 * Create a new user
	 * 
	 * @param userRegisterDTO
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("/register")
	public ResponseEntity<ResponseObject> registerUser(
			@Valid @ModelAttribute UserRegisterDTO userRegisterDTO,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				List<String> errors = bindingResult.getFieldErrors().stream()
						.map(FieldError::getDefaultMessage).toList();

				return ResponseEntity.badRequest().body(ResponseObject.builder()
						.status(400)
						.message("Registration Failed")
						.data(errors)
						.build());
			}
			UserResponse newUser = userService.registerUser(userRegisterDTO);
			return ResponseEntity.ok(ResponseObject.builder()
					.status(200)
					.message("Registration successfully")
					.data(newUser)
					.build());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(ResponseObject.builder()
					.status(400)
					.message("Registration Failed")
					.data(e.getMessage())
					.build());
		}
	}

	/**
	 * Login
	 * 
	 * @param userLoginDTO
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("/login")
	public ResponseEntity<ResponseObject> loginUser(
			@Valid @ModelAttribute UserLoginDTO userLoginDTO,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				List<String> errors = bindingResult.getFieldErrors().stream()
						.map(FieldError::getDefaultMessage).toList();

				return ResponseEntity.badRequest().body(ResponseObject.builder()
						.status(400)
						.message("Login Failed")
						.data(errors)
						.build());
			}
			// UserResponse userResponse = userService.loginUser(userLoginDTO);
			LoginResponse userResponse = userService.login(userLoginDTO);
			JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
			jwtAuthResponse.setAccessToken(userResponse.getToken());
			return ResponseEntity.ok(ResponseObject.builder()
					.status(200)
					.message("Login successfully")
					.data(userResponse)
					.build());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(ResponseObject.builder()
					.status(400)
					.message("Login Failed")
					.data(e.getMessage())
					.build());
		}
	}

	@PreAuthorize("isAuthenticated()")
	@PutMapping("")
	public ResponseEntity<ResponseObject> updateUser(
			@Valid @ModelAttribute UserUpdateDTO userUpdateDTO,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				List<String> errors = bindingResult.getFieldErrors().stream()
						.map(FieldError::getDefaultMessage).toList();
				return ResponseEntity.badRequest().body(ResponseObject.builder()
						.status(400)
						.message("Update User Failed")
						.data(errors)
						.build());
			}
			UserResponse userResponse = userService.updateUser(userUpdateDTO);
			return ResponseEntity.ok(
					ResponseObject.builder()
							.status(200)
							.message("Update User successfully")
							.data(userResponse)
							.build());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(ResponseObject.builder()
					.status(400)
					.message("Update User Failed")
					.data(e.getMessage())
					.build());
		}
	}

	/**
	 * Get all users
	 * 
	 * @return
	 */
	@GetMapping("/all")
	public ResponseEntity<ResponseObject> getUsers() {
		return ResponseEntity.ok(ResponseObject.builder()
				.status(200)
				.message("Get all users successfully")
				.data(userService.getAllUsers())
				.build());
	}

	/**
	 * Get user by UserId
	 * 
	 * @param userId
	 * @return
	 */
	@GetMapping("")
	public ResponseEntity<ResponseObject> getUserById(@RequestParam("id") Long userId) {
		try {
			UserResponse userResponse = userService.getUserById(userId);
			return ResponseEntity.ok(ResponseObject.builder()
					.status(200)
					.message("Get user successfully")
					.data(userResponse)
					.build());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(ResponseObject.builder()
					.status(400)
					.message("Get an user failed")
					.data(e.getMessage())
					.build());
		}
	}

	/**
	 * delete/un-active a user
	 */
	@PreAuthorize("isAuthenticated()")
	@DeleteMapping("")
	public ResponseEntity<ResponseObject> delete(@RequestParam("id") Long id) {
		try {
			userService.deleteUser(id);
			return ResponseEntity.ok(ResponseObject.builder()
					.status(200)
					.message("Delete user successfully")
					.build());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(ResponseObject.builder()
					.status(400)
					.message("Delete an user failed")
					.data(e.getMessage())
					.build());
		}
	}

	/**
	 * recover an user
	 */
	@PreAuthorize("isAuthenticated()")
	@PutMapping("/recover")
	public ResponseEntity<ResponseObject> recover(@RequestParam("id") Long id) {
		try {
			userService.recoverUser(id);
			return ResponseEntity.ok(ResponseObject.builder()
					.status(200)
					.message("User recovered successfully")
					.build());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(ResponseObject.builder()
					.status(400)
					.message("Recover an user failed")
					.data(e.getMessage())
					.build());
		}
	}
}
