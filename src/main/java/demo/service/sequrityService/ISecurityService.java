package demo.service.sequrityService;

public interface ISecurityService {
    String findLoginUsername();
    void login(String username, String password);
}
