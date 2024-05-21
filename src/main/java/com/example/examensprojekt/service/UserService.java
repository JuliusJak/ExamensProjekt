package com.example.examensprojekt.service;

import com.example.examensprojekt.model.User;
import com.example.examensprojekt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getUsersWithParams(Long id, String username, String password, String role) {
        return userRepository.findUsersWithParams(id, username, password, role);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean checkCredentials(String username, String password) {
        List<User> users = userRepository.findUsersWithParams(null, username, password, null);

        if (!users.isEmpty()) {
            User user = users.get(0);
            return user.getPassword().equals(password);
        }

        return false;
    }

}
