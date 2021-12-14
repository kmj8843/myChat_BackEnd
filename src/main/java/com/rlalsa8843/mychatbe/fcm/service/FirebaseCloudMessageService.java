package com.rlalsa8843.mychatbe.fcm.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.common.net.HttpHeaders;
import com.rlalsa8843.mychatbe.fcm.model.FcmMessage;
import com.rlalsa8843.mychatbe.fcm.model.Message;
import com.rlalsa8843.mychatbe.fcm.model.Notification;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;

@Service
public class FirebaseCloudMessageService {
    private final ObjectMapper objectMapper;

    public FirebaseCloudMessageService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void sendMessageTo(String targetToken, String title, String body) throws IOException {
        String message = makeMessage(targetToken, title, body);

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(message, MediaType.get("application/json; charset=utf-8"));
        String API_URL = "https://fcm.googleapis.com/v1/projects/mychat-8f636/messages:send";
        Request request = new Request.Builder()
                .url(API_URL)
                .post(requestBody)
                .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken())
                .addHeader(HttpHeaders.CONTENT_TYPE, "application/json; UTF-8")
                .build();

        client.newCall(request).execute();
    }

    private String getAccessToken() throws IOException {
        String firebaseConfigPath = "firebase/firebase_service_key.json";

        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource(firebaseConfigPath).getInputStream())
                .createScoped(Collections.singletonList("https://www.googleapis.com/auth/cloud-platform"));

        return googleCredentials.refreshAccessToken().getTokenValue();
    }

    private String makeMessage(String targetToken, String title, String body) throws JsonProcessingException {
        Notification notification = new Notification(title, body);
        Message message = new Message(notification, targetToken);
        FcmMessage fcmMessage = new FcmMessage(message);

        return objectMapper.writeValueAsString(fcmMessage);
    }

}
