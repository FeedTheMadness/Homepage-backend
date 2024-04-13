package com.ftmnet.homepage.service;

import com.ftmnet.homepage.entity.User;
import com.ftmnet.homepage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByName(username);
        return user.orElseGet(() -> createUser(username));
    }

    private User createUser(String name) {
        User user = User.builder()
                .name(name)
                .build();
        return userRepository.save(user);
    }
}
