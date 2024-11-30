package com.example.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.Model.User;
import java.util.List;






public interface EWalletRepo extends MongoRepository<User,String> {
    Optional<User> findById(String id);
    Optional<User> findByEmail(String email);
}
