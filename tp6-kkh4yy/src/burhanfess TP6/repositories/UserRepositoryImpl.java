package burhanfess.repositories;

import java.util.*;

import burhanfess.exceptions.InvalidCredentialsException;
import burhanfess.exceptions.MenfessNotFoundException;
import burhanfess.users.Admin;
import burhanfess.users.User;

public class UserRepositoryImpl implements UserRepository {
    private static UserRepositoryImpl instance;
    private List<User> users;

    private UserRepositoryImpl() {
        users = new ArrayList<>();
        users.add(new Admin("admin", "admin"));
    }

    public static UserRepositoryImpl getInstance() {
        if (instance == null) instance = new UserRepositoryImpl();
        return instance;
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    @Override
    public User getUserByUsername(String username) {
        return users.stream()
            .filter(u -> u.getUsername().equals(username))
            .findFirst()
            .orElse(null);
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public void changePassword(User user, String newPassword) {
        user.setPassword(newPassword);
    }

    @Override
    public void addItem(User item) {
        users.add(item);
    }

    @Override
    public List<User> getData() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getData'");
    }

    @Override
    public void removeItem(User item) {
        users.remove(item);
    }
}