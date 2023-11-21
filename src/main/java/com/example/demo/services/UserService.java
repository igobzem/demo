package com.example.demo.services;

import com.example.demo.data.User;
import com.example.demo.data.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    Logger logger = LoggerFactory.getLogger(UserService.class);
    private final List<User> users = new ArrayList<>();

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = users.stream().filter(u -> u.getEmail().equals(username.toLowerCase())).findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("Not found " + username));
        return new UserDetailsImpl(user);
    }

    public void add(User user) {
        user.setEmail(user.getEmail().toLowerCase());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        users.add(user);
    }

    public int getUsersSize() {
        return users.size();
    }
}
