package com.example.users;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testGetAllUsers() {
        User user = new User("user1", "John Doe", "john@example.com", "Male", "https://example.com/pic.jpg");
        List<User> users = List.of(user);
        Page<User> userPage = new PageImpl<>(users);

        when(userRepository.findAll(PageRequest.of(0, 10))).thenReturn(userPage);

        Page<User> result = userService.getAllUsers(0, 10);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(userRepository, times(1)).findAll(PageRequest.of(0, 10));
    }
}
