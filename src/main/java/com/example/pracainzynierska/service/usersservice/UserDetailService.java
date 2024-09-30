package com.example.pracainzynierska.service.usersservice;

import com.example.pracainzynierska.entity.User;
import com.example.pracainzynierska.repository.UserRepository;
import com.example.pracainzynierska.service.adapter.UserAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new UserAdapter(this.userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found")));
    }
    public void save(User user) {
        this.userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }
}
