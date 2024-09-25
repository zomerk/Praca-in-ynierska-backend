package com.example.pracainzynierska.repository;

import com.example.pracainzynierska.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    public Optional<User> findByEmail(String email);
    @Override
    public List<User> findAll();

}
