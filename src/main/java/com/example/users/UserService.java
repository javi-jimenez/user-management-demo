package com.example.users;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    public UserService(UserRepository userRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    public List<User> generateRandomUsers(int number) {
        String url = "https://randomuser.me/api/?results=" + number;
        RandomUserResponse response = restTemplate.getForObject(url, RandomUserResponse.class);

        return response != null ? response.getResults().stream()
                .map(result -> new User(result.getLogin().getUsername(),
                        result.getName().getFirst() + " " + result.getName().getLast(),
                        result.getEmail(),
                        result.getGender(),
                        result.getPicture().getMedium()))
                .collect(Collectors.toList()) : List.of();
    }
}
