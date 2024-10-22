package viettridao.mockproject.services.imp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import viettridao.mockproject.exceptions.DataNotFoundException;
import viettridao.mockproject.models.Role;
import viettridao.mockproject.repositories.RoleRepository;
import viettridao.mockproject.services.IRoleService;

import java.util.List;

/**
 * RoleService
 * Version: 1.0
 * Date: 5/23/2024
 * Modification Logs
 * DATE AUTHOR DESCRIPTION
 * -------------------------------------
 * 5/23/2024 diepit9x Create
 */
@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {
    private final RoleRepository roleRepository;

    /**
     * Get all roles in role table
     * 
     * @return List<Role>
     */
    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    /**
     * Get a role by roleId
     * 
     * @param roleId
     * @return
     * @throws Exception
     */
    @Override
    public Role getRole(Long roleId) throws Exception {
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new DataNotFoundException("Role does not exist"));
    }
}
