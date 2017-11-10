package demo.service.roleService;

import demo.model.Role;

import java.util.List;

public interface IRoleService {
    List<Role> roleList();
    Role findRoleByName(String role);
}
