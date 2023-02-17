package com.sourav959.swagger.service;

import com.sourav959.swagger.entity.User;
import com.sourav959.swagger.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@DisplayName("User service junit Test")
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private static User user1, user2;

    @BeforeAll
    static void init() {
        user1 = User.builder()
                .id(1)
                .name("Sourav Gupta")
                .email("souravgupta959@gmail.com")
                .gender("m")
                .status("Y")
                .build();
        user2 = User.builder()
                .id(2)
                .name("Payal Baldaniya")
                .email("payalbaldaniya@gmail.com")
                .gender("f")
                .status("Y")
                .build();
    }

    @Test
    @DisplayName("getAllUsers successful Test")
    void getAllUsersTest() {
        when(userRepository.findAll()).thenReturn(List.of(user1, user2));
        List<User> users = userService.getAllUsers();
        assertNotNull(users);
        assertEquals(2, users.size());
    }

    @Test
    @DisplayName("getUserById throws userId not found exception Test")
    void getUserByIdThrowsUserIdNotFoundExceptionTest() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> userService.getUserById(1));
    }

    @Test
    @DisplayName("getUserById successful Test")
    void getUserByIdSuccessfulTest() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user1));
        var user = userService.getUserById(1);
        assertNotNull(user);
    }

    @Test
    @DisplayName("saveUser successful Test")
    void saveUserSuccessfulTest() {
        when(userRepository.save(any(User.class))).thenReturn(user1);
        var user = userService.saveUser(user1);
        assertNotNull(user);
        assertEquals(user1, user);
    }

    @Test
    @DisplayName("deleteUserById throws userId not found exception Test")
    void deleteUserByIdThrowsUserNotFoundExceptionTest() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> userService.deleteUserById(1));
    }

    @Test
    @DisplayName("deleteUserById throws userId successful Test")
    void deleteUserByIdSuccessfulTest() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user1));
        doNothing().when(userRepository).deleteById(anyInt());
        assertDoesNotThrow(() -> userService.deleteUserById(1));
    }

}
