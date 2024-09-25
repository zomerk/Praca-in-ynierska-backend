package com.example.pracainzynierska.converter;

import com.example.pracainzynierska.dto.UserDTO;
import com.example.pracainzynierska.entity.User;
import com.example.pracainzynierska.enums.FitnessLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
@Component
public class DTOconverter {


    public User convertToUser(UserDTO userDTO) {
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
}
