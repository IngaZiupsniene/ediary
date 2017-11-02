package demo.repository;

import demo.model.Parents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IParentsRep extends JpaRepository<Parents, Long>{
}
