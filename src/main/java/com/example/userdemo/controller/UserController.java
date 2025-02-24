package com.example.userdemo.controller;

import com.example.userdemo.model.PublicHolidayResponse;
import com.example.userdemo.model.UserData;
import com.example.userdemo.service.UserService;
import com.example.userdemo.service.PublicHolidayClientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
//@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final PublicHolidayClientService publicHolidayClientService;

    public UserController(UserService userService, PublicHolidayClientService publicHolidayClientService) {
        this.userService = userService;
        this.publicHolidayClientService = publicHolidayClientService;
    }

    @GetMapping
    public List<UserData> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserData createUser(@RequestBody UserData userData) {
        return userService.saveUser(userData);
    }

    @GetMapping("/tree")
    public Map<String, Map<String, Map<String, List<UserData>>>> getUsersTree() {
        return userService.getUsersTree();
    }

    @GetMapping("/first/{year}/{countryCode}")
    public PublicHolidayResponse getFirstPublicHoliday(@PathVariable int year, @PathVariable String countryCode) {
        return publicHolidayClientService.getFirstPublicHoliday(year, countryCode);
    }


}