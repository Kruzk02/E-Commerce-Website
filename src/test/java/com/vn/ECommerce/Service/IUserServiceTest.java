package com.vn.ECommerce.Service;

import com.vn.ECommerce.Model.User;
import com.vn.ECommerce.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

class IUserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private IUserService userService;

    public IUserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveUser() {
        User user = new User();
        Mockito.when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userService.saveUser(user);

        assertEquals(user, savedUser);
        Mockito.verify(userRepository, Mockito.times(1)).save(eq(user));
    }

    @Test
    void findUserByEmail() {
        String email = "test@example.com";
        User user = new User();
        Mockito.when(userRepository.findUserByEmail(eq(email))).thenReturn(user);

        User foundUser = userService.findUserByEmail(email);

        assertEquals(user, foundUser);
        Mockito.verify(userRepository, Mockito.times(1)).findUserByEmail(eq(email));
    }

    @Test
    void findUserByUsername() {
        String username = "testUser";
        User user = new User();
        Mockito.when(userRepository.findUserByUsername(eq(username))).thenReturn(user);

        User foundUser = userService.findUserByUsername(username);

        assertEquals(user, foundUser);
        Mockito.verify(userRepository, Mockito.times(1)).findUserByUsername(eq(username));
    }
}
