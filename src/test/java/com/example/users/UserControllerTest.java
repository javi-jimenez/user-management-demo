package com.example.users;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    public UserControllerTest() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testGetAllUsers() throws Exception {
        mockMvc.perform(get("/api/users/")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateUser() throws Exception {
        User user = new User("user1", "John Doe", "john@example.com", "Male", "https://example.com/pic.jpg");

        when(userService.createUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"user1\",\"name\":\"John Doe\",\"email\":\"john@example.com\",\"gender\":\"Male\",\"pictureUrl\":\"https://example.com/pic.jpg\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("user1"));
    }
}
