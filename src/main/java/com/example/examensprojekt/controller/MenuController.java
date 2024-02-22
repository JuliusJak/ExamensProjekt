package com.example.examensprojekt.controller;

import com.example.examensprojekt.model.User;
import com.example.examensprojekt.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class MenuController {

    private final UserService userService;

    @Autowired
    public MenuController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/signup")
    public String showSignupForm() {
        return "signup";
    }

    @GetMapping("/home")
    public String showHomePage() {
        return "home";
    }


    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        Optional<User> user = userService.getUserByUsername(username);

        if (user.isPresent() && userService.checkCredentials(username, password)) {
            User loggedInUser = new User();
            loggedInUser.setId(user.get().getId());
            loggedInUser.setUsername(user.get().getUsername());
            loggedInUser.setRole(user.get().getRole());

            session.setAttribute("loggedInUser", loggedInUser);

            return "redirect:/home";
        } else {
            return "redirect:/login?error";
        }
    }

    @GetMapping("/user-info")
    @ResponseBody
    public User getUserInfo(HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        return loggedInUser;
    }

}
