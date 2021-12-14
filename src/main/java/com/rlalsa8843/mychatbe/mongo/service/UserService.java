package com.rlalsa8843.mychatbe.mongo.service;

import com.rlalsa8843.mychatbe.mongo.dto.User;
import com.rlalsa8843.mychatbe.mongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User findByDeviceID(String deviceID) {
        Optional<User> user = userRepository.findByDeviceID(deviceID);

        return user.orElseThrow(() -> new NoSuchElementException("회원 정보가 없습니다."));
    }

    public User findById(String id) {
        Optional<User> user = userRepository.findById(id);

        return user.orElseThrow(() -> new NoSuchElementException("회원 정보가 없습니다."));
    }

    public String getToken(String id) {
        return findById(id).getToken();
    }
}
