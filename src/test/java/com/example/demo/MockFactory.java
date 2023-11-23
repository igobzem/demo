package com.example.demo;

import com.example.demo.data.User;
import com.example.demo.data.UserDetailsImpl;
import com.example.demo.services.UserService;
import org.springframework.context.annotation.Bean;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockFactory {

    public static final String ADMIN = "admin";
    @Bean
    public UserService getUserService() {
        User user = new User();
        user.setEmail(ADMIN);
        UserService userService = mock(UserService.class);
        when(userService.loadUserByUsername(ADMIN)).thenReturn(new UserDetailsImpl(user));
        return userService;
    }

}
