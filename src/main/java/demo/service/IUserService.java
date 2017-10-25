package demo.service;

import demo.model.User;

public interface IUserService {
    void save(User user);
    User findByUserName(String username);




}
