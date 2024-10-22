package viettridao.mockproject.services;

import viettridao.mockproject.models.Role;

import java.util.List;

/**
 * IRoleService
 * Version: 1.0
 * Date: 5/23/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/23/2024 diepit9x Create
 */
public interface IRoleService {
    List<Role> getRoles();

    Role getRole(Long roleId) throws Exception;
}
