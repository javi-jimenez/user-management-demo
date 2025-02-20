package com.example.userdemo.service;

import com.example.userdemo.model.User;
import com.example.userdemo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepo;
    private final RestTemplate restTemplate;

    public UserService(UserRepository userRepo, RestTemplate restTemplate) {
        this.userRepo = userRepo;
        this.restTemplate = restTemplate;
    }


    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User saveUser(User user) {
        return userRepo.save(user);
    }

    public boolean userExists(String username) {
        return userRepo.existsById(username);
    }

    public Map<String, Map<String, Map<String, List<User>>>> getUsersTree() {
        List<User> users = userRepo.findAll();
        return users.stream().collect(
                Collectors.groupingBy(User::getCountry,
                        Collectors.groupingBy(User::getState,
                                Collectors.groupingBy(User::getCity)
                        )
                )
        );
    }
}