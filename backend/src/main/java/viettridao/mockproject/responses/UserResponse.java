package viettridao.mockproject.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import viettridao.mockproject.models.Location;
import viettridao.mockproject.models.Role;
import viettridao.mockproject.models.User;

/**
 * UserResponse
 * Version: 1.0
 * Date: 5/21/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/21/2024 diepit9x Create
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private Long id;

    private String firstName;

    private String lastName;

    private Location location;

    private String phone;

    private String email;

    private String username;

    private Boolean active;

    private Role role;

    private String jobDescription;

    public static UserResponse fromUser(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .location(user.getLocation())
                .phone(user.getPhone())
                .email(user.getEmail())
                .username(user.getUsername())
                .active(user.getActive())
                .jobDescription(user.getJobDescription())
                .role(user.getRole())
                .build();
    }
}
