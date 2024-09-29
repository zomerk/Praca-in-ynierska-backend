package com.example.pracainzynierska.service;

import com.example.pracainzynierska.dto.LoginDTO;
import com.example.pracainzynierska.entity.Admin;
import com.example.pracainzynierska.entity.Trainer;
import com.example.pracainzynierska.entity.User;
import com.example.pracainzynierska.exeption.EmailIsTakenExeption;
import com.example.pracainzynierska.repository.AdminRepository;
import com.example.pracainzynierska.repository.TrainerRepository;
import com.example.pracainzynierska.repository.UserRepository;
import com.example.pracainzynierska.service.adapter.AdminAdapter;
import com.example.pracainzynierska.service.adapter.TrainerAdapter;
import com.example.pracainzynierska.service.adapter.UserAdapter;
import com.example.pracainzynierska.service.usersservice.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final TrainerRepository trainerRepository;


    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> signUpUser(User user) {
        if(!checkIfEmailIsTaken(user.getEmail())) {
            throw new EmailIsTakenExeption("Email is taken");
        }
        userRepository.save(user);
        return ResponseEntity.ok().body(user);
    }

    public ResponseEntity<?> signUpAdmin(Admin admin) {
        if(!checkIfEmailIsTaken(admin.getEmail())) {
            throw new EmailIsTakenExeption("Email is taken");
        }
        adminRepository.save(admin);
        return ResponseEntity.ok().body(admin);
    }

    public ResponseEntity<?> signUpTrainer(Trainer trainer) {
        if(!checkIfEmailIsTaken(trainer.getEmail())) {
            throw new EmailIsTakenExeption("Email is taken");
        }
        trainerRepository.save(trainer);
        return ResponseEntity.ok().body(trainer);
    }


    public UserDetails authenticate(LoginDTO input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        if(userRepository.findByEmail(input.getEmail()).isPresent()){
            return new UserAdapter(userRepository.findByEmail(input.getEmail()).get());
        }
        else if (adminRepository.findByEmail(input.getEmail()).isPresent()){
            return new AdminAdapter(adminRepository.findByEmail(input.getEmail()).get());
        }
        else {
            return new TrainerAdapter(trainerRepository.findByEmail(input.getEmail()).get());
        }
    }
    public boolean checkIfEmailIsTaken(String email) {
        boolean isUserEmailTaken = userRepository.findByEmail(email).isPresent();
        boolean isAdminEmailTaken = adminRepository.findByEmail(email).isPresent();
        boolean isTrainerEmailTaken = trainerRepository.findByEmail(email).isPresent();

        return !(isUserEmailTaken || isAdminEmailTaken || isTrainerEmailTaken);
    }
}
