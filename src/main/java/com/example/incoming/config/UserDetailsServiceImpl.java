package com.example.incoming.config;


import com.example.incoming.repository.Impl.UserRepositoryImpl;
import com.example.incoming.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userRepository;

    public UserDetailsServiceImpl(UserService userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.incoming.entity.User user = userRepository.getUser(username);
        if (user == null) {
            throw new UsernameNotFoundException("UserNotFound");
        }
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("user"));
        return new User(user.getLogin(), user.getPassword(), authorities);
    }
}
