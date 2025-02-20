package com.example.users;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    @Operation(summary = "Get all users with pagination")
    public Page<User> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return userService.getAllUsers(page, size);
    }

    @GetMapping("/{username}/")
    @Operation(summary = "Get a single user by username")
    public User getUser(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping("/")
    @Operation(summary = "Create a new user")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{username}/")
    @Operation(summary = "Update an existing user")
    public User updateUser(@PathVariable String username, @RequestBody User user) {
        return userService.updateUser(username, user);
    }

    @DeleteMapping("/{username}/")
    @Operation(summary = "Delete a user by username")
    public boolean deleteUser(@PathVariable String username) {
        return userService.deleteUser(username);
    }

    @GetMapping("/generate/{number}/")
    @Operation(summary = "Generate random users")
    public List<User> generateRandomUsers(@PathVariable int number) {
        return userService.generateRandomUsers(number);
    }

    @GetMapping("/tree/")
    @Operation(summary = "Get users grouped by country, state, and city")
    public List<User> getUsersGroupedByLocation() {
        return userService.getUsersGroupedByLocation();
    }
}
