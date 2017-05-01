package main.service;

import java.util.List;

import main.entities.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
    
    List<User> readUsers();
    
    User readUser(String username);
    
    void deleteUser(String username);
    
}
