package com.example.examensprojekt.controller;

import com.example.examensprojekt.model.User;
import com.example.examensprojekt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8501")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    //create new user
    //can currently choose if they are admin or user
    //TODO
    //Make it so that they are users by default and not just anyone can become a admin
    //also change id generation to not be incremental
    @PostMapping("/create")
    public ResponseEntity<User> createNewUser(@RequestBody User user) {

        if (user.getRole() != "ADMIN"){
            user.setRole("USER");
        }
        User savedUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    //Get all users in the db
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = userRepository.findAll();

        return ResponseEntity.ok(allUsers);
    }

    //Search for user
    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUsers(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) String role) {

        List<User> searchResults = userRepository.findUsersWithParams(id, username, password, role);

        return ResponseEntity.ok(searchResults);
    }

    //Update username and/or password
    @PutMapping("/update")
    public ResponseEntity<String> updateUser(
            @RequestParam Long id,
            @RequestParam(required = false) String newUsername,
            @RequestParam(required = false) String newPassword) {

        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (newUsername != null && !newUsername.isEmpty()) {
                user.setUsername(newUsername);
            }

            if (newPassword != null && !newPassword.isEmpty()) {
                user.setPassword(newPassword);
            }

            userRepository.save(user);

            String message = "User with ID " + id + " has been successfully updated";
            return ResponseEntity.ok(message);
        } else {
            String message = "User with ID " + id + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    // Delete user
    @DeleteMapping("/delete")
    public String deleteUser(
            @RequestParam Long id) {

        userRepository.deleteById(id);

        return "User with id: "+ id +" has been deleted successfully";
    }
}
