package demo.service;

public interface ISecurityService {
    String findLoginUsername();
    void login(String username, String password);
}
