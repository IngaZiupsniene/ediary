package demo.service.userService;

import demo.model.User;
import demo.repository.IRoleRep;
import demo.repository.IUserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements IUserService {

    @Autowired
    IUserRep iUserRep;
    @Autowired
    IRoleRep iRoleRep;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword())); //paduodam sifruotus slaptazodzius

     // user.setRoles(new HashSet<>(iRoleRep.findAll())); //cia per konstruktoriu ikelem visas roles

        user.setRole(user.getRole());
        iUserRep.save(user); //panaudojam save metoda - irasom duomenis i DB.
    }





    @Override
    public User findByUserName(String name) {
        return iUserRep.findByUsername(name);
    }

}
