package com.example.pracainzynierska.service;

import com.example.pracainzynierska.entity.Admin;
import com.example.pracainzynierska.entity.Trainer;
import com.example.pracainzynierska.entity.User;
import com.example.pracainzynierska.repository.AdminRepository;
import com.example.pracainzynierska.repository.TrainerRepository;
import com.example.pracainzynierska.repository.UserRepository;
import com.example.pracainzynierska.service.adapter.AdminAdapter;
import com.example.pracainzynierska.service.adapter.TrainerAdapter;
import com.example.pracainzynierska.service.adapter.UserAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final TrainerRepository trainerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // Check if the email exists in the Admin table
        Optional<Admin> adminOptional = adminRepository.findByEmail(email);
        if (adminOptional.isPresent()) {
            return new AdminAdapter(adminOptional.get()); // return admin adapter if found
        }

        // Check if the email exists in the Trainer table
        Optional<Trainer> trainerOptional = trainerRepository.findByEmail(email);
        if (trainerOptional.isPresent()) {
            return new TrainerAdapter(trainerOptional.get()); // return trainer adapter if found
        }

        // Check if the email exists in the User table
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            return new UserAdapter(userOptional.get()); // return user adapter if found
        }

        // If email not found in any table, throw exception
        throw new UsernameNotFoundException("User not found with email: " + email);
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public Admin createAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }
    public Trainer createTrainer(Trainer trainer) {
        trainer.setPassword(passwordEncoder.encode(trainer.getPassword()));
        return trainerRepository.save(trainer);
    }

}
