package com.rlalsa8843.mychatbe.mongo.repository;

import com.rlalsa8843.mychatbe.mongo.dto.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByDeviceID(String deviceID);
    Optional<User> findById(String id);
}
