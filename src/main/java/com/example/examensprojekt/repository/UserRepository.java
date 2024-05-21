package com.example.examensprojekt.repository;

import com.example.examensprojekt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Get all users
    List<User> findAll();

    Optional<User> findById(Long id);

    //Search for a user matching all given params
    @Query("SELECT u FROM User u WHERE (:id IS NULL OR u.id = :id) " +
            "AND (:username IS NULL OR u.username = :username) " +
            "AND (:password IS NULL OR u.password = :password) " +
            "AND (:role IS NULL OR u.role = :role)")
    List<User> findUsersWithParams(Long id, String username, String password, String role);

    // Delete user by ID
    void deleteById(Long id);

    Optional<User> findByUsername(String username);
}

