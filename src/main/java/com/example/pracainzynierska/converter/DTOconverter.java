package com.example.pracainzynierska.converter;

import com.example.pracainzynierska.dto.AdminDTO;
import com.example.pracainzynierska.dto.TrainerDTO;
import com.example.pracainzynierska.dto.UserDTO;
import com.example.pracainzynierska.entity.Admin;
import com.example.pracainzynierska.entity.Trainer;
import com.example.pracainzynierska.entity.User;
import com.example.pracainzynierska.enums.FitnessLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

public class DTOconverter {
    public static User convertToUser(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAge(userDTO.getAge());
        user.setFitnessLevel(FitnessLevel.valueOf(userDTO.getFitnessLevel().toUpperCase())); // Convert String to Enum
        user.setGoal(userDTO.getGoal());
        return user;
    }

    public static Admin convertToAdmin(AdminDTO admin) {
        if (admin == null) {
            return null;
        }
        Admin admin1 = new Admin();
        admin1.setFirstName(admin.getFirstName());
        admin1.setLastName(admin.getLastName());
        admin1.setEmail(admin.getEmail());
        admin1.setPassword(admin.getPassword());
        return admin1;
    }

    public static Trainer convertToTrainer(TrainerDTO trainer) {
        if (trainer == null) {
            return null;
        }
        Trainer trainer1 = new Trainer();
        trainer1.setFirstName(trainer.getFirstName());
        trainer1.setLastName(trainer.getLastName());
        trainer1.setEmail(trainer.getEmail());
        trainer1.setPassword(trainer.getPassword());
        return trainer1;
    }
}
