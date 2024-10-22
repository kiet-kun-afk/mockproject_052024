package viettridao.mockproject.services;

import viettridao.mockproject.dtos.users.UserLoginDTO;
import viettridao.mockproject.dtos.users.UserRegisterDTO;
import viettridao.mockproject.dtos.users.UserUpdateDTO;
import viettridao.mockproject.models.User;
import viettridao.mockproject.responses.LoginResponse;
import viettridao.mockproject.responses.UserResponse;

import java.util.List;

/**
 * IUserService
 * Version: 1.0
 * Date: 5/21/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/21/2024 diepit9x Create
 */
public interface IUserService {
    UserResponse registerUser(UserRegisterDTO userRegisterDTO) throws Exception;

    UserResponse loginUser(UserLoginDTO userLoginDTO) throws Exception;

    UserResponse updateUser(UserUpdateDTO userUpdateDTO) throws Exception;

    UserResponse getUserById(long userId) throws Exception;

    List<UserResponse> getAllUsers();

    void deleteUser(Long userId) throws Exception;

    void recoverUser(Long userId) throws Exception;

    LoginResponse login(UserLoginDTO userLoginDTO) throws Exception;

    User getAuth(String roleName);
}
