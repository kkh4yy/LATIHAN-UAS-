package burhanfess.services;

import burhanfess.users.User;

public interface UnauthorizedService {
    void register(String username, String password);
    User login(String username, String password);
    void exit();
}
