package com.example.userdemo.controller;

import com.example.userdemo.model.User;
import com.example.userdemo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/api/users")
//@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/tree")
    public Map<String, Map<String, Map<String, List<User>>>> getUsersTree() {
        return userService.getUsersTree();
    }



}