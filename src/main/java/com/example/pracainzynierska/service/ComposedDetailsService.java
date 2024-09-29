package com.example.pracainzynierska.service;

import com.example.pracainzynierska.entity.Admin;
import com.example.pracainzynierska.entity.Trainer;
import com.example.pracainzynierska.entity.User;
import com.example.pracainzynierska.exeption.EmailIsTakenExeption;
import com.example.pracainzynierska.service.usersservice.AdminService;
import com.example.pracainzynierska.service.usersservice.TrainerService;
import com.example.pracainzynierska.service.usersservice.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComposedDetailsService implements UserDetailsService {
    @Autowired
    AdminService adminService;
    @Autowired
    TrainerService trainerService;
    @Autowired
    UserService userService;
    private List<UserDetailsService> services;


    @PostConstruct
    public void setServices() {
        List<UserDetailsService> new_services = new ArrayList<>();
        new_services.add(this.adminService); // injection
        new_services.add(this.trainerService);     // injection
        new_services.add(this.userService);     // injection
        this.services = new_services;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        for (UserDetailsService service : services) {
            try {
                UserDetails user = service.loadUserByUsername(username);
                return user;
            } catch (UsernameNotFoundException e) {
                continue;
            }
        }
        throw new UsernameNotFoundException("User Not Found");
    }

    public ResponseEntity<?> createUser(User user) {
        if(!checkIfEmailIsTaken(user.getEmail())) {
            throw new EmailIsTakenExeption("Email is taken");
        }
        userService.save(user);
        return ResponseEntity.ok().body(user);
    }

    public ResponseEntity<?> createAdmin(Admin admin) {
        if(!checkIfEmailIsTaken(admin.getEmail())) {
            throw new EmailIsTakenExeption("Email is taken");
        }
        adminService.save(admin);
        return ResponseEntity.ok().body(admin);
    }

    public ResponseEntity<?> createTrainer(Trainer trainer) {
        if(!checkIfEmailIsTaken(trainer.getEmail())) {
            throw new EmailIsTakenExeption("Email is taken");
        }
        trainerService.save(trainer);
        return ResponseEntity.ok().body(trainer);
    }
    public boolean checkIfEmailIsTaken(String email) {
        boolean isUserEmailTaken = userService.findByEmail(email).isPresent();
        boolean isAdminEmailTaken = adminService.findByEmail(email).isPresent();
        boolean isTrainerEmailTaken = trainerService.findByEmail(email).isPresent();

        return !(isUserEmailTaken || isAdminEmailTaken || isTrainerEmailTaken);
    }
}
