package com.example.userdemo.service;

import com.example.userdemo.model.UserData;
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


    public List<UserData> getAllUsers() {
        return userRepo.findAll();
    }

    public UserData saveUser(UserData userData) {
        return userRepo.save(userData);
    }

    public boolean userExists(String username) {
        return userRepo.existsById(username);
    }

    public Map<String, Map<String, Map<String, List<UserData>>>> getUsersTree() {
        List<UserData> userData = userRepo.findAll();
        return userData.stream().collect(
                Collectors.groupingBy(UserData::getCountry,
                        Collectors.groupingBy(UserData::getState,
                                Collectors.groupingBy(UserData::getCity)
                        )
                )
        );
    }
}