package demo.service.roleService;

import demo.model.Role;
import demo.repository.IRoleRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    IRoleRep iRoleRep;

    @Override
    public List<Role> roleList() {
        return iRoleRep.findAll();
    }
}
