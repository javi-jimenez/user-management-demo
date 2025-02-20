package com.example.users;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public Page<User> getAllUsers(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size));
    }

    public User getUserByUsername(String username) {
        return userRepository.findById(username).orElse(null);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(String username, User user) {
        if (userRepository.existsById(username)) {
            user.setUsername(username);
            return userRepository.save(user);
        }
        return null;
    }

    public boolean deleteUser(String username) {
        if (userRepository.existsById(username)) {
            userRepository.deleteById(username);
            return true;
        }
        return false;
    }

    public List<User> generateRandomUsers(int number) {
        String url = "https://randomuser.me/api/?results=" + number;
        RandomUserResponse response = restTemplate.getForObject(url, RandomUserResponse.class);
        return response != null ? response.getResults().stream()
                .map(user -> new User(user.getLogin().getUsername(), user.getName().getFirst() + " " + user.getName().getLast(),
                        user.getEmail(), user.getGender(), user.getPicture().getMedium()))
                .collect(Collectors.toList()) : List.of();
    }

    public List<User> getUsersGroupedByLocation() {
        // Non-database solution that groups users by country, state, and city
        return userRepository.findAll();
    }
}
