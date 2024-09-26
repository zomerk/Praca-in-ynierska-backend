package com.example.pracainzynierska.repository;

import com.example.pracainzynierska.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    public Optional<User> findByEmail(String email);
    @Override
    public List<User> findAll();

}
