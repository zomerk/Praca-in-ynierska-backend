package com.example.pracainzynierska.service.usersservice;

import com.example.pracainzynierska.entity.Trainer;
import com.example.pracainzynierska.repository.TrainerRepository;
import com.example.pracainzynierska.service.adapter.TrainerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrainerDetailService implements UserDetailsService {
    @Autowired
    TrainerRepository trainerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new TrainerAdapter(this.trainerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Trainer Not Found")));
    }
    public void save(Trainer trainer) {
        trainerRepository.save(trainer);
    }

    public Optional<Trainer> findByEmail(String email) {
        return trainerRepository.findByEmail(email);
    }
}
