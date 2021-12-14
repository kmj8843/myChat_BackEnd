package com.rlalsa8843.mychatbe.mongo.repository;

import com.rlalsa8843.mychatbe.mongo.dto.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PersonMongoDBRepository extends MongoRepository<Person, String> {
    Optional<Person> findByName(String name);

    @Override
    <S extends Person> List<S> saveAll(Iterable<S> entities);
}
