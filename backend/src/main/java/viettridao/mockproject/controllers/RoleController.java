package viettridao.mockproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import viettridao.mockproject.models.Role;
import viettridao.mockproject.responses.ResponseObject;
import viettridao.mockproject.services.IRoleService;

import java.util.List;

/**
 * RoleController
 * Version: 1.0
 * Date: 5/23/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/23/2024 diepit9x Create
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/roles")
public class RoleController {
    private final IRoleService roleService;

    /**
     * Get all roles
     * 
     * @return List<Role>
     */
    @GetMapping("/all")
    public ResponseEntity<ResponseObject> getAllRoles() {
        List<Role> roles = roleService.getRoles();
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .status(200)
                        .message("Get all roles successfully")
                        .data(roles)
                        .build());
    }

    /**
     * Get aa role by roleId
     * 
     * @param roleId
     * @return
     */
    @GetMapping("")
    public ResponseEntity<ResponseObject> getRoleById(@RequestParam("id") Long roleId) {
        try {
            Role role = roleService.getRole(roleId);
            return ResponseEntity.ok(
                    ResponseObject.builder()
                            .status(200)
                            .message("Get role successfully")
                            .data(role)
                            .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    ResponseObject.builder()
                            .status(200)
                            .message("Get role failed")
                            .data(e.getMessage())
                            .build());
        }
    }
}
