package demo.repository;

import demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRep extends JpaRepository<Role, Long> {


}
