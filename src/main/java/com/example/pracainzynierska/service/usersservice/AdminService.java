package com.example.pracainzynierska.service.usersservice;

import com.example.pracainzynierska.entity.Admin;
import com.example.pracainzynierska.repository.AdminRepository;
import com.example.pracainzynierska.service.adapter.AdminAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService implements UserDetailsService {
    @Autowired
    AdminRepository adminRepository;
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new AdminAdapter(this.adminRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Admin Not Found")));
    }
    public void save(Admin admin) {
        adminRepository.save(admin);
    }

    public Optional<Admin> findByEmail(String email) {
        return adminRepository.findByEmail(email);
    }
}
