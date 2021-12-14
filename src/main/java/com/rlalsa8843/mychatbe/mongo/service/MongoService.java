package com.rlalsa8843.mychatbe.mongo.service;

import com.rlalsa8843.mychatbe.mongo.dto.Person;
import com.rlalsa8843.mychatbe.mongo.repository.PersonMongoDBRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MongoService {
    private final PersonMongoDBRepository personMongoDBRepository;

    public MongoService(PersonMongoDBRepository personMongoDBRepository) {
        this.personMongoDBRepository = personMongoDBRepository;
    }

    public Person findByName(String name) {
        Optional<Person> person = personMongoDBRepository.findByName(name);

        return person.orElseThrow(() -> new NoSuchElementException("Cannot Find User"));
    }

    public void saveAll(List<Person> person) {
        personMongoDBRepository.saveAll(person);
    }
}
