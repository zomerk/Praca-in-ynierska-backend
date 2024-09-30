package com.example.pracainzynierska.service;

import com.example.pracainzynierska.service.usersservice.AdminDetailService;
import com.example.pracainzynierska.service.usersservice.TrainerDetailService;
import com.example.pracainzynierska.service.usersservice.UserDetailService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComposedDetailsService implements UserDetailsService {
    @Autowired
    AdminDetailService adminDetailService;
    @Autowired
    TrainerDetailService trainerDetailService;
    @Autowired
    UserDetailService userDetailService;
    private List<UserDetailsService> services;


    @PostConstruct
    public void setServices() {
        List<UserDetailsService> new_services = new ArrayList<>();
        new_services.add(this.adminDetailService); // injection
        new_services.add(this.trainerDetailService);     // injection
        new_services.add(this.userDetailService);     // injection
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
}
