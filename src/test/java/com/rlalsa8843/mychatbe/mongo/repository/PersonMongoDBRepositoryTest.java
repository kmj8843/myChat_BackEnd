package com.rlalsa8843.mychatbe.mongo.repository;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PersonMongoDBRepositoryTest {

    @Autowired
    private PersonMongoDBRepository personMongoDBRepository;

    @Test
    void findByName() {
        System.out.println(personMongoDBRepository.findByName("john").toString());
    }

    @Test
    void Sample() {
        String firebaseConfigPath = "firebase/firebase_service_key.json";

        GoogleCredentials googleCredentials = null;

        try {
            googleCredentials = GoogleCredentials
                    .fromStream(new ClassPathResource(firebaseConfigPath).getInputStream())
                    .createScoped(Arrays.asList("https://www.googleapis.com/auth/firebase.messaging"));
        } catch (IOException e) {
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAA");
            e.printStackTrace();
        }

        try {
            googleCredentials.refreshAccessToken();
        } catch (IOException e) {
            System.out.println("BBBBBBBBBBBBB");
            e.printStackTrace();
        }

        if (googleCredentials.getAccessToken() == null ) {
            System.out.println("NULL");
        } else {
            System.out.println("NOT NULL");
        }
        String result = googleCredentials.getAccessToken().getTokenValue();

        System.out.println(result);
    }

    @Test
    void getAccessToken() {

        try {



            String path = new ClassPathResource("firebase/firebase_service_key.json").getURL().toString();
            System.out.println(path);

            FileInputStream serviceAccount =
                    new FileInputStream("C:/Users/user/Desktop/temp/react/cssTest/myChatBE/out/production/resources/firebase/firebase_service_key.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
        System.out.println(options.getProjectId());
            FirebaseApp fa = FirebaseApp.initializeApp(options);

             FirebaseAuth.getInstance(fa);
            System.out.println( fa.getInstance().getOptions().getProjectId() );

            System.out.println( fa.getInstance().toString() );
        } catch (Exception e) {
            System.out.println("에러 발생");

            e.printStackTrace();
        }

    }

    @Test
    void aa() {
        try {
            GoogleCredentials googleCredentials = GoogleCredentials
                    .fromStream(new FileInputStream("C:/Users/user/Desktop/temp/react/cssTest/myChatBE/out/production/resources/firebase/firebase_service_key.json"))
                    .createScoped(Arrays.asList("https://www.googleapis.com/auth/cloud-platform"));

            System.out.println(googleCredentials.refreshAccessToken().getTokenValue());
            googleCredentials.refreshAccessToken();
            System.out.println(googleCredentials.getAccessToken().getTokenValue());

        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}