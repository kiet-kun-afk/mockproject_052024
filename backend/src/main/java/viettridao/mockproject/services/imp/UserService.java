package viettridao.mockproject.services.imp;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import viettridao.mockproject.configurations.security.jwt.JwtTokenProvider;
import viettridao.mockproject.dtos.users.UserLoginDTO;
import viettridao.mockproject.dtos.users.UserRegisterDTO;
import viettridao.mockproject.dtos.users.UserUpdateDTO;
import viettridao.mockproject.exceptions.DataNotFoundException;
import viettridao.mockproject.exceptions.InvalidParamException;
import viettridao.mockproject.models.Location;
import viettridao.mockproject.models.Role;
import viettridao.mockproject.models.User;
import viettridao.mockproject.repositories.LocationRepository;
import viettridao.mockproject.repositories.RoleRepository;
import viettridao.mockproject.repositories.UserRepository;
import viettridao.mockproject.responses.LoginResponse;
import viettridao.mockproject.responses.UserResponse;
import viettridao.mockproject.services.IUserService;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * UserService
 * Version: 1.0
 * Date: 5/21/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/21/2024 diepit9x Create
 */
@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final LocationRepository locationRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    private static final String PASSWORD_PATTERN = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,16}$";

    /**
     * validate the password
     */
    public boolean validatePassword(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    /**
     * Insert data for users
     * 
     * @param userRegisterDTO
     * @return
     * @throws Exception
     */
    @Override
    public UserResponse registerUser(UserRegisterDTO userRegisterDTO) throws Exception {
        if (userRepository.existsByUsername(userRegisterDTO.getUsername())) {
            throw new InvalidParamException("Username already exists");
        }
        if (userRepository.existsByEmail(userRegisterDTO.getEmail())) {
            throw new InvalidParamException("Email already exists");
        }
        if (userRepository.existsByPhone(userRegisterDTO.getPhone())) {
            throw new InvalidParamException("Phone already exists");
        }
        if (!validatePassword(userRegisterDTO.getPassword())) {
            throw new InvalidParamException("Password is not strong enough");
        }
        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getRetypePassword())) {
            throw new InvalidParamException("Passwords do not match");
        }

        Role role = roleRepository.findByName("USER")
                .orElseThrow(() -> new DataNotFoundException("Role does not exist"));

        User newuser = User.builder()
                .email(userRegisterDTO.getEmail())
                .username(userRegisterDTO.getEmail())
                .phone(userRegisterDTO.getPhone())
                .password(passwordEncoder.encode(userRegisterDTO.getPassword()))
                .active(true)
                .role(role)
                .jobDescription("USER")
                .build();
        userRepository.save(newuser);
        return UserResponse.fromUser(newuser);
    }

    /**
     * Check user infomation during login
     * 
     * @param userLoginDTO
     * @return
     * @throws Exception
     */
    @Override
    public UserResponse loginUser(UserLoginDTO userLoginDTO) throws Exception {
        User existingUser = userRepository.findByEmail(userLoginDTO.getEmail())
                .orElseThrow(() -> new DataNotFoundException("Wrong email/password"));

        if (!userLoginDTO.getPassword().equals(existingUser.getPassword())) {
            throw new InvalidParamException("Wrong email/password");
        }
        return UserResponse.fromUser(existingUser);
    }

    /**
     * Update user
     * 
     * @param userUpdateDTO
     * @return
     * @throws Exception
     */
    @Override
    public UserResponse updateUser(UserUpdateDTO userUpdateDTO) throws Exception {
        User existingUser = userRepository.findById(userUpdateDTO.getId())
                .orElseThrow(() -> new DataNotFoundException("User does not exist"));

        if (!userUpdateDTO.getEmail().equals(existingUser.getEmail()) &&
                userRepository.existsByEmail(userUpdateDTO.getEmail())) {
            throw new InvalidParamException("Email already exists");
        }
        if (!userUpdateDTO.getPhone().equals(existingUser.getPhone()) &&
                userRepository.existsByPhone(userUpdateDTO.getPhone())) {
            throw new InvalidParamException("Phone already exists");
        }
        Location existingLocation = locationRepository.findById(userUpdateDTO.getLocationId())
                .orElseThrow(() -> new DataNotFoundException("Location does not exist"));

        existingUser.setFirstName(userUpdateDTO.getFirstName());
        existingUser.setLastName(userUpdateDTO.getLastName());
        existingUser.setEmail(userUpdateDTO.getEmail());
        existingUser.setLocation(existingLocation);
        existingUser.setPhone(userUpdateDTO.getPhone());

        userRepository.save(existingUser);

        return UserResponse.fromUser(existingUser);
    }

    /**
     * Get a user by userId
     * 
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public UserResponse getUserById(long userId) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("User does not exist"));
        return UserResponse.fromUser(user);
    }

    /**
     * Get all users
     * 
     * @return
     */
    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(UserResponse::fromUser).toList();
    }

    @Override
    public void deleteUser(Long userId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException("User does not exist"));
        user.setActive(false);
        userRepository.save(user);
    }

    @Override
    public void recoverUser(Long userId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new DataNotFoundException("User does not exist"));
        user.setActive(true);
        userRepository.save(user);
    }

    @Override
    public LoginResponse login(UserLoginDTO userLoginDTO) throws Exception {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDTO.getEmail(), userLoginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return new LoginResponse("Login successfully", token);
    }

    @Override
    public User getAuth(String roleName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return userRepository.findAuthentication(roleName, currentPrincipalName, currentPrincipalName);
    }

}
