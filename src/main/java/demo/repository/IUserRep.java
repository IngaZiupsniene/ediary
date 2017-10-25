package demo.repository;

import demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRep extends JpaRepository<User, Long> {
    User findByUsername(String name); //musu sukurtas metodas -


}
