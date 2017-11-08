package demo.service.userService;

import demo.model.User;

public interface IUserService {
    User save(User user);
    User findByUserName(String username);




}
